package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T7_guru99 {
	WebDriver driver;
	String loginPageURL, userID, password, customeName, dateOfBirth, adress, city, state, pin, phone, email;
	By customeNameTextbox=By.name("name");
	By dobTextbox=By.name("dob");
	By addrTextbox=By.name("addr");
	By cityTextbox=By.name("city");
	By stateTextbox=By.name("state");
	By pinTextbox=By.name("pinno");
	By phoneTextbox=By.name("telephoneeno");
	By emailTextbox=By.name("emailid");
	By passTextbox=By.name("password");

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(20));
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/V1/index.php");

	}

	@Test
	public void TC01_Register() {
		loginPageURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("testguru99auto@gmail.com");
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		System.out.println("User ID : " + userID);
		System.out.println("Password : " + password);
	}

	@Test
	public void TC02_Login() {
		loginPageURL = driver.getCurrentUrl();
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//marquee [@class='heading3']")).getText(), "Welcome To Manager's Page of GTPL Bank");
	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
