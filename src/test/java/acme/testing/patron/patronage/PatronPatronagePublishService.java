package acme.testing.patron.patronage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronagePublishService extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code) {
		
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "List my patronages");
		super.checkListingExists();
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.clickOnMenu("Patron", "List my patronages");
		super.checkListingExists();
		super.checkColumnHasValue(0, 8, "true");

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, 
		final String legalStuff, final String budget, final String link, final String published) {
		
		super.signIn("patron3", "patron3");
		
		super.clickOnMenu("Patron", "List my patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Publish");
		super.checkErrorsExist();
		super.signOut();
	
	}

}
