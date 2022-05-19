package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit>{
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;




	@Override
	public boolean authorise(Request<Toolkit> request) {
		assert request != null;

		boolean result;

		result = request.getPrincipal().hasRole(Inventor.class); 


		return result;
	}

	@Override
	public void bind(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");
	}

	@Override
	public void unbind(Request<Toolkit> request, Toolkit entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"code", "title", "description", "assemblyNotes", "link");

	}

	@Override
	public Toolkit instantiate(Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		Inventor inventor;

		inventor = this.repository.findInventorById(request.getPrincipal().getActiveRoleId());
		result = new Toolkit();

		result.setPublished(false);
		result.setInventor(inventor);

		return result;
	}

	@Override
	public void validate(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//COMPROBAR ERRORES EN ATRIBUTO CODE
		if (!errors.hasErrors("code")) {
			Toolkit existing;
			//SI NO HAY NINGUN TL CON ESE CODE ENTONCES ES NULL
			existing = this.repository.findToolkitByCode(entity.getCode());
			//SI YA EXISTE LANZAR ERROR
			errors.state(request, existing == null, "code", "inventor.toolkit.form.error.duplicated");
		}
		//HACER FILTRO DE SPAM

	}

	@Override
	public void create(Request<Toolkit> request, Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}





}



