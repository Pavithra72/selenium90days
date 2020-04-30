package first21Days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Carwale {

	public static void main(String[] args) throws InterruptedException {
		// 1) Go to https://www.carwale.com/
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
		driver.get("https://www.carwale.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// 2) Click on Used
		driver.findElementByXPath("//li[@data-tabs='usedCars']").click();

		// 3) Select the City as Chennai
		driver.findElementByXPath("//input[@id='usedCarsList']").sendKeys("Chennai");
		driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']").click();

		// 4) Select budget min (8L) and max(12L) and Click Search
		driver.findElementByXPath("//li[text()='8 Lakh']").click();
		driver.findElementByXPath("(//li[text()='12 Lakh'])[2]").click();
		driver.findElementByXPath("(//span[@class='welcome-box__search-icon'])[2]").click();
		Thread.sleep(2000);

		// 5) Select Cars with Photos under Only Show Cars With
		driver.findElementByXPath("//span[text()='Cars with Photos']").click();
		Thread.sleep(3000);

		// 6) Select Manufacturer as "Hyundai" --> Creta

		// wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//li[@data-manufacture-en='Hyundai']//span)[1]")));
		WebElement slcHyundai = driver.findElementByXPath("(//li[@data-manufacture-en='Hyundai']//span)[1]");
		js.executeScript("arguments[0].click()", slcHyundai);
		// wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Creta']")));
		WebElement slcCreta = driver.findElementByXPath("//span[text()='Creta']");
		js.executeScript("arguments[0].click()", slcCreta);
		Thread.sleep(3000);

		// 7) Select Fuel Type as Petrol
		WebElement eleFuelType = driver.findElementByXPath("//h3[contains(text(),'Fuel Type')]");
		js.executeScript("arguments[0].click()", eleFuelType);
		WebElement elePetrol = driver.findElementByXPath("//li[contains(@name,'Petrol')]");
		js.executeScript("arguments[0].click()", elePetrol);
		Thread.sleep(3000);

		// 8) Select Best Match as "KM: Low to High"
		WebElement eleBestMatch = driver.findElementById("sort");
		Select eleprice = new Select(eleBestMatch);
		eleprice.selectByVisibleText("KM: Low to High");
		Thread.sleep(3000);

		// 9) Validate the Cars are listed with KMs Low to High
		String kmsValues = "";
		List<Integer> intKms = new ArrayList<Integer>();
		List<WebElement> lowKms = driver.findElementsByXPath("//span[@class='slkms vehicle-data__item']");
		int sizeKms = lowKms.size();
		for (int i = 0; i < sizeKms; i++) {
			kmsValues = lowKms.get(i).getText();
			String reKms = kmsValues.replaceAll("[^0-9]", "");
			int onlyKmsNos = Integer.parseInt(reKms);
			intKms.add(onlyKmsNos);

		}
		// System.out.println(intKms);
		List<Integer> sortKms = new ArrayList<Integer>(intKms);
		Collections.sort(sortKms);

		if (intKms.equals(sortKms)) {
			System.out.println("The Kilometers are sorted from Low to High");
		} else {
			System.out.println("The Kilometers are not sorted from Low to High");
		}

		// 10) Add the least KM ran car to Wishlist
		for (int j = 0; j < sizeKms; j++) {
			if (intKms.get(j).equals(sortKms.get(0))) {
				WebElement addToCart = driver
						.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])[" + (j + 1) + "]");
				js.executeScript("arguments[0].click()", addToCart);
				Thread.sleep(2000);
				break;
			}
		}
		// 11) Go to Wishlist and Click on More Details
		WebElement wishList = driver.findElementByXPath("//li[@data-action='ShortList&CompareWindow_Click']");
		js.executeScript("arguments[0].click()", wishList);

		WebElement moreDetails = driver.findElementByXPath("//a[contains(text(),'More details')]");
		js.executeScript("arguments[0].click()", moreDetails);

		Thread.sleep(3000);

		// 12) Print all the details under Overview in the Same way as displayed in
		// application
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allList = new ArrayList<String>(allWindows);
		driver.switchTo().window(allList.get(1));
		Map<String, String> details = new LinkedHashMap<String, String>();
		List<WebElement> overview = driver
				.findElementsByXPath("//div[@id='overview']//div[@class='equal-width text-light-grey']");
		List<WebElement> features = driver
				.findElementsByXPath("//div[@id='overview']//div[@class='equal-width dark-text']");
		int detailsSize = overview.size();
		for (int k = 0; k < detailsSize; k++) {
			String mapOverview = overview.get(k).getText();
			String mapFeatures = features.get(k).getText();
			details.put(mapOverview, mapFeatures);
		}

		for (Entry<String, String> each : details.entrySet()) {
			System.out.println(each.getKey() + " ---------- " + each.getValue());

		}
		//13) Close the browser.
		driver.quit();

	}

}
