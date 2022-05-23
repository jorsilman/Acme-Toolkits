/*
 * AuthenticatedAnnouncementListService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportListOwnService implements AbstractListService<Patron, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageReportRepository repository;

	// AbstractListService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		assert request != null;

		Collection<PatronageReport> result;
		result = this.repository.findPatronageReportsByPatronId(request.getPrincipal().getActiveRoleId());
		return result;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationMoment", "memorandum", "link");
	}

}