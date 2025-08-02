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

public class T06_techpanda_Element {
	WebDriver driver;
	//Khai bao va gan gia tri
	By emailTextboxBy=By.id("mail");
	By ageUnderRadioBy=By.id("under_18");
	By educationTextAreaBy=By.id("edu");
	By user5TextBy=By.xpath("//h5[text()='Name: User5']");
	By javaCheckboBy=By.id("java");
  
	
	By passwordTextboxBy=By.id("password");
	By bioTextAreaBy=By.id("bio");
  @BeforeClass
  public void beforeClass() {
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));
	  driver.manage().window().maximize();
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  
  }
  public boolean isElementDisplayed(By by) {
	  WebElement element=driver.findElement(by);
	  if (element.isDisplayed()) {
		System.out.println("Element is Diplayed: "+by);
		return true;
	} else {
		System.out.println("Element is not Displayed: "+by);
		return false;
	}
  }
  public boolean isElementEnable(By by) {
	  WebElement element=driver.findElement(by);
	  if (element.isEnabled()) {
		System.out.println("Element is Enable: "+by);
		return true;
	} else {
		System.out.println("Element is  Disenable: "+by);
		return false;
	}
  }
  public boolean isElementSelected(By by) {
	  WebElement element=driver.findElement(by);
	  if (element.isSelected()) {
		System.out.println("Element is Selected: "+by);
		return true;
	} else {
		System.out.println("Element is  di-selected: "+by);
		return false;
	}
  }
  public void sendkeyToElement(By by, String value) {
	WebElement element=driver.findElement(by);
	element.clear();
	element.sendKeys(value);
}
  public void clickToElement(By by) {
	WebElement element=driver.findElement(by);
	element.click();
}
  @Test
  public void TC1_isDisplayed() {
	  if (isElementDisplayed(emailTextboxBy)) {
		sendkeyToElement(emailTextboxBy, "Automation Testing");
	}
	  if (isElementDisplayed(ageUnderRadioBy)) {
			clickToElement(ageUnderRadioBy);
		}
	  if (isElementDisplayed(educationTextAreaBy)) {
			sendkeyToElement(educationTextAreaBy, "Automation Testing");
		}
	  Assert.assertFalse(isElementDisplayed(user5TextBy));
  }
  @Test
  public void TC2_isEnabled() {
	 driver.navigate().refresh();
	 //
	 Assert.assertTrue(isElementEnable(emailTextboxBy));
	 Assert.assertTrue(isElementEnable(ageUnderRadioBy));
	 Assert.assertTrue(isElementEnable(educationTextAreaBy));
	 
	 //
	 Assert.assertTrue(isElementEnable(passwordTextboxBy));
	 Assert.assertFalse(isElementEnable(bioTextAreaBy));
	  
  }
  @Test
  public void TC3_isSelected() {
	  driver.navigate().refresh();
	  clickToElement(ageUnderRadioBy);
	  clickToElement(javaCheckboBy);
	  //
	  Assert.assertTrue(isElementSelected(ageUnderRadioBy));
	  Assert.assertTrue(isElementSelected(javaCheckboBy));
	  //
	  clickToElement(javaCheckboBy);
	  Assert.assertTrue(isElementSelected(ageUnderRadioBy));
	  Assert.assertFalse(isElementSelected(javaCheckboBy));
	  
  }
 
  public void TC4_isDisplayed() {
	  
  }
  
  public void TC5_isDisplayed() {
	  
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
	  
  }

}
