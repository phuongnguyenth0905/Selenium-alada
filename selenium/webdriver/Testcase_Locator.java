package webdriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Testcase_Locator {
	WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  //driver= new FirefoxDriver();
	  driver= new ChromeDriver();
	  //driver.get("https://demo.guru99.com/test/login.html");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	  driver.manage().window().maximize();
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  
  }
  @Test
  public void Testcase() throws InterruptedException {
	 
	 //ID
	 driver.findElement(By.id("txtFirstname")).sendKeys("phuong");
	 Thread.sleep(3000);
	 //Class
	 driver.findElement(By.className("form-control")).sendKeys("autotest454@gmail.com");
	 Thread.sleep(3000);
	 //Name
	 driver.findElement(By.name("txtCEmail")).sendKeys("autotest454@gmail.com");
	 Thread.sleep(3000);
	 //Link text
	 driver.findElement(By.linkText("Forgot your password?")).click();
	 Thread.sleep(3000);
	 //Partial Link text
	 driver.findElement(By.partialLinkText("sử dụng")).click();
	 Thread.sleep(3000);
	 //Tag name
	 //driver.findElement(By.tagName(""));
	 //Thread.sleep(3000);
	 //css
	// driver.findElement(By.cssSelector(""));
	 //Thread.sleep(3000);
	 
	 //xpath
	 //driver.findElement(By.xpath(""));
	 //Thread.sleep(3000);
	 
  }

  @AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
