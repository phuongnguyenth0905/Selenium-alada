package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T02_Login_Locator_Css {
  WebDriver driver;
  @BeforeClass
  public void beforeClass() {
	  driver= new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	  driver.manage().window().maximize();
	  
  }
  @Test
  public void TC01_RegisterWithEmtyData() throws InterruptedException {
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
	  Thread.sleep(3000);
	  
	  //Kiểm tra các error message hiển thị tại form đăng ký
	  String nameErrorMessage=driver.findElement(By.xpath("//label[@id='txtLoginPasswordRegRes-error']")).getText();
	  Assert.assertEquals(nameErrorMessage, "Vui lòng nhập họ tên");
  }
  @Test
  public void TC02_RegisterWithInvalidEmail() {
	  
  }
  @Test
  public void TC03_RegisterWithIncorrectConfirmEmail() {
	  
  }
  @Test
  public void TC04_RegisterWithPasswordLessThan6Characters() {
	  
  }
  @Test
  public void TC05_RegisterWithIncorrectConfirmPassword() {
	  
  }
  @Test
  public void TC06_RegisterWithInvalidPhoneNumber() {
	  
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
