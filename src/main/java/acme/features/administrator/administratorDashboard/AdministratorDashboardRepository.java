package acme.features.administrator.administratorDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	//COMPOMENTS ===============================================================
	@Query("SELECT COUNT(i) FROM Item i WHERE i.itemType = acme.entities.item.ItemType.COMPONENT")
	int getNumberOfComponents();

	@Query("SELECT  i.technology, i.retailPrice.currency,AVG(i) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.COMPONENT GROUP BY  i.technology, i.retailPrice.currency")
	List<Object[]> getAverageRetailPriceOfComponentsByTechnologyAndCurrency();

	@Query("SELECT  i.technology, i.retailPrice.currency,STDDEV(i) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.COMPONENT GROUP BY  i.technology, i.retailPrice.currency")
	List<Object[]> getDeviationRetailPriceOfComponentsByTechnologyAndCurrency();

	@Query("SELECT  i.technology, i.retailPrice.currency,MIN(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.COMPONENT GROUP BY  i.technology, i.retailPrice.currency")
	List<Object[]> getMinimumRetailPriceOfComponentsByTechnologyAndCurrency();

	@Query("SELECT  i.technology, i.retailPrice.currency,MAX(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.COMPONENT GROUP BY  i.technology, i.retailPrice.currency")
	List<Object[]> getMaximumRetailPriceOfComponentsByTechnologyAndCurrency();


	//TOOLS =======================================================================
	@Query("SELECT COUNT(i) FROM Item i WHERE i.itemType = acme.entities.item.ItemType.TOOL")
	int getNumberOfTools();

	@Query("SELECT  i.retailPrice.currency,AVG(i) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.TOOL GROUP BY  i.retailPrice.currency")
	List<Object[]> getAverageRetailPriceOfToolsByCurrency();

	@Query("SELECT  i.retailPrice.currency,STDDEV(i) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.TOOL GROUP BY  i.retailPrice.currency")
	List<Object[]> getDeviationRetailPriceOfToolsByCurrency();

	@Query("SELECT  i.retailPrice.currency,MIN(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.TOOL GROUP BY   i.retailPrice.currency")
	List<Object[]> getMinimumRetailPriceOfToolsByCurrency();

	@Query("SELECT  i.retailPrice.currency,MAX(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.TOOL GROUP BY   i.retailPrice.currency")
	List<Object[]> getMaximumRetailPriceOfToolsByCurrency();




	//PATRONAGES ================================================================

	@Query("SELECT COUNT(p) FROM Patronage p WHERE p.status = acme.entities.patronage.PatronageStatus.ACCEPTED")
	int getTotalNumberOfAcceptedPatronages();

	@Query("SELECT COUNT(p) FROM Patronage p WHERE p.status = acme.entities.patronage.PatronageStatus.DENIED")
	int getTotalNumberOfDeniedPatronages();

	@Query("SELECT COUNT(p) FROM Patronage p WHERE p.status = acme.entities.patronage.PatronageStatus.PROPOSED")
	int getTotalNumberOfProposedPatronages();

	@Query("SELECT  p.budget.currency, AVG(p) FROM Patronage p GROUP BY  p.status")
	List<Object[]> getAverageBudgetOfPatronagesByStatus();

	@Query("SELECT  p.budget.currency, STDDEV(p) FROM Patronage p GROUP BY  p.status")
	List<Object[]> getDeviationBudgetOfPatronagesByStatus();

	@Query("SELECT  p.budget.currency, MIN(p.budget.amount) FROM Patronage p GROUP BY  p.status")	
	List<Object[]> getMinimumBudgetOfPatronagesByStatus();

	@Query("SELECT  p.budget.currency, MAX(p.budget.amount) FROM Patronage p GROUP BY  p.status")
	List<Object[]> getMaximumBudgetOfPatronagesByStatus();



}
