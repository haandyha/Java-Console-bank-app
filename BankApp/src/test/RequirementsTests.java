package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import application.FileEdit;

public class RequirementsTests {
	
	@Test
	public void testUserExistsMethodWithNonExistantUser() {
		FileEdit.saveUser("test", "word", "customer");
		assertFalse(FileEdit.doesUserExist("nick"));
	}
	
	@Test
	public void testUserExistsMethodWithExistingUser() {
		FileEdit.saveUser("test", "word", "customer");
		assertTrue(FileEdit.doesUserExist("test"));
	}
	
	@Test
	public void testLoginConfirmationWithNonExistantcredentials() {
		FileEdit.saveUser("test", "word", "customer");
		assertFalse(FileEdit.confirmLogin("fred", "pass"));
	}
	
	@Test
	public void testLoginConfirmationWithExistingUser() {
		FileEdit.saveUser("test", "word", "customer");
		assertTrue(FileEdit.confirmLogin("test", "word"));
	}
	
	@Test
	public void testCorrectAmountIsSavedToFileAfterDepositAction() {
		FileEdit.saveUser("test", "word", "customer");
		int acctNum = "test".hashCode();
		FileEdit.adjustBalance(acctNum, 100, 'd');
		assertEquals("100.0", FileEdit.readBalance(acctNum));
	}
	
	@Test
	public void testCorrectAmountIsSavedToFileAfterWithdrawAction() {
		FileEdit.saveUser("test", "word", "customer");
		int acctNum = "test".hashCode();
		FileEdit.adjustBalance(acctNum, 100, 'd');
		FileEdit.adjustBalance(acctNum, 50, 'w');
		assertEquals("50.0", FileEdit.readBalance(acctNum));
	}
}
