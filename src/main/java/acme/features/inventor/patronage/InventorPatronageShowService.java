package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.patronage.Patronage;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageRepository repository;
	
	

	// AbstractShowService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		final Patronage patronage = this.repository.findOnePatronageById(id);
		return patronage != null && patronage.getInventor().getId()==request.getPrincipal().getActiveRoleId();

		
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final int itemId  = request.getModel().getInteger("id");
		String targetCurrency = this.repository.findSystemCurrency();
		Money actualCurrency = this.repository.findOnePatronageById(itemId).getBudget();
		
		MoneyExchange change = moneyExchange.computeMoneyExchange(actualCurrency, targetCurrency);
		Money result = change.getTarget();
		
		
		model.setAttribute("priceInSC", result);
		
		
		model.setAttribute("inventorCompany", entity.getInventor().getCompany());
		model.setAttribute("inventorStatement", entity.getInventor().getStatement());
		model.setAttribute("inventorLink", entity.getInventor().getLink());
		model.setAttribute("masterId", entity.getId());

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startPeriodOfTime", "endPeriodOfTime", "link");
	}

}
