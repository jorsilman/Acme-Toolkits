package acme.features.authenticated.inventor.component;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ComponentRepository extends AbstractRepository{
	
	//Dada la id del inventor de un determinado item i, se comprueba que dicha id coincida con la del usuario logueado y que a su vez sea de tipo COMPONENT
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :id AND i.itemType = acme.entities.item.ItemType.COMPONENT")
	Collection<Item> findComponentsByInventorId(int id);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findItemById(int id);
	

}
