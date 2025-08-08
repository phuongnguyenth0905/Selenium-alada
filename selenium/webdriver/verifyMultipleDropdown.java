package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class verifyMultipleDropdown {
	WebDriver driver;
	Select select;
  
  @BeforeClass
  public void beforeClass() {
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(50));
	  driver.manage().window().maximize();
	  
  }
  @Test
  public void TestCase() {
	  
  }
  @Test
	public void TC_01() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String testing[] = {"Manual", "Mobile", "Security", "Perfomance"};

		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		Assert.assertTrue(select.isMultiple());
		
		for (String value : testing) {
			select.selectByVisibleText(value);
			Thread.sleep(500);
		}

		List<WebElement> selectedOption = select.getAllSelectedOptions();
		Assert.assertEquals(selectedOption.size(), 4);
		
		List<String> actualValues = new ArrayList<String>();

		for (WebElement option : selectedOption) {
			actualValues.add(option.getText());
		}
		
		List<String> expectedValues = Arrays.asList(testing);
		
		Assert.assertEquals(actualValues, expectedValues);
	}
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
