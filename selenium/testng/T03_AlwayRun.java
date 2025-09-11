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

public class T03_AlwayRun {
	WebDriver driver;
  @BeforeClass
  public void initBrowser() {
	  driver=new FirefoxDriver();
	  driver.get("https://www.facebook.com/");
	  Assert.assertTrue(false);
	 }
  @Test(groups = "user")
  public void TC01_CreateUser() {
	  
  }
  @Test(groups = "user")
  public void TC02_ViewUser() {
	  
  }
  @Test(groups = "user")
  public void TC03_EditUser() {
	 
  }
  @Test(groups = "user")
  public void TC04_MoveUser() {
	 
  }
  @Test(groups = "user")
  public void TC05_DeleteUser() {
	  
  }
  @Test(groups = "product")
  public void TC01_Product() {
	  
  }
  @AfterClass
  public void cleanBrowser() {
	  driver.quit();
  }
 }
