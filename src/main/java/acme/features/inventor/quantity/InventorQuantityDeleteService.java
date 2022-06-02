package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorQuantityDeleteService implements AbstractDeleteService<Inventor, Quantity>{



	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;


	@Override
	public boolean authorise(Request<Quantity> request) {
		assert request != null;

		boolean result;
		int quantityId;
		final Toolkit toolkit;

		quantityId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitByQuantityId(quantityId);
		result = (toolkit != null && (toolkit.isPublished() || request.isPrincipal(toolkit.getInventor())));

		return result;
	}

	@Override
	public void bind(Request<Quantity> request, Quantity entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "number", "item.code");		
	}

	@Override
	public void unbind(Request<Quantity> request, Quantity entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "number", "item.code");
		model.setAttribute("masterId", entity.getToolkit().getId());
		model.setAttribute("published", entity.getToolkit().isPublished());


	}

	@Override
	public Quantity findOne(Request<Quantity> request) {
		assert request != null;

		int quantityId;
		final Quantity result;

		quantityId = request.getModel().getInteger("id");
		result = this.repository.findQuantityById(quantityId); 

		return result;
	}

	@Override
	public void validate(Request<Quantity> request, Quantity entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;		
	}

	@Override
	public void delete(Request<Quantity> request, Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);		
	}

}
