package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, final String description, 
			final String assemblyNotes, final String published, final String link) {

		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		super.checkFormExists();
		
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Update");

		super.clickOnMenu("Inventor", "List my toolkits");
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		super.checkFormExists();
		

		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		



		super.clickOnSubmit("Delete");
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String description, 
			final String assemblyNotes, final String link) {
	super.signIn("inventor2", "inventor2");
	
	super.clickOnMenu("Inventor", "List my toolkits");
	super.checkListingExists();
	super.sortListing(0, "asc");
	super.clickOnListingRecord(0);
	super.checkFormExists();
	

	super.fillInputBoxIn("title", title);
	super.fillInputBoxIn("description", description);
	super.fillInputBoxIn("assemblyNotes", assemblyNotes);
	super.fillInputBoxIn("link", link);
	
	super.clickOnSubmit("Update");

	super.checkErrorsExist();

	super.signOut();
}

}
