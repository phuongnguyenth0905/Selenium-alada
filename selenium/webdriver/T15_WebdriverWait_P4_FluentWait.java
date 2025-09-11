package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T15_WebdriverWait_P4_FluentWait {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		// driver=new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC01_NotFoundOnlyImplicit() {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
		System.out.println("TC01_START: " + getDateTimeSecond());
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='reg_form_box']")).isDisplayed());
		} catch (Exception e) {
			System.out.println("go to catch ");
		}
		System.out.println("TC_01_END: " + getDateTimeSecond());
	}

	@Test
	public void TC02_FoundImplicitWait_explicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
		explicitWait = new WebDriverWait(driver, Duration.ofMillis(7));
		System.out.println("TC02_START - Implicit: " + getDateTimeSecond());
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='reg_form_box']")).isDisplayed());
		} catch (Exception e) {

		}

		System.out.println("TC_02_END Implicit: " + getDateTimeSecond());
		System.out.println("TC_02 START - Explicit: " + getDateTimeSecond());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='login']")));
		} catch (Exception e) {

		}
		System.out.println("TC_02 START - Explicit: " + getDateTimeSecond());
	}

	@Test
	public void TC03_Not_FoundImplicitexplicitWait() {
		explicitWait = new WebDriverWait(driver, Duration.ofMinutes(7));
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
		System.out.println("TC_03 START - Implicit: " + getDateTimeSecond());
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//button[@name='automation_testing']")).isDisplayed());
		} catch (Exception e) {

		}
		System.out.println("TC_03_END Implicit: " + getDateTimeSecond());
		System.out.println("TC_03 START - Explicit: " + getDateTimeSecond());
		try {
			explicitWait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='automation_testing']")));
		} catch (Exception e) {

		}
		System.out.println("TC_03 END - Explicit: " + getDateTimeSecond());

	}

	@Test
	public void TC04_Not_FoundOnlyExplicitBy() {
		explicitWait = new WebDriverWait(driver, Duration.ofMinutes(6));

		System.out.println("TC_04 START - Explicit: " + getDateTimeSecond());
		try {
			explicitWait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='automation_testing']")));
		} catch (Exception e) {

		}

		System.out.println("TC_04 END - Explicit: " + getDateTimeSecond());

	}

	@Test
	public void TC05_Not_FoundOnlyExplicit_WebElement() {
		explicitWait = new WebDriverWait(driver, Duration.ofMinutes(6));

		System.out.println("TC_05 START - Explicit: " + getDateTimeSecond());
		try {
			explicitWait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//button[@name='automation_testing']"))));
		} catch (Exception e) {

		}

		System.out.println("TC_05 END - Explicit: " + getDateTimeSecond());

	}

	public String getDateTimeSecond() {
		Date date = new Date();
		return date.toString();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
