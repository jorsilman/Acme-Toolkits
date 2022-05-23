package acme.features.any.toolkit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.toolkit.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{
	
	//Internal state
	
	@Autowired
	protected AnyToolkitRepository repository;
	
	@Autowired
	protected InventorToolkitRepository toolkitRepo;
	
	//AbstractShowService<Any, Toolkit> interface
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int id;
		Toolkit toolkit;

		id = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(id);
		result = toolkit.isPublished();
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final int tookitId  = request.getModel().getInteger("id");
		String targetCurrency = this.toolkitRepo.findSystemCurrency();
		
		List<MoneyExchange> priceInSC = new ArrayList<MoneyExchange>();
		Money result = new Money();
		List<Object[]> prices = this.toolkitRepo.getRetailPriceItemsOfToolkit(tookitId);
		
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

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);

		return result;
	}
	
	
}