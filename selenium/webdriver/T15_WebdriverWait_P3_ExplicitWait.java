package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T15_WebdriverWait_P3_ExplicitWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	By startButton = By.xpath("//button[normalize-space()='Start']");
	By loadingIcon = By.xpath("//div[@id='loading']");
	By textHello = By.xpath("//h4[normalize-space()='Hello World!']");
	String projectFolder = System.getProperty("user.dir");
	String picture1 = "picture1.jpg";
	String picture2 = "picture2.jfif";
	String picture3 = "picture3.jfif";
	String pic1_Path = projectFolder + "\\uploadFiles\\" + picture1;
	String pic2_Path = projectFolder + "\\uploadFiles\\" + picture2;
	String pic3_Path = projectFolder + "\\uploadFiles\\" + picture3;

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		// driver=new FirefoxDriver();

		driver.manage().window().maximize();
	}

	// @Test
	public void TC01_LoadingIcon_Invisible_10s() {
		explicitWait = new WebDriverWait(driver, Duration.ofMinutes(10));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButton).click();
		// Chờ cho icon load biêns mất trong 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
	}

	// @Test
	public void TC02_LoadingIcon_Invisible_4s() {
		explicitWait = new WebDriverWait(driver, Duration.ofMinutes(4));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButton).click();
		// Chờ cho icon load biêns mất trong 4s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
	}

	// @Test
	public void TC03_LoadingIcon_Visible_10s() {
		explicitWait = new WebDriverWait(driver, Duration.ofMinutes(10));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButton).click();
		// Chờ cho icon load biêns mất trong 10s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(textHello));
		Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
	}

	// @Test
	public void TC04_LoadingIcon_Visible_4s() {
		explicitWait = new WebDriverWait(driver, Duration.ofMinutes(4));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButton).click();
		// Chờ cho icon load biêns mất trong 4s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(textHello));
		Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
	}

	 @Test
	public void TC06_Select_Date() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, Duration.ofMinutes(10));
		WebElement selectDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(selectDate.getText(), "No Selected Dates to display.");
		// chờ cho ngày 11 có được click vào hay không?
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']/parent::td"))).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style)='display:none;']/div[@class='raDiv']")));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[text()='11']/parent::td[@class='rcSelected']")));
		selectDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(selectDate.getText(), "Thursday, September 11, 2025");
	}

	//@Test
	public void TC07_UploadFile() {
		driver.get("https://gofile.io/?t=uploadFiles");
		// Tạo explicit wait (2 phút là hợp lý)
		explicitWait = new WebDriverWait(driver, Duration.ofMinutes(2));

		// ==== 1. Upload file ====
		WebElement uploadInput = driver.findElement(By.xpath("//input[@type='file']"));
		uploadInput.sendKeys(pic1_Path + "\n" + pic2_Path + "\n" + pic3_Path);

		// ==== 2. Chờ progress bar biến mất ====
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.file-progressbar.bg-green-500.h-2.rounded-full"))));
		// ==== 3. Chờ link "Upload Complete" xuất hiện và click ====
		WebElement folderLink = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'linkSuccessCard')]")));
		folderLink.click();
		// ==== 4. Verify 3 file xuất hiện ====
		WebElement file1 = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[contains(@class,'item_open') and contains(text(),'" + picture1 + "')]")));
		WebElement file2 = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[contains(@class,'item_open') and contains(text(),'" + picture2 + "')]")));
		WebElement file3 = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[contains(@class,'item_open') and contains(text(),'" + picture3 + "')]")));

		Assert.assertTrue(file1.isDisplayed());

		Assert.assertTrue(file2.isDisplayed());

		Assert.assertTrue(file3.isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
