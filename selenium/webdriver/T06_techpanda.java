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
	  String registerPage= driver.getCurrentUrl();
	  Assert.assertEquals(registerPage, "https://live.techpanda.org/index.php/customer/account/create/");
	   }
  @Test
  public void TC02_VerifyTitle() {
  }
  @Test
  public void TC03_NavigateFunction() {
  }
  @Test
  public void TC04_GetPageSourceCode() {
  }
  @Test
  public void TC05() {
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
