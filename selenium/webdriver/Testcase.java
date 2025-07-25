package webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Testcase {
	WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  //driver= new FirefoxDriver();
	  driver= new ChromeDriver();
	  //driver.get("https://demo.guru99.com/test/login.html");
	  
  }
  @Test
  public void Testcase() throws InterruptedException {
	 driver.get("https://demo.guru99.com/test/login.html");
	 
	 //ID
	 driver.findElement(By.id("email")).sendKeys("phuong123@gmail.com");
	 //Class
	 driver.findElement(By.className("account_input form-control")).sendKeys("123456");
	 //Name
	 driver.findElement(By.name("send")).click();
	 //Link text
	 driver.findElement(By.className("Forgot your password?")).click();
	 //Partial Link text
	 driver.findElement(By.partialLinkText(""));
	 Thread.sleep(3000);
	 //Tag name
	 driver.findElement(By.tagName(""));
	 //css
	 driver.findElement(By.cssSelector(""));
	 
	 //xpath
	 driver.findElement(By.xpath(""));
	 
  }

  @AfterClass
  public void afterClass() {
	  
	  //driver.quit();
  }

}
