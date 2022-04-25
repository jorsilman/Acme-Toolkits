package acme.testing.any.userAccount;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class AnyUserAccountTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/userAccount/list.csv", encoding = "utf-8", numLinesToSkip = 4)
	@Order(10)
	public void positiveTest(final int recordIndex, final String username,final String name, 
			final String surname,final String email , 
			final String roles, final String rolesShow) {
		
		super.clickOnMenu("All","List User Accounts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, username);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, surname);
		super.checkColumnHasValue(recordIndex, 3, email);
		//super.checkColumnHasValue(recordIndex, 4, roles);
		


		
	/*	super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.email", email);
		super.checkInputBoxHasValue("roles", rolesShow);*/
	}
}