package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(11)
	public void positive(final int recordIndex, final String sequenceNumber, final String memorandum, final String link, final String confirmation) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my patronages");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("Create report");
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create report");
		
		
		super.clickOnMenu("Inventor", "List my patronage reports");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, sequenceNumber);
		//super.checkColumnHasValue(recordIndex, 1, creationMoment);
		super.checkColumnHasValue(recordIndex, 2, memorandum);
		super.checkColumnHasValue(recordIndex, 3, link);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("sequenceNumber", sequenceNumber);
		//super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(12)
	public void negative(final int recordIndex, final String memorandum, final String link, final String confirmation) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my patronages");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("Create report");
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create report");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}
