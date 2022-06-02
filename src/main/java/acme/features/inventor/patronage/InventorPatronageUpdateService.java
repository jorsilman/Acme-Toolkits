package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageUpdateService implements AbstractUpdateService<Inventor, Patronage>{
	
	@Autowired
	protected InventorPatronageRepository inventorPatronageRepository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		boolean result = false;
		
		final int id = request.getModel().getInteger("id");
		final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(id);
		result = patronage.getInventor().getId()==request.getPrincipal().getActiveRoleId();
		return result;
	}
	

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "code", "legalStuff", "budget", "creationDate", "startPeriodOfTime", "endPeriodOfTime", "link");
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startPeriodOfTime", "endPeriodOfTime", "link");
		
		
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage patronage;
		int id;
		
		id = request.getModel().getInteger("id");
		patronage = this.inventorPatronageRepository.findOnePatronageById(id);
		return patronage;
	}


	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		PatronageStatus status;
		
		status = PatronageStatus.valueOf(request.getModel().getString("status"));
		entity.setStatus(status);
		this.inventorPatronageRepository.save(entity);
		
	}


	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(entity.getId());
			errors.state(request, patronage.getCode().equals(entity.getCode()), "code", "inventor.patronage.form.error.codeChanged");
		}
		if(!errors.hasErrors("legalStuff")) {
			final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(entity.getId());
			errors.state(request, patronage.getLegalStuff().equals(entity.getLegalStuff()), "legalStuff", "inventor.patronage.form.error.legalStuffChanged");
		}
		if(!errors.hasErrors("budget")) {
			final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(entity.getId());
			errors.state(request, patronage.getBudget().equals(entity.getBudget()), "budget", "inventor.patronage.form.error.budgetChanged");
		}
		if(!errors.hasErrors("creationDate")) {
			final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(entity.getId());
			errors.state(request, patronage.getCreationDate().equals(entity.getCreationDate()), "creationDate", "inventor.patronage.form.error.creationMomentChanged");
		}
		if(!errors.hasErrors("startPeriodOfTime")) {
			final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(entity.getId());
			errors.state(request, patronage.getStartPeriodOfTime().equals(entity.getStartPeriodOfTime()), "startPeriodOfTime", "inventor.patronage.form.error.startPeriodOfTimeChanged");
		}
		if(!errors.hasErrors("endPeriodOfTime")) {
			final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(entity.getId());
			errors.state(request, patronage.getEndPeriodOfTime().equals(entity.getEndPeriodOfTime()), "endPeriodOfTime", "inventor.patronage.form.error.endPeriodOfTimeChanged");
		}
		if(!errors.hasErrors("link")) {
			final Patronage patronage = this.inventorPatronageRepository.findOnePatronageById(entity.getId());
			errors.state(request, patronage.getLink().equals(entity.getLink()), "link", "inventor.patronage.form.error.linkChanged");
		}
		
	
	}

}
