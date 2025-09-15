package testng;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class T07_Parameter {
	WebDriver driver;
	WebDriverWait explicitWait;
	By emailTextbox = By.xpath("//input[@id='email']");
	By passwordTextbox = By.xpath("//input[@id='pass']");
	By loginTextbox = By.xpath("//button[@id='send2']");

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browSerName) {
		if (browSerName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browSerName.equals("chome")) {
			driver = new ChromeDriver();
		} else if (browSerName.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			new RuntimeException("Your browser name is invalid");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30));
		driver.manage().window().maximize();
	}

	@Test
	public void TC01() throws InterruptedException {
		// explicitWait = new WebDriverWait(driver, Duration.ofMinutes(4));
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']")).click();

		driver.findElement(By.xpath("//img[@title='Show products in category Notebooks']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//h2[@class='product-title']//a[normalize-space()='Asus Laptop']")).click();
		driver.findElement(By.xpath("//button[@id='add-to-cart-button-5']")).click();
		//driver.findElement(By.xpath("//span[@title='Close']")).click();

		// driver.findElement(By.xpath("//span[text()='//a[@class='ico-cart']")).click();
		Thread.sleep(3000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
