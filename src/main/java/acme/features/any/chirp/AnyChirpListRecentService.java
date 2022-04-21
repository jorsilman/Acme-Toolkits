package acme.features.any.chirp;

import java.util.Calendar; 
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import acme.entities.chirp.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;



@Service
public class AnyChirpListRecentService implements AbstractListService<Any, Chirp> {


	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyChirpRepository repository;

	@Override
	public boolean authorise(Request<Chirp> request) {
		assert request != null;

		return true;
	}



	@Override
	public Collection<Chirp> findMany(final Request<Chirp> request){
		assert request != null;

		Collection<Chirp> result;
		Calendar calendar;
		Date deadline;

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,-1);
		deadline = calendar.getTime();

		result = this.repository.findRecentChirps(deadline);

		return result;
	}
	
	@Override
	public void unbind(Request<Chirp> request, Chirp entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "title","author","body", "email");
	}



	






}
