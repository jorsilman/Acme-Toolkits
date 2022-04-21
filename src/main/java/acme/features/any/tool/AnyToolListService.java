package acme.features.any.tool;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

public class AnyToolListService implements AbstractListService<Any, Item> {
	
	@Autowired
	protected AnyToolRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		

		Collection<Item> result;
		result = this.repository.findManyItems();
		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "type");
	}
	
	


}
