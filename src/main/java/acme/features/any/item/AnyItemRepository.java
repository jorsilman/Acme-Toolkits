package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository{
	@Query("select i from Item i where i.itemType = 1")
	Collection<Item> findManyItems();
	
	@Query("select i from Item i where i.id = :id")
	Item findItemById(int id);
	
	

}
