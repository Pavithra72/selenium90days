package first21Days;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CRM {

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
		driver.get("https://demo.1crmcloud.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		//2) Give username as admin and password as admin
		driver.findElementById("login_user").sendKeys("admin");
		driver.findElementById("login_pass").sendKeys("admin");
		
		//3) Choose theme as Claro Theme
		WebElement selectTheme = driver.findElementById("login_theme");
		Select theme = new Select(selectTheme);
		theme.selectByVisibleText("Claro Theme");
		driver.findElementById("login_button").click();
		Thread.sleep(3000);
		
		//4) Click on Sales and Marketting 
		driver.findElementByXPath("//div[text()='Sales & Marketing']").click();
		Thread.sleep(3000);
		
		//5) Click Create contact
		driver.findElementByXPath("//div[text()='Create Contact']").click();
		Thread.sleep(5000);
		
		//6) Select Title and type First name, Last Name, Email and Phone Numbers
	//	WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@id='DetailFormsalutation-input']")));
		driver.findElementByXPath("//div[@id='DetailFormsalutation-input']").click();
		Thread.sleep(500);
		driver.findElementByXPath("//div[text()='Mrs.']").click();
		driver.findElementById("DetailFormfirst_name-input").sendKeys("Pavithra");
		driver.findElementById("DetailFormlast_name-input").sendKeys("Srinivasan");
		driver.findElementById("DetailFormemail1-input").sendKeys("pavi7@gmail.com");
		driver.findElementById("DetailFormphone_work-input").sendKeys("9710724561");
		
		//7) Select Lead Source as "Public Relations"
		driver.findElementById("DetailFormlead_source-input").click();
		Thread.sleep(500);
		driver.findElementByXPath("//div[text()='Public Relations']").click();
		Thread.sleep(1000);
		//8) Select Business Roles as "Sales"
		driver.findElementById("DetailFormbusiness_role-input").click();
		Thread.sleep(500);
		driver.findElementByXPath("//div[text()='Sales']").click();
		Thread.sleep(1000);
		
		//9) Fill the Primary Address, City, State, Country and Postal Code and click Save
		driver.findElementById("DetailFormprimary_address_street-input").sendKeys("D 205, MGR Road, Nanganallur");
		driver.findElementById("DetailFormprimary_address_city-input").sendKeys("Chennai");
		driver.findElementById("DetailFormprimary_address_state-input").sendKeys("TamilNadu");
		driver.findElementById("DetailFormprimary_address_country-input").sendKeys("India");
		driver.findElementById("DetailFormprimary_address_postalcode-input").sendKeys("600061");
		driver.findElementById("DetailForm_save2").click();
		Thread.sleep(5000);
		
		//10) Mouse over on Today's Activities and click Meetings
		WebElement todayActivities = driver.findElementByXPath("(//div[@class='menu-label'])[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(todayActivities).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("//div[text()='Meetings']").click();
		Thread.sleep(5000);
		
		//11) Click Create
		driver.findElementByXPath("(//button[@class='input-button first'])[1]").click();
		Thread.sleep(5000);
		
		//12) Type Subject as "Project Status" , Status as "Planned" 
		driver.findElementById("DetailFormname-input").sendKeys("Project Status");
		
		
		//13) Start Date & Time as tomorrow 3 pm and Duration as 1hr
		driver.executeScript("window.scrollBy(0, 250)");
		driver.findElementByXPath("//div[@id='DetailFormdate_start-input']").click();
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//div[@class='grid-cell number-cell text-right day inside current selected quiet responsive']/following::div)[1]")));
		driver.findElementByXPath("(//div[@class='grid-cell number-cell text-right day inside current selected quiet responsive']/following::div)[1]").click();
		driver.findElementByXPath("(//input[@class='input-text'])[4]").clear();
		driver.findElementByXPath("(//input[@class='input-text'])[4]").sendKeys("15:00");
		driver.findElementByXPath("//div[@class='active-icon uii-accept uii-lg uii']").click();
		Thread.sleep(1000);
		driver.findElementById("DetailFormduration-time").clear();
		driver.findElementById("DetailFormduration-time").sendKeys("1h 00m",Keys.TAB);
		
		//14) Click Add paricipants, add your created Contact name and click Save
		driver.findElementByXPath("//button[@name='addInvitee']").click();
		Thread.sleep(500);
		driver.findElementByXPath("(//input[@class='input-text'])[4]").sendKeys("Pavithra");
		Thread.sleep(500);
		driver.findElementByXPath("(//div[@class='option-cell input-label '])[14]").click();
		driver.findElementByXPath("//span[@id='DetailForm_save2-label']").click();
		Thread.sleep(5000);
		String proTitle = driver.findElementByXPath("//div[@id='_form_header']").getText();
		
		//15) Go to Sales and Marketting-->Contacts
		WebElement salesMarketing = driver.findElementByXPath("//div[text()='Sales & Marketing']");
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(salesMarketing).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("//div[text()='Contacts']").click();
		Thread.sleep(5000);
		
		//16) search the lead Name and click the name from the result
		driver.findElementByXPath("//input[@id='filter_text']").sendKeys("Pavithra");
		Thread.sleep(500);
		driver.findElementByXPath("(//div[@class='menu-option single'])[15]").click();
	//	Thread.sleep(2000);
		//driver.findElementByXPath("//span[@class='detailLink']").click();
		Thread.sleep(5000);
		//17) Check weather the Meeting is assigned to the contact under Activities Section.
		driver.executeScript("window.scrollBy(0, 750)");
		String projectName = driver.findElementByXPath("//span[@class='detailLink']").getText();
		if(projectName.equalsIgnoreCase(proTitle)) {
			System.out.println("The Meeting is scheduled to the created contact under activities section");
		}else {
			System.out.println("The Meeting is not scheduled to the created contact under activities section");
		}

		//close the window
		driver.close();
	}

}
