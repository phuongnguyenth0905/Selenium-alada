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

public class T09_Depency {
	String fullName="Automation Testing";
  @Test
  public void TC01_CreateUser() {
	  Assert.assertNotNull(fullName);
	  //Test này failed nếu như nó không tạo được user thì lấy gì để : view/edit/move/delete
  }
  @Test(dependsOnMethods = "TC01_CreateUser")
  public void TC02_ViewUser() {
	  
  }
  @Test(dependsOnMethods = "TC01_CreateUser")
  public void TC03_EditUser() {
	  Assert.assertNull(fullName);
  }
  @Test(dependsOnMethods = "TC01_CreateUser")
  public void TC04_MoveUser() {
	  System.out.println("Run BeforeMethod 2");
  }
  @Test(dependsOnMethods = { "TC01_CreateUser","TC03_EditUser"})
  public void TC05_DeleteUser() {
	  
  }
 }
