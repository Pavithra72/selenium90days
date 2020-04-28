package first21Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JustDial2 {

	public static void main(String[] args) throws InterruptedException, IOException {

//1) https://www.justdial.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		// disable the notifications
	
		  ChromeOptions options = new ChromeOptions();
		 options.addArguments("--disable-notifications");
		
		 	// Launching Chrome Browser
		ChromeDriver driver = new ChromeDriver(options);
		// To Load the url
		driver.get("https://www.justdial.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		String actPh = "";

//2) Set the Location as Chennai
		// driver.findElementByXPath("//input[@id='city']").clear();
		// driver.findElementByXPath("//input[@id='city']").sendKeys("Chennai");
		// driver.findElementByXPath("//b[text()='Chennai']").click();
//3) Click Auto Care in the left menu
		driver.findElementByXPath("//span[text()='Auto care']").click();
		Thread.sleep(1000);
//4) Click Car Repair
		driver.findElementByXPath("//span[text()='Car Repair']").click();
		Thread.sleep(1000);
//5) Click Car Brand as Hyundai
		driver.findElementByXPath("(//span[text()='Hyundai'])[1]").click();
		Thread.sleep(1000);
//6) Click Make as Hyundai Xcent
		driver.findElementByXPath("(//span[text()='Hyundai Xcent'])[1]").click();
		Thread.sleep(2000);
		
		try {
		  WebDriverWait wait = new WebDriverWait(driver, 30);
		  wait.until(ExpectedConditions.visibilityOf(driver.findElementByCssSelector("section#best_deal_div>section>span")));
		  driver.findElementByCssSelector("section#best_deal_div>section>span").click();
		}catch(Exception e) {
			System.out.println("alert not found");
		}
		finally {
			Map<String,String> hm = new LinkedHashMap<String,String>();
			hm.put("+", "dc");
			hm.put("(", "fe");
			hm.put(")", "hg");
			hm.put("-", "ba");
			hm.put("0", "acb");
			hm.put("1", "yz");
			hm.put("2", "wx");
			hm.put("3", "vu");
			hm.put("4", "ts");
			hm.put("5", "rq");
			hm.put("6", "po");
			hm.put("7", "nm");
			hm.put("8", "lk");
			hm.put("9", "ji");
			List<String> sortedCompanyName = new ArrayList<String>();
			List<String> sortedPhoneNo = new ArrayList<String>();
			List<WebElement> eleRating = driver.findElementsByXPath("//span[@class='green-box']");
			for (int i = 0; i < eleRating.size(); i++) {
				String ratingChar = eleRating.get(i).getText();
				float rating = Float.parseFloat(ratingChar);

				if (rating >= 4.5) {
					WebElement eleVote = driver.findElementByXPath("(//span[@class='green-box'])["+(i+1)+"]/following-sibling::span[contains(text(),'Votes')]");
					String votesChars = eleVote.getText();
					//System.out.println(votesChars);
					  String voteallchars = votesChars.trim(); 
					  String votesString = voteallchars.replaceAll("[^0-9]", ""); 
					  int vote = Integer.parseInt(votesString); 
					 //System.out.println(vote); 
					  if (vote > 50) {
					  String companyName = driver.findElementByXPath("((//span[@class='green-box'])["+(i+1)+"]/preceding::span[@class='lng_cont_name'])["+(i+1)+"]").getText();
					//  System.out.println(companyName); 
					  sortedCompanyName.add(companyName); 
					//  System.out.println("To test");
					  if(i > 0) {
						  System.out.println("Entered if loop i > 0");
					  List<WebElement> PhoneNum = driver.findElementsByXPath("((//span[@class='green-box'])["+(i+1)+"]/preceding::p[@class='newrtings '])["+i+"]/following-sibling::p/span/span[contains(@class,'mobilesv icon')]");
					  int phSize = PhoneNum.size();
					//  System.out.println("size is "+phSize);
				//	 System.out.println("Test after list");
					 
					  for(int ph = 0; ph<phSize; ph++) {
						//  System.out.println("Entering For loop");
						  String name = PhoneNum.get(ph).getAttribute("class");
						//  System.out.println("Class Name is  --> "+name);
						  String convertedPh = name.substring(14);
						//  System.out.println("The converted Ph is "+convertedPh);
						 for (Entry<String, String> eachEntry: hm.entrySet()) {
							if(eachEntry.getValue().equalsIgnoreCase(convertedPh)) {
							//	System.out.println("key is "+eachEntry.getKey());
								actPh = actPh+(eachEntry.getKey());
							}
						}
						  
					  }
					  System.out.println("The value of decoded Ph Number is "+actPh);
					  sortedPhoneNo.add(actPh);
					  actPh = "";
					  }
					 else if(i==0){
					//	  System.out.println("Entered if loop i = 0");

						 List<WebElement> phoneNumI = driver.findElementsByXPath("(//span[@class='green-box'])["+(i+1)+"]/parent::a/parent::p/following-sibling::p/span/span[contains(@class,'mobilesv icon')]");
						 int phSizeI = phoneNumI.size();
						//  System.out.println("size is "+phSizeI);
					//	 System.out.println("Test after list");
						 
						  for(int ph = 0; ph<phSizeI; ph++) {
							//  System.out.println("Entering For loop");
							  String name = phoneNumI.get(ph).getAttribute("class");
						//	  System.out.println("Class Name is  --> "+name);
							  String convertedPh = name.substring(14);
						//	  System.out.println("The converted Ph is "+convertedPh);
							 for (Entry<String, String> eachEntry: hm.entrySet()) {
								if(eachEntry.getValue().equalsIgnoreCase(convertedPh)) {
							//		System.out.println("key is "+eachEntry.getKey());

									actPh = actPh+(eachEntry.getKey());
								}
							}
							  
						  }
						  System.out.println("The value of decoded Ph Number is "+actPh);
						  sortedPhoneNo.add(actPh);
						  actPh = "";
					 }
						 
					 
				}
			}
				}
			File file = new File("C:\\TestLeaf\\Learning90Days\\output.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sh = wb.createSheet("first Sheet");
	for(int j=0 ; j<sortedCompanyName.size();j++ ) {
		
		sh.createRow(j).createCell(0).setCellValue(sortedCompanyName.get(j));	
		sh.getRow(j).createCell(1).setCellValue(sortedPhoneNo.get(j));	
		
	}
	FileOutputStream fos = new FileOutputStream(file);
	wb.write(fos);
		}
		driver.close();
	
}
	}




		 
//7) Click on Location and Enter Porur
		
		
		
		/*
		 * driver.findElementByXPath("//a[text()='Location']").clear();
		 * driver.findElementByXPath("//a[text()='Location']").click();
		 * driver.findElementByXPath("//input[@id='sortbydist']").sendKeys("Porur");
		 */
		 		 
//8) Select Porur from the dropdown list
		
		
		
		/*
		 * driver.findElementByXPath("(//b[text()='Porur'])[1]").click();
		 * Thread.sleep(1000);
		 */		 
		 // 9) Select Distance starting from 1 km
		
		 // driver.findElementByXPath("//span[text()='Distance ']").click();
		//  driver.findElementByXPath("//a[text()='1 km']").click(); Thread.sleep(3000);
		 
//10) Identify all the Service Center having Ratings >=4.5 and Votes >=50
		
			
//11) Save all the Service Center name and Phone number matching the above condition in excel 
		
//12) Close the browser
		// driver.close();


