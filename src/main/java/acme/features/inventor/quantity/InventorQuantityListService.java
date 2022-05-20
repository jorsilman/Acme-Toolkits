package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.ItemType;
import acme.entities.item.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorQuantityListService implements AbstractListService<Inventor, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int masterId;
		final Toolkit toolkit;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findToolkitById(masterId);
		result = (toolkit != null && (toolkit.isPublished() || request.isPrincipal(toolkit.getInventor())));

		return result;
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;

		Collection<Quantity> result;
		int masterId;

		//DEVUELVE TODAS LAS QUANTITIES DE UN TOOLKIT
		masterId = request.getModel().getInteger("masterId");
		result = this.repository.findQuantitiesById(masterId);


		return result;
	}



	@Override
	public void unbind(Request<Quantity> request, Collection<Quantity> entity, Model model) {
		assert request != null;

		assert model != null;

		int toolkitId;

		toolkitId = request.getModel().getInteger("masterId");

		model.setAttribute("masterId", toolkitId);

		request.unbind(entity, model, "number", "item.code", "item.name", "item.itemType");

	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "number", "item.code", "item.name", "item.itemType");
	}

}
