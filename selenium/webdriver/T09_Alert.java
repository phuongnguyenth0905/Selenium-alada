package webdriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T09_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	By resultText=By.xpath("//p[@id='result']");
	String projectFolder=System.getProperty("user.dir");
  
  @BeforeClass
  public void beforeClass() {
	  driver=new FirefoxDriver();
	  explicitWait=new WebDriverWait(driver, Duration.ofMinutes(30));
	  driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(50));
	  driver.manage().window().maximize();
	  
	  }
  
  public void TC01_AcceptAlert() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");;
	  driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	  sleepInSecond(2);
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  alert=driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "I am a JS Alert");
	  alert.accept();
	  Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");
  }
  
  public void TC02_ConfirmAlert() {//yes/No
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  alert=driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	  alert.dismiss();
	  sleepInSecond(2);
	  Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
  sleepInSecond(2);
  }
  
  public void TC03_PromptAlert() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  String inputText="Alert susscessfull";
	  sleepInSecond(2);
	  driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	  sleepInSecond(2);
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  alert=driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "I am a JS prompt");
	  alert.sendKeys(inputText);
	  alert.accept();
	  sleepInSecond(2);
	  Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: Alert susscessfull");
	  
  }
  
  public void TC04_AuthencationAlertByPassUrl() {
	  driver.get("http://the-internet.herokuapp.com/");
	  String basicAuthenlink=driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
	  sleepInSecond(2);
	  System.out.println(basicAuthenlink);
	  sleepInSecond(3);
	  inputUserAndPasswordToHref(basicAuthenlink, "admin", "admin");
	  sleepInSecond(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
  }
  @Test
  public void TC04_AuthencationAlert_AutoIT() throws IOException   {
	 String username="admin";
	 String password="admin";
	  driver.get("http://the-internet.herokuapp.com/");
	 String fifoxAuthen=projectFolder+"\\autoIT\\authen_firefox.exe";
	  
	  // Find the Basic Auth link and click it to trigger the alert.
      driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();
      // Execute the AutoIt script to handle the authentication dialog.
      Runtime.getRuntime().exec(new String[] {fifoxAuthen,username, password});
      // Pause to allow AutoIt to run.
      sleepInSecond(6);
      Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
  }
  public void sleepInSecond(long time) {
	 try {
		 Thread.sleep(time*1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
 //common
  public void inputUserAndPasswordToHref(String href, String user, String pass) {
	String[] hrefSplit=href.split("//");
	href=hrefSplit[0]+"//"+user+":"+pass+"@"+hrefSplit[1];
	driver.get(href);
}
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
