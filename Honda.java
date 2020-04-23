package first21Days;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Honda {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		// disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		// Launching Chrome Browser
		ChromeDriver driver = new ChromeDriver(options);
		// To Load the url
		driver.get("https://www.honda2wheelersindia.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElementByXPath("//button[@class='close']").click();

		// Click on scooters and click dio
		driver.findElementByXPath("(//a[text()='Scooter'])[1]").click();
		// driver.findElementByXPath("(//div[@class='owl-item']/following::img)[3]").click();
		driver.findElementByXPath("//img[@src='/assets/images/thumb/dioBS6-icon.png']").click();

		// Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		Thread.sleep(2000);
		WebElement eleengine1 = driver.findElementByXPath("//a[text()='ENGINE']");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleengine1).perform();
		Thread.sleep(1000);
		// Get Displacement value
		String dioDisplacement = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span")
				.getText();
		System.out.println("The displacement value of DIO is " + dioDisplacement);
		String rDioDisplacement = dioDisplacement.replace("c", " ");
		// System.out.println(rdioDisplacement);
		float flDioDisplacement = Float.parseFloat(rDioDisplacement);
		// System.out.println(fldioDisplacement);

		// Go to Scooters and click Activa 125
		driver.findElementByXPath("(//a[text()='Scooter'])[1]").click();
		Thread.sleep(2000);
		// driver.findElementByXPath("(//div[@class='owl-item']/following::img)[5]").click();
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click();

		// Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		Thread.sleep(3000);
		WebElement eleengine2 = driver.findElementByXPath("//a[text()='ENGINE']");
		Actions builder2 = new Actions(driver);
		builder2.moveToElement(eleengine2).perform();
		Thread.sleep(2000);
		// Get Displacement value
		String activaDisplacement = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span")
				.getText();
		System.out.println("The displacement value of DIO is " + activaDisplacement);
		String rActivaDisplacement = activaDisplacement.replace("c", " ");
		float flActivaDisplacement = Float.parseFloat(rActivaDisplacement);

		// Compare Displacement of Dio and Activa 125 and print the Scooter name having
		// better Displacement.
		if (flActivaDisplacement > flDioDisplacement) {
			System.out.println("Activa 125 has better displacement");
		} else {
			System.out.println("Dio has better displacement");
		}

		// Click FAQ from Menu
		driver.findElementByXPath("(//a[text()='FAQ'])[1]").click();
		Thread.sleep(2000);

		// Click Activa 125 BS-VI under Browse By Product
		WebElement activa125 = driver.findElementByXPath("//a[text()='Activa 125 BS-VI']");
		activa125.click();
		Thread.sleep(2000);
		// Click Vehicle Price
		driver.findElementByXPath("//a[text()=' Vehicle Price']").click();

		// Make sure Activa 125 BS-VI selected and click submit
		WebElement modelDD = driver.findElementById("ModelID6");
		Select model = new Select(modelDD);
		String selectedModel = model.getFirstSelectedOption().getText();
		// System.out.println(selectedModel);
		if (selectedModel.equalsIgnoreCase("Activa 125 BS-VI")) {
			driver.findElementByXPath("//button[@id='submit6']").click();
		} else {
			System.out.println("The selected vehicle is not Activa 125 BS-VI");
		}
		// click the price link
		driver.findElementByXPath("//a[text()='Click here to know the price of Activa 125 BS-VI.']").click();
		Thread.sleep(3000);

		// Go to the new Window and select the state as Tamil Nadu and city as Chennai
		Set<String> nextWindow = driver.getWindowHandles();
		List<String> allList = new ArrayList<String>(nextWindow);
		driver.switchTo().window(allList.get(1));
// state selected as Tamil Nadu
		WebElement stateId = driver.findElementById("StateID");
		Select selectStateId = new Select(stateId);
		selectStateId.selectByVisibleText("Tamil Nadu");
// city selected as Chennai
		WebElement cityId = driver.findElementById("CityID");
		Select selectCityId = new Select(cityId);
		selectCityId.selectByVisibleText("Chennai");

		// Click Search
		driver.findElementByXPath("//button[text()='Search']").click();

		// Print all the 3 models and their prices

		Map<String, String> priceOfScooters = new LinkedHashMap();

		String scooterModel = "";
		String price = "";

		for (int i = 1; i <= 3; i++) {
			scooterModel = driver
					.findElementByXPath("//table[@id='gvshow']//tr[" + i + "]//td[contains(text(),'ACTIVA')]")
					.getText();
			price = driver
					.findElementByXPath(
							"//table[@id='gvshow']//tr[" + i + "]//td[contains(text(),'ACTIVA')]/following-sibling::td")
					.getText();

			priceOfScooters.put(scooterModel, price);

		}

		for (Entry<String, String> each : priceOfScooters.entrySet()) {
			System.out.println(each.getKey() + " price is " + each.getValue());
			
		}

		// Close the Browser
		driver.quit();
	}

}
