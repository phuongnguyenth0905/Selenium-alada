package testng;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class T08_Loop {
	WebDriver driver;
	String setURL, gender, firstname, lastname, email, password, confirmpassword, companyName;

	By genderRadio = By.xpath("//span[@class='female']/child::input");
	By firstNameTextbox = By.id("FirstName");
	By lastNameTextbox = By.id("LastName");
	By emailTextbox = By.id("Email");
	By passwordNameTextbox = By.id("Password");
	By confirmPasswordNameTextbox = By.id("ConfirmPassword");
	By companyNameTextbox = By.id("Company");

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");

		gender = "M";
		firstname = "NORMA";
		lastname = "LANCASTER";
		email = "testqa" + getRandomNumber() + "@hotmail.com";
		password = "Test12345#";
		companyName = "Mante and Sons";

	}

	@Test(invocationCount = 3)
	public void TC01() throws InterruptedException {
		/*driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(genderRadio).click();
		driver.findElement(firstNameTextbox).sendKeys(firstname);
		driver.findElement(lastNameTextbox).sendKeys(lastname);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(companyNameTextbox).sendKeys(companyName);
		driver.findElement(passwordNameTextbox).sendKeys(password);
		driver.findElement(confirmPasswordNameTextbox).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		System.out.println("First name: " + firstname + "\n" + "Last name: " + lastname + "\n" + "Email: " + email
				+ "\n" + "Company name:  " + companyName);

		driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']")).click();
		driver.findElement(By.xpath("//img[@title='Show products in category Notebooks']")).click();
		driver.findElement(By.xpath("//img[@title='Show details for Asus Laptop']")).click();
		driver.findElement(By.xpath("//button[@id='add-to-cart-button-5']")).click();
		driver.findElement(By.xpath("//span[@title='Close']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='//a[@class='ico-cart']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@class='ico-logout']")).click();*/
		// testqa8984@hotmail.com
		// Test12345#
		//driver.findElement(By.className("ico-login")).click();
		//driver.findElement(emailTextbox).sendKeys(email);
		//driver.findElement(passwordNameTextbox).sendKeys(password);
		//driver.findElement(By.xpath("//button[text()='Log in']")).click();
		driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']")).click();
		
		driver.findElement(By.xpath("//img[@title='Show products in category Notebooks']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//h2[@class='product-title']//a[normalize-space()='Asus Laptop']")).click();
		driver.findElement(By.xpath("//button[@id='add-to-cart-button-5']")).click();
		driver.findElement(By.xpath("//span[@title='Close']")).click();
		
		//driver.findElement(By.xpath("//span[text()='//a[@class='ico-cart']")).click();
		Thread.sleep(3000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
