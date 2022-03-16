package acme.forms;

import java.io.Serializable;

import acme.framework.datatypes.Money;

public class AdministratorDashboard implements Serializable{
		// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

		Integer				components;
		Money				averageComponents;
		Money				deviationComponents;
		Money				minimumRetailPriceComponents;
		Money				maximumRetailPriceComponents;
		
		Integer				tools;
		Money				averageTools;
		Money				deviationTools;
		Money				minimumRetailPriceTools;
		Money				maximumRetailPriceTools;
		
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

		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------
}
