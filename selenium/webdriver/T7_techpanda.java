package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T7_techpanda {
	WebDriver driver;
	String firstname, middlename, lastname, email, password, confirmation, emailLogin, passwordLogin;
	// TC01

	By myAccount = By.xpath("//div[@class='footer']//a[text()='My Account']");
	By createAccount = By.xpath("//a[@title='Create an Account']");

	By firstnameTextbox = By.id("firstname");
	By middlenameTextbox = By.id("middlename");
	By lastnameTextbox = By.id("lastname");
	By emailTextbox = By.name("email");
	By passwordTextboxBy = By.id("password");
	By confirmationTextbox = By.name("confirmation");
	By register = By.xpath("//button[@title='Register']");
	By newsletterCheckbox = By.xpath("//input[@title='Sign Up for Newsletter']");
	By emailLoginTextbox = By.name("login[username]");
	By passwordLoginText = By.name("login[password]");
	By loginButton = By.xpath("//button[@title='Login']");

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30));
		driver.manage().window().maximize();
		driver.get("https://live.techpanda.org/");

		// TC01
		firstname = "Tran";
		middlename = "Thanh";
		lastname = "Nam";
		email = "thanhtran" + getRandomNumber() + "@hotmail.com";
		password = "123456";
		confirmation = "123456";
		// TC02
		emailLogin = "thanhtran259341975@hotmail.com";
		passwordLogin = "123456";

	}
	@Test
	public void TC01_Textbox_Textarea_CreateAccount() throws InterruptedException {
		// //div[@class='footer']//a[text()='My Account']
		driver.findElement(myAccount).click();
		driver.findElement(createAccount).click();

		driver.findElement(firstnameTextbox).sendKeys(firstname);
		driver.findElement(middlenameTextbox).sendKeys(middlename);
		driver.findElement(lastnameTextbox).sendKeys(lastname);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmationTextbox).sendKeys(confirmation);
		driver.findElement(newsletterCheckbox).click();
		driver.findElement(register).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(text(),'Send anyway')]")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering')]")).getText(),
				"Thank you for registering with Main Website Store.");

	}

	
	public void TC02_Verify_Infnormation() throws InterruptedException {
		driver.get("https://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(emailLoginTextbox).sendKeys(emailLogin);
		driver.findElement(passwordLoginText).sendKeys(passwordLogin);
		driver.findElement(loginButton).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(text(),'Send anyway')]")).click();
		Thread.sleep(3000);

		// Verify: firstname, lastname, email
				
		//Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(), 'Change Password')]/parent::p/br[1]/preceding-sibling::text()")).getText() +"\n","Tran Thanh Nam");

		//Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(), 'Change Password')]/parent::p/text()[contains(., '@')]")).getText(),"thanhtran259341975@hotmail.com");
		
		// Lấy đoạn văn bản chứa cả họ tên, email, link "Change Password"
		WebElement infoElement = driver.findElement(By.xpath("//a[contains(text(), 'Change Password')]/parent::p"));
		String fullText = infoElement.getText();

		// Cắt dòng đầu tiên nếu muốn lấy họ tên:
		String[] lines = fullText.split("\n");
		String namecheck = lines[0].trim();    // "Tran Thanh Nam"
		String emailcheck = lines[1].trim();   // "thanhtran259341975@hotmail.com"

		Assert.assertEquals(namecheck, firstname+ " "+middlename+" "+lastname);
		Assert.assertEquals(emailcheck, emailLogin);
//Step8
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2")).click();
		driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
		driver.findElement(By.xpath("//input[@id='Quality 1_5']")).click();
		driver.findElement(By.id("review_field")).sendKeys("bb Good application, pretty easy to navigate");
		driver.findElement(By.id("summary_field")).sendKeys("mm don't worry");
		driver.findElement(By.id("nickname_field")).sendKeys("mimi11");
		driver.findElement(By.xpath("//button[@title='Submit Review']")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(text(),'Send anyway')]")).click();
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//ancestor::span")).getText(), "Your review has been accepted for moderation.");
		
		/*thanhtran259341975@hotmail.com
		123456*/

	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999999);

	}

}
