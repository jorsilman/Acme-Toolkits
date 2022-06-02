package acme.features.inventor.quantity;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.entities.item.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityUpdateService implements AbstractUpdateService<Inventor, Quantity>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;

	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int quantityId;
		final Toolkit toolkit;

		quantityId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitByQuantityId(quantityId);
		result = (toolkit != null && (toolkit.isPublished() || request.getPrincipal().getActiveRoleId()==toolkit.getInventor().getId()));

		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "number", "item.code");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "number", "item.code");
		model.setAttribute("masterId", entity.getToolkit().getId());
		model.setAttribute("published", entity.getToolkit().isPublished());
		
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		int quantityId;
		final Quantity result;
		
		quantityId = request.getModel().getInteger("id");
		result = this.repository.findQuantityById(quantityId); 
		
		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
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
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
