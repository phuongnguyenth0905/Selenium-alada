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

public class T15_WebdriverWait_P1_ElementStatus {
	WebDriver driver;
	WebDriverWait explicitWait;
  
  @BeforeClass
  public void beforeClass() {
	  //driver=new ChromeDriver();
	  driver=new FirefoxDriver();
	  explicitWait=new WebDriverWait(driver, Duration.ofMinutes(10));
	  driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));
	  driver.manage().window().maximize();
	  
  }
  @Test
  public void TC01_ElementVisibleDisplayed() {
	  driver.get("https://live.techpanda.org/index.php/customer/account/login/");
	  /*Điều kiện để 1 element Displayed visible
	   - Element không có trên UI (bắt buộc)
	   - Element có trong DOM (bắt buộc)
	    */ 
	  //wait My Account link at header is displayed
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer']//a[text()='My Account']")));
	  //Verify My Account link is displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).isDisplayed());
	  //Click to Login button
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  //Wait Email error message is displayed
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));
	  //Verify Email error message is displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).isDisplayed());
  }
  @Test
  public void TC02_ElementInvisibleUndisplayed__InDOM() {
	  /*Điều kiện để 1 element Displayed visible
	   - Element không có trên UI (bắt buộc)
	   - Element có trong DOM (bắt buộc)
	    */
	  driver.navigate().refresh();
	  //
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
  }
  @Test
  public void TC02_ElementInvisibleUndisplayed_Not_InDOM() {
	  /*Điều kiện để 1 element unDisplayed /invisible
	   - Element không có trên UI (bắt buộc)
	   - Element không có trong DOM (bắt buộc)
	    */
	  driver.navigate().refresh();
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));
  }
  @Test
  public void TC03_ElementPresence() {
	  driver.navigate().refresh();
	  /*Điều kiện để 1 element Presence
	   - Element không có trên UI (Case01)
	   - Element có trên UI (Case02)
	   - Element có trong DOM (bắt buộc)
	    */
	  //Wait My Account link at header is presence- Element không có trên UI(Case 1)
	  explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
  }
  
  @Test
  public void TC04_ElementStaleness() {
	  driver.navigate().refresh();
	  /*Điều kiện để 1 element Staleness
	   - Element không có trên UI (bắt buộc)
	   - Element có trong DOM (bắt buộc)
	    */ 
	  //1. Tìm element lúc nó đang xuất hiện -> lưu vào 1 biến
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  WebElement emailErrorMessage=driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));
	  driver.navigate().refresh();
	  //2. Element không xuất hiện nữa -> Kiểm tra(chờ) nó staleness: Ạjax loading, Improgess icon
	  explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
