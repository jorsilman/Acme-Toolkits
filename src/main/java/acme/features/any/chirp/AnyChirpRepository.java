package acme.features.any.chirp;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chirp.Chirp;

import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnyChirpRepository extends AbstractRepository{



	@Query("select ch from Chirp ch where ch.creationMoment > :deadline")
	Collection<Chirp> findRecentChirps(Date deadline);


}
