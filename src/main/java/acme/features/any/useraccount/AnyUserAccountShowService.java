package acme.features.any.useraccount;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Anonymous;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractShowService;

@Service
public class AnyUserAccountShowService implements AbstractShowService<Any, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyUserAccountRepository repository;

	@Override
	public boolean authorise(Request<UserAccount> request) {
		assert request != null;
			
		return true;
	}

	@Override
	public UserAccount findOne(Request<UserAccount> request) {
		assert request != null;

		UserAccount result;
		int id;

		//Obtengo el ID
		id = request.getModel().getInteger("id");
		//Obtengo la UserAccount
		result = this.repository.findUserAccountById(id);
		result.getRoles().forEach(r -> {
		});

		return result;
	}

	@Override
	public void unbind(Request<UserAccount> request, UserAccount entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		StringBuilder buffer;
		Collection<UserRole> roles;

		request.unbind(entity, model, "username", "identity.name", "identity.surname", "identity.email");

		//Obtengo todos los roles 
		roles = entity.getRoles();
		buffer = new StringBuilder();
		for (final UserRole role : roles) {
			//Se añade el nombre del Rol
			buffer.append(role.getAuthorityName());
			buffer.append("");
		}

		//Se añade al modelo el atributo con su correspondiente valor
		model.setAttribute("roleList", buffer.toString());

		//Si el rol es Anónimo no permite actualizar los valores
		if (entity.hasRole(Anonymous.class)) {
			model.setAttribute("canUpdate", false);
		} else {
			model.setAttribute("canUpdate", true);
		}
	}

}


