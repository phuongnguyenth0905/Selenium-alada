package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class T05_PrioritySkip {
	WebDriver driver;
	
	@Test(enabled = false)
	  public void TC01_CreateUser() {
		 }
	 @Test(enabled = false, description = "User after created success")
	  public void TC02_ViewUser() {
		  
	  }
	  @Test(description = "User after created success")
	  public void TC03_EditUser() {
		 
	  }
	  @Test(priority = 4)
	  public void TC04_MoveUser() {
		 
	  }
	  @Test(priority = 5)
	  public void TC05_DeleteUser() {
		  
	  }
	  @Test(priority = 6)
	  public void TC01_Product() {
		  
	  }
 }
