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
public class InventorQuantityCreateComponentService implements AbstractCreateService<Inventor, Quantity> {

	@Autowired
	InventorQuantityRepository repository;



	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findToolkitById(masterId);
		result = (toolkit != null && !toolkit.isPublished() && request.getPrincipal().getActiveRoleId()==toolkit.getInventor().getId());

		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final String itemCode;
		final Item item;

		itemCode = request.getModel().getString("item.codeProxy");
		item = this.repository.findItemByCode(itemCode);
		item.setItemType(ItemType.COMPONENT);
		entity.setItem(item);
		request.bind(entity, errors, "number", "item.codeProxy");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final int toolkitId = request.getModel().getInteger("masterId");


		final Collection<Item> publishedItems = this.repository.findPublishedComponents();
		final Collection<Item> assignedItems = this.repository.findComponentsByToolkitId(toolkitId);
		final Collection<Item> nonAssignedItems = publishedItems;

		nonAssignedItems.removeAll(assignedItems);



		request.unbind(entity, model, "number", "item.code");
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
		model.setAttribute("published", entity.getToolkit().isPublished());
		model.setAttribute("items", nonAssignedItems);


	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {

		assert request != null;

		Quantity result;
		int masterId;
		Toolkit toolkit;
		Item item;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findToolkitById(masterId);
		item = new Item();
		item.setItemType(ItemType.COMPONENT);

		result = new Quantity();
		result.setToolkit(toolkit);
		result.setItem(item);


		return result;


	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
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
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
