package webdriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T11_Popup {
	WebDriver driver;
	
  
  @BeforeClass
  public void beforeClass() {
	  driver=new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
	  driver.manage().window().maximize();
	  
	  
  }
  
  public void TC01_PopupInDOM_() {
	  driver.get("https://ngoaingu24h.vn/");
	  driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
	  sleepInSecond(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='auth-form']")).isDisplayed());
	  driver.findElement(By.xpath("//div[@class='input-item']//input[@autocomplete='username']")).sendKeys("automationfc");
	  driver.findElement(By.xpath("//div[@class='input-item']//input[@name='password']")).sendKeys("automationfc123");
	  driver.findElement(By.xpath("//div[@class='forgot-password']/following::div//button")).click();
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='notistack-snackbar']")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");
  }
  
  public void TC02_PopupInDOM_() {
	  driver.get("https://skills.kynaenglish.vn/dang-nhap");
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='right']")).isDisplayed());
	  driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automation323@hotmail.com");
	  driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("123456");
	  driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='password-form-login-message']")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
  }
  
  public void TC03_PopupNotInDOM() {
	  driver.get("https://tiki.vn/");
	  driver.findElement(By.cssSelector("img[alt='close-icon']")).click();
	  sleepInSecond(3);
	  driver.findElement(By.cssSelector(".sc-7f8ef5b2-19.hTmXuX")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='sc-2745a82-0 gvDbCz']")).isDisplayed());
	  driver.findElement(By.xpath("//p[@class='login-with-email']")).click();
	  driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(), "Email không được để trống");
	  Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(), "Mật khẩu không được để trống");
	  driver.findElement(By.xpath("//img[@class='close-img']")).click();
	  //Assert.assertFalse(driver.findElement(By.xpath("//div[@class='sc-2745a82-0 gvDbCz']")).isDisplayed());
  }
  
  public void TC04_PopupNotInDOM() {
	  driver.get("https://www.facebook.com/");
	  driver.findElement(By.xpath("//a[text()='Create new account']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='fb_content clearfix _9bq7']")).isDisplayed());
	  driver.findElement(By.xpath("//a[text()='Create new account']")).click();
	  Assert.assertFalse(driver.findElement(By.xpath("//div[@class='fb_content clearfix _9bq7']")).isDisplayed());
  }
  
  public void TC05_RandomPopup_InDOM() {
	  driver.get("https://vnk.edu.vn/");
	  List<WebElement> popup = driver.findElements(By.xpath("//div[contains(@class,'pum-active')]"));

	    if (popup.size() > 0 && popup.get(0).isDisplayed()) {
	        System.out.println("Popup hiển thị");
	        driver.findElement(By.xpath("//button[@class='pum-close popmake-close']")).click();
	    } else {
	        System.out.println("Không có popup");
	    }
	  driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
	 //list khoa hoc
	  List<WebElement> khoahocText=driver.findElements(By.xpath("//span[@class='ez-toc-section']"));
	  List<String> actualCourse=new ArrayList<String>();
	  for(WebElement contentKhoaHoc: khoahocText) {
		  String text=contentKhoaHoc.getText().trim();
		  if(!text.isEmpty()) {
			  actualCourse.add(text);
		  }
		 System.out.println(contentKhoaHoc.getText());
		 Assert.assertTrue(actualCourse.isEmpty(), "Danh sách khóa học bị trống!");
	  }
  }
  
  public void TC06_RandomPopup_InNotDOM() {
	  driver.get("https://www.javacodegeeks.com");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	  List<WebElement> randomPopup = driver.findElements(By.xpath("//div[@class='lepopup-element lepopup-element-2 lepopup-element-rectangle lepopup-animated lepopup-fadeIn']"));
	  if(randomPopup.size()> 0 && randomPopup.get(0).isDisplayed()) {
		  //close popup
		  driver.findElement(By.xpath("//input[@name='lepopup-20']")).sendKeys("auto123@gmail.com");
		 driver.findElement(By.xpath("//a[normalize-space()='×']")).click();
		  
	  } else {
		  System.out.println("Không có popup");
		  	
	}
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	 driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys("Agile Testing");
	 driver.findElement(By.xpath("//button[@id='search-submit']")).click();
	  
	 Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='page-title']")).getText(), "Search Results for: Agile Testing");
	 sleepInSecond(3);
  }
  @Test
  public void TC07_RandomPopup_InNotDOM() {
	  driver.get("https://dehieu.vn/");
	  List<WebElement> randomPopup =driver.findElements(By.xpath("//div[@class='modal-content css-modal-bt']"));
	  if(randomPopup.size()> 0 && randomPopup.get(0).isDisplayed()) {
		  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='modal-content css-modal-bt']")).isDisplayed());
		  driver.findElement(By.xpath("//input[@placeholder='Họ tên']")).sendKeys("Auto Test");
		  driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("auto364@live.com");
		  driver.findElement(By.xpath("//input[@placeholder='Số điện thoại']")).sendKeys("0998877559");
		  driver.findElement(By.xpath("//button[@id='submit-form']")).click();
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='css-end-content']//p[2]")).getText(), "Bạn đã đăng ký thành công");
		  sleepInSecond(4);
		  driver.findElement(By.xpath("//button[@class='close']")).click();	  
	  }
	  // Tìm icon search
	  WebElement searchIcon = driver.findElement(By.xpath("//i[@class='fa fa-search']"));
	  sleepInSecond(4);
	  // Scroll đến icon
	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchIcon);

	  // Nhập từ khóa vào ô tìm kiếm
	  driver.findElement(By.xpath("//input[@placeholder='Tìm khóa học']")).sendKeys("Testing");
	  sleepInSecond(4);
	  // Chờ icon có thể click
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.elementToBeClickable(searchIcon));

	  // Click icon search để submit
	  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIcon);
	  sleepInSecond(4);
	  // Assert kết quả
	  Assert.assertEquals(driver.findElement(By.cssSelector("div[class='container'] div h4")).getText(),"Tìm thấy 0 khóa học với từ khóa \"Testing\"");
	  sleepInSecond(4);
  }
  
  public void TC08_ShadowDOM() {
	  driver.get("https://automationfc.github.io/shadow-dom");
  }
  
  public void TC09_() {
	  driver.get("");
  }
  public void sleepInSecond(long time) {
	try {
		Thread.sleep(time*100);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
