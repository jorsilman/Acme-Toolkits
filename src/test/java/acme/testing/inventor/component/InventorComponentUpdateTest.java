package acme.testing.inventor.component;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorComponentUpdateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/component/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, 
		final String code, final String technology, final String description, 
		final String retailPrice, final String link, final String type, final String published) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor", "List my components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update component");
		
		super.clickOnMenu("Inventor", "List my components");
		
		super.checkListingExists();
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("published", published);
		super.checkInputBoxHasValue("itemType", "COMPONENT");
		
		
		//super.clickOnSubmit("Delete component");
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/component/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String name, 
		final String code, final String technology, final String description, 
		final String retailPrice, final String link, final String type, final String published) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor", "List my components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update component");
		super.checkErrorsExist();
	}
}
