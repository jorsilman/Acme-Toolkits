package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import acme.entities.patronage.PatronageStatus;
import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatronDashboard implements Serializable{

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;


	// Attributes -------------------------------------------------------------

	int                                                 totalNumberOfProposedPatronages;
	int                                                 totalNumberOfAcceptedPatronages;
	int                                                 totalNumberOfDeniedPatronages;

	Map<Pair<String, PatronageStatus>, Double>          averageBudgetOfPatronagesByCurrencyAndStatus;
	Map<Pair<String, PatronageStatus>, Double>          deviationBudgetOfPatronagesByCurrencyAndStatus;
	Map<Pair<String, PatronageStatus>, Double>          minimumBudgetOfPatronagesByCurrencyAndStatus;
	Map<Pair<String, PatronageStatus>, Double>          maximumBudgetOfPatronagesByCurrencyAndStatus;
	// Map<Pair<Money.currency, Patronage.patronageStatus>, Money.amount>
	

}
