package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

public interface InventorToolkitRepository extends AbstractRepository {

	
	@Query("select toolkit from Toolkit toolkit where toolkit.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select distinct(quantity.toolkit) from Quantity quantity where quantity.item.inventor.id = :id")
	Collection<Toolkit> findToolkitsByInventorId(int id);
	
	@Query("select sum(quantity.number*quantity.item.retailPrice.amount) from Quantity quantity where quantity.toolkit.id = :id and quantity.item.retailPrice.currency = :currency")
	Double findRetailPriceByToolkitId(int id, String currency);
		
	@Query("select distinct(quant.item) from Quantity quant where quant.toolkit.id = :id")
	Collection<Item> findItemsByToolkitId(int id);

}
