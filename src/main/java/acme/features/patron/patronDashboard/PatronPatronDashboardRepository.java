/*
 * AuthenticatedAnnouncementRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.patronDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.PatronageStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronDashboardRepository extends AbstractRepository {

	@Query("SELECT COUNT(patronage) FROM Patronage patronage where patronage.status = :status")
	int getNumberOfPatronagesByStatus(PatronageStatus status);
	
	@Query("SELECT patronage.budget.currency, patronage.status, AVG(patronage.budget.amount)  FROM Patronage patronage GROUP BY patronage.budget.currency, patronage.status")
	List<Object[]> findAverageBudgetOfPatronagesByCurrencyAndStatus();
	
	@Query("SELECT patronage.budget.currency, patronage.status, STDDEV(patronage.budget.amount)  FROM Patronage patronage GROUP BY patronage.budget.currency, patronage.status")
	List<Object[]> findDeviationBudgetOfPatronagesByCurrencyAndStatus();
	
	@Query("SELECT patronage.budget.currency, patronage.status, MIN(patronage.budget.amount)  FROM Patronage patronage GROUP BY patronage.budget.currency, patronage.status")
	List<Object[]> findMinimumBudgetOfPatronagesByCurrencyAndStatus();
	
	@Query("SELECT patronage.budget.currency, patronage.status, MAX(patronage.budget.amount)  FROM Patronage patronage GROUP BY patronage.budget.currency, patronage.status")
	List<Object[]> findMaximumBudgetOfPatronagesByCurrencyAndStatus();
	

}