package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirp.Chirp;
import acme.features.spam.SpamDetectorService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp>{
	
	@Autowired
	protected AnyChirpRepository repository;

	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request !=null;
		return true;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors !=null;
		
		request.bind(entity, errors, "title","author","body","email");
		
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model !=null;
		
		request.unbind(entity, model, "title","author","body","email");
		model.setAttribute("confirm", false);
		
	}

	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;
		
		final Chirp result = new Chirp();
		Date now;
		now = new Date(System.currentTimeMillis()-1);
		result.setCreationMoment(now);
		
		return result;
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors !=null;
		final boolean confirm = request.getModel().getBoolean("confirm");
		errors.state(request, confirm, "confirm", "any.chirp.confirm.error");
		final String title = request.getModel().getString("title");
		final boolean spam = SpamDetectorService.isSpam(title);
		errors.state(request, spam, "title", "any.chirp.spam.title.error");
		
	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity !=null;
		
		this.repository.save(entity);
		
	}

}
