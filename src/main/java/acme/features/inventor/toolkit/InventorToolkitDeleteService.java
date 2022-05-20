package acme.features.inventor.toolkit;

import java.util.Collection;


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
public class InventorToolkitDeleteService implements AbstractDeleteService<Inventor, Toolkit>{

	
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
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "published");
				
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
		
		
				
	}

	@Override
	public void delete(Request<Toolkit> request, Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		Collection<Quantity> quantities;
		int toolkitId;
		
		toolkitId = entity.getId();
		quantities = this.repository.findQuantitiesOfToolkitId(toolkitId);
		 
		for (Quantity quantity : quantities) {
			this.repository.delete(quantity);
		}
		
		this.repository.delete(entity);		
	}

}
