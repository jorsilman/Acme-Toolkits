package acme.features.authenticated.inventor.tool;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ToolRepository extends AbstractRepository{
	
	//Cojo el id del inventor de un cierto item i y lo comparo con el id del inventor actualmente logueado y que dicho item sea de tipo TOOL
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :id AND i.itemType = acme.entities.item.ItemType.TOOL")
	Collection<Item> findToolsByInventorId(int id);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findItemById(int id);
	

}
