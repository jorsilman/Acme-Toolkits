package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository{
	@Query("select i from Item i where i.itemType = 1")
	Collection<Item> findManyTools();
	
	@Query("select i from Item i where i.itemType = 0")
	Collection<Item> findManyComponents();
	
	
	@Query("select i from Item i where i.id = :id")
	Item findItemById(int id);
	
	@Query("select distinct(q.item) from Quantity q where q.toolkit.id = :masterId")
	Collection<Item> findItemsByToolkitId(int masterId);
	
	@Query("select sc.systemCurrency from SystemConfiguration sc ")
	String findSystemCurrency();
	
	@Query("select i.retailPrice from Item i where i.id= :id and i.itemType = 0")
	Money findComponentPriceById(int id);
	
	@Query("select i.retailPrice from Item i where i.id= :id and i.itemType = 1")
	Money findToolPriceById(int id);

	@Query("SELECT i FROM Item i WHERE i.code = :code")
	Item findItemByCode(String code);
	
	@Query("select sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrencies();
	
	
	

}
