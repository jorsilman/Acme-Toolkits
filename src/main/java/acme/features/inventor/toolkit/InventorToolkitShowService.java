package acme.features.inventor.toolkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.toolkit.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;
 
		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(masterId);
		result = (toolkit != null && request.isPrincipal(toolkit.getInventor()));

		return result;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		
		assert request != null;
		final int id  = request.getModel().getInteger("id");
		return this.repository.findToolkitById(id);
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final int tookitId  = request.getModel().getInteger("id");
		String targetCurrency = this.repository.findSystemCurrency();
		
		List<MoneyExchange> priceInSC = new ArrayList<MoneyExchange>();
		Money result = new Money();
		List<Object[]> prices = this.repository.getRetailPriceItemsOfToolkit(tookitId);
		
		for(Object[] p:prices) {
			result.setAmount((Double)p[0]);
			result.setCurrency(p[1].toString());
			priceInSC.add(moneyExchange.computeMoneyExchange(result, targetCurrency));

		}
		
		Double amount = 0.0;
		for(MoneyExchange m:priceInSC) {
			amount += m.getTarget().getAmount();
		}
		result.setAmount(amount);
		result.setCurrency(targetCurrency);
		
		
		
		
		model.setAttribute("retailPriceTookit", result);
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes","published", "link");
		
	}
	/*
	private Money getPriceOfSystemCurrency(Money money) {
		
		AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		
		MoneyExchange moneyChange = new MoneyExchange();
		
		String systemCurrency = this.repository.findSystemCurrency();
		
		if(!money.getCurrency().equals(systemCurrency)) {
			change = this.repository.fin
			
			change = moneyExchange.computeMoneyExchange(money, systemCurrency);
			this.repository.save(change);
			
			
		}else {
			change.setSource(money);
			change.setTarget(money);
			change.setTargetCurrency(systemCurrency);
			change.setDate(new Date);
		}
		
	}*/

}
