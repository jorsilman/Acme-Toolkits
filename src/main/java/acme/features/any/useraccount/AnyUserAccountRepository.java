package acme.features.any.useraccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnyUserAccountRepository extends AbstractRepository{
	

	@Query("select ua from UserAccount ua WHERE ua.enabled=true")
	Collection<UserAccount> findAccounts();
	
	@Query("select ua from UserAccount ua WHERE ua.id = :id")
	UserAccount findUserAccountById(Integer id);

}
