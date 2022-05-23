package acme.features.patron.patronage;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronageUpdateService implements AbstractUpdateService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repo;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean res;
		int id;
		Patronage p;
		id = request.getModel().getInteger("id");
		p = this.repo.findOnePatronageById(id);
		res = (p != null && !p.isPublished() && request.isPrincipal(p.getPatron()));
		
		return res;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "legalStuff", "budget", "startPeriodOfTime", "endPeriodOfTime", "link", "published");
	
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "legalStuff", "budget", "startPeriodOfTime", "endPeriodOfTime","link", "published");
		model.setAttribute("inventorId", entity.getInventor().getId());
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage res;
		int id;
		id = request.getModel().getInteger("id");
		res = this.repo.findOnePatronageById(id);
		
		return res;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			Patronage p;
			p = this.repo.findPatronageByCode(entity.getCode());
			errors.state(request, p == null || p.getId() == entity.getId(), "code", "patron.patronage.form.error.duplicated");
			final Patronage patronage = this.repo.findOnePatronageById(entity.getId());
			errors.state(request, patronage.getCode().equals(entity.getCode()), "code", "patron.patronage.form.error.codeChanged");
		}
		
		if(!errors.hasErrors("startPeriodOfTime")) {
			final Date fecha1=DateUtils.addMonths(entity.getCreationDate(), 1);
			errors.state(request,entity.getStartPeriodOfTime().after(fecha1), "startPeriodOfTime", "patron.patronage.form.error.soon-to-close");
				
			}
		if(!errors.hasErrors("endPeriodOfTime")) {
			final Date fecha2=DateUtils.addMonths(entity.getStartPeriodOfTime(), 1);
			errors.state(request,entity.getEndPeriodOfTime().after(fecha2), "endPeriodOfTime", "patron.patronage.form.error.minimum");
				
			}
		if (!errors.hasErrors("budget")) {
			final String[] currencies = this.repo.findSystemConfiguration().getAcceptedCurrencies().split(",");
            Boolean acceptedCu = false;
            for(int i=0;i<currencies.length;i++) {
                if(entity.getBudget().getCurrency().equals(currencies[i].trim())) {
                    acceptedCu = true;
                }
            }
			
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative");
			errors.state(request, acceptedCu, "budget", "patron.patronage.form.error.non-accepted");
		}
		
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		this.repo.save(entity);
	}

}
