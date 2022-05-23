/*
 * AuthenticatedAnnouncementShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.patronage.Patronage;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;

	// AbstractShowService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return request.getPrincipal().getActiveRoleId() == this.repository.findOnePatronageById(request.getModel().getInteger("id")).getPatron().getId();
		
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

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startPeriodOfTime", "endPeriodOfTime", "link", "published");
	}

}
