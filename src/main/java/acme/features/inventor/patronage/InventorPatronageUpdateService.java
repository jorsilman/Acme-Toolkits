package acme.features.inventor.patronage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageStatus;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageUpdateService implements AbstractUpdateService<Inventor, Patronage>{
	
	@Autowired
	protected InventorPatronageRepository inventorPatronageRepository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		boolean result = false;
		
		final int id = request.getModel().getInteger("id");
		final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(id);
		result = patronage.getInventor().getId()==request.getPrincipal().getActiveRoleId();
		return result;
	}
	

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "code", "legalStuff", "budget", "startPeriodOfTime", "endPeriodOfTime", "link");
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final int itemId  = request.getModel().getInteger("id");
		final String targetCurrency = this.inventorPatronageRepository.findSystemCurrency();
		final Money actualCurrency = this.inventorPatronageRepository.findOnePatronageById(itemId).getBudget();
		
		final MoneyExchange change = moneyExchange.computeMoneyExchange(actualCurrency, targetCurrency);
		final Money result = change.getTarget();
		final Date creationDate = entity.getCreationDate();
		model.setAttribute("creationDate", creationDate);
		
		model.setAttribute("priceInSC", result);
		
		model.setAttribute("patronCompany", entity.getPatron().getCompany());
		model.setAttribute("patronStatement", entity.getPatron().getStatement());
		model.setAttribute("patronLink", entity.getPatron().getLink());
		model.setAttribute("masterId", entity.getId());
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "startPeriodOfTime", "endPeriodOfTime", "link");
		
		
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage patronage;
		int id;
		
		id = request.getModel().getInteger("id");
		patronage = this.inventorPatronageRepository.findOnePatronageById(id);
		return patronage;
	}


	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		PatronageStatus status;
		
		status = PatronageStatus.valueOf(request.getModel().getString("status"));
		entity.setStatus(status);
		this.inventorPatronageRepository.save(entity);
		
	}


	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(entity.getId());
			errors.state(request, patronage.getCode().equals(entity.getCode()), "code", "inventor.patronage.form.error.codeChanged");
		}
		if(!errors.hasErrors("legalStuff")) {
			final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(entity.getId());
			errors.state(request, patronage.getLegalStuff().equals(entity.getLegalStuff()), "legalStuff", "inventor.patronage.form.error.legalStuffChanged");
		}
		if(!errors.hasErrors("link")) {
			final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(entity.getId());
			errors.state(request, patronage.getLink().equals(entity.getLink()), "link", "inventor.patronage.form.error.linkChanged");
		}
		
	
	}

}
