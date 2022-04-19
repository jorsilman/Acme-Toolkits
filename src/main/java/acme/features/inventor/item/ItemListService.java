package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class ItemListService implements AbstractListService<Inventor, Item>{

	@Autowired
	protected ItemRepository itemRepo;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		final Principal principal = request.getPrincipal();
		final String type = request.getModel().getString("type");
		if (type.equals("component")) {
			return this.itemRepo.findComponentsByInventorId(principal.getActiveRoleId());
		}else {
			return this.itemRepo.findToolsByInventorId(principal.getActiveRoleId());
		}
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "retailPrice", "itemType");
	}

}
