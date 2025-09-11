package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class T04_Assertion {
	@Test
	public void TC01() {
		String fullName = "Automation Testing";
		String address = null;
		// Verify condition istrue
		Assert.assertTrue(fullName.contains("Testing"));
		Assert.assertTrue(fullName.contains("Web ap"));
		Assert.assertTrue(fullName.contains("Manual"));
		Assert.assertTrue(fullName.contains("Automation Testing"));
		Assert.assertNull(address);
		Assert.assertNull(fullName);
	}
}
