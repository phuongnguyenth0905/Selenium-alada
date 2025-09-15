package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T7_nopcommerce {
	WebDriver driver;
	String setURL, gender, firstname, lastname, email, password, confirmpassword, companyName;
	
	By genderRadio=By.xpath("//span[@class='female']/child::input");
	By firstNameTextbox=By.id("FirstName");
	By lastNameTextbox=By.id("LastName");
	By emailTextbox=By.id("Email");
	By passwordNameTextbox=By.id("Password");
	By confirmPasswordNameTextbox=By.id("ConfirmPassword");
	By companyNameTextbox=By.id("Company");

  
  @BeforeClass
  public void beforeClass() {
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(50));
	  driver.manage().window().maximize();
	  driver.get("https://demo.nopcommerce.com/register");
	  
	  gender="M";
	  firstname="NORMA";
	  lastname="LANCASTER";
	  email="testqa"+getRandomNumber()+"@hotmail.com";
	  password="Test12345#";
	  companyName="Mante and Sons";
	  
  }
  @Test
  public void TC1_register() {
	 // driver.getCurrentUrl();
	  driver.findElement(By.xpath("//a[text()='Register']")).click();
	  driver.findElement(genderRadio).click();
	  driver.findElement(firstNameTextbox).sendKeys(firstname);
	  driver.findElement(lastNameTextbox).sendKeys(lastname);
	  driver.findElement(emailTextbox).sendKeys(email);
	  driver.findElement(companyNameTextbox).sendKeys(companyName);
	  driver.findElement(passwordNameTextbox).sendKeys(password);
	  driver.findElement(confirmPasswordNameTextbox).sendKeys(password);
	  driver.findElement(By.id("register-button")).click();
	  System.out.println("First name: "+firstname+"\n"+"Last name: "+lastname+"\n"+"Email: "+email+"\n"+"Company name:  "+companyName);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Your registration completed']")).getText(), "Your registration completed");
	  
	  driver.findElement(By.className("ico-login")).click();
	  driver.findElement(emailTextbox).sendKeys(email);
	  driver.findElement(passwordNameTextbox).sendKeys(password);
	  driver.findElement(By.xpath("//button[text()='Log in']")).click();
	  driver.findElement(By.className("ico-account")).click();
	  driver.findElement(By.xpath("//li[@class='customer-addresses inactive']")).click();
	  driver.findElement(By.xpath("//button[text()='Add new']")).click();
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  public int getRandomNumber() {
	Random rand=new Random();
	return rand.nextInt(9999);
}

}
