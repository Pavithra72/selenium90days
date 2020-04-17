package first21Days;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		// disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		// Launching Chrome Browser
		ChromeDriver driver = new ChromeDriver(options);
		// To Load the url https://www.makemytrip.com/
		driver.get("https://www.makemytrip.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Click Hotels
		driver.findElementByXPath("//span[@class='chNavIcon appendBottom2 chSprite chHotels']").click();

		// Enter city as Goa, and choose Goa, India
		driver.findElementByXPath("//span[@class='lbl_input latoBold  appendBottom5']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("Goa");
		driver.findElementByXPath("//p[text()='Goa, India']").click();

		// Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		driver.findElementByXPath("(//div[text()='15'])[2]").click();
		driver.findElementByXPath("(//div[text()='19'])[2]").click();

		// Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click
		// Apply Button.
		driver.findElementByXPath("//input[@class='hsw_inputField guests font20']").click();
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();

		WebElement childAge = driver.findElementByClassName("ageSelectBox");

		Select age = new Select(childAge);
		age.selectByVisibleText("11");
		driver.findElementByXPath("//button[@class='primaryBtn btnApply']").click();

		// Click Search button
		driver.findElementByXPath("//button[@class='primaryBtn font24 latoBold widgetSearchBtn']").click();

		// click on the black screen
		driver.findElementByXPath("//body[@class='bodyFixed overlayWholeBlack']").click();

		// Select locality as Baga
		driver.findElementByXPath("//label[text()='Baga']").click();

		// Select 5 start in Star Category under Select Filters
		driver.findElementByXPath("//label[text()='5 Star']").click();

		// Click on the first resulting hotel and go to the new window
		driver.findElementByXPath("(//div[@class='imgCont'])[1]").click();
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allhandles = new ArrayList<String>(allWindows);
		driver.switchTo().window(allhandles.get(1));

		// Print the Hotel Name
		String hotelName = driver.findElementByXPath("//h1[@id='detpg_hotel_name']").getText();
		System.out.println("The Hotel name is " + hotelName);

		// Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
		driver.findElementByXPath("(//span[text()='SELECT'])[1]").click();
		driver.findElementByXPath("//span[@class='close']").click();

		// Click on BOOK THIS NOW
		driver.findElementByXPath("//a[text()='BOOK THIS NOW']").click();

		// Print the Total Payable amount
		String totalAmt = driver.findElementByXPath("//span[@id='revpg_total_payable_amt']").getText();
		System.out.println("The Total payable amount is " + totalAmt);

		// Close the browser
		driver.quit();
	}

}
