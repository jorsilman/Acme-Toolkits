/*
 * AuthenticatedAnnouncementShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.patronDashboard;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.PatronageStatus;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronDashboardRepository repository;

	// AbstractShowService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;

		final PatronDashboard result = null;
		
		final int proposedPatronages = this.repository.getNumberOfPatronagesByStatus(PatronageStatus.PROPOSED);
		final int acceptedPatronages = this.repository.getNumberOfPatronagesByStatus(PatronageStatus.ACCEPTED);
		final int deniedPatronages = this.repository.getNumberOfPatronagesByStatus(PatronageStatus.DENIED);
		
		result.setTotalNumberOfProposedPatronages(proposedPatronages);
		result.setTotalNumberOfAcceptedPatronages(acceptedPatronages);
		result.setTotalNumberOfDeniedPatronages(deniedPatronages);
		
		final Map<Pair<String, PatronageStatus>, Double> averageBudgetOfPatronagesByCurrencyAndStatus = new HashMap<Pair<String, PatronageStatus>, Double>();
		final Map<Pair<String, PatronageStatus>, Double> deviationBudgetOfPatronagesByCurrencyAndStatus = new HashMap<Pair<String, PatronageStatus>, Double>();
		final Map<Pair<String, PatronageStatus>, Double> minimumBudgetOfPatronagesByCurrencyAndStatus = new HashMap<Pair<String, PatronageStatus>, Double>();
		final Map<Pair<String, PatronageStatus>, Double> maximumBudgetOfPatronagesByCurrencyAndStatus = new HashMap<Pair<String, PatronageStatus>, Double>();
		
		for (final PatronageStatus status : PatronageStatus.values()) {
			final Collection<PatronDashboardMapper> averages = this.repository.findAverageBudgetOfPatronagesByStatus(status);
			final Collection<PatronDashboardMapper> deviations = this.repository.findDeviationBudgetOfPatronagesByStatus(status);
			final Collection<PatronDashboardMapper> minimums = this.repository.findMinimumBudgetOfPatronagesByStatus(status);
			final Collection<PatronDashboardMapper> maximums = this.repository.findMaximumBudgetOfPatronagesByStatus(status);
			
			for (final PatronDashboardMapper average : averages) {
				averageBudgetOfPatronagesByCurrencyAndStatus.put(Pair.of(average.getCurrency(), status), average.getValue());
			}
			
			for (final PatronDashboardMapper deviation : deviations) {
				averageBudgetOfPatronagesByCurrencyAndStatus.put(Pair.of(deviation.getCurrency(), status), deviation.getValue());
			}
			
			for (final PatronDashboardMapper minimum : minimums) {
				averageBudgetOfPatronagesByCurrencyAndStatus.put(Pair.of(minimum.getCurrency(), status), minimum.getValue());
			}
			
			for (final PatronDashboardMapper maximum : maximums) {
				averageBudgetOfPatronagesByCurrencyAndStatus.put(Pair.of(maximum.getCurrency(), status), maximum.getValue());
			}
		}
		
		result.setAverageBudgetOfPatronagesByCurrencyAndStatus(averageBudgetOfPatronagesByCurrencyAndStatus);
		result.setDeviationBudgetOfPatronagesByCurrencyAndStatus(deviationBudgetOfPatronagesByCurrencyAndStatus);
		result.setMinimumBudgetOfPatronagesByCurrencyAndStatus(minimumBudgetOfPatronagesByCurrencyAndStatus);
		result.setMaximumBudgetOfPatronagesByCurrencyAndStatus(maximumBudgetOfPatronagesByCurrencyAndStatus);
	
		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalNumberOfProposedPatronages", "totalNumberOfAcceptedPatronages", "totalNumberOfDeniedPatronages",
			"averageBudgetOfPatronagesByCurrencyAndStatus", "deviationBudgetOfPatronagesByCurrencyAndStatus",
			"minimumBudgetOfPatronagesByCurrencyAndStatus", "maximumBudgetOfPatronagesByCurrencyAndStatus");
	}

}