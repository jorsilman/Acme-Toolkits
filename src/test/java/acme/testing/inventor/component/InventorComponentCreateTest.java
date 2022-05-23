package acme.testing.inventor.component;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorComponentCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/component/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, 
		final String code, final String technology, final String description, 
		final String retailPrice, final String link, final String type, final String published) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor", "List my components");
		super.checkListingExists();
		super.clickOnButton("Create a new component");
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create component");
		
		super.clickOnMenu("Inventor", "List my components");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, retailPrice);
		super.checkColumnHasValue(recordIndex, 2, published);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("published", "false");
		super.checkInputBoxHasValue("itemType", "COMPONENT");
		
		
		super.clickOnSubmit("Delete component");
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/component/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String name, 
		final String code, final String technology, final String description, 
		final String retailPrice, final String link, final String type, final String published) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor", "List my components");
		super.checkListingExists();
		super.clickOnButton("Create a new component");
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create component");
		super.checkErrorsExist();
		
		super.signOut();
	}
		
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/item/create-component");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/inventor/item/create-component");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("patron2", "patron2");
		super.navigate("/inventor/item/create-component");
		super.checkPanicExists();
		super.signOut();
		
	}

}
