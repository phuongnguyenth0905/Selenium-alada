package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class T06_DataProvider {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='email']");
	By passwordTextbox = By.xpath("//input[@id='pass']");
	By loginTextbox = By.xpath("//button[@id='send2']");

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@Test
	public void TC01(String username, String pass) throws InterruptedException {
		driver.get("https://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(pass);
		driver.findElement(loginTextbox).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
@DataProvider
public Object[][]UserAndPasswordata(){
	return new Object[][] {
		{"testNG123@gmail.com","123456"},
		{"testNG123@gmail.com","123456"},
		{"testNG123@gmail.com","123456"}
	};
}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
