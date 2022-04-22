package acme.features.any.useraccount;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Administrator;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractListService;


@Service
public class AnyUserAccountListService implements AbstractListService<Any, UserAccount> {


	// Internal state ---------------------------------------------------------

	@Autowired

	protected 	AnyUserAccountRepository repository;


	@Override
	public boolean authorise(Request<UserAccount> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<UserAccount> findMany(Request<UserAccount> request) {
		assert request != null;

		
		List<UserAccount> result;
		//Obtiene el rol segun la peticion que reciba
		//final String roleString = request.getModel().getString("role"); 

		//Obtiene todas los usuarios
		final Collection<UserAccount> accounts = this.repository.findAccounts();

		/*Filtra aquellas cuyo rol no sea ni anonimo ni admin
		 * Luego filtra y se queda con aquellos usuarios tengan el rol de la peticion */
		
		
		 result = accounts.stream()
				.filter(account -> !account.isAnonymous() && !account.hasRole(Administrator.class))
				.collect(Collectors.toList());
		for (UserAccount userAccount : result) {
				userAccount.getRoles().forEach(r-> { ; } );
			
		}
		
		return result;
	}

	@Override
	public void unbind(Request<UserAccount> request, UserAccount entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		StringBuilder buffer;
		Collection<UserRole> roles;

		roles = entity.getRoles();
		buffer = new StringBuilder();
		
		int i = roles.size();
		
		for (final UserRole rol : roles) {
				buffer.append(rol.getAuthorityName());
				
				if(i > 1) {
					buffer.append(", ");
					i--;
				}
			
		}
		
		model.setAttribute("roles", buffer.toString());
		
		//Lo de identity viene en el framework 
		request.unbind(entity, model,"username", "identity.name", "identity.surname", "identity.email");

	}



}
