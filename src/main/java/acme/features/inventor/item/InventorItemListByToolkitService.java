package acme.features.inventor.item;

import java.util.Collection;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

public class InventorItemListByToolkitService implements AbstractListService<Inventor, Item>{

	protected InventorItemRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		final int id = request.getModel().getInteger("masterId");
		return this.repository.findItemsByToolkitId(id);
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		
		
		request.unbind(entity, model, "name","code","technology","description","retailPrice","link","type");
		
	}
	
	
	
}
