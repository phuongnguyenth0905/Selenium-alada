package webdriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T10_UserInteractions {
	WebDriver driver;
	Actions action;
	String projectFolder=System.getProperty("user.dir");
	String jsPathFile=projectFolder+"\\dragAndDrop\\drag_and_drop_helper.js";
	String jQeryPathFile=projectFolder+"\\dragAndDrop\\jquery_load_helper.js";
	JavascriptExecutor jsExecutor;
  
  @BeforeClass
  public void beforeClass() {
	  driver=new ChromeDriver();
	  action=new Actions(driver);
	  jsExecutor=(JavascriptExecutor)driver;
	  driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(50));
	  driver.manage().window().maximize();
	  
  }
  //@Test
  public void TC01_HoverToElement_Tooltip() {
	  driver.get("https://automationfc.github.io/jquery-tooltip/");
	  action.moveToElement(driver.findElement(By.id("age"))).perform();
	 String hoverElement=driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText();
	 System.out.println(hoverElement);
	 sleepInSecond(5);
	 Assert.assertEquals(hoverElement, "We ask for your age only for statistical purposes.");
  }
  //@Test
  public void TC02_HoverToElement_myntra() {
	  driver.get("http://www.myntra.com/");
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
	  sleepInSecond(3);
	  driver.findElement(By.xpath("//ul[@class='desktop-navBlock']/descendant::a[text()='Home & Bath']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
	  Assert.assertTrue(driver.findElement(By.xpath("//li[@class='breadcrumbs-item']/descendant::span[text()='Kids Home Bath']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
  }
  
  public void TC03_HoverToElement_fahasa() {
	  driver.get("https://www.fahasa.com/");
	  driver.findElement(By.id("close-popup")).click();
	  action.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
	  sleepInSecond(0);
	  //Sách trong nước
	  action.moveToElement(driver.findElement(By.xpath("//span[text()='Sách Trong Nước']/ancestor::li"))).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Sách Trong Nước']/ancestor::li")).isDisplayed());
	//FOREIGN BOOKS
	  action.moveToElement(driver.findElement(By.xpath("//span[text()='FOREIGN BOOKS']/ancestor::li"))).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='FOREIGN BOOKS']/ancestor::li")).isDisplayed());
	  //VPP - Dụng Cụ Học Sinh
	  action.moveToElement(driver.findElement(By.xpath("//span[text()='VPP - Dụng Cụ Học Sinh']/ancestor::li"))).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='VPP - Dụng Cụ Học Sinh']/ancestor::li")).isDisplayed());
	  //Đồ Chơi
	  action.moveToElement(driver.findElement(By.xpath("//span[text()='Đồ Chơi']/ancestor::li"))).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Đồ Chơi']/ancestor::li")).isDisplayed());
	  //Làm Đẹp - Sức Khỏe
	  action.moveToElement(driver.findElement(By.xpath("//span[text()='Làm Đẹp - Sức Khỏe']/ancestor::li"))).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Làm Đẹp - Sức Khỏe']/ancestor::li")).isDisplayed());
	  //Sách Giáo Khoa 2025
	  action.moveToElement(driver.findElement(By.xpath("//span[text()='Sách Giáo Khoa 2025']/ancestor::li"))).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Sách Giáo Khoa 2025']/ancestor::li")).isDisplayed());
	  //VPP - DCHS Theo Thương Hiệu
	  action.moveToElement(driver.findElement(By.xpath("//span[text()='VPP - DCHS Theo Thương Hiệu']/ancestor::li"))).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='VPP - DCHS Theo Thương Hiệu']/ancestor::li")).isDisplayed());
	  //Đồ Chơi Theo Thương Hiệu
	  action.moveToElement(driver.findElement(By.xpath("//span[text()='Đồ Chơi Theo Thương Hiệu']/ancestor::li"))).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Đồ Chơi Theo Thương Hiệu']/ancestor::li")).isDisplayed());
	  //Bách Hóa Online - Lưu Niệm
	  action.moveToElement(driver.findElement(By.xpath("//span[text()='Bách Hóa Online - Lưu Niệm']/ancestor::li"))).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Bách Hóa Online - Lưu Niệm']/ancestor::li")).isDisplayed());
	 
  }
   
  public void TC04_ClickAndHold_automationfc() {
	  driver.get("https://automationfc.github.io/jquery-selectable/");
	  List<WebElement> listNumber=driver.findElements(By.xpath("//ol[@id='selectable']//li"));
	  System.out.println("List number="+listNumber.size());
	  Assert.assertEquals(listNumber.size(), 30);
	  action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(7)).release().perform();
	  listNumber=driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
	  System.out.println("List number="+listNumber.size());
	  Assert.assertEquals(listNumber.size(), 8);
	  sleepInSecond(3);
	  
  }
  
  public void TC05_ClickAndHoldRandom_automationfc() {
	  driver.get("https://automationfc.github.io/jquery-selectable/");
	  List<WebElement> listNumbers=driver.findElements(By.xpath("//ol[@id='selectable']//li"));
	  System.out.println("List number="+listNumbers.size());
	  Assert.assertEquals(listNumbers.size(), 30);
	  action.keyDown(Keys.CONTROL).perform();
	  action.click(listNumbers.get(1)).perform();
	  action.click(listNumbers.get(3)).perform();
	  action.click(listNumbers.get(7)).perform();
	  action.click(listNumbers.get(10)).perform();
	  action.click(listNumbers.get(14)).perform();
	  action.click(listNumbers.get(22)).perform();
	  action.click(listNumbers.get(29)).perform();
	  action.keyUp(Keys.CONTROL).perform();
	  
	  listNumbers=driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
	  System.out.println("List number="+listNumbers.size());
	  Assert.assertEquals(listNumbers.size(), 7);
	  sleepInSecond(3);
  }
  
  public void TC06_DoubleClick_automationfc() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	  Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).getText(), "Hello Automation Guys!");
	  sleepInSecond(3);
  }
  
  public void TC08_RightClick() {
	driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit")).isDisplayed());
	action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
	sleepInSecond(2);
	Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
	
	driver.findElement(By.cssSelector(".context-menu-icon-quit")).click();
	driver.switchTo().alert().accept();
	sleepInSecond(3);
	Assert.assertFalse(driver.findElement(By.cssSelector(".context-menu-icon-quit")).isDisplayed());
}
  
  public void TC09_DragDrop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement sourceCircle=driver.findElement(By.id("draggable"));
		WebElement targetCircle=driver.findElement(By.id("droptarget"));
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='You did great!']")).getText(), "You did great!");
		Assert.assertEquals(targetCircle.getCssValue("background-color"), "rgba(3, 169, 244, 1)");
	}
  
  public void TC10_DragDrop_Css() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String sourceCss="#column-a";
		String targetCss="#column-b";
		String jsContent=getContentFile(jsPathFile);
		//A to B
		jsContent = jsContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(jsContent);
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
		
		//B to A
		jsExecutor.executeScript(jsContent);
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-b']/header[text()='A']"));
	}
  @Test
  public void TC10_DragDrop_HTML5() throws AWTException {
	  driver.get("https://automationfc.github.io/drag-drop-html5/");
	  		
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		dragAndDropHTML5ByXpath(sourceXpath, targetXpath);
		sleepInSecond(3);
		
		dragAndDropHTML5ByXpath(sourceXpath, targetXpath);
		sleepInSecond(3);
		
		dragAndDropHTML5ByXpath(sourceXpath, targetXpath);
		sleepInSecond(3);
  }
  public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
  private boolean isElementDisplayed(String locator) {
	return driver.findElement(By.xpath(locator)).isDisplayed();
}
public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
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
