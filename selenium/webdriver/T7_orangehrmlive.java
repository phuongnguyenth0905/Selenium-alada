package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T7_orangehrmlive {
	
    WebDriverWait wait;
	WebDriver driver;
	String firstName, middleName, lastName, employeeId, userName, password, confirmPassword, commentImmigration, numberImmigration;
	
	By firstNameTextbox=By.name("firstName");
	By middleNameTextbox=By.name("middleName");
	By lastNameTextbox=By.name("lastName");
	By employeeIdTextbox=By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
	By userNameTextbox=By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
	By passwordTextbox=By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input");
	By confirmPasswordTextbox=By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input");
	By commentTextbox=By.xpath("//label[text()='Comments']//parent::div/following-sibling::div//textarea");
	By numberTextbox=By.xpath("//label[text()='Number']//parent::div/following-sibling::div//input");
	
	
  
  @BeforeClass
  public void beforeClass() {
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(50));
	  driver.manage().window().maximize();
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	  
      wait=new WebDriverWait(driver, Duration.ofSeconds(10));
 	  
	  firstName="Nathaniel";
	  middleName="B";
	  lastName="Smith";
	  employeeId="0"+getRandomNumber();
	  userName="janeblue"+getRandomNumber();
	  password="Test123@";
	  confirmPassword="Test123@";
	  commentImmigration="Eligible Status Check";
	  numberImmigration="8976893466";
	  
  }
  @Test
  public void TestCase() throws InterruptedException {
	  driver.getCurrentUrl();
	  driver.findElement(By.name("username")).sendKeys("Admin");
	  driver.findElement(By.name("password")).sendKeys("admin123");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  driver.findElement(By.xpath("//span[text()='PIM']")).click();
	  driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
	  
	  driver.findElement(firstNameTextbox).sendKeys(firstName);
	  driver.findElement(middleNameTextbox).sendKeys(middleName);
	  driver.findElement(lastNameTextbox).sendKeys(lastName);
	  WebElement employeeIdField = driver.findElement(employeeIdTextbox);
	  employeeIdField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	  employeeIdField.sendKeys(Keys.DELETE);

	  employeeIdField.sendKeys(employeeId);
	  
	  driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div[@class='oxd-form-row user-form-header']/self::div//label")).click();
	 
	  driver.findElement(userNameTextbox).sendKeys(userName);
	  driver.findElement(passwordTextbox).sendKeys(password);
	  driver.findElement(confirmPasswordTextbox).sendKeys(confirmPassword);
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  //Thread.sleep(10000);
	  //Verify at Personal Details
	  
	  
	  Assert.assertEquals(driver.findElement(firstNameTextbox).getAttribute("value"), firstName);
	  Thread.sleep(3000);
	  Assert.assertEquals(driver.findElement(middleNameTextbox).getAttribute("value"), middleName);
	  Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"), lastName);
		
	  
	  driver.findElement(By.xpath("//a[text()='Immigration']")).click();
	  driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//parent::div//child::i")).click();
	  driver.findElement(numberTextbox).sendKeys(numberImmigration);
	  driver.findElement(commentTextbox).sendKeys(commentImmigration);
	  driver.findElement(By.xpath("//div[@class='oxd-form-actions']//child::button[2]")).click();
	  //edit Edit Immigration
	  driver.findElement(By.xpath("//div[text()='Passport']/ancestor::div[@class='oxd-table-row oxd-table-row--with-border']//child::button[2]")).click();
	  
	  WebElement numberInput = wait.until(ExpectedConditions.presenceOfElementLocated(numberTextbox));
	  wait.until(ExpectedConditions.attributeToBeNotEmpty(numberInput, "value"));
	  Assert.assertEquals(numberInput.getAttribute("value"), numberImmigration);
	  
	  WebElement commentInput = wait.until(ExpectedConditions.presenceOfElementLocated(commentTextbox));
	  wait.until(ExpectedConditions.attributeToBeNotEmpty(commentInput, "value"));
	  Assert.assertEquals(commentInput.getAttribute("value"), commentImmigration);
	  
	  //logout
	  driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
	  driver.findElement(By.xpath("//a[text()='Logout']")).click();
	  
	  //login username
	  driver.findElement(By.name("username")).sendKeys(userName);
	  driver.findElement(By.name("password")).sendKeys(password);
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  driver.findElement(By.xpath("//span[text()='My Info']")).click();
	  
	  WebElement firstNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(firstNameTextbox));
	  wait.until(ExpectedConditions.attributeToBeNotEmpty(firstNameInput, "value"));
	  Assert.assertEquals(firstNameInput.getAttribute("value"), firstName);
	  //Assert.assertEquals(driver.findElement(firstNameTextbox).getAttribute("value"), firstName);
	  
	  Assert.assertEquals(driver.findElement(middleNameTextbox).getAttribute("value"), middleName);
	  Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"), lastName);
	  
	  
	  WebElement employeeIdInput = wait.until(ExpectedConditions.presenceOfElementLocated(employeeIdTextbox));
	  wait.until(ExpectedConditions.attributeToBeNotEmpty(employeeIdInput, "value"));
	  Assert.assertEquals(employeeIdInput.getAttribute("value"), employeeId);
	  // Assert.assertEquals(driver.findElement(employeeIdTextbox).getAttribute("value"), employeeId);
		
	  driver.findElement(By.xpath("//a[text()='Immigration']")).click();
	  driver.findElement(By.xpath("//div[text()='Passport']/ancestor::div[@class='oxd-table-row oxd-table-row--with-border']//child::button[2]")).click();
	  
	 
	  WebElement numberInput2 = wait.until(ExpectedConditions.presenceOfElementLocated(numberTextbox));
	  wait.until(ExpectedConditions.attributeToBeNotEmpty(numberInput2, "value"));
	  Assert.assertEquals(numberInput2.getAttribute("value"), numberImmigration);
	  
	  WebElement commentInput2 = wait.until(ExpectedConditions.presenceOfElementLocated(commentTextbox));
	  wait.until(ExpectedConditions.attributeToBeNotEmpty(commentInput2, "value"));
	  Assert.assertEquals(commentInput2.getAttribute("value"), commentImmigration);
	 
	  
	  
  }
  public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
