package acme.features.inventor.items;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

public interface InventorItemRepository extends AbstractRepository{
	
	@Query("select item from Item item where item.inventor.id = :id")
	Collection<Item> findItemsByInventorId(int id);
	
	@Query("select item from Item item where item.id = :id")
	Item findItemById(int id);
	
	
	@Query("select toolkit from Toolkit toolkit where toolkit.id = :masterId")
	Toolkit findOneToolkitById(int masterId);
	
	
	

	@Query("select distinct(quantity.item) from Quantity quantity where quantity.toolkit.id = :masterId")
	Collection<Item> findItemsByToolkitId(int masterId);
	
	@Query("select distinct(quantity.toolkit) from Quantity quantity where quantity.item.inventor.id = :id")
	Collection<Toolkit> findToolkitsByInventorId(int id);

}
