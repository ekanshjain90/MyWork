package Bing;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.memetix.mst.language.Language;								// include jar to avoid errors here
import com.memetix.mst.translate.Translate;

public class ApiTest 

{
	public WebDriver driver;
	
	
	
	
	@BeforeSuite
    public void setBrowser()

	{
		 System.setProperty("webdriver.gecko.driver","D:\\Eclipse Projects\\geckodriver.exe");
		 driver=new FirefoxDriver();
	}
	
	
	
	
	@Test(priority=0)
	
	public void browserLaunch() throws InterruptedException

	{
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("http://www.bing.com/translator");
		Thread.sleep(1500);
	}
	
	
	
	@Test(priority=1)
  
	public void trans() throws Exception 
	{
		driver.findElement(By.cssSelector("#srcText")).sendKeys("Hello");
		List<WebElement> li = driver.findElements(By.cssSelector(".dropdownArrow"));
		li.get(1).click();
		Thread.sleep(1500);
		List<WebElement> li1=driver.findElements(By.xpath(".//*[@id='LS_LangList']/table/tbody/tr[10]/td[3]"));
		li1.get(1).click();
		driver.findElement(By.cssSelector("#TranslateButton")).click();
		Thread.sleep(1500);
		
		String translated=driver.findElement(By.cssSelector("#destText")).getText();
		
		Translate.setClientId("ekanshjain");											// Using api methods
	    Translate.setClientSecret("mjSJT0KUwCUSDsSN00WrbntKx+fLDv+Vu+o+nTXslTs=");
	    String englishString = "Hello";
	    String spanishTranslation = Translate.execute(englishString, Language.SPANISH);	//api method translate
	    Assert.assertEquals(translated, spanishTranslation);
	    driver.close();
	}
}
