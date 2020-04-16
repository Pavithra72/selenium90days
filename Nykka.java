package first21Days;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Nykka {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		// disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		// Launching Chrome Browser
		ChromeDriver driver = new ChromeDriver(options);
		// To Load the url https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Mouseover on Brands and Mouseover on Popular
		WebElement eleBrand = driver.findElementByXPath("//a[text()='brands']");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleBrand).perform();

		WebElement elePopular = driver.findElementByXPath("//a[text()='Popular']");
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(elePopular).perform();

		// Click L'Oreal Paris
		driver.findElementByXPath("(//li[@class='brand-logo menu-links']//img)[5]").click();
		Thread.sleep(3000);
		// Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allhandles = new ArrayList<String>(allWindows);
		driver.switchTo().window(allhandles.get(1));
		String titleofcurrrentpage = driver.getTitle();
		 System.out.println("The Title of the page is " + titleofcurrrentpage);
		if (titleofcurrrentpage.contains("L'Oreal Paris")) {
			System.out.println("The Title of the page contains L'Oreal paris");
		} else {
			System.out.println("The Title of the page doesn't contain L'Oreal paris");
		}

		// Click sort By and select customer top rated
		driver.findElementByXPath("//i[@class='fa fa-angle-down']").click();
		driver.findElementByXPath("//input[@id='3']/following-sibling::div[1]").click();
		Thread.sleep(1000);

		// Click Category and click Shampoo
		driver.findElementByXPath("(//div[@class='pull-right filter-options-toggle'])[1]").click();
		driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined'])[1]").click();

		// check whether the Filter is applied with Shampoo
		String filterOption = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']").getText();
		// System.out.println(filterOption);
		if (filterOption.contains("Shampoo")) {
			System.out.println("The filter is applied with Shampoo");
		} else {
			System.out.println("The filter is not applied with Shampoo");
		}
		
		//Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElementByXPath("//span[text()=\"L'Oreal Paris Colour Protect Shampoo\"]").click();
		
		//GO to the new window and select size as 175ml
		Set<String> allWindows1 = driver.getWindowHandles();
		List<String> allhandles1 = new ArrayList<String>(allWindows1);
		driver.switchTo().window(allhandles1.get(2));
		
		driver.findElementByXPath("//span[text()='175ml']").click();
		
		//Print the MRP of the product
		String mrp = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		System.out.println(" The MRP of the shampoo is " + mrp);
		
		// Click on ADD to BAG
		driver.findElementByXPath("(//button[@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  '])[1]").click();
		Thread.sleep(2000);
		//Go to Shopping Bag
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		
		//Print the Grand Total amount
		String grandTotal = driver.findElementByXPath("//div[@class='value medium-strong']").getText();
		System.out.println("The grand total is " + grandTotal);
		
		//Click Proceed
		driver.findElementByXPath("//button[@class='btn full fill no-radius proceed ']").click();
		Thread.sleep(3000);
		
		//Click on Continue as Guest
		driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();
		
		//Print the warning message (delay in shipment)
		String delayMsg = driver.findElementByXPath("//div[@class='message']").getText();
		System.out.println("The message displayed is " + delayMsg);
		// Close all windows
		driver.quit();

	}

}
