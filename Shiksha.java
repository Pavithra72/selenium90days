package first21Days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class Shiksha {

	public static void main(String[] args) throws InterruptedException {

		// 1) Go to https://studyabroad.shiksha.com/
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
		driver.get("https://studyabroad.shiksha.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2) Mouse over on Colleges and click MS in Computer Science &Engg under MS
		// Colleges
		WebElement eleCollege = driver.findElementByXPath("(//label[text()='Colleges '])[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleCollege).perform();
		Thread.sleep(500);
		driver.findElementByXPath("//a[text()='MS in Computer Science &Engg']").click();
		Thread.sleep(3000);

		// 3) Select GRE under Exam Accepted and Score 300 & Below
		driver.findElementByXPath("(//p[text()='GRE'])[1]").click();
		Thread.sleep(1000);
		WebElement slctScore = driver.findElementByXPath("(//select[@class='score-select-field'])[1]");
		Select score = new Select(slctScore);
		score.selectByVisibleText("300 & below");
		Thread.sleep(2000);

		// 4) Max 10 Lakhs under 1st year Total fees, USA under countries
		driver.findElementByXPath("//span[@class='common-sprite']/preceding::p[contains(text(),'Max 10 Lakhs')]")
				.click();
		Thread.sleep(3000);
		driver.findElementByXPath("//a[text()='USA']/ancestor::label/span").click();
		Thread.sleep(3000);

		// 5) Select Sort By: Low to high 1st year total fees
		WebElement sortCategory = driver.findElementById("categorySorter");
		Select category = new Select(sortCategory);
		category.selectByVisibleText("Low to high 1st year total fees");
		Thread.sleep(3000);

		// 6) Click Add to compare of the College having least fees with Public
		// University, Scholarship and Accomadation
		List<WebElement> collegesList = driver
				.findElementsByXPath("//div[contains(@id,'categoryPageListing_tupleId')]");
		int listSize = collegesList.size();
		List<Double> colFee = new ArrayList<Double>();
		// List<WebElement> tickMark =
		// driver.findElementsByXPath("(//div[contains(@id,'categoryPageListing_tupleId')])[1]//span[@class='tick-mark']");
		for (int i = 1; i <= listSize; i++) {
			if (driver
					.findElementsByXPath(
							"(//div[contains(@id,'categoryPageListing_tupleId')])[" + i + "]//span[@class='tick-mark']")
					.size() == 3) {
				colFee.add(Double.parseDouble(driver
						.findElementByXPath("(//div[@class='detail-col flLt']/p[contains(text(),'Rs')])[" + i + "]")
						.getText().replaceAll("[^0-9\\s.]+|\\.(?!\\d)", "")));
			}
		}
		Thread.sleep(1000);
		Collections.sort(colFee);
		driver.findElementByXPath("(//div[@class='detail-col flLt'])/p[contains(text(),'" + colFee.get(0)
				+ "')]/ancestor::div[@class='clearwidth']/following-sibling::div[@class='compare-box flRt customInputs']//span")
				.click();

		// 7) Select the first college under Compare with similar colleges
		driver.findElementByXPath("//a[text()=' Texas Southern University']").click();
		Thread.sleep(500);

		// 8) Click on Compare College>
		driver.findElementByXPath("//strong[text()='Compare Colleges >']").click();
		Thread.sleep(3000);

		// 9) Select When to Study as 2021
		driver.findElementByXPath("//strong[text()='2021']").click();
		Thread.sleep(1000);

		// 10) Select Preferred Countries as USA
		WebElement preferredCountry = driver.findElementByXPath("//div[text()='Preferred Countries']");
		js.executeScript("arguments[0].click()", preferredCountry);
		Thread.sleep(1000);
		driver.findElementByXPath("//div[@class='in-tab']//label[text()[normalize-space()='USA']]").click();
		driver.findElementByXPath("//a[text()='ok']").click();
		Thread.sleep(1000);

		// 11) Select Level of Study as Masters
		driver.findElementByXPath("//strong[text()='Masters']").click();
		Thread.sleep(500);

		// 12) Select Preferred Course as MS
		WebElement preferredCourse = driver.findElementByXPath("//div[text()='Preferred Course']");
		js.executeScript("arguments[0].click()", preferredCourse);
		Thread.sleep(500);
		driver.findElementByXPath("//li[text()='MS']").click();
		Thread.sleep(1000);

		// 13) Select Specialization as "Computer Science & Engineering"
		WebElement preferredspl = driver.findElementByXPath("//div[text()='Preferred Specialisations']");
		js.executeScript("arguments[0].click()", preferredspl);
		Thread.sleep(500);
		driver.findElementByXPath("//li[text()='Computer Science & Engineering']").click();
		Thread.sleep(1000);

		// 14) Click on Sign Up
		driver.findElementByXPath("//a[@id='signup']").click();
		Thread.sleep(2000);

		// 15) Print all the warning messages displayed on the screen for missed
		// mandatory fields
		List<WebElement> errorMsgs = driver
				.findElementsByXPath("//div[@class='input-helper']//div[contains(text(),'Please')]");
		System.out.println("Error messages for mandatory fields are displayed below :");
		for (int i = 0; i < errorMsgs.size(); i++) {
			String errorMsgText = errorMsgs.get(i).getText();
			if (errorMsgText.length() > 0) {
				System.out.println(errorMsgText);
			}
		}
		driver.close();
	}

}
