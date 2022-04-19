package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

public interface ItemRepository extends AbstractRepository{
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :id AND a.ItemType = 0")
	Collection<Item> findToolsByInventorId(int id);
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :id AND a.ItemType = 1")
	Collection<Item> findComponentsByInventorId(int id);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findItemById(int id);
	

}
