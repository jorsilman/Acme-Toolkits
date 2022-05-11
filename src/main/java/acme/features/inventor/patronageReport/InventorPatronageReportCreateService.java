/*
 * WorkerApplicationCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.patronageReport;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	// AbstractCreateService<Worker, Application> interface -------------------


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		boolean result;
		int patronageId;
		final Patronage patronage;

		patronageId = request.getModel().getInteger("patronageId");
		patronage = this.repository.findOnePatronageById(patronageId);
		result = (patronage != null && request.isPrincipal(patronage.getInventor()));

		return result;
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		int patronageId;
		Patronage patronage;
		String lastSerialNumber;
		int newSerialNumber;
		String newSerialNumberParsed;
		Date moment;
		Calendar calendar;

		patronageId = request.getModel().getInteger("patronageId");
		patronage = this.repository.findOnePatronageById(patronageId);
		
		lastSerialNumber = this.repository.findLastSerialNumber();
		newSerialNumber = Integer.valueOf(lastSerialNumber) + 1;
		newSerialNumberParsed = String.format("%04d", newSerialNumber); // Must be 4 characters long, left-pads as many 0s as necessary
		

		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();

		result = new PatronageReport();
		result.setSerialNumber(newSerialNumberParsed);
		result.setMemorandum("");
		result.setLink("");
		result.setCreationMoment(moment);
		//result.setSequenceNumber("");
		result.setPatronage(patronage);

		return result;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "serialNumber", "memorandum", "link", "confirmation");
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final boolean confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "inventor.patronage-report.confirmatio.error");
		
		// TODO: Check sequenceNumber
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "serialNumber", "memorandum", "link");
		model.setAttribute("confirmation", false);
		model.setAttribute("patronageId", request.getModel().getAttribute("patronageId"));
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		Calendar calendar;

		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();

		entity.setCreationMoment(moment);
		this.repository.save(entity);
	}

}
