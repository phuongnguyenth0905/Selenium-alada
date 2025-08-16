package webdriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T09_Button_RadioButton_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	By loginButon=By.xpath("//button[@class='fhs-btn-login']");
	By registerButon=By.xpath("//div[@ht='click_register_mailSubmit']");
	
  @BeforeClass
  public void beforeClass() {
	  driver=new FirefoxDriver();
	  jsExcutor=(JavascriptExecutor) driver;
	  driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(50));
	  driver.manage().window().maximize();
	  
	  
  }
  
  public void TC01_Button_fahasa() {
	  driver.get("https://www.fahasa.com/customer/account/create");
	  driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
	  //verify login buton is disable
	  Assert.assertFalse(isElementEnabled(loginButon));
	  driver.findElement(By.id("login_username")).sendKeys("thanhnam7623@outlook.com");
	  driver.findElement(By.id("login_password")).sendKeys("123456");
	  ////verify login buton is enabled
	  Assert.assertTrue(isElementEnabled(loginButon));
	  
	  driver.navigate().refresh();
	  driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
	  Assert.assertFalse(isElementEnabled(loginButon));
	  
	  driver.navigate().refresh();
	  driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
	  Assert.assertFalse(isElementEnabled(loginButon));
	  
	  //remove disabled attribute of login button
	  removeDisableAttributeByJS(loginButon);
	  sleepInSecond(3);
	  
	  Assert.assertTrue(isElementEnabled(loginButon));
	  driver.findElement(loginButon).click();
	  sleepInSecond(3);
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	  Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
  }
  
  public void TC01_Button_huawei() {
	  driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
	  Assert.assertTrue(isElementEnabled(registerButon));
	  sleepInSecond(3);
	  removeDisableAttributeByJS(registerButon);
	  sleepInSecond(3);
	  Assert.assertTrue(isElementEnabled(registerButon));
	  driver.findElement(registerButon).click();
	  sleepInSecond(3);
	  Assert.assertTrue(isElementEnabled(registerButon));
  }
  
  public void TC02_DefaultCheckboxRadio_telerik() {
	  driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	  //Default
	  WebElement dualZoneCheckbox=driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/child::input"));
	  dualZoneCheckbox.click();
	  sleepInSecond(3);
	  Assert.assertTrue(isElementSelected(dualZoneCheckbox));
	  
	  //radio
	  driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
	  WebElement petrol2Radio=driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/child::input"));
	  petrol2Radio.click();
	  sleepInSecond(3);
	  Assert.assertTrue(isElementSelected(petrol2Radio));
	  
  }
  
  public void TC02_CheckboxRadio_Custom() {
	  driver.get("https://material.angular.io/components/radio/examples");
	  WebElement autumRadioInput=driver.findElement(By.xpath("//label[text()='Summer']/preceding-sibling::div"));
	  clickByJS(autumRadioInput);
	  sleepInSecond(3);
	  Assert.assertTrue(isElementSelected(autumRadioInput));
	  
  }
  
  public void TC03_CheckboxRadio_Custom_() {
	  driver.get("https://material.angular.io/components/checkbox/examples");
	  //Checked
	  WebElement checkedCheckboxInput=driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/child::input"));
	  clickByJS(checkedCheckboxInput);
	  sleepInSecond(3);
	  Assert.assertTrue(isElementSelected(checkedCheckboxInput));
	  clickByJS(checkedCheckboxInput);
	  sleepInSecond(3);
	  Assert.assertFalse(isElementSelected(checkedCheckboxInput));
	  //Indeterminate
	  WebElement indeterminateCheckboxInput=driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/child::input"));
	  clickByJS(indeterminateCheckboxInput);
	  sleepInSecond(3);
	  Assert.assertTrue(isElementSelected(indeterminateCheckboxInput));
	  clickByJS(indeterminateCheckboxInput);
	  sleepInSecond(3);
	  Assert.assertFalse(isElementSelected(indeterminateCheckboxInput));
  }
  
  public void TC04_SelectAllCheckbox() {
	driver.get("https://automationfc.github.io/multiple-fields/");
	List<WebElement> checkboxsAll=driver.findElements(By.xpath("//input[@type='checkbox']"));
	//check
	for (WebElement checkbox : checkboxsAll) {
		if (!isElementSelected(checkbox)) {
			checkbox.click();
			//sleepInSecond(1);
		}
		Assert.assertTrue(isElementSelected(checkbox));
	}
	//uncheck
	for (WebElement checkbox : checkboxsAll) {
		if (isElementSelected(checkbox)) {
			checkbox.click();
			//sleepInSecond(1);
		}
		Assert.assertFalse(isElementSelected(checkbox));
	}
	WebElement heartAttackCheckboxInput=driver.findElement(By.xpath("//label[contains(text(),'Heart Attack')]/preceding-sibling::input"));
	heartAttackCheckboxInput.click();
	 Assert.assertTrue(isElementSelected(heartAttackCheckboxInput));
	 sleepInSecond(2);
}
  
  public void TC05_ubuntu_RadioCustom() {
	driver.get("https://login.ubuntu.com/");
	WebElement newUserRadioInput=driver.findElement(By.xpath("//input[@id='id_new_user']"));
	clickByJS(newUserRadioInput);
	sleepInSecond(2);
	Assert.assertTrue(isElementSelected(newUserRadioInput));
	//
	WebElement acceptCheckboxInput=driver.findElement(By.id("id_accept_tos"));
	clickByJS(acceptCheckboxInput);
	sleepInSecond(2);
	Assert.assertTrue(isElementSelected(acceptCheckboxInput));
}
  @Test
public void TC06_CustomCheckboxRadio() {
	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
	WebElement canthoRadioButonInput=driver.findElement(By.xpath("//span[text()='Cần Thơ']//ancestor::div[@class='YEVVod']"));
	Assert.assertFalse(isElementSelected(canthoRadioButonInput));
	sleepInSecond(3);
	clickByJS(canthoRadioButonInput);
	sleepInSecond(2);
	Assert.assertFalse(isElementSelected(canthoRadioButonInput));
}
public void TC07() {
	
}
  //common
  public boolean isElementEnabled(By by) {
	WebElement element=driver.findElement(by);
	if (element.isEnabled()) {
		System.out.println("Element is enabled: "+by);
		return true;
	} else {
		System.out.println("Element is Disabled: "+by);
		return false;
	}
}
  public boolean isElementSelected(By by) {
		WebElement element=driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is enabled: "+by);
			return true;
		} else {
			System.out.println("Element is Disabled: "+by);
			return false;
		}
	}
  public boolean isElementSelected(WebElement element) {		
		if (element.isSelected()) {
			System.out.println("Element is Selected");
			return true;
		} else {
			System.out.println("Element is de-selected");
			return false;
		}
	}
  public void clickByJS(By by) {
	  WebElement element=driver.findElement(by);
	  jsExcutor.executeScript("arguments[0].click();", element);
	  
}
  public void clickByJS(WebElement element) {
	  jsExcutor.executeScript("arguments[0].click();", element);
	  
}
  public void removeDisableAttributeByJS(By by) {
	  WebElement element=driver.findElement(by);
	  jsExcutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	  jsExcutor.executeScript("arguments[0].removeAttribute('hwid-disabled')", element);
	  
}
  public void sleepInSecond(long time) {
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
