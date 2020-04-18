package first21Days;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HpProgram {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		// Launching Chrome Browser
		ChromeDriver driver = new ChromeDriver();
		// To Load the url https://store.hp.com/in-en/
		driver.get("https://store.hp.com/in-en/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Mouse over on Laptops menu and click on Pavilion
		WebElement eleLaptop = driver.findElementByXPath("(//span[text()='Laptops'])[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleLaptop).perform();
		driver.findElementByXPath("(//span[text()='Pavilion'])[1]").click();
		Thread.sleep(2000);

		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		driver.findElementByXPath("(//input[@class='product-filter-checkbox'])[2]").click();
		Thread.sleep(3000);

		// Hard Drive Capacity -->More than 1TB
		driver.findElementByXPath("//span[text()='More than 1 TB']").click();
		Thread.sleep(4000);
		// Select Sort By: Price: Low to High
		WebElement sort = driver.findElementById("sorter");
		Select sortAsc = new Select(sort);
		sortAsc.selectByVisibleText("Price : Low to High");
		Thread.sleep(4000);
		// Print the First resulting Product Name and Price
		String laptopName = driver.findElementByXPath("(//a[@class='product-item-link'])[1]").getText();
		System.out.println("The Product name is " + laptopName);
		String productPrice = driver.findElementByXPath("(//span[@class='price'])[2]").getText();
		System.out.println("The Product price is " + productPrice);
		// Click on Add to Cart
		driver.findElementByXPath("(//button[@title='Add To Cart'])[1]").click();
		Thread.sleep(3000);
		// Click on Shopping Cart icon --> Click on View and Edit Cart
		driver.findElementByXPath("//a[@title='Shopping Cart']").click();
		driver.findElementByXPath("//a[@class='action primary viewcart']").click();
		Thread.sleep(4000);
		// Check the Shipping Option --> Check availability at Pincode
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("600061");
		driver.findElementByXPath("//button[text()='check']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();

		// Verify the order Total against the product price
		String orderTotal = driver.findElementByXPath("(//span[@class='price'])[7]").getText();
		System.out.println("The Order total is " + orderTotal);
		Thread.sleep(5000);
		boolean price;
		if (orderTotal.equalsIgnoreCase(productPrice)) {
			price = true;
		} else {
			price = false;
		}
		// Proceed to Checkout if Order Total and Product Price matches
		if (price == true) {
			driver.findElementByXPath("(//span[text()='Proceed to Checkout'])[1]").click();
			Thread.sleep(3000);
		} else {
			System.out.println("The price didnt match");
		}
		// Click on Place Order
		driver.findElementByXPath("(//span[text()='Place Order'])[4]").click();

		// Capture the Error message and Print
		String errMsg = driver.findElementByXPath("//div[@class='message notice']").getText();
		System.out.println("The Error message is " + errMsg);

		// Close Browser
		driver.close();

	}

}
