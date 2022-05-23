package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor, Toolkit>{

	
	@Autowired
	protected InventorToolkitRepository repository;

	
	
	@Override
	public boolean authorise(Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;
 
		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(masterId);
		result = (toolkit != null && !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor()));

		return result;
	}

	@Override
	public void bind(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");
		
	}

	@Override
	public void unbind(Request<Toolkit> request, Toolkit entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link","published");
				
	}

	@Override
	public Toolkit findOne(Request<Toolkit> request) {
		Toolkit toolkit;
		toolkit = this.repository.findToolkitById(request.getModel().getInteger("id"));
	
		return toolkit;
	}

	@Override
	public void validate(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		//VALIDAR QUE EL CODIGO ES EL MISMO
		
				if(!errors.hasErrors("code")) {
					

					final Toolkit existing = this.repository.findToolkitByCode(entity.getCode());
					errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.toolkit.form.error.duplicated");
					final Toolkit toolkit = this.repository.findToolkitById(entity.getId());
					errors.state(request, toolkit.getCode().equals(entity.getCode()), "code", "inventor.toolkit.form.error.codeChanged");
					
				}
		
		//FILTRO DE SPAM
				
	}

	@Override
	public void update(Request<Toolkit> request, Toolkit entity) {
		assert request != null;
		assert entity != null;
		

		this.repository.save(entity);		
	}

}
