package acme.features.any.tool;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

public interface AnyToolRepository extends AbstractRepository{
	@Query("select i from Item i where i.itemType = 1")
	Collection<Item> findManyItems();
	
	@Query("select i from Item i where i.id = :id")
	Item findItemById(int id);

}
