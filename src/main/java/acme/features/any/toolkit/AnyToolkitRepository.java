package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);
	
	@Query("select t from Toolkit t where t.published = true")
	Collection<Toolkit> findManyToolkitsPublished();
	
	@Query("select distinct(quant.item) from Quantity quant where quant.toolkit.id = :id")
	Collection<Item> findItemsByToolkitId(int id);
	
	@Query("select sum(quantity.number*quantity.item.retailPrice.amount) from Quantity quantity where quantity.toolkit.id = :id and quantity.item.retailPrice.currency = :currency")
	Double findRetailPriceByToolkitId(int id, String currency);
	

}
