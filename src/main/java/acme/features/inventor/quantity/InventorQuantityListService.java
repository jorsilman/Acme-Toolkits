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
	public void unbind(Request<Quantity> request, Quantity entity, Model model) {
		assert request != null;
		//assert !CollectionHelper.someNull(entities);
		assert model != null;

		int toolkitId;
		//Toolkit toolkit;

		//final boolean showCreate;

		toolkitId = request.getModel().getInteger("masterId");
		//toolkit = this.repository.findToolkitById(toolkitId);
		
		//showCreate = (!toolkit.isPublished() && request.isPrincipal(toolkit.getInventor()));

		model.setAttribute("toolkitId", toolkitId);
		//model.setAttribute("showCreate", showCreate);	
		request.unbind(entity, model, "number", "item.code", "item.name", "item.itemType");

	}
/*
	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;


		final String defaultCurrency = this.repository.getSystemConfiguration().getSystemCurrency();

		final Money retailPrice = MoneyExchanger.of(entity.getItem().getRetailPrice(), defaultCurrency)//.execute().getTarget();

		model.setAttribute("item.retailPrice", retailPrice);
		request.unbind(entity, model, "number", "item.code", "item.name", "item.technology");
	}*/
}
