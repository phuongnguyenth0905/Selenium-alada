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
  public void TC01_RegisterWithEmtyData() {
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("");
	  driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("");
	  driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("");
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("");
	  driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("");
	  driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("");
	  driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
	  		  
	  //Kiểm tra các error message hiển thị tại form đăng ký
	  String nameErrorMessage=driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText();
	  Assert.assertEquals(nameErrorMessage, "Vui lòng nhập họ tên");
	  
	  String emailErrorMessage=driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText();
	  Assert.assertEquals(emailErrorMessage, "Vui lòng nhập email");
	  
	  String confirmEmailErrorMessage=driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
	  Assert.assertEquals(confirmEmailErrorMessage, "Vui lòng nhập lại địa chỉ email");
	  
	  String passwordErrorMessage=driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
	  Assert.assertEquals(passwordErrorMessage, "Vui lòng nhập mật khẩu");
	  
	  String confirmPasswordErrorMessage=driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
	  Assert.assertEquals(confirmPasswordErrorMessage, "Vui lòng nhập lại mật khẩu");
	  
	  String phoneErrorMessage=driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
	  Assert.assertEquals(phoneErrorMessage, "Vui lòng nhập số điện thoại.");
  }
  @Test
  public void TC02_RegisterWithInvalidEmail() {
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Tran Mai Linh");
	  driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("auto test12@123");//email invalid
	  driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("auto test12@123");
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0356813575");
	  driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
	  		  
	  //Kiểm tra các error message hiển thị tại form đăng ký
		  
	  String emailErrorMessage=driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText();
	  Assert.assertEquals(emailErrorMessage, "Vui lòng nhập email hợp lệ");
	  
	  String confirmEmailErrorMessage=driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
	  Assert.assertEquals(confirmEmailErrorMessage, "Email nhập lại không đúng");
	   
  }
  @Test
  public void TC03_RegisterWithIncorrectConfirmEmail() {
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Tran Mai Linh");
	  driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("testauto123@gmail.com");
	  driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@345@789");
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0356813575");
	  driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
	  
	  String confirmEmailErrorMessage=driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
	  Assert.assertEquals(confirmEmailErrorMessage, "Email nhập lại không đúng");
	  	  
  }
  @Test
  public void TC04_RegisterWithPasswordLessThan6Characters() {
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Tran Mai Linh");
	  driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("testauto123@gmail.com");
	  driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("testauto123@gmail.com");
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("000");
	  driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123");
	  driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0356813575");
	  driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
	  
	  String passwordErrorMessage=driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
	  Assert.assertEquals(passwordErrorMessage, "Mật khẩu phải có ít nhất 6 ký tự");
	  
	  String confirmPasswordErrorMessage=driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
	  Assert.assertEquals(confirmPasswordErrorMessage, "Mật khẩu phải có ít nhất 6 ký tự");
	  	  
  }
  @Test
  public void TC05_RegisterWithIncorrectConfirmPassword() {
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Tran Mai Linh");
	  driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("testauto123@gmail.com");
	  driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("testauto123@gmail.com");
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("333333");
	  driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0356813575");
	  driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
	 
	  String confirmPasswordErrorMessage=driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
	  Assert.assertEquals(confirmPasswordErrorMessage, "Mật khẩu bạn nhập không khớp");
	  
	  //String phoneErrorMessage=driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
	  //Assert.assertEquals(phoneErrorMessage, "Vui lòng nhập số điện thoại.");*/
  }
  @Test
  public void TC06_RegisterWithInvalidPhoneNumber() {
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Tran Mai Linh");
	  driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("testauto123@gmail.com");
	  driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("testauto123@gmail.com");
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("333333");
	  //driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0356813575658");
	  //driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		  
	  //Kiểm tra các error message hiển thị tại form đăng ký	  
	  //String phoneErrorMessage=driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
	 // Assert.assertEquals(phoneErrorMessage, "Số điện thoại phải từ 10-11 số.");
	  
	  driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("123");
	  driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
	  
	  String phoneErrorMessage2=driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
	  Assert.assertEquals(phoneErrorMessage2, "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
