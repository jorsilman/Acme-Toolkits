package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.item.Quantity;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.entities.toolkit.Toolkit;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {


	@Query("select toolkit from Toolkit toolkit where toolkit.id = :id")
	Toolkit findToolkitById(int id);

	@Query("select toolkit from Toolkit toolkit where toolkit.inventor.id = :id")
	Collection<Toolkit> findToolkitsByInventorId(int id);

	@Query("select sum(quantity.number*quantity.item.retailPrice.amount) from Quantity quantity where quantity.toolkit.id = :id and quantity.item.retailPrice.currency = :currency")
	Double findRetailPriceByToolkitId(int id, String currency);

	@Query("select i from Inventor i where i.id = :id ")
	Inventor findInventorById(int id);

	@Query("select tl from Toolkit tl where tl.code = :toolkitCode")
	Toolkit findToolkitByCode(String toolkitCode);

	@Query("select q from Quantity q where q.toolkit.id = :id")
	Collection<Quantity> findQuantitiesOfToolkitId(int id);
	
	@Query("select q.item from Quantity q where q.toolkit.id =: id")
	Collection<Item> findItemsOfToolkitByToolkitId(int id);
	
	@Query("select sc from SystemConfiguration sc ")
	SystemConfiguration getSystemConfiguration();


}
