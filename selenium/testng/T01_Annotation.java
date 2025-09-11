package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class T01_Annotation {
  @Test
  public void TC01() {
	  System.out.println("Run Test 1");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Run BeforeMethod 2");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Run AfterMethod 3");
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Run BeforeClass 4");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Run AfterClass 5");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Run BeforeTest 6");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Run AfterTest 7");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Run BeforeSuite 8");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("Run AfterSuite 9");
  }

}
