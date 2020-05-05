package first21Days;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zalando {

	public static void main(String[] args) throws InterruptedException {
//			1) Go to https://www.zalando.com/

		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		// disable the notifications

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		options.addArguments("--incognito");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		// Launching Chrome Browser
		ChromeDriver driver = new ChromeDriver(options);
		// To Load the url
		driver.get("https://www.zalando.com/");

//		2) Get the Alert text and print it
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("The Alert text is : " + alertText);

//		3) Close the Alert box and click on Zalando.uk
		alert.accept();

		// To maximize the browser
		driver.manage().window().maximize();

		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);

		driver.findElementByXPath("//a[text()='Zalando.uk']").click();
		Thread.sleep(3000);
		// driver.findElementByXPath("//div[@class='uc-btn-footer-container']").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[@id='uc-btn-accept-banner']")));
		WebElement eleThatsOk = driver.findElementByXPath("//button[text()[normalize-space()='That’s OK']]");
		js.executeScript("arguments[0].click()", eleThatsOk);
		Thread.sleep(2000);

//			4) Click Women--> Clothing and click Coat 
		driver.findElementByXPath("(//span[text()='Women'])[1]").click();
		Thread.sleep(2000);
		WebElement eleClothing = driver.findElementByXPath("//span[text()='Clothing']");
		actions.moveToElement(eleClothing).perform();
		driver.findElementByXPath("//span[text()='Coats']").click();
		Thread.sleep(3000);

//			5) Choose Material as cotton (100%) and Length as thigh-length
		driver.findElementByXPath("//span[text()='Length']").click();
		driver.findElementByXPath("//span[text()='thigh-length']").click();
		WebElement eleLengthSave = driver.findElementByXPath("//button[text()='Save']");
		js.executeScript("arguments[0].click()", eleLengthSave);

		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Material']").click();
		driver.findElementByXPath("//span[text()='cotton (100%)']").click();
		WebElement eleMaterialSave = driver.findElementByXPath("//button[text()='Save']");
		js.executeScript("arguments[0].click()", eleMaterialSave);

		Thread.sleep(3000);

//			6) Click on Q/S designed by MANTEL - Parka coat
		driver.findElementByXPath("//div[text()='MANTEL - Parka - navy']").click();
		Thread.sleep(2000);

//			7) Check the availability for Color as Olive and Size as 'M'
		driver.findElementByXPath("(//img[@alt='olive'])[2]").click();
		Thread.sleep(1000);

//		8) If the previous preference is not available, check  availability for Color Navy and Size 'M'
		if (driver.findElementByXPath("//button[@id='picker-trigger']").isEnabled() == true || driver
				.findElementByXPath("//h2[text()='Out of stock']").getText().equalsIgnoreCase("Out of stock")) {
			driver.findElementByXPath("(//img[@alt='navy'])[2]").click();
			driver.findElementByXPath("//span[text()='Choose your size']").click();
			driver.findElementByXPath("//span[text()='M']").click();
		} else {
			driver.findElementByXPath("//span[text()='Choose your size']").click();
			if (driver.findElementByXPath("//span[text()='M']").isEnabled() == true) {
				driver.findElementByXPath("//span[text()='M']").click();
			} else {
				System.out.println("The size M not available");
			}
		}

//			9) Add to bag only if Standard Delivery is free
		if (driver.findElementByXPath("(//span[@class='AtOZbZ'])[2]").getText().equalsIgnoreCase("Free")) {
			driver.findElementByXPath("//button[@aria-label='Add to bag']").click();
			Thread.sleep(2000);
		} else {
			System.out.println("The standard delivery is not free so not adding to cart.");
		}
//			10) Mouse over on Your Bag and Click on "Go to Bag"
		WebElement addToCart = driver.findElementByXPath("//a[@class='z-navicat-header_navToolItemLink']");
		actions.moveToElement(addToCart).perform();
		driver.findElementByXPath("//div[text()='Go to bag']").click();
		Thread.sleep(2000);

//			11) Capture the Estimated Deliver Date and print
		String strEstDeliverDate = driver.findElementByXPath("//div[@data-id='delivery-estimation']//span").getText();
		System.out.println("The Estimated Deliver Date for the Coat is : " + strEstDeliverDate);

//			12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print
		WebElement eleFreeDelivery = driver
				.findElementByXPath("(//span[@class='z-navicat-header-uspBar_message-split_styled'])[2]");
		actions.moveToElement(eleFreeDelivery).perform();
		String strToolTip = eleFreeDelivery.getAttribute("title");
		System.out.println("The Tool Tip of free Delivery & Returns is : " + strToolTip);

//			13) Click on FREE DELIVERY & RETURNS
		eleFreeDelivery.click();
		Thread.sleep(3000);

//			14) Click on Start chat in the Start chat and go to the new window
		if (driver.findElementByXPath("//span[text()='Start chat']").isEnabled() == true) {
			driver.findElementByXPath("//span[text()='Start chat']").click();
		} else {
			System.out.println("The chat box not enabled");
		}

//			15) Enter you first name and a dummy email and click Start Chat
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allList = new ArrayList<String>(allWindows);
		driver.switchTo().window(allList.get(1));
		driver.findElementByXPath("//input[@id='prechat_customer_name_id']").sendKeys("Ashwath");
		driver.findElementByXPath("//input[@id='prechat_customer_email_id']").sendKeys("Ashu@ddd.com");
		driver.findElementByXPath("//button[@id='prechat_submit']").click();
		Thread.sleep(5000);

//			16) Type Hi, click Send and print thr reply message and close the chat window.
		driver.findElementByXPath("//textarea[@id='liveAgentChatTextArea']").sendKeys("Hi");
		driver.findElementByXPath("//button[text()='Send']").click();
		String strReply = driver.findElementByXPath("//span[@class='client']/following-sibling::span[1]").getText();
		System.out.println("The Reply message is : " + strReply);

		driver.quit();

	}

}
