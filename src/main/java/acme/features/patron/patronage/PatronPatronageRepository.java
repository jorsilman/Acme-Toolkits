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

package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("select patronage FROM Patronage patronage WHERE patronage.id = :id")
	Patronage findOnePatronageById(int id);

	@Query("SELECT patronage FROM Patronage patronage WHERE patronage.patron.id = :id")
	Collection<Patronage> findPatronagesByPatronId(int id);
	

	@Query("SELECT p FROM Patronage p WHERE p.code LIKE :code")
	Patronage findPatronageByCode(String code);
	
	@Query("SELECT i FROM Inventor i WHERE i.id = :id")
	Inventor findInventorById(int id);
	
	@Query("SELECT i FROM Inventor i")
	Collection<Inventor> findAllInventors();
	
	@Query("SELECT patron FROM Patron patron WHERE patron.id=:id")
    Patron findPatronById(int id);
	
	@Query("SELECT s FROM SystemConfiguration s")
	SystemConfiguration findSystemConfiguration();
	
	@Query("SELECT c.systemCurrency FROM SystemConfiguration c")
	String defaultCurrency();

	@Query("select sc.systemCurrency from SystemConfiguration sc ")
	String findSystemCurrency();


}
