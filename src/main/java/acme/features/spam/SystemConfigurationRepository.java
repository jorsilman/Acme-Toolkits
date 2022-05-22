package acme.features.spam;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SystemConfigurationRepository extends AbstractRepository{

	@Query("select s from SystemConfiguration s")
	List<SystemConfiguration> getSystemConfiguration();
	
	@Query("select distinct(s.strongSpamWords) from SystemConfiguration s")
	Collection<String> getStrongSpamWords();
	
	@Query("select s.strongSpamThreshold from SystemConfiguration s")
	Double getStrongSpamThreshold();
	
	@Query("select s.weakSpamThreshold from SystemConfiguration s")
	Double getWeakSpamThreshold();
}
