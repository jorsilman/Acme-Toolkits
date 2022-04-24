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
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("select patronage from Patronage patronage where patronage.id = :id")
	Patronage findOnePatronageById(int id);

	@Query("select patronage from Patronage patronage where patronage.patron.id = :id")
	Collection<Patronage> findPatronagesByPatronId(int id);

}
