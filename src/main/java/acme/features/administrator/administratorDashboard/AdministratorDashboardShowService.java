package acme.features.administrator.administratorDashboard;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.PatronageStatus;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		final AdministratorDashboard result = new AdministratorDashboard	();


		//OBTENEMOS LOS VALORES
		final int totalNumberOfComponents = this.repository.getNumberOfComponents();
		final int totalNumberOfTools = this.repository.getNumberOfTools();
		final int totalNumberOfProposedPatronages = this.repository.getTotalNumberOfProposedPatronages();
		final int totalNumberOfAcceptedPatronages = this.repository.getTotalNumberOfAcceptedPatronages();
		final int totalNumberOfDeniedPatronages = this.repository.getTotalNumberOfDeniedPatronages();

		//LOS ASIGNAMOS
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setTotalNumberOfTools(totalNumberOfTools);
		result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
		result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
		result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);

		//CREAMOS LOS MAP DE COMPONENTS
		final Map<Pair<String, String>, Double> avgRPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> devRPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();;
		final Map<Pair<String, String>, Double> minRPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();;
		final Map<Pair<String, String>, Double> maxRPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();;

		//CREAMOS LOS MAP DE TOOLS

		final Map<String, Double> avgRPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> devRPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> minRPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> maxRPriceOfToolsByCurrency = new HashMap<String, Double>();

		//CREAMOS LOS MAP DE PATRONAGES
		final Map<PatronageStatus, Double> avgBudgetOfPatronagesByStatus = new HashMap<PatronageStatus, Double>();
		final Map<PatronageStatus, Double> devBudgetOfPatronagesByStatus = new HashMap<PatronageStatus, Double>();
		final Map<PatronageStatus, Double> minBudgetOfPatronagesByStatus = new HashMap<PatronageStatus, Double>();
		final Map<PatronageStatus, Double> maxBudgetOfPatronagesByStatus = new HashMap<PatronageStatus, Double>();

		//AHORA ASIGNAMOS VALORES A LOS MAPS CON LOS METODOS DEL REPO

		//COMPONENTS
		for(final Object[] o: this.repository.getAverageRetailPriceOfComponentsByTechnologyAndCurrency()) {
			avgRPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(o[0].toString(),o[1].toString()), (Double) o[2]);
		}

		for(final Object[] o: this.repository.getDeviationRetailPriceOfComponentsByTechnologyAndCurrency()) {
			devRPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(o[0].toString(),o[1].toString()), (Double) o[2]);
		}

		for(final Object[] o: this.repository.getMinimumRetailPriceOfComponentsByTechnologyAndCurrency()) {
			minRPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(o[0].toString(),o[1].toString()), (Double) o[2]);
		}

		for(final Object[] o: this.repository.getMaximumRetailPriceOfComponentsByTechnologyAndCurrency()) {
			maxRPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(o[0].toString(),o[1].toString()), (Double) o[2]);
		}


		result.setAverageRetailPriceOfComponentsByTechnologyAndCurrency(avgRPriceOfComponentsByTechnologyAndCurrency);
		result.setDeviationRetailPriceOfComponentsByTechnologyAndCurrency(devRPriceOfComponentsByTechnologyAndCurrency);
		result.setMinimumRetailPriceOfComponentsByTechnologyAndCurrency(minRPriceOfComponentsByTechnologyAndCurrency);
		result.setMaximumRetailPriceOfComponentsByTechnologyAndCurrency(maxRPriceOfComponentsByTechnologyAndCurrency);

		//ITEMS

		for(final Object[] o: this.repository.getAverageRetailPriceOfToolsByCurrency()) {
			avgRPriceOfToolsByCurrency.put(o[0].toString(),(Double) o[1]);
		}

		for(final Object[] o: this.repository.getDeviationRetailPriceOfToolsByCurrency()) {
			devRPriceOfToolsByCurrency.put(o[0].toString(),(Double) o[1]);
		}

		for(final Object[] o: this.repository.getMinimumRetailPriceOfToolsByCurrency()) {
			minRPriceOfToolsByCurrency.put(o[0].toString(),(Double) o[1]);
		}

		for(final Object[] o: this.repository.getMaximumBudgetOfPatronagesByStatus()) {
			maxRPriceOfToolsByCurrency.put(o[0].toString(),(Double) o[1]);
		}

		result.setAverageRetailPriceOfToolsByCurrency(avgRPriceOfToolsByCurrency);
		result.setDeviationRetailPriceOfToolsByCurrency(devRPriceOfToolsByCurrency);
		result.setMinimumRetailPriceOfToolsByCurrency(minRPriceOfToolsByCurrency);
		result.setMaximumRetailPriceOfToolsByCurrency(maxRPriceOfToolsByCurrency);

		//PATRONAGES

		for(final Object[] o: this.repository.getAverageBudgetOfPatronagesByStatus()) {
			avgBudgetOfPatronagesByStatus.put(PatronageStatus.valueOf(o[0].toString()), (Double) o[1]);
		}

		for(final Object[] o: this.repository.getDeviationBudgetOfPatronagesByStatus()) {
			minBudgetOfPatronagesByStatus.put(PatronageStatus.valueOf(o[0].toString()), (Double) o[1]);
		}

		for(final Object[] o: this.repository.getMinimumBudgetOfPatronagesByStatus()) {
			minBudgetOfPatronagesByStatus.put(PatronageStatus.valueOf(o[0].toString()), (Double) o[1]);
		}

		for(final Object[] o: this.repository.getMaximumBudgetOfPatronagesByStatus()) {
			maxBudgetOfPatronagesByStatus.put(PatronageStatus.valueOf(o[0].toString()), (Double) o[1]);
		}

		result.setAverageBudgetOfPatronagesByStatus(avgBudgetOfPatronagesByStatus);
		result.setDeviationBudgetOfPatronagesByStatus(devBudgetOfPatronagesByStatus);
		result.setMinimumBudgetOfPatronagesByStatus(minBudgetOfPatronagesByStatus);
		result.setMaximumBudgetOfPatronagesByStatus(maxBudgetOfPatronagesByStatus);


		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfComponents","averageRetailPriceOfComponentsByTechnologyAndCurrency","deviationRetailPriceOfComponentsByTechnologyAndCurrency",
				"minimumRetailPriceOfComponentsByTechnologyAndCurrency","maximumRetailPriceOfComponentsByTechnologyAndCurrency",
				"totalNumberOfTools","averageRetailPriceOfToolsByCurrency","deviationRetailPriceOfToolsByCurrency","minimumRetailPriceOfToolsByCurrency","maximumRetailPriceOfToolsByCurrency",
				"totalNumberOfProposedPatronages","totalNumberOfAcceptedPatronages","totalNumberOfDeniedPatronages","averageBudgetOfPatronagesByStatus","deviationBudgetOfPatronagesByStatus","minimumBudgetOfPatronagesByStatus","maximumBudgetOfPatronagesByStatus"
				);

	}

}
