package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.item.Item;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolShowService implements AbstractShowService<Inventor,Item> {
	// Internal state --------------------------------------------------
	
		@Autowired
		protected InventorItemRepository itemRepo;
		
		// AbstractShowService<Inventor, Item> interface --------------------
		
		@Override
		public boolean authorise(final Request<Item> request) {
			assert request != null;
			Integer id;
			Item item;
			boolean result;
			id = request.getModel().getInteger("id");
			item = this.itemRepo.findItemById(id);
			
			result = request.getPrincipal().getActiveRoleId() == item.getInventor().getId();
			return result;
		}

		@Override
		public Item findOne(final Request<Item> request) {
			assert request != null;
			
			Integer id;
			Item item;
			id = request.getModel().getInteger("id");
			item = this.itemRepo.findItemById(id);
			
			return item;
		}

		@Override
		public void unbind(final Request<Item> request, final Item entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
			final int itemId  = request.getModel().getInteger("id");
			final String targetCurrency = this.itemRepo.findSystemCurrency();
			final Money actualCurrency = this.itemRepo.findToolPriceById(itemId);
			
			final MoneyExchange change = moneyExchange.computeMoneyExchange(actualCurrency, targetCurrency);
			final Money result = change.getTarget();
			
			
			model.setAttribute("priceInSC", result);
			model.setAttribute("itemType", entity.getItemType());
			request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "published");
			
		}
		
}
