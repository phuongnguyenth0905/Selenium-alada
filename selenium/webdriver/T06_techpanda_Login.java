package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T06_techpanda_Login {
	WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofMillis(40));
	  driver.manage().window().maximize();
	  
  }
  @Test
  public void TC1_LoginWithEmptyEmailAndPassword() {
	  driver.get("http://live.techpanda.org/");
	  driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("");
	  driver.findElement(By.id("pass")).sendKeys("");
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  
	  String emailErrorMessage=driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[text()='This is a required field.']")).getText();
	  Assert.assertEquals(emailErrorMessage, "This is a required field.");
	  String passErrorMessage=driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div[text()='This is a required field.']")).getText();
	  Assert.assertEquals(passErrorMessage, "This is a required field.");
	  
  }
  @Test
  public void TC2_LoginWithInvalidEmail() {
	  driver.get("http://live.techpanda.org/");
	  driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("1234@1234.123");
	  driver.findElement(By.id("pass")).sendKeys("123456");
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  String emailErrorMessage=driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[contains(text(), 'example johndoe@domain.com')]")).getText();
	  Assert.assertEquals(emailErrorMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	  
  }
  @Test
  public void TC3_LoginWithPasswordLessThan6Character() {
	  driver.get("http://live.techpanda.org/");
	  driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("johndoe@domain.com");
	  driver.findElement(By.id("pass")).sendKeys("123");
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  
	  String passErrorMessage=driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div[contains(text(), 'enter 6 or more characters')]")).getText();
	  Assert.assertEquals(passErrorMessage, "Please enter 6 or more characters without leading or trailing spaces.");
  }
  @Test
  public void TC4_LoginWithIncorrectEmailAndPassword() throws InterruptedException {
	  //automation@gmail.com
	  //123123123
	  driver.get("http://live.techpanda.org/");
	  driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("testauto123@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123456");
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  Thread.sleep(3000);
	  //driver.get("http://live.techpanda.org/index.php/customer/account/loginPost/");
	  driver.findElement(By.xpath("//button[contains(text(),'Send anyway')]")).click();
	    
	  String textHello=driver.findElement(By.xpath("//p[contains(@class,'hello')]/strong")).getText();
	  Assert.assertEquals(textHello, "Hello, auto test!");
	  //This is a required field.
	  //String emailErrorMessage=driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[text()='This is a required field.']")).getText();
	  //Assert.assertEquals(emailErrorMessage, "This is a required field.");
	 	  
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
