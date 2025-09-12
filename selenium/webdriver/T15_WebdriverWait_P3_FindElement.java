package webdriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T15_WebdriverWait_P3_FindElement {
	WebDriver driver;
	
  
  @BeforeClass
  public void beforeClass() {
	  //driver=new ChromeDriver();
	  driver=new FirefoxDriver();
	 
	  //driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));
	  driver.manage().window().maximize();
	  
  }
  @Test
  public void TC02_ImplicitWait_LessThan() {
	  driver.get("https://automationfc.github.io/dynamic-loading/");
	  //driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
	  
	  driver.findElement(By.xpath("//button[normalize-space()='Start']")).click();
	  sleepInseconds(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//h4[normalize-space()='Hello World!']")).isDisplayed());
  }
  @Test
  public void TC02_ImplicitWait_ThanOfEqual() {
	  driver.get("https://automationfc.github.io/dynamic-loading/");
	  //driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(6));
	  
	  driver.findElement(By.xpath("//button[normalize-space()='Start']")).click();
	  sleepInseconds(10);
	  Assert.assertTrue(driver.findElement(By.xpath("//h4[normalize-space()='Hello World!']")).isDisplayed());
	 
  }
  
  public void sleepInseconds(long time) {
	try {
		Thread.sleep(time*1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
