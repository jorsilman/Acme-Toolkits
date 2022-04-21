package acme.features.authenticated.inventor.component;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ComponentRepository extends AbstractRepository{
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = id AND i.itemType = 0")
	Collection<Item> findComponentsByInventorId(int id);
	
	@Query("SELECT i FROM Item i WHERE i.id = id")
	Item findItemById(int id);
	

}
