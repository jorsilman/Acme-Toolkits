package acme.features.any.useraccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.framework.roles.UserRole;


@Repository
public interface AnyUserAccountRepository extends AbstractRepository{
	

	@Query("SELECT ua FROM UserAccount ua WHERE ua.enabled=true")
	Collection<UserAccount> findAccounts();
	


}
