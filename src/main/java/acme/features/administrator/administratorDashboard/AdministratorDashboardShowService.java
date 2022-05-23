package acme.features.administrator.administratorDashboard;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
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

		AdministratorDashboard result;


		//OBTENEMOS LOS VALORES
		int totalNumberOfComponents = this.repository.getNumberOfComponents();
		int totalNumberOfTools = this.repository.getNumberOfTools();
		int totalNumberOfProposedPatronages = this.repository.getTotalNumberOfProposedPatronages();
		int totalNumberOfAcceptedPatronages = this.repository.getTotalNumberOfAcceptedPatronages();
		int totalNumberOfDeniedPatronages = this.repository.getTotalNumberOfDeniedPatronages();


		//CREAMOS LOS MAP DE COMPONENTS
		Map<Pair<String, String>, Double> avgRPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();
		Map<Pair<String, String>, Double> devRPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();;
		Map<Pair<String, String>, Double> minRPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();;
		Map<Pair<String, String>, Double> maxRPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();;

		//CREAMOS LOS MAP DE TOOLS

		Map<String, Double> avgRPriceOfToolsByCurrency = new HashMap<String, Double>();
		Map<String, Double> devRPriceOfToolsByCurrency = new HashMap<String, Double>();
		Map<String, Double> minRPriceOfToolsByCurrency = new HashMap<String, Double>();
		Map<String, Double> maxRPriceOfToolsByCurrency = new HashMap<String, Double>();

		//CREAMOS LOS MAP DE PATRONAGES
		Map<String, Double> avgBudgetOfPatronagesByStatus = new HashMap<String, Double>();
		Map<String, Double> devBudgetOfPatronagesByStatus = new HashMap<String, Double>();
		Map<String, Double> minBudgetOfPatronagesByStatus = new HashMap<String, Double>();
		Map<String, Double> maxBudgetOfPatronagesByStatus = new HashMap<String, Double>();

		//AHORA ASIGNAMOS VALORES A LOS MAPS CON LOS METODOS DEL REPO

		//COMPONENTS
		for( Object[] o: this.repository.getAverageRetailPriceOfComponentsByTechnologyAndCurrency()) {
			avgRPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(o[0].toString(),o[1].toString()), (Double) o[2]);
		}

		for(Object[] o: this.repository.getDeviationRetailPriceOfComponentsByTechnologyAndCurrency()) {
			devRPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(o[0].toString(),o[1].toString()), (Double) o[2]);
		}

		for(Object[] o: this.repository.getMinimumRetailPriceOfComponentsByTechnologyAndCurrency()) {
			minRPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(o[0].toString(),o[1].toString()), (Double) o[2]);
		}

		for(Object[] o: this.repository.getMaximumRetailPriceOfComponentsByTechnologyAndCurrency()) {
			maxRPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(o[0].toString(),o[1].toString()), (Double) o[2]);
		}


		//ITEMS

		for(Object[] o: this.repository.getAverageRetailPriceOfToolsByCurrency()) {
			avgRPriceOfToolsByCurrency.put(o[0].toString(),(Double) o[1]);
		}

		for(Object[] o: this.repository.getDeviationRetailPriceOfToolsByCurrency()) {
			devRPriceOfToolsByCurrency.put(o[0].toString(),(Double) o[1]);
		}

		for(Object[] o: this.repository.getMinimumRetailPriceOfToolsByCurrency()) {
			minRPriceOfToolsByCurrency.put(o[0].toString(),(Double) o[1]);
		}

		for(Object[] o: this.repository.getMaximumBudgetOfPatronagesByStatus()) {
			maxRPriceOfToolsByCurrency.put(o[0].toString(),(Double) o[1]);
		}

		String systemCurrency = this.repository.findSystemCurrency();

		//PATRONAGES

		for(Object[] o: this.repository.getAverageBudgetOfPatronagesByStatus()) {
			Money m = new Money();
			m.setAmount((Double) o[1]);
			m.setCurrency(o[2].toString());
			AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
			MoneyExchange change = moneyExchange.computeMoneyExchange(m, systemCurrency);
			
			avgBudgetOfPatronagesByStatus.put(o[0].toString(), (Double) o[1]);
		}

		for(Object[] o: this.repository.getDeviationBudgetOfPatronagesByStatus()) {
			Money m = new Money();
			m.setAmount((Double) o[1]);
			m.setCurrency(o[2].toString());
			AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
			MoneyExchange change = moneyExchange.computeMoneyExchange(m, systemCurrency);
			
			minBudgetOfPatronagesByStatus.put(o[0].toString(), (Double) o[1]);
		}

		for(Object[] o: this.repository.getMinimumBudgetOfPatronagesByStatus()) {
			Money m = new Money();
			m.setAmount((Double) o[1]);
			m.setCurrency(o[2].toString());
			AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
			MoneyExchange change = moneyExchange.computeMoneyExchange(m, systemCurrency);
			
			minBudgetOfPatronagesByStatus.put(o[0].toString(), (Double) o[1]);
		}

		for(Object[] o: this.repository.getMaximumBudgetOfPatronagesByStatus()) {
			Money m = new Money();
			m.setAmount((Double) o[1]);
			m.setCurrency(o[2].toString());
			AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
			MoneyExchange change = moneyExchange.computeMoneyExchange(m, systemCurrency);
			
			maxBudgetOfPatronagesByStatus.put(o[0].toString(), change.getTarget().getAmount());
		}
		
		result = new AdministratorDashboard();
		
		//LOS ASIGNAMOS
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setTotalNumberOfTools(totalNumberOfTools);
		result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
		result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
		result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);


		//COMPONENTS
		result.setAverageRetailPriceOfComponentsByTechnologyAndCurrency(avgRPriceOfComponentsByTechnologyAndCurrency);
		result.setDeviationRetailPriceOfComponentsByTechnologyAndCurrency(devRPriceOfComponentsByTechnologyAndCurrency);
		result.setMinimumRetailPriceOfComponentsByTechnologyAndCurrency(minRPriceOfComponentsByTechnologyAndCurrency);
		result.setMaximumRetailPriceOfComponentsByTechnologyAndCurrency(maxRPriceOfComponentsByTechnologyAndCurrency);


		//TOOLS
		result.setAverageRetailPriceOfToolsByCurrency(avgRPriceOfToolsByCurrency);
		result.setDeviationRetailPriceOfToolsByCurrency(devRPriceOfToolsByCurrency);
		result.setMinimumRetailPriceOfToolsByCurrency(minRPriceOfToolsByCurrency);
		result.setMaximumRetailPriceOfToolsByCurrency(maxRPriceOfToolsByCurrency);
		
		//PATRONAGES
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
		
		//ESTABLECEMOS EL ATRIBUTO CON LA ENTIDAD 
			//COMPONENTS
		model.setAttribute("avC", entity.getAverageRetailPriceOfComponentsByTechnologyAndCurrency().toString());
		model.setAttribute("devC", entity.getDeviationRetailPriceOfComponentsByTechnologyAndCurrency().toString());
		model.setAttribute("minC", entity.getMinimumRetailPriceOfComponentsByTechnologyAndCurrency().toString());
		model.setAttribute("maxC", entity.getMaximumRetailPriceOfComponentsByTechnologyAndCurrency().toString());
		
			//ITEMS
		model.setAttribute("avI", entity.getAverageRetailPriceOfToolsByCurrency().toString());
		model.setAttribute("devI", entity.getDeviationRetailPriceOfToolsByCurrency().toString());
		model.setAttribute("minI", entity.getMinimumRetailPriceOfToolsByCurrency().toString());
		model.setAttribute("maxI", entity.getMaximumRetailPriceOfToolsByCurrency().toString());
		
			//PATRONAGES
			
		
		model.setAttribute("avP", entity.getAverageBudgetOfPatronagesByStatus().toString());
		model.setAttribute("devP", entity.getDeviationBudgetOfPatronagesByStatus().toString());
		model.setAttribute("minP", entity.getMinimumBudgetOfPatronagesByStatus().toString());
		model.setAttribute("maxP", entity.getMaximumBudgetOfPatronagesByStatus().toString());
		
		
		
		
		
		

		request.unbind(entity, model, "totalNumberOfComponents","averageRetailPriceOfComponentsByTechnologyAndCurrency","deviationRetailPriceOfComponentsByTechnologyAndCurrency",
				"minimumRetailPriceOfComponentsByTechnologyAndCurrency","maximumRetailPriceOfComponentsByTechnologyAndCurrency",
				"totalNumberOfTools","averageRetailPriceOfToolsByCurrency","deviationRetailPriceOfToolsByCurrency","minimumRetailPriceOfToolsByCurrency","maximumRetailPriceOfToolsByCurrency",
				"totalNumberOfProposedPatronages","totalNumberOfAcceptedPatronages","totalNumberOfDeniedPatronages","averageBudgetOfPatronagesByStatus","deviationBudgetOfPatronagesByStatus","minimumBudgetOfPatronagesByStatus","maximumBudgetOfPatronagesByStatus"
				);

	}

}
