package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyComponentShowService implements AbstractShowService<Any, Item>{
	
	@Autowired
	protected AnyItemRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("type", entity.getItemType());
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link");
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		return this.repository.findItemById(id);
	}



	

}

