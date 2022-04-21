package acme.features.authenticated.inventor.tool;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ToolRepository extends AbstractRepository{
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = id AND i.itemType = 1")
	Collection<Item> findToolsByInventorId(int id);
	
	@Query("SELECT i FROM Item i WHERE i.id = id")
	Item findItemById(int id);
	

}
