package TatocTasks;

import java.util.Iterator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.Reporter;



public class GridGate 
{
	public String baseurl = "http://10.0.1.86/tatoc";
	public WebDriver driver;
	public WebElement frame;
	JavascriptExecutor js;
	Cookie cookie;
	
	
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
		driver.get(baseurl);
		Thread.sleep(1500);
		driver.findElement(By.linkText("Basic Course")).click();
	}
	
	
	
	@Test(priority=1)
	public void gridGateRed() throws InterruptedException

	{
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(".redbox")).click();
		Thread.sleep(1500);
		driver.navigate().back();
	}
	
	
	
	@Test(priority=2)
	public void gridGateGreen() throws InterruptedException

	{
		Thread.sleep(1500);
		//driver.findElement(By.cssSelector(".greenbox")).click();
		driver.get("http://10.0.1.86/tatoc/basic/windows");
	}
		
	
	
	
	
		
	/*@Test(priority=3)
	public void dungeon() throws InterruptedException
	{
		System.out.println("Method started.....");
		  driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		  Thread.sleep(2000);
		  driver.switchTo().frame("main");
		  System.out.println("Switched to main frame");
		  String box1=driver.findElement(By.cssSelector("#answer")).getAttribute("class");
		  driver.switchTo().frame("child");
		  String box2=driver.findElement(By.cssSelector("#answer")).getAttribute("class");
		  System.out.println("**********"+box1);
		  System.out.println("***********"+box2);
		  
		  while(!box1.equals(box2))
		  {
		   driver.switchTo().defaultContent();
		   driver.switchTo().frame("main");
		   Thread.sleep(1000);
		   driver.findElement(By.linkText("Repaint Box 2")).click();
		   driver.switchTo().frame("child");
		   box2=driver.findElement(By.cssSelector("#answer")).getAttribute("class");
		   System.out.println(box2);
		  }
		  driver.switchTo().defaultContent();
		  driver.switchTo().frame("main");
		  driver.findElement(By.linkText("Proceed")).click();
		
		
		
	}	

*/



	
	@Test(priority=4)
	public void dragNdrop() throws InterruptedException
	{
		
		WebElement element = driver.findElement(By.cssSelector("#dragbox"));
			WebElement target = driver.findElement(By.cssSelector("#dropbox"));
			Actions act=new Actions(driver);
			act.moveToElement(element).clickAndHold().moveToElement(target).release().build().perform();
			driver.findElement(By.cssSelector(".page>a")).click();	
		
	}
	
	
	
	@Test(priority=5)
	public void pop_up() throws InterruptedException
	{
		driver.findElement(By.linkText("Launch Popup Window")).click();
		Set<String> s1=driver.getWindowHandles();
		Iterator<String> i1=s1.iterator();
		String parent=i1.next();
		String child=i1.next();
		driver.switchTo().window(child);
		driver.findElement(By.cssSelector("#name")).sendKeys("Ekansh");		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#submit")).click();
		Thread.sleep(2000);
		driver.switchTo().window(parent);
		Actions ac=new Actions(driver);
		ac.moveToElement(driver.findElement(By.linkText("Proceed"))).click().build().perform();
	}
	
	
	
	
	@Test(priority=6)
	public void addCookie() throws InterruptedException
	{
		driver.findElement(By.linkText("Generate Token")).click();
			String token_value=driver.findElement(By.cssSelector("span[id='token']")).getText();
			String token=token_value.replace("Token:"," ");
			token.trim();
			System.out.println("******************Token"+token);
			cookie = new Cookie("mycookie", token);			
			driver.manage().addCookie(cookie);
			Set<Cookie> cookiesList =  driver.manage().getCookies();
			System.out.println("Total Number Of cookies : " +cookiesList.size());
			for(Cookie currentcookie :cookiesList)
			{
				System.out.println(String.format("%s -> %s -> %s", "Domain Name : "+currentcookie.getDomain(), "Cookie Name : "+currentcookie.getName(), "Cookie Value : "+currentcookie.getValue()));
			}
			driver.findElement(By.cssSelector("a[onclick*='gonext']")).click();	//Cookie adding properly but not navigating forward
	}


	
	
	/*
	@AfterTest
	public void endSession()
	{
		driver.quit();
	}
	*/
	
	
  
}
