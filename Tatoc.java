package Advance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Tatoc 
{
	public WebDriver driver;
	public WebElement hover,click;
	
	
	
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
		driver.get("http://10.0.1.86/tatoc");
		Thread.sleep(1500);
		driver.findElement(By.linkText("Advanced Course")).click();
	}
	
	
	
	@Test(priority=1)
	
	public void hover() throws InterruptedException
	{
		Actions ac=new Actions(driver);
		hover=driver.findElement(By.cssSelector(".menutitle"));
		click=driver.findElement(By.cssSelector("span[onclick*='gonext']"));
		Thread.sleep(1000);
		ac.moveToElement(hover).click(click).build().perform();
	}
	
	
	
	@Test(priority=2)
	
	public void queryGate() throws InterruptedException, ClassNotFoundException, SQLException
	{
		String dbUrl="jdbc:mysql://10.0.1.86/tatoc";
		String username = "tatocuser";
		String password = "tatoc01";
		String sym=driver.findElement(By.cssSelector("#symboldisplay")).getText();
		String query = "select credentials.name,credentials.passkey from credentials INNER JOIN identity on identity.id=credentials.id where identity.symbol='"+sym+"'";
		
		System.out.println("***Loading driver***");
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("***Driver Loaded***");
		
		System.out.println("***Making connection***");
		Connection con = DriverManager.getConnection(dbUrl,username,password);
		System.out.println("***Connection made***");
		
		Statement stmt = con.createStatement();
		ResultSet rs= stmt.executeQuery(query);	
		
		
		while (rs.next())
		{
    		String name=rs.getString(1);								//(1) is the index number of column that needs to be printed
    		String passkey=rs.getString(2);
    		driver.findElement(By.cssSelector("#name")).sendKeys(name);
    		driver.findElement(By.cssSelector("#passkey")).sendKeys(passkey);
		}
		
		driver.findElement(By.cssSelector("#submit")).click();
		con.close();	
	}
	
	
	
	@Test(priority=3)
	public void ooyala()throws InterruptedException
	{
		
	}
}
