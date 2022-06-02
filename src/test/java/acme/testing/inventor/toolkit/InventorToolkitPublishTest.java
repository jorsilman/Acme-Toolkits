package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitPublishTest extends TestHarness{

	@Test
	@Order(10)
	public void positiveTest() {
		super.signIn("inventor2", "inventor2");
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(0, 2, "true");

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String title, final String description, 
			final String assemblyNotes, final String published, final String link) {
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
	
	super.clickOnSubmit("Publish");

	super.checkErrorsExist();

	super.signOut();
}
}
