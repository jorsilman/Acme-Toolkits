package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyComponentShowService implements AbstractShowService<Any, Item>{
	
	@Autowired
	protected AnyItemRepository repository;
	
	

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final int itemId  = request.getModel().getInteger("id");
		final String targetCurrency = this.repository.findSystemCurrency();
		Money actualCurrency; 
		if(entity.getItemType()==ItemType.COMPONENT) {
			actualCurrency = this.repository.findComponentPriceById(itemId);
		}else {
			actualCurrency= this.repository.findToolPriceById(itemId);
		}
		
		final MoneyExchange change = moneyExchange.computeMoneyExchange(actualCurrency, targetCurrency);
		final Money result = change.getTarget();
		
		
		model.setAttribute("priceInSC", result);

		model.setAttribute("type", entity.getItemType());
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link");
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		return this.repository.findItemById(id);
	}



	

}

