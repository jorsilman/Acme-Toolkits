package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageUpdateTest extends TestHarness {
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String legalStuff,final String budget,final String startPeriodOfTime,final String endPeriodOfTime,
			final String link) {

		super.signIn("patron2", "patron2");

		super.clickOnMenu("Patron", "List my patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		
		super.fillInputBoxIn("legalStuff", legalStuff );
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startPeriodOfTime", startPeriodOfTime);
		super.fillInputBoxIn("endPeriodOfTime", endPeriodOfTime);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Update");

		super.clickOnMenu("Patron", "List my patronages");
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		

		
		
		super.checkInputBoxHasValue("legalStuff", legalStuff );
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("startPeriodOfTime", startPeriodOfTime);
		super.checkInputBoxHasValue("endPeriodOfTime", endPeriodOfTime);
		super.checkInputBoxHasValue("link", link);
		



		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex,final String legalStuff,final String budget,final String startPeriodOfTime,final String endPeriodOfTime,
			final String link) {
	super.signIn("patron2", "patron2");
	
	super.clickOnMenu("Patron", "List my patronages");
	super.checkListingExists();
	super.sortListing(1, "asc");
	super.clickOnListingRecord(0);
	super.checkFormExists();
	

	super.fillInputBoxIn("legalStuff", legalStuff );
	super.fillInputBoxIn("budget", budget);
	super.fillInputBoxIn("startPeriodOfTime", startPeriodOfTime);
	super.fillInputBoxIn("endPeriodOfTime", endPeriodOfTime);
	super.fillInputBoxIn("link", link);
	
	super.clickOnSubmit("Update");

	super.checkErrorsExist();

	super.signOut();
}
}
