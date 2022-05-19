package acme.features.inventor.quantity;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.entities.item.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor, Quantity> {

	@Autowired
	InventorQuantityRepository repository;



	@Override
	public boolean authorise(Request<Quantity> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;
 
		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findToolkitById(masterId);
		result = (toolkit != null && !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor()));

		return result;
	}

	@Override
	public void bind(Request<Quantity> request, Quantity entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final String itemCode;
		final Item item;
		
		itemCode = request.getModel().getString("item.code");
		item = this.repository.findItemByCode(itemCode);
		
		entity.setItem(item);
		request.bind(entity, errors, "number", "item.code");
	}

	@Override
	public void unbind(Request<Quantity> request, Quantity entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
				final int toolkitId = request.getModel().getInteger("masterId");
		
		
		Collection<Item> publishedItems = this.repository.findPublishedItems();
		Collection<Item> assignedItems = this.repository.findItemsByToolkitId(toolkitId);
		Collection<Item> nonAssignedItems = publishedItems;
		
		nonAssignedItems.removeAll(assignedItems);
		
		
		request.unbind(entity, model, "number", "item.code");
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
		model.setAttribute("published", entity.getToolkit().isPublished());
		model.setAttribute("items", nonAssignedItems);
		

	}

	@Override
	public Quantity instantiate(Request<Quantity> request) {

		assert request != null;

		Quantity result;
		int masterId;
		Toolkit toolkit;
		Item item;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findToolkitById(masterId);
		item = new Item();

		result = new Quantity();
		result.setToolkit(toolkit);
		result.setItem(item);
		
		return result;

		
	}

	@Override
	public void validate(Request<Quantity> request, Quantity entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("item.code")) {
			final Item item;
			final boolean exists;
			
			item = entity.getItem();
			exists = item.getId() != 0;
			
			errors.state(request, exists, "item.code", "inventor.quantity.form.error.invalid");
			if (exists)	errors.state(request, this.repository.countByItemIdAndToolkitId(item.getId(), entity.getToolkit().getId()) == 0, "item.code", "inventor.quantity.form.error.duplicated");
		}
		
		if (!errors.hasErrors("number")) {
			final Item item;
			final boolean exists;
			final Boolean isTool;
			
			item = entity.getItem();
			exists = item.getId() != 0;
			isTool= exists && item.getItemType().equals(ItemType.TOOL);
			
			if (Boolean.TRUE.equals(isTool)) errors.state(request, entity.getNumber() == 1, "number", "inventor.quantity.form.error.exceed");
		}
		
	}

	@Override
	public void create(Request<Quantity> request, Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
