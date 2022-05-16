package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.item.Quantity;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository 
public interface InventorQuantityRepository extends AbstractRepository{

	//@Query("select distinct(q.item) from quantity q where q.item.inventor.id = :inventorId and q.toolkit.id !=  :toolkitId")
	//Collection<Item> findItemsByInventorIdAndNotToolkitId(int inventorId,int toolkitId);
	/*
	@Query("select i from Item i where i.inventor.id = :id")
	Collection<Item> findItemsByInventorId(int id);
	
	@Query("select distinct(q.item) from Quantity q where q.toolkit.id = :masterId")
	Collection<Item> findItemsByToolkitId(int masterId);*/
	
	@Query("SELECT q from Quantity q WHERE q.toolkit.id = :id")
	Collection<Quantity> findQuantitiesById(int id);
	
	@Query("SELECT q from Quantity q WHERE q.id = :id")
	Quantity findQuantityById(int id);
	
	@Query("SELECT q.toolkit FROM Quantity q WHERE q.id = :quantityId")
	Toolkit findToolkitByQuantityId(int quantityId);
	
	@Query("select tl from Toolkit tl where tl.id = :id")
	Toolkit findToolkitById(int id);

	@Query("SELECT i FROM Item i WHERE i.code = :itemCode")
	Item findItemByCode(String itemCode);

	@Query("SELECT COUNT(*) FROM Quantity q WHERE q.item.id = :itemId AND q.toolkit.id = :toolkitId")
	int countByItemIdAndToolkitId(int itemId, int toolkitId);
	
	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

}
