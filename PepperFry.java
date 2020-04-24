package first21Days;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PepperFry {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		// disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		// Launching Chrome Browser
		ChromeDriver driver = new ChromeDriver(options);
		// To Load the url
		driver.get("https://www.pepperfry.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		//2) Mouseover on Furniture and click Office Chairs under Chairs 
		WebElement eleFurniture = driver.findElementByXPath("(//a[text()='Furniture'])[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleFurniture).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("(//a[text()='Office Chairs'])[1]").click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//div[@id='regPopUp']//a)[1]")));
		driver.findElementByXPath("(//div[@id='regPopUp']//a)[1]").click();
		Thread.sleep(2000);
		//3) click Executive Chairs 
		driver.findElementByXPath("(//h5[text()='Executive Chairs'])[1]").click();
		Thread.sleep(3000);
		
		//4) Change the minimum Height as 50 in under Dimensions 
		WebElement height = driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]");
		height.clear();
		height.sendKeys("50",Keys.ENTER);
		Thread.sleep(3000);
		
		//5) Add "Poise Executive Chair in Black Colour" chair to Wishlist 
		driver.findElementByXPath("//a[@data-productname='Poise Executive Chair in Black Colour']").click();
		
		//6) Mouseover on Homeware and Click Pressure Cookers under Cookware 
		WebElement eleHomeware = driver.findElementByXPath("(//a[text()='Homeware'])[1]");
		Actions builder1= new Actions(driver);
		builder1.moveToElement(eleHomeware).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("(//a[text()='Pressure Cookers'])[1]").click();
		Thread.sleep(3000);
		
		//7) Select Prestige as Brand 
		driver.findElementByXPath("(//label[text()='Prestige'])[1]").click();
		Thread.sleep(2000);
		
		//8) Select Capacity as 1-3 Ltr 
		driver.findElementByXPath("//label[@for='capacity_db1_Ltr_-_3_Ltr']").click();
		Thread.sleep(3000);
		
		//9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist 
		driver.findElementByXPath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']").click();
		
		//10) Verify the number of items in Wishlist 
		String noOfItems = driver.findElementByXPath("(//span[@class='count_alert'])[2]").getText();
		if(noOfItems.equalsIgnoreCase("2")) {
			System.out.println("The no of items added in the wishlist is 2");
		}else {
			System.out.println("The no of items added in the wishlist is not equal to 2");
		}
		
	
		//11) Navigate to Wishlist 
		driver.findElementByXPath("//div[@class='wishlist_bar']/a").click();
		Thread.sleep(3000);
		
		driver.switchTo().activeElement();
		//12) Move Pressure Cooker only to Cart from Wishlist 
		//driver.executeScript("window.scrollBy(0, 750)");
		//WebDriverWait wait1 = new WebDriverWait(driver, 30);
	//wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr By')]/following::a[@class='addtocart_icon'])[2]")));
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		
		WebElement addToCart = driver.findElementByXPath("(//a[contains(text(),'Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr By')]/following::a[@class='addtocart_icon'])[2]");
		js.executeScript("arguments[0].click()", addToCart);

		Thread.sleep(2000);
		
		//13) Check for the availability for Pincode 600128 
		driver.findElementByXPath("//input[@class='srvc_pin_text']").sendKeys("600061");
		driver.findElementByXPath("//a[text()='Check']").click();
		Thread.sleep(2000);
		//14) Click Proceed to Pay Securely 
		driver.findElementByXPath("//a[contains(text(),'Proceed to pay securely')]").click();
		Thread.sleep(2000);
		
		//15 Click Proceed to Pay 
		driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();
		Thread.sleep(3000);
		
		//16) Capture the screenshot of the item under Order Item 
		driver.findElementByXPath("//div[@id='ordersummary_accordian_header']").click();
		Thread.sleep(2000);
		File src = driver.findElementByXPath("//div[@class='slick-list draggable']//li").getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/snap1.png");
		FileUtils.copyFile(src, dst);
		//17) Close the browser
		driver.quit();
	}

}
