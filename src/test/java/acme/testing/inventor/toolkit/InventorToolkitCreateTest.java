package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitCreateTest extends TestHarness {


	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, final String description, 
			final String assemblyNotes, final String published, final String link) {

		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Inventor", "List my toolkits");

		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, published);
		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		



		super.clickOnSubmit("Delete");
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String title, final String description, 
			final String assemblyNotes, final String published, final String link) {
	super.signIn("inventor2", "inventor2");

	super.clickOnMenu("Inventor", "List my toolkits");
	super.checkListingExists();
	super.clickOnButton("Create");
	super.checkFormExists();
	super.fillInputBoxIn("code", code);
	super.fillInputBoxIn("title", title);
	super.fillInputBoxIn("description", description);
	super.fillInputBoxIn("assemblyNotes", assemblyNotes);
	super.fillInputBoxIn("link", link);
	super.clickOnSubmit("Create");

	super.checkErrorsExist();

	super.signOut();
}

@Test
@Order(30)
public void hackingTest() {
	super.checkNotLinkExists("Account");
	super.navigate("/inventor/toolkit/create");
	super.checkPanicExists();

	super.signIn("administrator", "administrator");
	super.navigate("/inventor/toolkit/create");
	super.checkPanicExists();
	super.signOut();

	super.signIn("patron2", "patron2");
	super.navigate("/inventor/toolkit/create");
	super.checkPanicExists();
	super.signOut();

}
}
