package acme.entities.systemConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface SystemConfigurationRepository extends AbstractRepository{
	
	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration findSystemConfiguration();

}
