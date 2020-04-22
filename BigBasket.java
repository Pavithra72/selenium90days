package first21Days;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class BigBasket {

	public static void main(String[] args) throws InterruptedException {
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
		driver.get("https://www.bigbasket.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElementByXPath("//span[@class='close-btn']").click();
		Thread.sleep(1000);
		// mouse over on Shop by Category
		WebElement eleCategory = driver.findElementByXPath("//a[text()=' Shop by Category ']");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleCategory).perform();
		Thread.sleep(1000);

		// Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS
		WebElement elefood = driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]");
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(elefood).perform();
		Thread.sleep(3000);
		WebElement elerice = driver.findElementByXPath("(//a[text()='Rice & Rice Products'])[2]");
		Actions builder2 = new Actions(driver);
		builder2.moveToElement(elerice).perform();
		Thread.sleep(3000);

		// Click on Boiled & Steam Rice
		driver.findElementByXPath("(//a[text()='Boiled & Steam Rice'])[2]").click();
		Thread.sleep(6000);
		// Choose the Brand as bb Royal
		driver.findElementByXPath("(//span[text()='bb Royal'])[1]").click();
		Thread.sleep(5000);
		// Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
		driver.findElementByXPath(
				"(//a[text()='Ponni Boiled Rice - Super Premium']/following::div[@class='btn-group btn-input clearfix ng-scope'])[1]")
				.click();
		driver.findElementByXPath(
				"(//a[text()='Ponni Boiled Rice - Super Premium']/following::span[contains(text(),'5 kg')])[1]")
				.click();

		// print the price of Rice
		String ricePrice = driver
				.findElementByXPath(
						"(//a[text()='Ponni Boiled Rice - Super Premium']/following::span[@class='discnt-price'])[1]")
				.getText();
		System.out.println("The price of the rice is " + ricePrice);

		// Click Add button
		driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::button[@qa='add'])[1]")
				.click();
		Thread.sleep(2000);
		// driver.findElementByXPath("(//a[text()='Continue'])[1]").click();
		// Thread.sleep(4000);

		// Verify the success message displayed
		String addMsg = driver.findElementByXPath("//div[@class='toast-title']").getText();
		if (addMsg.equalsIgnoreCase("Successfully added Ponni Boiled Rice - Super Premium 5 kg to the basket")) {
			System.out.println("The meassage after adding rice into cart is " + addMsg);
			Thread.sleep(3000);
		} else {
			System.out.println("Wrong message displayed" + addMsg);
		}
		// Type Dal in Search field and enter
		driver.findElementByXPath("//input[@id='input']").sendKeys("DAL", Keys.ENTER);
		Thread.sleep(3000);
		// Go to Toor/Arhar Dal and select 2kg & set Qty 2
		driver.findElementByXPath(
				"(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::div[@class='btn-group btn-input clearfix ng-scope'])[1]")
				.click();
		driver.findElementByXPath(
				"(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::span[contains(text(),'2 kg')])[1]").click();
		WebElement quantity = driver
				.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::input)[1]");
		quantity.clear();
		quantity.sendKeys("2");

		// Print the price of Dal
		String dalPrice = driver
				.findElementByXPath(
						"(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::span[@class='discnt-price'])[1]")
				.getText();
		System.out.println("The Price of Dal is " + dalPrice);
		// Click Add button
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::button[@qa='add'])[1]")
				.click();
		Thread.sleep(2000);

		String addMsgDal = driver.findElementByXPath("//div[@class='toast-title']").getText();
		if (addMsgDal.equalsIgnoreCase("Successfully added Toor/Arhar Dal/Thuvaram Paruppu 2 kg to the basket")) {
			System.out.println("The meassage after adding dal into cart is " + addMsgDal);
		} else {
			System.out.println("Wrong message displayed" + addMsgDal);
		}
		driver.findElementByXPath("//button[@class='toast-close-button']").click();
		// Mouse hover on My Basket
		WebElement elecart = driver.findElementByXPath("//span[@class='basket-content']");
		Actions builder3 = new Actions(driver);
		builder3.moveToElement(elecart).perform();
		Thread.sleep(3000);

		// Validate the Sub Total displayed for the selected items
		String subTotal = driver.findElementByXPath("//span[@qa='subTotalMB']").getText();
		// System.out.println(subTotal);
		// String total = subTotal.replaceAll("\\D", "");
		double dblSTotal = Double.parseDouble(subTotal);
		System.out.println("Subtotal before reducing the Quantity of dhal is " + dblSTotal);
		System.out.println(" ");

		String rPrice = driver.findElementByXPath("(//span[@qa='priceMB'])[1]").getText();
		String rQuantity = driver.findElementByXPath("(//div[@qa='pcsMB'])[1]").getText();
		String riceQuantity = rQuantity.substring(0, 1);
		// System.out.println(riceQuantity);
		double totalRiceQuantity = Double.parseDouble(riceQuantity);
		double priceOfRice = Double.parseDouble(rPrice);
		double riceTotalPrice = totalRiceQuantity * priceOfRice;
		System.out.println("Total Price of Rice is " + riceTotalPrice);
		System.out.println(" ");

		String dPrice = driver.findElementByXPath("(//span[@qa='priceMB'])[2]").getText();
		String dQuantity = driver.findElementByXPath("(//div[@qa='pcsMB'])[2]").getText();
		String dalQuantity = dQuantity.substring(0, 1);
		// System.out.println(dalQuantity);
		double totalDalQuantity = Double.parseDouble(dalQuantity);
		double priceOfDal = Double.parseDouble(dPrice);
		double dalTotalPrice = totalDalQuantity * priceOfDal;
		System.out.println("Total price of dal before reducing the Quantity is " + dalTotalPrice);
		System.out.println(" ");

		double overallTotal = riceTotalPrice + dalTotalPrice;
		if (dblSTotal == overallTotal) {
			System.out.println(
					"The SubTotal is validated with the calculated amount for the products before reducing the Quantity");
		} else {
			System.out.println("The subtotal is getting mismatched");
		}
		System.out.println(" ");
		System.out.println(" ");

		// Reduce the Quantity of Dal as 1
		driver.findElementByXPath("(//button[@qa='decQtyMB'])[2]").click();
		Thread.sleep(1000);
		// Validate the Sub Total for the current items
		String dPrice1 = driver.findElementByXPath("(//span[@qa='priceMB'])[2]").getText();
		String dQuantity1 = driver.findElementByXPath("(//div[@qa='pcsMB'])[2]").getText();
		String dalQuantity1 = dQuantity1.substring(0, 1);
		// System.out.println(dalQuantity1);
		double totalDalQuantity1 = Double.parseDouble(dalQuantity1);
		double priceOfDal1 = Double.parseDouble(dPrice1);
		double dalTotalPrice1 = totalDalQuantity1 * priceOfDal1;
		System.out.println("Total price of dal after reducing the quantity is " + dalTotalPrice1);
		System.out.println(" ");

		String subTotal1 = driver.findElementByXPath("//span[@qa='subTotalMB']").getText();
		double dblSTotal1 = Double.parseDouble(subTotal1);
		System.out.println("Subtotal after reducing the quantity of dal is " + dblSTotal1);
		System.out.println(" ");

		double overallTotal1 = riceTotalPrice + dalTotalPrice1;

		if (dblSTotal1 == overallTotal1) {
			System.out.println(
					"The SubTotal is validated with the calculated amount for the products after reducing the quantity of dal");
		} else {
			System.out.println("The subtotal is getting mismatched");
		}

		// Close the Browser
		driver.close();
	}

}
