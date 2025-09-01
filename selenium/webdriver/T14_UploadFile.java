package webdriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T14_UploadFile {
	WebDriver driver;
	String projectFolder=System.getProperty("user.dir");
	String buildImageName="buildI.jpg";
	String churchImageName="church.jpg";
	String landscapeImageName="landscape.jpg";
	
	String buildImagePath=projectFolder+"\\uploadFiles\\"+buildImageName;
	String churchImagePath=projectFolder+"\\uploadFiles\\"+churchImageName;
	String landscapeImagePath=projectFolder+"\\uploadFiles\\"+landscapeImageName;
	//one file
	String firefoxAutoITOnFile=projectFolder+"\\autoIT\\firefoxUploadOneTime.exe";
	String chromeAutoITOnFile=projectFolder+"\\autoIT\\chromeUploadOneTime.exe";
	//multiple file
	String firefoxAutoITMultipleFile=projectFolder+"\\autoIT\\firefoxUploadMultiple.exe";
	String chromeAutoITMultipleFile=projectFolder+"\\autoIT\\chromeUploadMultiple.exe";
  @BeforeClass
  public void beforeClass() {
	  driver=new ChromeDriver();
	  //driver=new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(50));
	  driver.manage().window().maximize();

  }
  //@Test
  public void TC01_UploadFileBySenkey() {
	  driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	
	  WebElement uploadFile=driver.findElement(By.xpath("//input[@type='file']"));
	  sleepInSecond(7);
	  //upload file
	  uploadFile.sendKeys(buildImagePath);
	  uploadFile=driver.findElement(By.xpath("//input[@type='file']"));
	  uploadFile.sendKeys(churchImagePath);
	  uploadFile=driver.findElement(By.xpath("//input[@type='file']"));
	  uploadFile.sendKeys(landscapeImagePath);
	  //upload multiple file
	  //uploadFile.sendKeys(buildImagePath+"\n"+churchImagePath+"\n"+landscapeImagePath);
	  sleepInSecond(7);
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+buildImageName+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+churchImageName+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+landscapeImageName+"']")).isDisplayed());
	  List<WebElement> startButton=driver.findElements(By.xpath("//button[@type='submit']"));
	  for(WebElement start:startButton) {
		  start.click();
	  }
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+buildImageName+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+churchImageName+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+landscapeImageName+"']")).isDisplayed());
  }
  //@Test
  public void TC02_UploadFileByAutoIT() throws IOException {
	  driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	  driver.findElement(By.cssSelector(".btn-success")).click();
	  sleepInSecond(3);
	 // one file
	   if (driver.toString().contains("firefox")) {
		Runtime.getRuntime().exec(new String[] {firefoxAutoITOnFile,buildImagePath});
	} else if(driver.toString().contains("chrome")){
		Runtime.getRuntime().exec(new String[] {chromeAutoITOnFile,buildImagePath});
	}
	  //multiple file
//	  if (driver.toString().contains("firefox")) {
//		Runtime.getRuntime().exec(new String[] {firefoxAutoITMultipleFile,buildImagePath, churchImagePath, landscapeImagePath});
//	} else if(driver.toString().contains("chrome")){
//		Runtime.getRuntime().exec(new String[] {chromeAutoITMultipleFile,buildImagePath,churchImagePath, landscapeImagePath});
//	}
	  sleepInSecond(3);
  }
  @Test
  public void TC03_UploadFileByRobot() throws AWTException {
	  driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	  //driver.findElement(By.cssSelector(".btn-success")).click();
	  String[] files = {buildImagePath, churchImagePath, landscapeImagePath};
	  for (String path : files) {
		    driver.findElement(By.cssSelector(".btn-success")).click();
		    sleepInSecond(2);
		 // Nhập path file
	  StringSelection select=new StringSelection(path);
	  //copy to Clipboard
	  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
	  
	  Robot robot=new Robot();
	  sleepInSecond(3);
	  //Nhấn Enter
	  robot.keyPress(KeyEvent.VK_ENTER);
	  robot.keyRelease(KeyEvent.VK_ENTER);
	  sleepInSecond(1);
	  //Nhấn xuống ctrl-v
	  robot.keyPress(KeyEvent.VK_CONTROL);
	  robot.keyPress(KeyEvent.VK_V);
	  sleepInSecond(1);
	  //Nhả ctrl-v
	  robot.keyRelease(KeyEvent.VK_CONTROL);
	  robot.keyRelease(KeyEvent.VK_V);
	  sleepInSecond(1);
	  //nhấn enter
	  robot.keyPress(KeyEvent.VK_ENTER);
	  robot.keyRelease(KeyEvent.VK_ENTER);
	  sleepInSecond(1);
	  }
  }
  public void sleepInSecond(long time) {
		 try {
			 Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
