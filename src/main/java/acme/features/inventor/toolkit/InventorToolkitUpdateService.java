package acme.features.inventor.toolkit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.toolkit.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor, Toolkit>{

	
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
		result = (toolkit != null && !toolkit.isPublished() && request.getPrincipal().getActiveRoleId()==toolkit.getInventor().getId());

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final int tookitId  = request.getModel().getInteger("id");
		final String targetCurrency = this.repository.findSystemCurrency();
		
		final List<MoneyExchange> priceInSC = new ArrayList<MoneyExchange>();
		final Money result = new Money();
		final List<Object[]> prices = this.repository.getRetailPriceItemsOfToolkit(tookitId);
		
		for(final Object[] p:prices) {
			result.setAmount((Double)p[0]);
			result.setCurrency(p[1].toString());
			priceInSC.add(moneyExchange.computeMoneyExchange(result, targetCurrency));

		}
		
		Double amount = 0.0;
		for(final MoneyExchange m:priceInSC) {
			amount += m.getTarget().getAmount();
		}
		result.setAmount(amount);
		result.setCurrency(targetCurrency);
		
		
		
		
		model.setAttribute("retailPriceTookit", result);
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link","published");
				
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		Toolkit toolkit;
		toolkit = this.repository.findToolkitById(request.getModel().getInteger("id"));
	
		return toolkit;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		//VALIDAR QUE EL CODIGO ES EL MISMO
		
				if(!errors.hasErrors("code")) {
					

					final Toolkit existing = this.repository.findToolkitByCode(entity.getCode());
					errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.toolkit.form.error.duplicated");
					final Toolkit toolkit = this.repository.findToolkitById(entity.getId());
					errors.state(request, toolkit.getCode().equals(entity.getCode()), "code", "inventor.toolkit.form.error.codeChanged");
					
				}
		
		//FILTRO DE SPAM
				
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		

		this.repository.save(entity);		
	}

}
