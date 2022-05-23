package acme.features.patron.patronage;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{
	
	// Internal state
	@Autowired
	protected PatronPatronageRepository repo;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);
		entity.setStatus(PatronageStatus.PROPOSED);
		entity.setPublished(false);
		entity.setInventor(this.repo.findInventorById(Integer.valueOf(request.getModel().getAttribute("inventorId").toString())));
	
		request.bind(entity, errors, "code", "legalStuff", "budget", "startPeriodOfTime", "endPeriodOfTime", "link");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "legalStuff", "budget", "startPeriodOfTime", "endPeriodOfTime", "link", "published");
		model.setAttribute("inventors", this.repo.findAllInventors());
	}
	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;
		
		final Patronage res;
		Date ini;
		Date end;
		
		ini = DateUtils.addMonths(new Date(System.currentTimeMillis() + 300000), 1);
		end = DateUtils.addMonths(ini, 1);
		end = DateUtils.addMinutes(end, 1);
		
		res = new Patronage();
		res.setLegalStuff("");
		res.setStatus(PatronageStatus.PROPOSED);
		res.setStartPeriodOfTime(ini);
		res.setEndPeriodOfTime(end);
		res.setPatron(this.repo.findPatronById(request.getPrincipal().getActiveRoleId()));
		
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
			errors.state(request, p == null, "code", "patron.patronage.form.error.duplicated");
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
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		this.repo.save(entity);
	}

}
