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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T08_Customer_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;
	Select select;
	String[] months={"August","March","November"};
	String[] newMonths={"February","July","October","January","May"};
  
  @BeforeClass
  public void beforeClass() {
	  driver=new FirefoxDriver();
	  explicitWait=new WebDriverWait(driver, Duration.ofMinutes(30));
	  jsExcutor=(JavascriptExecutor) driver;
	  driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
	  driver.manage().window().maximize();
	 
	  
  }
  
  public void TC01_jqueryui() {
	  driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
	  selectTheItemInEdittableDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li", "4");
	  sleepInSecond(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[text()='4']")).isDisplayed());
	  
	 selectTheItemInEdittableDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li", "10");
	 sleepInSecond(3);
	 Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[text()='10']")).isDisplayed());
	 
	 selectTheItemInEdittableDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li", "19");
	 sleepInSecond(3);
	 Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[text()='19']")).isDisplayed());
	 
	 selectTheItemInEdittableDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li", "1");
	 sleepInSecond(3);
	 Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[text()='1']")).isDisplayed());
  }
  
  public void TC02_ReactJS() {
	  driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
	  selectTheItemInEdittableDropdown("//input[@class='search']", "//div[@role='option']/span", "Christian");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Christian");
	  
	  selectTheItemInEdittableDropdown("//input[@class='search']", "//div[@role='option']/span", "Justen Kitsune");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Justen Kitsune");
	  
	  selectTheItemInEdittableDropdown("//input[@class='search']", "//div[@role='option']/span", "Elliot Fu");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Elliot Fu");
	  
	  selectTheItemInEdittableDropdown("//input[@class='search']", "//div[@role='option']/span", "Matt");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Matt");
	  
	 selectTheItemInEdittableDropdown("//input[@class='search']", "//div[@role='option']/span", "Stevie Feliciano");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Stevie Feliciano");
	  
  }
  
  public void TC04_Editable_Flag() {
	  driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
	  selectTheItemInEdittableDropdown("//input[@class='search']", "//div[@role='option']/span", "American Samoa");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"American Samoa");
	  
	  selectTheItemInEdittableDropdown("//input[@class='search']", "//div[@role='option']/span", "Argentina");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Argentina");
	  
	  selectTheItemInEdittableDropdown("//input[@class='search']", "//div[@role='option']/span", "Belize");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Belize");
	  
	  selectTheItemInEdittableDropdown("//input[@class='search']", "//div[@role='option']/span", "Albania");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Albania");
	  
	 selectTheItemInEdittableDropdown("//input[@class='search']", "//div[@role='option']/span", "Azerbaijan");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Azerbaijan");
	  
  }
  
  public void TC05_huawei() {
	driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
	selectTheItemInEdittableDropdown("(//span[@class='hwid-select-text'])[1]", "//span[@class='list-item-text']" , "Bangladesh");
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='hwid-select-text'])[1]")).getText(),"Bangladesh");
	  
	selectTheItemInEdittableDropdown("(//span[@class='hwid-select-text'])[1]", "//span[@class='list-item-text']" , "Vietnam");
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='hwid-select-text'])[1]")).getText(),"Vietnam");
}
  
  public void TC05_honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		//Chọn xe
		selectTheItemInEdittableDropdown("btn dropdown-toggle", "//a[@class='dropdown-item']" , "CR-V G (Trắng ngà)");
		sleepInSecond(3);
		//Assert.assertEquals(driver.findElement(By.xpath("btn dropdown-toggle")).getText(),"CR-V G (Trắng ngà)");
		//Tỉnh/ TP
		//selectTheItemInEdittableDropdown("(//div[@class='sl-dt'])[2]", "//select[@name='province']" , "Bắc Ninh");
		///sleepInSecond(3);
		//Assert.assertEquals(driver.findElement(By.xpath("//select[@name='province']")).getText(),"Bắc Ninh");
	//Khu vực
		//selectTheItemInEdittableDropdown("(//div[@class='sl-dt'])[3]", "//select[@name='registration_fee']" , "Khu vực III");
		//sleepInSecond(3);
		//Assert.assertEquals(driver.findElement(By.xpath("//a[@class='dropdown-item']")).getText(),"Khu vực III");
	
	}
  @Test
  public void TC05_() {
		driver.get("https://sps.finpeace.vn/tools/sktccn");
		selectTheItemInEdittableDropdown("//input[@id='job_id']", "//span[@class='ant-select-selection-item']", "Bất động sản");
	}
	  
  
 public void TC03_VueJS() {
	  driver.get("https://mikerodham.github.io/vue-dropdowns/");
	  
	  selectTheItemInEdittableDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Second Option");
	  sleepInSecond(2);
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"Second Option");
	  
	  selectTheItemInEdittableDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Third Option");
	  sleepInSecond(2);
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"Third Option");
	  
	  selectTheItemInEdittableDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","First Option");
	  sleepInSecond(2);
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"First Option");
	  
  }
 
 public void TC04_Editable() {
	  driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
	  
 }
 
 public void TC05_MultiSelect() {
	  driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
	  
	  selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//span", months);
	  sleepInSecond(2);
	  //Assert.assertTrue(areItemSelected(months));
	  driver.navigate().refresh();
	  
	  selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//span", newMonths);
	  sleepInSecond(2);
	 // Assert.assertTrue(areItemSelected(newMonths));
	  sleepInSecond(2);
 }
 public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
	 driver.findElement(By.xpath(parentXpath)).click();
	 explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
	 List<WebElement> allItems=driver.findElements(By.xpath(childXpath));
	 for (WebElement childElement: allItems) {
		for (String item : expectedValueItem) {
			if (childElement.getText().equals(item)) {
				jsExcutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				sleepInSecond(1);
				childElement.click();
				sleepInSecond(1);
				
				List<WebElement> itemSelected=driver.findElements(By.xpath("//li[@class='selected']//input"));
				System.out.println("Item selected= "+itemSelected.size());
				if (expectedValueItem.length==itemSelected.size()) {
					break;
				}
			}
		}
	}
	
}
 public boolean areItemSelected(String[] months) {
	List<WebElement> itemSelected=driver.findElements(By.xpath("//li[@class='selected']//input"));
	int numberItemSelected=itemSelected.size();
	
	String allItemSelectedText=driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
	System.out.println("Text da chon: "+allItemSelectedText);
	if (numberItemSelected<=3 && numberItemSelected>0) {
		boolean status=true;
		for(String item:months) {
			if (!allItemSelectedText.contains(item)) {
				status=false;
				return status;
			}
			
		}
		return status;
	}else if (numberItemSelected>=12) {
		return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
	}else if (numberItemSelected>3 && numberItemSelected<12) {
		return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='\" + numberItemSelected + \" of 12 selected']")).isDisplayed();
	} else {
		return false;
	}
}
  public void selectTheItemInEdittableDropdown(String parentXpath, String childXpath, String expectedItem) {
	driver.findElement(By.xpath(parentXpath)).click();
	sleepInSecond(2);
	explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
	List<WebElement> childItems=driver.findElements(By.xpath(childXpath));
	for (WebElement actualItem : childItems) {
		if (actualItem.getText().trim().equals(expectedItem)) {
			jsExcutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
			sleepInSecond(3);
			actualItem.click();
			break;
		}
	}
}

  public void sleepInSecond(long second) {
	try {
		Thread.sleep(second*1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
