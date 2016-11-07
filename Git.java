package GitOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Git 
{
	public WebDriver driver;
	FirefoxProfile profile = new FirefoxProfile();
	
	
	
	
	@BeforeSuite
	
    public void setBrowser()
	{
		 System.setProperty("webdriver.gecko.driver","D:\\Eclipse Projects\\geckodriver.exe");
		 profile.setPreference("browser.download.folderList", 2);
		 profile.setPreference("browser.download.dir", "C:\\Users\\ekansh.jain\\Downloads");
		 profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		 profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
		 profile.setPreference("browser.download.manager.showWhenStarting", false);
		 profile.setPreference("browser.download.manager.focusWhenStarting", false);  
		 profile.setPreference("browser.download.useDownloadDir", true);
		 profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		 profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		 profile.setPreference("browser.download.manager.closeWhenDone", true);
		 profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		 profile.setPreference("browser.download.manager.useWindow", false);
		 profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
		 profile.setPreference("pdfjs.disabled", true);
		 driver=new FirefoxDriver(profile);
	}
	
	
	
	
	@Test(priority=0)
	
	public void browserLaunch() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://github.com/login");		
	}
	
	
	
	@Test(priority=1)
	
	public void login()throws InterruptedException
	{
		driver.findElement(By.cssSelector("#login_field")).sendKeys("ekanshjain90");
		driver.findElement(By.cssSelector("#password")).sendKeys("Ek@nS@123");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(".btn.btn-primary.btn-block")).click();
	}
	
	
	
	
	/*
	@Test(priority=2)
	
	public void createRepo() throws InterruptedException
	{
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".btn.btn-sm.btn-primary")).click();
		driver.findElement(By.cssSelector("#repository_name")).sendKeys("SampleRepo");
		driver.findElement(By.cssSelector(".btn.btn-primary.first-in-line")).click();
	}
	*/
	
	
	
	
	@Test(priority=3)
	
	public void selectRepo()throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("span[title*='Sample']")).click();

	}
	
	
	
	
	@Test(priority=4)
	
	public void cloneRepo() throws InterruptedException
	{
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".btn.btn-sm.btn-primary.select-menu-button.js-menu-target")).click();
		driver.findElement(By.cssSelector("a[href*='master.zip']")).click();		
	}
	
	
	
	@Test(priority=5)
	
	public void checkCloning()
	{
		File varTmpDir = new File("C:\\Users\\ekansh.jain\\Downloads\\SampleRepo-master\\SampleRepo-master\\Hello.txt");
		boolean exists = varTmpDir.exists();
		System.out.println("*******************"+exists+"*****************");
		Assert.assertEquals(exists,true);
	}
	
	

	@Test(priority=6)
	
	public void push()throws InterruptedException
	{
		Thread.sleep(1000);
		//driver.get(driver.getCurrentUrl());
		driver.findElement(By.cssSelector("#js-repo-pjax-container")).click();	// Alternative for above statement, just to click on screen

		driver.findElement(By.cssSelector("a[href*='upload']")).click();

		driver.findElement(By.cssSelector(".manual-file-chooser.js-manual-file-chooser")).sendKeys("D:\\Test\\Hello.txt");
		driver.findElement(By.cssSelector(".btn.btn-primary.js-blob-submit")).click();
	}
	
	

	@Test(priority=7)
	
	public void validatePush()throws InterruptedException, IOException
	{
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a[title*='Hello.txt']")).click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		String gitFile=driver.findElement(By.cssSelector("#LC1")).getText();
		System.out.println("-----------------Contents from git repository file :"+gitFile);
		FileReader FR = new FileReader("D:\\Test\\Hello.txt");
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";
		StringBuilder  stringBuilder = new StringBuilder();
		while((Content = BR.readLine())!= null)
		{
			stringBuilder.append(Content);
		}
		System.out.println("--------------Contents from local file : "+stringBuilder);
		
		Assert.assertEquals(stringBuilder.toString(), gitFile.toString());
	}
}
