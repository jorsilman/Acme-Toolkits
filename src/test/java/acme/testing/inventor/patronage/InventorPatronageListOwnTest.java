package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageListOwnTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list-own.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String status, final String statusShow, final String code, final String legalStuff, final String budget, final String creationDate, final String startPeriodOfTime, final String endPeriodOfTime, final String link, final String inventorCompany, final String inventorStatement, final String inventorLink) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, legalStuff);
		super.checkColumnHasValue(recordIndex, 3, budget);
		super.checkColumnHasValue(recordIndex, 4, creationDate);
		super.checkColumnHasValue(recordIndex, 5, startPeriodOfTime);
		super.checkColumnHasValue(recordIndex, 6, endPeriodOfTime);
		super.checkColumnHasValue(recordIndex, 7, link);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", statusShow);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startPeriodOfTime", startPeriodOfTime);
		super.checkInputBoxHasValue("endPeriodOfTime", endPeriodOfTime);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("inventorCompany", inventorCompany);
		super.checkInputBoxHasValue("inventorStatement", inventorStatement);
		super.checkInputBoxHasValue("inventorLink", inventorLink);


		super.signOut();
	}

}
