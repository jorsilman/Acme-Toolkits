package acme.features.inventor.items;

import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item>{
	
	protected InventorItemRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		// TODO Auto-generated method stub
		assert request !=null;
		
		return true;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		// TODO Auto-generated method stub
		assert request !=null;
		final int id = request.getModel().getInteger("id");
		final Item result = this.repository.findItemById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		
		
		request.unbind(entity, model, "name","code","technology","description","retailPrice","link","type");
		
	}

}
