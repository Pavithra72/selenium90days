package first21Days;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Airbnb {

	public static void main(String[] args) throws InterruptedException {
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
		// To maximize the browser
		driver.manage().window().maximize();

		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);

//			1) Go to https://www.airbnb.co.in/
		driver.get("https://www.airbnb.co.in/");
		Thread.sleep(3000);
		WebElement eleBtnOk = driver.findElementByXPath("//button[@class='optanon-allow-all accept-cookies-button']");
		js.executeScript("arguments[0].click()", eleBtnOk);

//			2) Type Coorg in location and Select Coorg, Karnataka
		driver.findElementByXPath("//input[@id='bigsearch-query-attached-query']").sendKeys("Coorg");
		driver.findElementByXPath("//div[text()='Coorg, Karnataka']").click();
		Thread.sleep(2000);
//			3) Select the Start Date as June 1st and End Date as June 5th
		driver.findElementByXPath("(//div[text()='June 2020']/following::div[contains(text(),'1')])[1]").click();
		driver.findElementByXPath("(//div[text()='June 2020']/following::div[contains(text(),'5')])[1]").click();

//			4) Select guests as 6 adults, 3 child and Click Search
		driver.findElementByXPath("//div[text()='Add guests']").click();
		for (int i = 0; i < 6; i++) {
			driver.findElementByXPath("(//button[@aria-label='increase value'])[1]").click();
		}
		for (int i = 0; i < 3; i++) {
			driver.findElementByXPath("(//button[@aria-label='increase value'])[2]").click();
		}
		WebElement eleSearch = driver.findElementByXPath("//span[text()='Search']");
		js.executeScript("arguments[0].click()", eleSearch);

		Thread.sleep(3000);

//			5) Click Cancellation flexibility and enable the filter and Save
		driver.findElementByXPath("(//span[text()='Cancellation flexibility'])[1]").click();
		driver.findElementByXPath("//button[@id='filterItem-switch-flexible_cancellation-true']").click();
		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();
		Thread.sleep(3000);

//			6) Select Type of Place as Entire Place and Save
		driver.findElementByXPath("(//span[text()='Type of place'])[1]").click();
		driver.findElementByXPath("//div[text()='Entire place']").click();
		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();
		Thread.sleep(3000);

//			7) Set Min price as 3000 and  max price as 5000
		driver.findElementByXPath("(//span[text()='Price'])[1]").click();
		Thread.sleep(500);
		WebElement eleMinPrice = driver.findElementByXPath("//div[@class='_fywymp7']/input[@id='price_filter_min']");
		actions.doubleClick(eleMinPrice).perform();
		// eleMinPrice.clear();
		eleMinPrice.sendKeys("3000");
		Thread.sleep(500);
		WebElement eleMaxPrice = driver.findElementByXPath("//div[@class='_fywymp7']/input[@id='price_filter_max']");
		// actions.doubleClick().perform();
		// actions.doubleClick(eleMaxPrice).perform();
		String maxPrice = eleMaxPrice.getAttribute("value");
		// System.out.println(maxPrice);
		int maxPriceLen = maxPrice.length();
		// System.out.println(maxPriceLen);
		for (int j = 0; j < maxPriceLen; j++) {
			eleMaxPrice.sendKeys(Keys.BACK_SPACE);
		}
		eleMaxPrice.sendKeys("5000");
		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();
		Thread.sleep(3000);

//			8) Click More Filters and set 3 Bedrooms and 3 Bathrooms
		driver.findElementByXPath("(//span[text()='More filters'])[1]").click();
		Thread.sleep(500);
		for (int k = 0; k < 3; k++) {
			driver.findElementByXPath("(//button[@aria-label='increase value'])[2]").click();
			driver.findElementByXPath("(//button[@aria-label='increase value'])[3]").click();
		}

//			9) Check the Amenities with Kitchen, Facilities with Free parking on premisses, Property as House and Host Language as English
//			    and click on Stays only when stays available
		driver.findElementByXPath("//div[text()='Kitchen']").click();
		driver.findElementByXPath("//div[text()='Free parking on premises']").click();
		driver.findElementByXPath("//div[text()='House']").click();
		driver.findElementByXPath("//div[text()='English']").click();
		String btnText = driver.findElementByXPath("//button[@class='_2i58o3a']").getText();
		// System.out.println(btnText);
		String repBtnText = btnText.replaceAll("[^0-9]", "");
		// System.out.println(repBtnText);
		int intBtnText = Integer.parseInt(repBtnText);
		if (intBtnText > 0) {
			driver.findElementByXPath("//button[@class='_2i58o3a']").click();
		}
		String strDate = driver.findElementByXPath("(//button[@class='_37g0dr4'])[2]").getText();
		String strGuests = driver.findElementByXPath("(//button[@class='_37g0dr4'])[3]").getText();
		// System.out.println("The date choosed for vacation is :"+strDate);
		// System.out.println("no of guests : "+strGuests);
		Thread.sleep(1000);
//			10) Click Prahari Nivas, the complete house
		WebElement elePrahari = driver.findElementByXPath("(//a[@aria-label='Prahari Nivas, the complete house'])[1]");
		js.executeScript("arguments[0].click()", elePrahari);

		Set<String> allWindows = driver.getWindowHandles();
		List<String> allLists = new ArrayList<String>(allWindows);
		driver.switchTo().window(allLists.get(1));
		Thread.sleep(10000);
		driver.executeScript("window.scrollBy(0, 1230)");
//			11) Click on "Show all * amenities"
		WebElement eleShowAll = driver.findElementByXPath("//button[contains(text(),'Show all') and contains(text(),'amenities')]");
		//wait.until(
			//	ExpectedConditions.visibilityOf(driver.findElementByXPath("//[contains(text(),'Show all') and contains(text(),'amenities')]")));
		js.executeScript("arguments[0].click()", eleShowAll);

		Thread.sleep(2000);

//			12) Print all the Not included amenities
		System.out.println("The Not included amenities are below: ");
		List<WebElement> lstNonAmenities = driver.findElementsByXPath("//div[text()='Not included']//following::del");
		int sizeLstNonAmenities = lstNonAmenities.size();
		for (int a = 0; a < sizeLstNonAmenities; a++) {
			String allNonAmenities = lstNonAmenities.get(a).getText();
			System.out.println(allNonAmenities);
		}
		driver.findElementByXPath("//div[@aria-label='Amenities']//button[@aria-label='Close']").click();
		// 13) Verify the Check-in date, Check-out date and Guests
		String strCheckinDate = driver.findElementByXPath("//input[@id='checkin']").getAttribute("value");
		String strCheckoutDate = driver.findElementByXPath("//input[@id='checkout']").getAttribute("value");
		String strGuest = driver.findElementByXPath("//span[@class='guest-label__text guest-label__text-guests']")
				.getText();
		if (strCheckinDate.equalsIgnoreCase("2020-06-01") && strCheckoutDate.equalsIgnoreCase("2020-06-05")) {
			System.out.println("The Checkin date is : " + strCheckinDate);
			System.out.println("The Checkout date is : " + strCheckoutDate);
		}
		if (strGuest.equalsIgnoreCase("9 guests")) {
			System.out.println("The No of guests are :" + strGuest);
		}
//			14) Read all the Sleeping arrangements and Print
		Map<String, String> maps = new LinkedHashMap<String, String>();
		List<WebElement> lstBedrooms = driver
				.findElementsByXPath("//div[@class='_7y0rt79']//following-sibling::div[@class='_1p3joamp']");
		List<WebElement> lstBeds = driver
				.findElementsByXPath("//div[@class='_7y0rt79']//following-sibling::div[@class='_czm8crp']");
		int bedroomsSize = lstBedrooms.size();
		for (int k = 0; k < bedroomsSize; k++) {
			String mapOverview = lstBedrooms.get(k).getText();
			String mapFeatures = lstBeds.get(k).getText();
			maps.put(mapOverview, mapFeatures);
		}
//		for (Entry<String, String> each : maps.entrySet()) {
//			System.out.println(each.getKey() + " ---------- " + each.getValue());
//
//		}
		for (int b=0;b<2;b++) {
			driver.findElementByXPath("//div[@class='_1mlprnc' and contains(@style,'right')]").click();
		}
		Thread.sleep(2000);

//		for (int b=1; b<=100; b++) {
//			try {
//			if(driver.findElementByXPath("//div[@class='_1mlprnc' and contains(@style,'right')]").isEnabled()){
//			driver.findElementByXPath("//div[@class='_1mlprnc' and contains(@style,'right')]").click();
//			
//			}else {
//				break;
//			}
//			}
//		catch(NoSuchElementException e) {
//			break;
//		}
//		}
//		Thread.sleep(2000);
		List<WebElement> lstBedrooms1 = driver
				.findElementsByXPath("//div[@class='_7y0rt79']//following-sibling::div[@class='_1p3joamp']");
		List<WebElement> lstBeds1 = driver
				.findElementsByXPath("//div[@class='_7y0rt79']//following-sibling::div[@class='_czm8crp']");
		int bedroomsSize1 = lstBedrooms1.size();
		for (int k = 0; k < bedroomsSize1; k++) {
			String mapOverview1 = lstBedrooms1.get(k).getText();
			String mapFeatures1 = lstBeds1.get(k).getText();
			maps.put(mapOverview1, mapFeatures1);
		}

//System.out.println(maps);
		for (Entry<String, String> each : maps.entrySet()) {
			System.out.println(each.getKey() + " ---------- " + each.getValue());

		}

//			15) Close all the browsers
		driver.quit();

	}

}

