package acme.testing.patron.patronage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronageCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, 
		final String legalStuff, final String budget, final String link, final String published) {
		
		super.signIn("patron5", "patron5");
		
		super.clickOnMenu("Patron", "Create a patronage");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Patron", "List my patronages");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, legalStuff);
		super.checkColumnHasValue(recordIndex, 3, budget);
		super.checkColumnHasValue(recordIndex, 7, link);
		super.checkColumnHasValue(recordIndex, 8, published);
		super.clickOnListingRecord(recordIndex);
		
		
		super.checkFormExists();
				
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("published", "false");
		
		
		super.clickOnSubmit("Delete");
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, 
		final String legalStuff, final String budget, final String link, final String published) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron", "Create a patronage");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		super.signOut();
	
	}

}
