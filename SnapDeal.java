package first21Days;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException {
//		1) Go to https://www.snapdeal.com/

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
		driver.get("https://www.snapdeal.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Actions builder = new Actions(driver);
//			‎2) Mouse over on Toys, Kids' Fashion & more and click on Toys
		WebElement toysKids = driver.findElementByXPath("//span[contains(text(),'Toys, Kids')]");

		builder.moveToElement(toysKids).perform();

//			3) Click Educational Toys in Toys & Games
		driver.findElementByXPath("//span[text()='Educational Toys']").click();
		Thread.sleep(3000);
//			‎4) Click the Customer Rating 4 star and Up 
		driver.findElementByXPath("//label[@for='avgRating-4.0']").click();
		Thread.sleep(2000);
//			5) Click the offer as 40-50
		driver.findElementByXPath("//label[@for='discount-40%20-%2050']").click();
		Thread.sleep(2000);
//			6) Check the availability for the pincode
		driver.findElementByXPath("//input[@placeholder='Enter your pincode']").sendKeys("600061");
		driver.findElementByXPath("//button[text()='Check']").click();
		Thread.sleep(3000);
//			7) Click the Quick View of the first product 
		WebElement firstToyProduct = driver.findElementByXPath("(//p[@class='product-title'])[1]");
		builder.moveToElement(firstToyProduct).perform();
		;
		driver.findElementByXPath("(//div[@class='clearfix row-disc']//div)[1]").click();
//			8) Click on View Details
		driver.findElementByXPath("//a[contains(text(),' view')]").click();
		Thread.sleep(3000);

//			9) Capture the Price of the Product and Delivery Charge
		String priceOfProduct = driver.findElementByXPath("//span[@class='payBlkBig']").getText();
		System.out.println("The Price of the toy is " + priceOfProduct);
		int intPrice = Integer.parseInt(priceOfProduct);
		// System.out.println(intPrice);
		String deliveryChargeOfToy = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		System.out.println("The delivery charge of the toy is " + deliveryChargeOfToy.substring(4));
		String reDeliveryCharge = deliveryChargeOfToy.replaceAll("[^0-9]", "");
		// System.out.println("replaced delivery charge "+reDeliveryCharge);
		int intDeliveryCharge = Integer.parseInt(reDeliveryCharge);
		// System.out.println("delivery charge in int "+intDeliveryCharge);
		int totalPrice = intPrice + intDeliveryCharge;
		Thread.sleep(2000);

//			10) Validate the You Pay amount matches the sum of (price+deliver charge)
		driver.findElementByXPath("//span[text()='add to cart']").click();
		Thread.sleep(2000);
		String finalPrice = driver.findElementByXPath("(//span[@class='price'])[2]").getText();
		System.out.println("The Price for the toy you pay finally is " + finalPrice);
		String reFinalPrice = finalPrice.replaceAll("[^0-9]", "");
		int intFinalPrice = Integer.parseInt(reFinalPrice);
		if (intFinalPrice == totalPrice) {
			System.out.println("The price in the cart is equal to the sum of the price in the page ");
		} else {
			System.out.println("The price in the cart is not equal to the sum of the price in the page ");
		}

//			11) Search for Sanitizer
		driver.findElementByXPath("//input[@id='inputValEnter']").sendKeys("Sanitizer");
		driver.findElementByXPath("(//a[@data-labelname='sanitizer'])[1]").click();
		Thread.sleep(3000);

//			12) Click on Product "BioAyurveda Neem Power Hand Sanitizer"
		driver.findElementByXPath("(//p[contains(text(),'BioAyurveda Neem Power')])[1]").click();
		Thread.sleep(3000);

//			13) Capture the Price and Delivery Charge
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allList = new ArrayList<String>(allWindows);
		driver.switchTo().window(allList.get(1));
		String priceOfSanitizer = driver.findElementByXPath("//span[@class='payBlkBig']").getText();
		System.out.println("The Price of the Sanitizer is " + priceOfSanitizer);
		int intSanitizerPrice = Integer.parseInt(priceOfSanitizer);

		String deliveryChargeOfSanitizer = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		System.out.println("The Delivery charge for the Sanitizer is " + deliveryChargeOfSanitizer.substring(4));
		String reDeliveryChargeSanitizer = deliveryChargeOfSanitizer.replaceAll("[^0-9]", "");
		// System.out.println("replaced delivery charge "+reDeliveryCharge);
		int intDeliveryChargeSanitizer = Integer.parseInt(reDeliveryChargeSanitizer);
		// System.out.println("delivery charge in int "+intDeliveryCharge);
		int totalPriceOfSanitizer = intSanitizerPrice + intDeliveryChargeSanitizer;
		System.out.println("the total price of sanitizer is " + totalPriceOfSanitizer);
		Thread.sleep(2000);

//			14) Click on Add to Cart
		driver.findElementByXPath("(//span[text()='ADD TO CART'])[1]").click();
		Thread.sleep(3000);

//			15) Click on Cart 
		driver.findElementByXPath("//i[@class='sd-icon sd-icon-cart-icon-white-2']").click();
		Thread.sleep(3000);

//			16) Validate the Proceed to Pay matches the total amount of both the products

		String priceOfBothProducts = driver.findElementByXPath("//input[@class='btn btn-xl rippleWhite cart-button']")
				.getAttribute("value");
		System.out.println("The price of both items is " + priceOfBothProducts.substring(15));
		String rePriceOfBoth = priceOfBothProducts.replaceAll("[^0-9]", "");
		int intPriceOfBoth = Integer.parseInt(rePriceOfBoth);
		int intTotalOfBothProducts = totalPrice + totalPriceOfSanitizer;
		if (intPriceOfBoth == intTotalOfBothProducts) {
			System.out.println("The Price in the cart is same as the sum of both products price");
		} else {
			System.out.println("The Price in the cart is not same as the sum of both products price");

		}
//			17) Close all the windows
		driver.quit();

	}

}
