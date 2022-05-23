package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorComponentCreateService implements AbstractCreateService<Inventor, Item>{
	
	// Internal state ------------------------------------------------
	
		@Autowired
		protected InventorItemRepository itemRepo;
		
		
		@Override
		public boolean authorise(final Request<Item> request) {
			assert request !=null; 
			return true;  
		}
		

		
		@Override
		public void bind(final Request<Item> request, final Item entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			entity.setPublished(false);
			request.bind(entity, errors, "name","code",  "technology", "description", "retailPrice", "link");
		}
		
	
		@Override
		public void unbind(final Request<Item> request, final Item entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model, "name","code", "technology", "description", "retailPrice", "link", "published");
			
		}
		
		@Override
		public Item instantiate (final Request<Item> request) {
			assert request != null;
			
			
			
			
			Inventor inventor;
			final Integer id = request.getPrincipal().getActiveRoleId();
			inventor = this.itemRepo.findInventorById(id);
			
			Item result;
			result = new Item();
		
			result.setInventor(inventor);
			result.setItemType(ItemType.COMPONENT);
		
			
		
			return result;
			
			
			}
		
		@Override
		public void validate(final Request<Item> request, final Item entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			if(!errors.hasErrors("code")) {
				final Item existing = this.itemRepo.findItemByCode(entity.getCode());
				errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.item.form.error.duplicated");
				errors.state(request, entity.getCode().length() == 9, "code", "inventor.item.form.error.invalidCode");
			
			}
			if(!errors.hasErrors("retailPrice")) {
				final boolean accepted = this.itemRepo.findAcceptedCurrencies().matches("(.*)" + entity.getRetailPrice().getCurrency()+ "(.*)");
				errors.state(request, accepted, "retailPrice", "inventor.item.form.error.currency");
				errors.state(request, entity.getRetailPrice().getAmount()>0, "retailPrice", "inventor.item.form.error.negative");
			}
		}
				
		@Override
		public void create(final Request<Item> request, final Item entity) {
			assert request != null;
			assert entity != null;
			this.itemRepo.save(entity);
			
		}


}