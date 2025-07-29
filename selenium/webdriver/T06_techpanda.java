package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T06_techpanda {
 WebDriver driver;
  @BeforeClass
  public void beforeClass() {
	  driver= new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	  driver.manage().window().maximize();
  } 
  @Test
  public void TC01_VerifyUrl() {
	  driver.get("https://live.techpanda.org/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  //https://live.techpanda.org/index.php/customer/account/login/
	  String loginPageUrl = driver.getCurrentUrl();
	  Assert.assertEquals(loginPageUrl, "https://live.techpanda.org/index.php/customer/account/login/");
	  driver.findElement(By.xpath("//div[@class='buttons-set']//a")).click();
	  String urlRegisterPage= driver.getCurrentUrl();
	  Assert.assertEquals(urlRegisterPage, "https://live.techpanda.org/index.php/customer/account/create/");
	   }
  @Test
  public void TC02_VerifyTitle() {
	  driver.get("https://live.techpanda.org/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  //https://live.techpanda.org/index.php/customer/account/login/
	  String titleLoginPageUrl = driver.getTitle();
	  Assert.assertEquals(titleLoginPageUrl, "Customer Login");
	  driver.findElement(By.xpath("//div[@class='buttons-set']//a")).click();
	  String titleRegisterPage= driver.getTitle();
	  Assert.assertEquals(titleRegisterPage, "Create New Customer Account");
	  
  }
  @Test
  public void TC03_NavigateFunction() {
	 driver.get("http://live.techpanda.org/");
	 driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	 driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	 String urlRegisterPage= driver.getCurrentUrl();
	 Assert.assertEquals(urlRegisterPage, "https://live.techpanda.org/index.php/customer/account/create/");
	 driver.navigate().back();
	 String loginPageUrl = driver.getCurrentUrl();
	 Assert.assertEquals(loginPageUrl, "https://live.techpanda.org/index.php/customer/account/login/");
	 driver.navigate().forward();
	 String titleRegisterPage=driver.getTitle();
	 Assert.assertEquals(titleRegisterPage,"Create New Customer Account");
	 	 	 
  }
  @Test
  public void TC04_GetPageSourceCode() {
	  driver.get("http://live.techpanda.org/");	
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  //Login or Create an Account
	  String textLoginorCreateanAccount=driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText();
	  Assert.assertEquals(textLoginorCreateanAccount, "LOGIN OR CREATE AN ACCOUNT");
	  //CREATE AN ACCOUNT
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  String textCreateanAccount= driver.findElement(By.xpath("//div[@class='account-create']//h1")).getText();
			  
	  Assert.assertEquals(textCreateanAccount, "CREATE AN ACCOUNT");
  }
  @Test
  public void TC05() {
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
