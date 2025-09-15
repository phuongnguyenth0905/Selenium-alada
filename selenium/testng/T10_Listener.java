package testng;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;

@Listeners(ScreenshotListener.class)
public class T10_Listener {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		// driver.get("https://demo.nopcommerce.com/");

	}

	public WebDriver getDriver() {
		return driver;
	}

	@Test
	public void TC01_Create_User() {

	}

	@Test
	public void TC02_View_User() {

	}

	@Test
	public void TC03_Edit_User() {

	}

	@Test
	public void TC04_Movie_User() {

	}

	@Test
	public void TC05_Delete_User() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
