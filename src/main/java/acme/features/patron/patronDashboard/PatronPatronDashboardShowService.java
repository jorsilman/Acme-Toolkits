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

import java.util.HashMap;
import java.util.List;
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

		PatronDashboard result = new PatronDashboard();
		
		int proposedPatronages = this.repository.getNumberOfPatronagesByStatus(PatronageStatus.PROPOSED);
		int acceptedPatronages = this.repository.getNumberOfPatronagesByStatus(PatronageStatus.ACCEPTED);
		int deniedPatronages = this.repository.getNumberOfPatronagesByStatus(PatronageStatus.DENIED);
		
		result.setTotalNumberOfProposedPatronages(proposedPatronages);
		result.setTotalNumberOfAcceptedPatronages(acceptedPatronages);
		result.setTotalNumberOfDeniedPatronages(deniedPatronages);
		
		Map<Pair<String, PatronageStatus>, Double> averageBudgetOfPatronagesByCurrencyAndStatus = new HashMap<Pair<String, PatronageStatus>, Double>();
		Map<Pair<String, PatronageStatus>, Double> deviationBudgetOfPatronagesByCurrencyAndStatus = new HashMap<Pair<String, PatronageStatus>, Double>();
		Map<Pair<String, PatronageStatus>, Double> minimumBudgetOfPatronagesByCurrencyAndStatus = new HashMap<Pair<String, PatronageStatus>, Double>();
		Map<Pair<String, PatronageStatus>, Double> maximumBudgetOfPatronagesByCurrencyAndStatus = new HashMap<Pair<String, PatronageStatus>, Double>();
		
		List<Object[]> averages = this.repository.findAverageBudgetOfPatronagesByCurrencyAndStatus();
		List<Object[]> deviations = this.repository.findDeviationBudgetOfPatronagesByCurrencyAndStatus();
		List<Object[]> minimums = this.repository.findMinimumBudgetOfPatronagesByCurrencyAndStatus();
		List<Object[]> maximums = this.repository.findMaximumBudgetOfPatronagesByCurrencyAndStatus();

		for (Object[] average : averages) {
			averageBudgetOfPatronagesByCurrencyAndStatus.put(Pair.of(average[0].toString(), PatronageStatus.valueOf(average[1].toString())), (Double) average[2]);
		}
		
		for (Object[] deviation : deviations) {
			deviationBudgetOfPatronagesByCurrencyAndStatus.put(Pair.of(deviation[0].toString(), PatronageStatus.valueOf(deviation[1].toString())), (Double) deviation[2]);
		}
		
		for (Object[] minimum : minimums) {
			minimumBudgetOfPatronagesByCurrencyAndStatus.put(Pair.of(minimum[0].toString(), PatronageStatus.valueOf(minimum[1].toString())), (Double) minimum[2]);
		}
		
		for (Object[] maximum : maximums) {
			maximumBudgetOfPatronagesByCurrencyAndStatus.put(Pair.of(maximum[0].toString(), PatronageStatus.valueOf(maximum[1].toString())), (Double) maximum[2]);
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
		
		model.setAttribute("average", entity.getAverageBudgetOfPatronagesByCurrencyAndStatus().toString());
		model.setAttribute("deviation", entity.getDeviationBudgetOfPatronagesByCurrencyAndStatus().toString());
		model.setAttribute("minimum", entity.getMinimumBudgetOfPatronagesByCurrencyAndStatus().toString());
		model.setAttribute("maximum", entity.getMaximumBudgetOfPatronagesByCurrencyAndStatus().toString());
		
		request.unbind(entity, model, "totalNumberOfProposedPatronages", "totalNumberOfAcceptedPatronages", "totalNumberOfDeniedPatronages",
			"averageBudgetOfPatronagesByCurrencyAndStatus", "deviationBudgetOfPatronagesByCurrencyAndStatus",
			"minimumBudgetOfPatronagesByCurrencyAndStatus", "maximumBudgetOfPatronagesByCurrencyAndStatus");
	}

}