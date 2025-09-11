package webdriver;

import org.testng.annotations.Test;

import net.bytebuddy.asm.Advice.Return;

import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T15_Wait_P5_FluentWait {
	WebDriver driver;
	FluentWait<WebDriver> fluentDriver;

	@BeforeClass
	public void beforeClass() {
		// driver=new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//driver.get("https://www.facebook.com/");
	}

	//@Test
	public void TC01_WaitAndClick() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		waitAndClickToElementBy(By.xpath("//button[normalize-space()='Start']"));
		waitAndVerifyText(By.xpath("//h4[normalize-space()='Hello World!']"), "Hello World!");
	}

	//@Test
	public void TC02_WaitAndVerifyText() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		waitAndVerifyTextEndWith(By.xpath("//div[@id='javascript_countdown_time']"), "00");
	}

	@Test
	public void TC03_() {
		driver.get("https://admin-demo.nopcommerce.com");
		waitAndClickToElementBy(By.xpath("//button[normalize-space()='Log in']"));
		waitAndVerifyText(By.xpath("//h4[normalize-space()='Welcome to your store!']"), "Welcome to your store!");
	}

	public void waitAndClickToElementBy(By xpath) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Tổng thời gian để chờ
		fluentDriver.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(1))// Tần số mỗi giây check 1
																							// lần
				.ignoring(NoSuchElementException.class);// Nếu gặp exception là find không thấy element sẽ bỏ qua
		WebElement element = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(xpath);
			}

		});
		element.click();
	}

	public void waitAndVerifyTextEndWith(By xpath, String expectedResultText) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Tổng thời gian để chờ
		fluentDriver.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(1000))// Tần số mỗi giây check 1 lần
		.ignoring(NoSuchElementException.class);// Nếu gặp exception là find không thấy element sẽ bỏ qua
		
		fluentDriver.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				String actualText=driver.findElement(xpath).getText();
				System.out.println("Text = "+actualText);
				return actualText.endsWith(expectedResultText);
			}

		});

	}
	public void waitAndVerifyText(By xpath, String expectedResultText) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Tổng thời gian để chờ
		fluentDriver.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(1))// Tần số mỗi giây check 1
																							// lần
				.ignoring(NoSuchElementException.class);// Nếu gặp exception là find không thấy element sẽ bỏ qua
		fluentDriver.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(xpath).getText().equals(expectedResultText);
			}

		});

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
