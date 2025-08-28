package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class T11_12_Popup_WindowsTab {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait wait;
	Select select;
	Actions actions;

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		actions = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
		driver.manage().window().maximize();
	}

	public void TC01_PopupInDOM_() {
		driver.get("https://ngoaingu24h.vn/");
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='auth-form']")).isDisplayed());
		driver.findElement(By.xpath("//div[@class='input-item']//input[@autocomplete='username']"))
				.sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@class='input-item']//input[@name='password']")).sendKeys("automationfc123");
		driver.findElement(By.xpath("//div[@class='forgot-password']/following::div//button")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='notistack-snackbar']")).getText(),
				"Bạn đã nhập sai tài khoản hoặc mật khẩu!");
	}

	public void TC02_PopupInDOM_() {
		driver.get("https://skills.kynaenglish.vn/dang-nhap");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='right']")).isDisplayed());
		driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automation323@hotmail.com");
		driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='password-form-login-message']")).getText(),
				"Sai tên đăng nhập hoặc mật khẩu");
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
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(),
				"Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(),
				"Mật khẩu không được để trống");
		driver.findElement(By.xpath("//img[@class='close-img']")).click();
		// Assert.assertFalse(driver.findElement(By.xpath("//div[@class='sc-2745a82-0
		// gvDbCz']")).isDisplayed());
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
		// list khoa hoc
		List<WebElement> khoahocText = driver.findElements(By.xpath("//span[@class='ez-toc-section']"));
		List<String> actualCourse = new ArrayList<String>();
		for (WebElement contentKhoaHoc : khoahocText) {
			String text = contentKhoaHoc.getText().trim();
			if (!text.isEmpty()) {
				actualCourse.add(text);
			}
			System.out.println(contentKhoaHoc.getText());
			Assert.assertTrue(actualCourse.isEmpty(), "Danh sách khóa học bị trống!");
		}
	}

	public void TC06_RandomPopup_InNotDOM() {
		driver.get("https://www.javacodegeeks.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		List<WebElement> randomPopup = driver.findElements(By.xpath(
				"//div[@class='lepopup-element lepopup-element-2 lepopup-element-rectangle lepopup-animated lepopup-fadeIn']"));
		if (randomPopup.size() > 0 && randomPopup.get(0).isDisplayed()) {
			// close popup
			driver.findElement(By.xpath("//input[@name='lepopup-20']")).sendKeys("auto123@gmail.com");
			driver.findElement(By.xpath("//a[normalize-space()='×']")).click();

		} else {
			System.out.println("Không có popup");

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys("Agile Testing");
		driver.findElement(By.xpath("//button[@id='search-submit']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='page-title']")).getText(),
				"Search Results for: Agile Testing");
		sleepInSecond(3);
	}

	public void TC07_RandomPopup_InNotDOM() {
		driver.get("https://dehieu.vn/");
		List<WebElement> randomPopup = driver.findElements(By.xpath("//div[@class='modal-content css-modal-bt']"));
		if (randomPopup.size() > 0 && randomPopup.get(0).isDisplayed()) {
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='modal-content css-modal-bt']")).isDisplayed());
			driver.findElement(By.xpath("//input[@placeholder='Họ tên']")).sendKeys("Auto Test");
			driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("auto364@live.com");
			driver.findElement(By.xpath("//input[@placeholder='Số điện thoại']")).sendKeys("0998877559");
			driver.findElement(By.xpath("//button[@id='submit-form']")).click();
			Assert.assertEquals(driver.findElement(By.xpath("//div[@class='css-end-content']//p[2]")).getText(),
					"Bạn đã đăng ký thành công");
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
		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='container'] div h4")).getText(),
				"Tìm thấy 0 khóa học với từ khóa \"Testing\"");
		sleepInSecond(4);
	}

	public void TC08_ShadowDOM() {
		driver.get("https://automationfc.github.io/shadow-dom");
		// 1. Tìm phần tử host (div với id="shadow_host")
		WebElement shadowHost = driver.findElement(By.id("shadow_host"));

		// 2. Lấy shadow root
		SearchContext shadowRoot = shadowHost.getShadowRoot();

		// 3. Tìm phần tử "some text" bên trong shadow root
		WebElement someText = shadowRoot.findElement(By.cssSelector("span.info"));
		//
		Assert.assertEquals(someText.getText(), "some text");

		// tìm phần tử nested text bên trong shadow root
		WebElement nestedShadowHost = shadowRoot.findElement(By.cssSelector("#nested_shadow_host"));
		SearchContext nestedShadow_Root = nestedShadowHost.getShadowRoot();
		WebElement nestedtextElement = nestedShadow_Root.findElement(By.cssSelector("#nested_shadow_content > div"));
		Assert.assertEquals(nestedtextElement.getText(), "nested text");

		// checkbox
		WebElement checbox = shadowRoot.findElement(By.cssSelector("input[type='checkbox']"));
		sleepInSecond(3);
		checbox.click();
		sleepInSecond(3);
		Assert.assertTrue(checbox.isSelected());
	}

	public void TC09_Shadow_Popup() {
		driver.get("https://books-pwakit.appspot.com/");

		WebElement shadowHost = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));
		SearchContext shadowRoot1 = shadowHost.getShadowRoot();
		WebElement searchText1 = shadowRoot1.findElement(By.cssSelector("#input"));
		searchText1.sendKeys("Harry Potter");

		WebElement searchIcon_ShadowHost = shadowRoot1.findElement(By.cssSelector("book-input-decorator"));
		SearchContext shadowRoot2 = searchIcon_ShadowHost.getShadowRoot();
		// Tìm và click icon search trước
		WebElement searchIcon = shadowRoot2.findElement(By.cssSelector("div.icon"));
		searchIcon.click();
		// 1. Scroll thật như người dùng để kích hoạt lazy-load / virtualize
		Actions act = new Actions(driver);
		for (int k = 0; k < 6; k++) { // scroll vài nhịp là đủ
			act.sendKeys(Keys.PAGE_DOWN).perform();
			sleepInSecond(3);
		}
		sleepInSecond(5);

		// 2. Dò toàn bộ shadow tree và lấy tối đa 20 title từ mọi <book-item>
		@SuppressWarnings("unchecked")
		List<String> titles = (List<String>) jsExecutor.executeScript(
				"const out=[];" + "const visit = (root) => {" + "  const items = root.querySelectorAll('book-item');"
						+ "  items.forEach(it => {" + "    const sr = it.shadowRoot;" + "    if (sr) {"
						+ "      const el = sr.querySelector('.title, h2, div');" + "      if (el) {"
						+ "        const s = (el.textContent || '').trim();" + "        if (s) out.push(s);" + "      }"
						+ "    }" + "  });" + "  root.querySelectorAll('*').forEach(el => {"
						+ "    if (el.shadowRoot) visit(el.shadowRoot);" + "  });" + "};" + "visit(document);"
						+ "return out.slice(0, 20);");

		// 3. In kết quả (tối đa 20)
		if (titles == null || titles.isEmpty()) {
			System.out.println("Danh sách 0 tiêu đề sách:");
		} else {
			System.out.println("Danh sách " + titles.size() + " tiêu đề sách:");
			for (int i = 0; i < titles.size(); i++) {
				System.out.println((i + 1) + ". " + titles.get(i));
			}
		}
	}

	public void TC10_IFrame() {
		driver.get("https://toidicodedao.com/");
		driver.switchTo().frame(driver.findElement(By.cssSelector(".fb_iframe_widget iframe")));
		Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'Tôi đi code dạo')]")).getText(),
				"Tôi đi code dạo");
		Assert.assertEquals(driver
				.findElement(By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div")).getText(),
				"396,210 followers");

	}

	public void TC11_basiciFram_formsite() {
		driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
		driver.findElement(By.xpath("//button[contains(@class,'osano-cm-close')]")).click();

		// Scroll và click để load form
		WebElement preview = driver.findElement(By.id("imageTemplateContainer"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", preview);
		jsExecutor.executeScript("arguments[0].click();", preview);
		sleepInSecond(7);

		// Switch vào iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("#formTemplateContainer iframe")));

		// Chọn Year + Residence + Female
		new Select(driver.findElement(By.id("RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
		sleepInSecond(7);
		new Select(driver.findElement(By.id("RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");
		sleepInSecond(7);
		driver.findElement(By.xpath("//label[normalize-space()='Female']")).click();
		sleepInSecond(7);
		driver.switchTo().defaultContent();

		driver.findElement(By.xpath("//a[text()='Pricing']/preceding::a[text()='Log in']")).click();
		driver.findElement(By.xpath("//button[@id='login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='message-error']")).getText(),
				"Username and password are both required.");

	}

	public void TC11_iFrame_formsite() {
		driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

		// Đóng popup cookie
		driver.findElement(By.xpath("//button[@class=' osano-cm-dialog__close osano-cm-close ']")).click();

		try {
			// 1. Scroll nhẹ xuống preview template
			WebElement previewArea = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.id("imageTemplateContainer")));
			jsExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'nearest'});",
					previewArea);
			sleepInSecond(5); // chỉ chờ nhẹ

			// 2. Click preview để kích hoạt form
			jsExecutor.executeScript("arguments[0].click();", previewArea);
			sleepInSecond(5); // chờ form load

			// 3. Switch sang iframe
			wait.until(ExpectedConditions
					.frameToBeAvailableAndSwitchToIt(By.cssSelector("#formTemplateContainer iframe")));
			// 4. Chọn Year
			selectDropdownByVisibleText("//select[@id='RESULT_RadioButton-2']", "Sophomore");

			// 5. Chọn Residence
			selectDropdownByVisibleText("//select[@id='RESULT_RadioButton-3']", "South Dorm");
			// 3. Chọn giới tính Female
			WebElement femaleRadio = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='Female']")));
			scrollToElement(femaleRadio);
			femaleRadio.click();
			sleepInSecond(10);

			// 6. Trở lại default content
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Scroll đến element trước khi click

	public void scrollToElement(WebElement element) {
		jsExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
		sleepInSecond(1);
	}

	// Hàm chọn dropdown bằng text hiển thị

	public void selectDropdownByVisibleText(String dropdownXpath, String text) {
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXpath)));
		scrollToElement(dropdown);
		select = new Select(dropdown);
		select.selectByVisibleText(text);
	}

	public void TC11_iFrame_jqueryui() {
		/*
		 * driver.get("https://jqueryui.com/dialog/#modal-form"); // Switch vào iframe
		 * driver.switchTo().frame(driver.findElement(By.cssSelector("#content > iframe"
		 * ))); driver.findElement(By.xpath("//button[@id='create-user']")).click();
		 * WebElement nameInput = driver.findElement(By.xpath("//input[@id='name']"));
		 * nameInput.clear(); nameInput.sendKeys("Test Auto Check");
		 * 
		 * WebElement emailInput = driver.findElement(By.xpath("//input[@id='email']"));
		 * emailInput.clear(); emailInput.sendKeys("auto236@live.com");
		 * 
		 * WebElement passInput =
		 * driver.findElement(By.xpath("//input[@id='password']")); passInput.clear();
		 * passInput.sendKeys("123456");
		 * 
		 * driver.findElement(By.xpath("//button[normalize-space()='Create an account']"
		 * )).click(); //driver.findElement(By.
		 * xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).click();
		 * sleepInSecond(7);
		 */
		// -------------
		driver.get("https://www.embedgooglemap.net/");

		// driver.switchTo().frame(driver.findElement(By.cssSelector("#recaptcha-demo >
		// div > div > iframe")));
		driver.findElement(By.xpath("//button[normalize-space()='+']")).click();
		sleepInSecond(7);
		driver.findElement(By.xpath("//button[normalize-space()='-']")).click();

		sleepInSecond(7);
		driver.findElement(By.xpath("//a[normalize-space()='ON']")).click();
		sleepInSecond(7);
		// Actions actions = new Actions(driver);
		// slide
		WebElement widthSlider = driver.findElement(By.cssSelector("div#slider"));
		WebElement heightSlider = driver.findElement(By.cssSelector("div#slider2"));
		// Kéo sang phải 50 pixel
		actions.dragAndDropBy(widthSlider, 50, 0).perform();

		// Hoặc kéo sang trái 30 pixel
		actions.dragAndDropBy(widthSlider, -30, 0).perform();
		// Kéo sang phải 50 pixel
		actions.dragAndDropBy(heightSlider, 80, 0).perform();
		sleepInSecond(7);
		// Hoặc kéo sang trái 30 pixel
		actions.dragAndDropBy(heightSlider, -350, 0).perform();
		sleepInSecond(7);
	}

	public void TC13_Frame_netbanking() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("autotest35624");
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[normalize-space()='CONTINUE']")).click();
		driver.switchTo().defaultContent();
		sleepInSecond(7);
		// driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("autotest35624");
		driver.findElement(By.xpath("//input[@id='keyboard']")).sendKeys("123456");
		sleepInSecond(7);
		driver.findElement(By.id("loginBtn")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='error-msg ng-binding ng-scope']")).getText(),
				"Customer ID/IPIN (Password) is invalid. Please try again.");
		sleepInSecond(7);
	}

	
	public void TC13_WindowTab_automationfc() {
//		Step 01 - Truy cập vào trang: https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html ");
		String parentID=driver.getWindowHandle();
		//		Step 02 - Click "GOOGLE" link -> Switch qua tab mới
		driver.findElement(By.xpath("//a[normalize-space()='GOOGLE']")).click();
		sleepInSecond(10);
		// Step 03 - Kiểm tra title của window mới = Google
		switchToWindowByTitle("GOOGLE");
		sleepInSecond(7);
		Assert.assertEquals(driver.getTitle(), "Google");
		//in ra title new tab
		System.out.println("1. Title windows is:"+driver.getTitle());
		System.out.println("1. ID windows is:"+driver.getWindowHandle());
		
		// Step 04 - Switch về parent window
		switchToWindowByUrl("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(7);

//			Step 05 - Click "FACEBOOK" link -> Switch qua tab mới
		driver.findElement(By.xpath("//a[normalize-space()='FACEBOOK']")).click();
		switchToWindowByTitle("Facebook – log in or sign up");
//			Step 06 - Kiểm tra title của window mới = Facebook - Đăng nhập hoặc đăng ký
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		System.out.println("2. Title windows is:"+driver.getTitle());
//			Step 07 - Switch về parent window
		switchToWindowByTitle("Selenium WebDriver");
		sleepInSecond(7);
//			Step 08 - Click "TIKI" link -> Switch qua tab mới
		driver.findElement(By.xpath("//a[normalize-space()='TIKI']")).click();
		switchToWindowByUrl("https://tiki.vn/");
		sleepInSecond(7);
//			Step 09 - Kiểm tra title của window mới = Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		System.out.println("3. Title windows is:"+driver.getTitle());
//			Step 10 - Close tất cả cửa sổ/tab ngoại trừ parent window 
		sleepInSecond(7);
		//11. Kiểm tra đã quay về window parent thành công
		Assert.assertTrue(closeAllWindowsWithoutParent(parentID));
		System.out.println("3. Title windows is:"+parentID);
	}

	public void switchToWindowByTitle(String title) {
		String parentWindow = driver.getWindowHandle();
		new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(d -> d.getWindowHandles().size() > 1);
		Set<String> allWindows = driver.getWindowHandles();
		//System.out.println("All window handles: " + allWindows);
		for (String runWindows : allWindows) {
			//driver.switchTo().window(runWindows);
			//String currentWindows = driver.getTitle();
			if (!runWindows.equals(parentWindow)) {
	            driver.switchTo().window(runWindows);
	           // System.out.println("Switched to window title: " + driver.getTitle());
	            break;
	        }
		}
		new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.titleContains(title));

    String actualTitle = driver.getTitle();
   // System.out.println("Actual title: " + actualTitle);

    Assert.assertTrue(
        actualTitle.contains(title),
        "Expected title to contain: " + title + ", but got: " + actualTitle);
	}

	public void switchToWindowByUrl(String uRl) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWindows = driver.getCurrentUrl();
			if (currentWindows.contains(uRl)) {
				return;
			}
		}

	}

	public boolean closeAllWindowsWithoutParent(String parentID) {
		  Set<String> allWindows = driver.getWindowHandles();
		    for (String windowID : allWindows) {
		        if (!windowID.equals(parentID)) {
		            driver.switchTo().window(windowID);
		            driver.close();
		        }
		    }
		    // Quay lại parent
		    driver.switchTo().window(parentID);

		    // Trả về true nếu chỉ còn 1 tab (parent)
		    return driver.getWindowHandles().size() == 1;
	}
	
	public void TC14_WindowsTab_techpanda() {
//		Step 01 - Truy cập vào trang: http://live.techpanda.org/
		driver.get("https://live.techpanda.org/");
		String parentID=driver.getWindowHandle();
//		Step 02 - Click vào Mobile tab
		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();
		//khi click sang Mobile tab là đã chuyển page
		switchToWindowByTitle("Mobile");
//		Step 03 - Add sản phẩm Sony Xperia vào để Compare (Add to Compare)
		driver.findElement(By.xpath("(//a[@class='link-compare'][normalize-space()='Add to Compare'])[1]")).click();
//		Verify text hiển thị: The product Sony Xperia has been added to comparison list.
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Sony Xperia')]")).getText(), "The product Sony Xperia has been added to comparison list.");
//		Step 04 - Add sản phẩm Samsung Galaxy vào để Compare (Add to Compare)
		driver.findElement(By.xpath("(//a[@class='link-compare'][normalize-space()='Add to Compare'])[3]")).click();
//		Verify text hiển thị: The product Samsung Galaxy has been added to comparison list.
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Samsung Galaxy')]")).getText(), "The product Samsung Galaxy has been added to comparison list.");
//		Step 05 - Click to Compare button
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
//		Step 06 - Switch qua cửa sổ mới (chứa 2 sản phẩm đã được Add vào để Compare)
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
//		Step 07 - Verify title của cửa sổ bằng: Products Comparison List - Magento Commerce
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
//		Step 08 - Close tab và chuyển về Parent Window
		
		sleepInSecond(7);
		driver.findElement(By.xpath("//span[text()='Close Window']")).click();
		switchToWindowByTitle("Mobile");
//		Step 09 - Click 'Clear All' link và accept alert
		driver.findElement(By.xpath("//a[normalize-space()='Clear All']")).click();
		sleepInSecond(7);
		Alert alert=driver.switchTo().alert();
		sleepInSecond(7);
		alert.accept();
//		Step 10 - Verify message xuất hiện: The comparison list was cleared.	
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='The comparison list was cleared.']")).getText(), "The comparison list was cleared.");
		}
	
	public void TC15_WindowsTab_dictionary() {
		driver.get("https://dictionary.cambridge.org/vi/");
		String parentID=driver.getWindowHandle();
		//1. Click vào dăng nhập link
		driver.findElement(By.xpath("//span[@class='tb'][contains(text(),'Đăng nhập')]")).click();
		switchToWindowByTitle("Login");
		//2. Click Login button
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		//3. Verify mesage
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='gigya-error-msg-gigya-login-form-password']")).getText(), "This field is required");
		//4. Close cửa sổ login và switch về tab trước đó
		closeAllWindowsWithoutParent(parentID);
		//5. NHập key vào ô Search -> click Search
		driver.findElement(By.xpath("//input[@id='searchword']")).sendKeys("Test auto");
		driver.findElement(By.xpath("//button[@title='Tìm kiếm']")).click();
		
		//6. Verify page Search chứa key mới tìm kiếm
		switchToWindowByTitle("Test auto - Bạn đã đánh vần đúng chưa. Các cách đánh vần khác trong Từ điển tiếng Anh Anh - Cambridge Dictionary");
		Assert.assertEquals(driver.getTitle(), "Test auto - Bạn đã đánh vần đúng chưa. Các cách đánh vần khác trong Từ điển tiếng Anh Anh - Cambridge Dictionary");
	}
	@Test
	public void TC16_() {
		driver.get("https://courses.dce.harvard.edu/");
		String parentID=driver.getWindowHandle();
//		2. Click Student Login
		driver.findElement(By.xpath("//a[normalize-space()='Student Login']")).click();
		sleepInSecond(5);
		
		
//		3. Switch qua cưả sổ mới
		switchToWindowByTitle("Harvard Division of Continuing Education Login Portal");
//		4. Verify màn hình Login Portal
		sleepInSecond(5);
		Assert.assertEquals(driver.getTitle(), "Harvard Division of Continuing Education Login Portal");
//		5. Close login window & về trang trước
		sleepInSecond(5);
		closeAllWindowsWithoutParent(parentID);
		sleepInSecond(5);
//		6. Verify màn hình authentication
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='sam-wait__head ']")).isDisplayed());
//		7. Close popup authentication và nhập key ô Search
		driver.findElement(By.xpath("//button[@class='fa fa-times sam-wait__close']")).click();
		//
		driver.findElement(By.xpath("//input[@id='crit-keyword']")).sendKeys("Opioid Epidemic");
		driver.findElement(By.xpath("//button[@id='search-button']")).click();
//		8. Verify thông tin course hiển thị
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='result__title']")).getText(), "The Opioid Epidemic");
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
