package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import acme.framework.datatypes.Money;


@Getter
@Setter
public class PatronDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

		protected static final long serialVersionUID = 1L;
		
		
	// Attributes -------------------------------------------------------------
	
	Integer				proposedPatronages;						
	Integer				acceptedPatronages;
	Integer				deniedPatronages;
	
	Money				averageProposedPatronages;
	Money				deviationProposedPatronages;
	Money				minimumBudgetProposedPatronages;
	Money				maximumBudgetProposedPatronages;
	
	Money				averageAcceptedPatronages;
	Money				deviationAcceptedPatronages;
	Money				minimumBudgetAcceptedPatronages;
	Money				maximumBudgetAcceptedPatronages;
	
	Money				averageDeniedPatronages;
	Money				deviationDeniedPatronages;
	Money				minimumBudgetDeniedPatronages;
	Money				maximumBudgetDeniedPatronages;
	
		
}
