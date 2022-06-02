package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit>{


	@Autowired
	protected InventorToolkitRepository repository;



	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;

		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(masterId);
		result = (toolkit != null && !toolkit.isPublished() && request.getPrincipal().getActiveRoleId()==toolkit.getInventor().getId());

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");

	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int id;
		id = request.getModel().getInteger("id");
		model.setAttribute("id", id);
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link","published");

	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		Toolkit toolkit;
		int toolkitId;
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(toolkitId);

		return toolkit;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Collection<Item> items;
		int toolkitId;
		toolkitId = request.getModel().getInteger("id");
		items = this.repository.findItemsOfToolkitByToolkitId(toolkitId);

		//VALIDAR SI TIENE ARTICULOS
		errors.state(request, !items.isEmpty(),"*", "inventor.toolkit.form.error.no-items");

		//FILTRO DE SPAM

		//VALIDAR QUE EL CODIGO ES EL MISMO

		if(!errors.hasErrors("code")) {


			final Toolkit existing = this.repository.findToolkitByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.toolkit.form.error.duplicated");
			final Toolkit toolkit = this.repository.findToolkitById(entity.getId());
			errors.state(request, toolkit.getCode().equals(entity.getCode()), "code", "inventor.toolkit.form.error.codeChanged");

		}

	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		entity.setPublished(true);
		this.repository.save(entity);		
	}

}
