package first21Days;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Myntra {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		//disable the notifications
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		//Launching Chrome Browser
		ChromeDriver driver = new ChromeDriver(options);
		//To Load the url
		driver.get(" https://www.myntra.com/");
		//To maximize the browser
		driver.manage().window().maximize();
		//implicitly wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Mouse over on WOMEN (Actions -> moveToElement
		WebElement eleWomen = driver.findElementByXPath("(//a[text()='Women'])[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleWomen).perform();
		
		//Click Jackets & Coats
		driver.findElementByLinkText("Jackets & Coats").click();
		Thread.sleep(3000);
		
		//Find the total count of item (top)
		String count = driver.findElementByXPath("//span[@class='title-count']").getText();
		String totalCount = count.replaceAll("\\D","");
		int tCNum = Integer.parseInt(totalCount);
		System.out.println("The total count of items are "+ tCNum  );
		
		//Validate the sum of categories count matches
		String cat1 = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		String catJacket = cat1.replaceAll("\\D","");
		int catJacketcount = Integer.parseInt(catJacket);
		//System.out.println(catJacketcount);
		
		String cat2 = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		String catCoat = cat2.replaceAll("\\D","");
		int catCoatcount = Integer.parseInt(catCoat);
		//System.out.println(catCoatcount);
		
		int totalCatCount = catJacketcount +catCoatcount;
		//System.out.println(totalCatCount);
		if(tCNum == totalCatCount ) {
			System.out.println("The sum of categories " + totalCatCount +" equals the total count of items" );
		}
		
		//Check Coats 
		driver.findElementByXPath("//label[text()='Coats']").click();
		Thread.sleep(2000);
		
		// Click + More option under BRAND
		driver.findElementByXPath("//div[@class='brand-more']").click();
		Thread.sleep(3000);
		//Type MANGO and click checkbox
		driver.findElementByXPath("//input[@class='FilterDirectory-searchInput']").sendKeys("MANGO");
		Thread.sleep(3000);
		driver.findElementByXPath("//label[@class=' common-customCheckbox']//div[1]").click();
		//Close the pop-up x
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		Thread.sleep(2000);
		
		//Confirm all the Coats are of brand MANGO
		List<WebElement> brand = driver.findElementsByXPath("//h3[@class='product-brand']");
		int brandCount =0;
		for (WebElement brandname : brand) {
			String bName = brandname.getText();
			if(bName.equalsIgnoreCase("MANGO")) {
				brandCount=brandCount+1;
			}
			
			}
		if(brandCount == brand.size())
		{
			System.out.println("All the items are brand MANGO");
		}
		
		//Sort by Better Discount
		WebElement sortBy = driver.findElementByXPath("//div[@class='sort-sortBy']");
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(sortBy).perform();

		driver.findElementByXPath("//label[text()='Better Discount']").click();
		Thread.sleep(2000);
		
		//Find the price of first displayed item
		List<WebElement> priceofall = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		String priceOfFirst = priceofall.get(0).getText();
		String itemPrice = priceOfFirst.replaceAll("\\D","");
		int price = Integer.parseInt(itemPrice);
		System.out.println("The Price of first item is "+price);
		
		//Mouse over on size of the first item
		WebElement sizeOfFirst = driver.findElementByXPath("(//span[@class='product-discountedPrice'])[1]");
		Actions builder2 = new Actions(driver);
		builder2.moveToElement(sizeOfFirst).perform();
		
		//Click on WishList Now
		driver.findElementByXPath("(//span[@class='product-actionsButton product-wishlist product-prelaunchBtn'])[1]").click();

		//Close Browser
		driver.close();
		}
	
		
}
	


