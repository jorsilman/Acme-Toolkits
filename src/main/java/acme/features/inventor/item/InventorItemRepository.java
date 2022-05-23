package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository{
	
	//Dado el id de un determinado inventor de un item lo comparo con el id del usuario logueado y veo que ese item sea de tipo COMPONENT o TOOL
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :id AND i.itemType = acme.entities.item.ItemType.COMPONENT")
	Collection<Item> findComponentsByInventorId(int id);
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :id AND i.itemType = acme.entities.item.ItemType.TOOL")
	Collection<Item> findToolsByInventorId(int id);
	
	//Obtengo un item que coincida con un id determinado
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findItemById(int id);
	

	@Query("SELECT i FROM Inventor i WHERE i.id = :id")
	Inventor findInventorById(int id);

	@Query("select distinct(q.item) from Quantity q where q.toolkit.id = :masterId")
	Collection<Item> findItemsByToolkitId(int masterId);

	@Query("select i from Item i where i.inventor.id = :id")
	Collection<Item> findItemsByInventorId(int id);
	

	@Query("SELECT i FROM Item i WHERE i.id = :id and i.itemType = 0")
	Item findToolById(int id);

	@Modifying
	@Query("DELETE FROM Quantity q WHERE q.item.id = :id")
	void deleteQuantityByItemId(int id);
	

	@Query("select i.retailPrice from Item i where i.id= :id and i.itemType = 1")
	Money findToolPriceById(int id);

	@Query("SELECT i FROM Item i WHERE i.code = :code")
	Item findItemByCode(String code);
	
	@Query("select sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrencies();


	@Query("select i.retailPrice from Item i where i.id= :id and i.itemType = 0")
	Money findComponentPriceById(int id);
	
	@Query("select sc.systemCurrency from SystemConfiguration sc ")
	String findSystemCurrency();
}
