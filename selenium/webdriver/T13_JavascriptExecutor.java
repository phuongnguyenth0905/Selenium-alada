package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v136.dom.DOM.GetElementByRelationRelation;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T13_JavascriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(50));
		driver.manage().window().maximize();

	}

	// @Test
	public void TC01_JavascriptExecutor_techpanda() {
		driver.get("http://live.techpanda.org/");
//	  Step 01 - Truy cập vào trang: http://live.techpanda.org/

//	 Step 02 - Sử dụng JE để get domain của page
		String techpandaDomain = (String) executeForBrowser("return document.domain");
		System.out.println("Domain is: " + techpandaDomain);

//		  Verify domain = live.techpanda.org
		Assert.assertEquals(techpandaDomain, "live.techpanda.org");
//		  Step 03 - Sử dụng JE để get URL của page
		String techpandaUrl = (String) executeForBrowser("return document.URL");
		System.out.println("Url is: " + techpandaUrl);
//		  Verify URL = http://live.techpanda.org/
		Assert.assertEquals(techpandaUrl, "https://live.techpanda.org/");

//		  Step 04 - Open MOBILE page (Sử dụng JE)
		clickToElementByJS("//a[normalize-space()='Mobile']");
		sleepInSecond(7);
//		  Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
//		  (Hướng dẫn sử dụng following-sibling)

//		  Step 06 - Verify message được hiển thị: Samsung Galaxy was added to your shopping cart. (Sử dụng JE - Get inner text)
		Assert.assertTrue(getInnerText(driver).contains("Samsung Galaxy was added to your shopping cart."));
//		  Step 07 - Open Customer Service page (Sử dụng JE)
		clickToElementByJS("//a[normalize-space()='Customer Service']");
		sleepInSecond(7);
		// Verify title của page=Customoer Service
		String titleCustomerService = (String) executeForBrowser("return document.title");
		Assert.assertEquals(titleCustomerService, "Customer Service");
		sleepInSecond(7);
//		  Step 08 - Scroll tới element Newsletter textbox nằm ở cuối page (Sử dụng JE)
		scrollToElementonDown("//input[@id='newsletter']");
		sleepInSecond(7);
//		  Step 09 - Input email hợp lệ vào Newsletter textbox (Sử dụng JE)
		senkeyToElementByJS("//input[@id='newsletter']", generateEmail());
		sleepInSecond(7);
//		  Step 10 - Click vào Subscribe button (Sử dụng JE)
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(5);
//		  Step 11 - Verify text có hiển thị Thank you for your subscription. (Sử dụng JE - Get inner text)
		Assert.assertFalse(getInnerText(driver).contains("Thank you for your subscription."));
		sleepInSecond(5);
		// Step12 - Navigate tới domain fb
		navigateToUrlByJS("https://www.facebook.com/");
		// Verify domain fb
		String domainFacebook = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainFacebook, "www.facebook.com");

	}

	// @Test
	public void TC02_JavascriptExecutor_HTML5_automationfc() {
		// Step 01:
//	  Access vào trang: https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/html5/index.html");
//		  Step 02:
//		  Click Submit và verify message hiển thị từ field Name textbox.
		highlightElement("//input[@name='submit-btn']");
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(5);
		// Lấy message cho field Name
		String messageName = getElementValidationMessage("//input[@id='fname']");
		System.out.println("Validation message: " + messageName);

		// Assert để verify message
		Assert.assertEquals(messageName, "Please fill out this field.");
//		  Step 03:
//		  Input dữ liệu vào field Name và click Submit → verify message hiển thị từ field Password textbox.
		highlightElement("//input[@id='fname']");
		senkeyToElementByJS("//input[@id='fname']", "auto Check");
		sleepInSecond(5);
		highlightElement("//input[@name='submit-btn']");
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(5);
		String messagePass = getElementValidationMessage("//input[@id='pass']");
		Assert.assertEquals(messagePass, "Please fill out this field.");
//		  Step 04:
//		  Input dữ liệu vào field Password và click Submit → verify message hiển thị từ field Email textbox.
		highlightElement("//input[@id='pass']");
		senkeyToElementByJS("//input[@id='pass']", "123456");
		sleepInSecond(5);
		highlightElement("//input[@name='submit-btn']");
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(5);
		String messageEmail = getElementValidationMessage("//input[@id='em']");
		Assert.assertEquals(messageEmail, "Please fill out this field.");
//		  Step 05:
//		  Input dữ liệu không hợp lệ vào field Email và click Submit → verify message hiển thị từ field Email textbox.
		highlightElement("//input[@id='em']");
		senkeyToElementByJS("//input[@id='em']", "123456");
		sleepInSecond(5);
		highlightElement("//input[@name='submit-btn']");
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(5);
		String messageEmail2 = getElementValidationMessage("//input[@id='em']");
		Assert.assertEquals(messageEmail2, "Please include an '@' in the email address. '123456' is missing an '@'.");
//		  Step 06:
//		  Input dữ liệu không hợp lệ vào field Email và click Submit → verify message hiển thị từ field Email textbox.

//		  Step 07:
//		  Input dữ liệu hợp lệ vào field Email và click Submit → verify message hiển thị từ field Address textbox.
		highlightElement("//input[@id='em']");
		senkeyToElementByJS("//input[@id='em']", generateEmail());
		sleepInSecond(5);
		highlightElement("//input[@name='submit-btn']");
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(5);
		String messageaddr = getElementValidationMessage("//form[@action='/']//fieldset//ul//li//select");
		Assert.assertEquals(messageaddr, "Please select an item in the list.");
	}

	public void TC03_JavascriptExecutor_HTML5_ubuntu() {
		driver.get("https://login.ubuntu.com/");
		highlightElement("//input[@id='id_new_user']");

		clickToElementByJS("//input[@id='id_new_user']");
		sleepInSecond(7);

		highlightElement("//span[text()='Create account']");

		clickToElementByJS("//span[text()='Create account']");

		sleepInSecond(7);
		String mesageEmail = getElementValidationMessage("(//input[@name='email'])[1]");
		Assert.assertEquals(mesageEmail, "Please fill out this field.");

		highlightElement("(//input[@name='email'])[2]");
		senkeyToElementByJS("(//input[@name='email'])[2]", "12345");
		clickToElementByJS("//span[text()='Create account']");
		sleepInSecond(7);
		String mesageEmail2 = getElementValidationMessage("(//input[@name='email'])[2]");
		Assert.assertEquals(mesageEmail2, "Please include an '@' in the email address. '12345' is missing an '@'.");
		sleepInSecond(7);
		highlightElement("(//input[@name='email'])[2]");
		// email
		senkeyToElementByJS("(//input[@name='email'])[2]", generateEmail());
		// full name
		highlightElement("//input[@id='id_displayname']");
		senkeyToElementByJS("//input[@id='id_displayname']", "manual123");
		// username
		highlightElement("//input[@id='id_username']");
		senkeyToElementByJS("//input[@id='id_username']", "Check Test Auto");
		// pass
		highlightElement("(//input[@id='id_password'])[2]");
		senkeyToElementByJS("(//input[@id='id_password'])[2]", "Test123@456");

		highlightElement("//input[@id='id_passwordconfirm']");
		senkeyToElementByJS("//input[@id='id_passwordconfirm']", "Test123@456");
		//
		highlightElement("//span[text()='Create account']");
		clickToElementByJS("//span[text()='Create account']");
		highlightElement("//label[@for='id_accept_tos']");
		clickToElementByJS("//label[@for='id_accept_tos']");
		sleepInSecond(7);
		// String
		// mesageEmail2=getElementValidationMessage("(//input[@name='email'])[2]");
		// Assert.assertEquals(mesageEmail2, "Please include an '@' in the email
		// address. '12345' is missing an '@'.");
		sleepInSecond(7);
	}

	
	public void TC03_JavascriptExecutor_HTML5_rode() {
		driver.get("https://warranty.rode.com/");
		highlightElement("//a[normalize-space()='Create an account']");

		clickToElementByJS("//a[normalize-space()='Create an account']");
		sleepInSecond(5);
		// click button Create acc
		highlightElement("//button[normalize-space()='Create account']");
		clickToElementByJS("//button[normalize-space()='Create account']");
		sleepInSecond(7);
		// name
		String messageName = getElementValidationMessage("//input[@id='name']");
		Assert.assertEquals(messageName, "Please fill out this field.");
		sleepInSecond(7);
		System.out.println("Message name is: " + messageName);
		highlightElement("//input[@id='name']");
		sleepInSecond(7);
		// nhap text name field
		senkeyToElementByJS("//input[@id='name']", "Automation Check");
		sleepInSecond(7);
		highlightElement("//input[@id='email']");
		// nhập wrong format email
		sleepInSecond(7);
		senkeyToElementByJS("//input[@id='email']", "123456");
		highlightElement("//button[normalize-space()='Create account']");
		sleepInSecond(7);
		// click button Create acc
		clickToElementByJS("//button[normalize-space()='Create account']");
		String messageEmail = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(messageEmail, "Please include an '@' in the email address. '123456' is missing an '@'.");
		System.out.println("Message email is: " + messageEmail);
		// input correct email
		highlightElement("//input[@id='email']");
		senkeyToElementByJS("//input[@id='email']", generateEmail());
		clickToElementByJS("//button[normalize-space()='Create account']");
		// pass
		highlightElement("//input[@id='password']");

		clickToElementByJS("//button[normalize-space()='Create account']");
		sleepInSecond(7);
		String messagePass = getElementValidationMessage("//input[@id='password']");
		Assert.assertEquals(messagePass, "Please fill out this field.");
		System.out.println("Message Password is: " + messagePass);
		// incorrect pass
		highlightElement("//input[@id='password']");
		senkeyToElementByJS("//input[@id='password']", "1235");
		clickToElementByJS("//button[normalize-space()='Create account']");
		sleepInSecond(7);
		/**/
		// pass
		highlightElement("//input[@id='password_confirmation']");

		clickToElementByJS("//button[normalize-space()='Create account']");
		sleepInSecond(7);
		String messageConfirmPass = getElementValidationMessage("//input[@id='password_confirmation']");
		Assert.assertEquals(messageConfirmPass, "Please fill out this field.");
		System.out.println("Message Password is: " + messageConfirmPass);
		// incorrect pass
		highlightElement("//input[@id='password_confirmation']");
		senkeyToElementByJS("//input[@id='password_confirmation']", "123");
		waitToJQueryAndJSLoadedSuccess(driver);
		clickToElementByJS("//button[normalize-space()='Create account']");
		sleepInSecond(7);
		String messageConfirmPass1=getElement(driver, "//p[contains(text(),'confirmation does not')]").getText();
		waitToJQueryAndJSLoadedSuccess(driver);
		
		Assert.assertEquals(messageConfirmPass1, "The password confirmation does not match.");
		System.out.println("Message Password is: " + messageConfirmPass1);
		sleepInSecond(7);
	//login email
		highlightElement("//a[normalize-space()='Sign in']");
		clickToElementByJS("//a[normalize-space()='Sign in']");
		clickToElementByJS("//button[normalize-space()='Sign in']");
		String messageEmailLogin=getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(messageEmailLogin, "Please fill out this field.");
		sleepInSecond(7);
		waitToJQueryAndJSLoadedSuccess(driver);
		highlightElement("//input[@id='email']");
		senkeyToElementByJS("//input[@id='email']", generateEmail());
		clickToElementByJS("//button[normalize-space()='Sign in']");
		String messagePassLogin=getElementValidationMessage("//input[@id='password']");
		Assert.assertEquals(messagePassLogin, "Please fill out this field.");
		sleepInSecond(7);
	}
	
	public void TC03_JavascriptExecutor_HTML5_sieuthimaymocthietbi() {
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		//Invalid Last name
		highlightElement("//button[@value='Đăng ký']");
		clickToElementByJS("//button[@value='Đăng ký']");
		String messageLastName=getElementValidationMessage("//input[@id='lastName']");
		Assert.assertEquals(messageLastName, "Please fill out this field.");
		sleepInSecond(7);
		//invalid First Name
		highlightElement("//input[@id='lastName']");
		senkeyToElementByJS("//input[@id='lastName']", "Auto");
		highlightElement("//button[@value='Đăng ký']");
		clickToElementByJS("//button[@value='Đăng ký']");
		String messageFistName=getElementValidationMessage("//input[@id='firstName']");
		Assert.assertEquals(messageFistName, "Please fill out this field.");
		sleepInSecond(7);
		//Invalid Email
		highlightElement("//input[@id='firstName']");
		senkeyToElementByJS("//input[@id='firstName']", "Test");
		highlightElement("//input[@id='email']");
		clickToElementByJS("//button[@value='Đăng ký']");
		String messageEmail=getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(messageEmail, "Please fill out this field.");
		sleepInSecond(7);
		//Invalid Password
		highlightElement("//input[@id='email']");
		senkeyToElementByJS("//input[@id='email']", generateEmail());
		highlightElement("//input[@id='password']");
		clickToElementByJS("//button[@value='Đăng ký']");
		String messagePass=getElementValidationMessage("//input[@id='password']");
		Assert.assertEquals(messagePass, "Please fill out this field.");
		sleepInSecond(7);
		//Login
		highlightElement("//a[@class='btn-link-style btn-register']");
		clickToElementByJS("//a[@class='btn-link-style btn-register']");
		//incorrect email+pass
		highlightElement("//input[@id='customer_email']");
		senkeyToElementByJS("//input[@id='customer_email']", generateEmail());
		highlightElement("//input[@id='customer_password']");
		senkeyToElementByJS("//input[@id='customer_password']", "111");
		clickToElementByJS("//button[contains(text(),'Đăng nhập')]");
		String errorMessage=getElement(driver,"//div[contains(text(),'Thông tin đăng nhập không chính xác.')]").getText();
		Assert.assertEquals(errorMessage, "Thông tin đăng nhập không chính xác.");		
		//quên pass
		highlightElement("//a[contains(text(),'Quên mật khẩu?')]");
		clickToElementByJS("//a[contains(text(),'Quên mật khẩu?')]");
		highlightElement("//input[@id='recover-email']");
		senkeyToElementByJS("//input[@id='recover-email']", "123");
		clickToElementByJS("//button[contains(text(),'Lấy lại mật khẩu')]");
		String errorEmail=getElementValidationMessage("//input[@id='recover-email']");
		sleepInSecond(7);
		Assert.assertEquals(errorEmail, "Please include an '@' in the email address. '123' is missing an '@'.");
	}
	
	public void TC03_JavascriptExecutor_HTML5_pexels() {
		driver.get("https://www.pexels.com/vi-vn/join-contributor/");
		highlightElement("(//span[contains(text(),'chia sẻ nội dung')])[1]");
		clickToElementByJS("(//span[contains(text(),'chia sẻ nội dung')])[1]");
		sleepInSecond(9);
		waitToJQueryAndJSLoadedSuccess(driver);
		String messaagefName=getElement(driver,"//input[@placeholder='Tên']/following::p[1]").getText();
		Assert.assertEquals(messaagefName, "Đây là trường bắt buộc.");
		String messaagelName=getElement(driver,"//input[@placeholder='Họ']/following::p[1]").getText();
		Assert.assertEquals(messaagelName, "Đây là trường bắt buộc.");
		String messaageEmail=getElement(driver,"//input[@placeholder='Email']/following::p[1]").getText();
		Assert.assertEquals(messaageEmail, "Đây là trường bắt buộc.");
		String messaagePass=getElement(driver,"//input[@placeholder='Mật khẩu']/following::p[1]").getText();
		Assert.assertEquals(messaagePass, "Đây là trường bắt buộc.");
		sleepInSecond(9);
		waitToJQueryAndJSLoadedSuccess(driver);
		highlightElement("//span[text()='Đăng nhập']");
		clickToElementByJS("//span[text()='Đăng nhập']");
		waitToJQueryAndJSLoadedSuccess(driver);
		//Đây là trường bắt buộc.
		sleepInSecond(7);
		highlightElement("(//span[text()='English']/parent::span/following::button)[1]");
		clickToElementByJS("(//span[text()='English']/parent::span/following::button)[1]");
		highlightElement("//span[text()='Đăng nhập']");
		clickToElementByJS("//span[text()='Đăng nhập']");
		sleepInSecond(9);
	}
	@Test
	public void TC04_JavascriptExecutor_techpanda() {
		driver.get("http://live.techpanda.org/");
		highlightElement("//span[@class='label'][normalize-space()='Account']");
		clickToElementByJS("//span[@class='label'][normalize-space()='Account']");
		highlightElement("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']");
		clickToElementByJS("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']");
		//step3
		highlightElement("//a[@title='Create an Account']");
		clickToElementByJS("//a[@title='Create an Account']");
		//input valid
		highlightElement("//input[@id='firstname']");
		senkeyToElementByJS("//input[@id='firstname']", "Automation ");
		highlightElement("//input[@id='middlename']");
		senkeyToElementByJS("//input[@id='middlename']", "G");
		highlightElement("//input[@id='lastname']");
		senkeyToElementByJS("//input[@id='lastname']", "Test");
		highlightElement("//input[@id='email_address']");
		senkeyToElementByJS("//input[@id='email_address']", generateEmail());
		highlightElement("//input[@id='password']");
		senkeyToElementByJS("//input[@id='password']", "123456");
		highlightElement("//input[@id='confirmation']");
		senkeyToElementByJS("//input[@id='confirmation']", "123456");
		highlightElement("//button[@title='Register']");
		clickToElementByJS("//button[@title='Register']");
		clickToElementByJS("//button[text()='Send anyway']");
		String messageSuccess=getElement(driver, "//span[contains(text(),'Thank you')]").getText();
		Assert.assertEquals(messageSuccess, "Thank you for registering with Main Website Store.");
		//logout
		highlightElement("//span[@class='label'][normalize-space()='Account']");
		clickToElementByJS("//span[@class='label'][normalize-space()='Account']");
		highlightElement("//a[normalize-space()='Log Out']");
		clickToElementByJS("//a[normalize-space()='Log Out']");
		driver.navigate().forward();
		sleepInSecond(7);
	}

	// Các hàm dùng chung
	public Object executeForBrowser(String javaScript) {
		// JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		// JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		// JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textActual);
	}

	public void scrollToBottomPage(WebDriver driver) {
		// JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		// JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("window.location='" + url + "'");
	}

	public void highlightElement(String locator) {
		// JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(3);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);

	}

	public String getElementTextByJS(String locator) {
		WebElement element = getElement(driver, locator);
		return (String) jsExecutor.executeScript("return arguments[0].textContent;", element);
	}

	public void clickToElementByJS(String locator) {
		// JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElementOnTop(String locator) {
		// JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToElementonDown(String locator) {
		// JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", element);
	}

	public void senkeyToElementByJS(String locator, String value) {
		//
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].value='" + value + "';"
				+ "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));"
				+ "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", element);
	}

	public void setAttributeInDom(String locator, String attributeValue) {
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeValue + "');", element);
	}

	public void removeAttributeInDom(String locator, String attributeRemove) {
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		WebElement element = getElement(driver, locator);
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", element);
	}

	public String getElementValidationMessage(String locator) {
		WebElement element = getElement(driver, locator);
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}

	public boolean isImageLoaded(String locator) {
		WebElement element = getElement(driver, locator);
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				element);
		return status;
	}

	public boolean waitToJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofMinutes(30));
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}

			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");

			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public WebElement getElement(WebDriver driver, String xpathLocator)//
	{

		return driver.findElement(By.xpath(xpathLocator));
	}

	public String generateEmail() {
		Random rand = new Random();
		return "autotest" + rand.nextInt(9999) + "@live.co";
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
