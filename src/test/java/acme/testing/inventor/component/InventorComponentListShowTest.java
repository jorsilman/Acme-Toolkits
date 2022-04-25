package acme.testing.inventor.component;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorComponentListShowTest extends TestHarness {
	
	// Test cases ---------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/component/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, 
		final String code, final String technology, final String description, final String retailPrice, final String link,final String type) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, retailPrice);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
		
	}

}
