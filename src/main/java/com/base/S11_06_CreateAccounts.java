package com.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class S11_06_CreateAccounts 
{
	
	public RemoteWebDriver driver;
	
	@Parameters({"url","username","password"})
@BeforeMethod
public void pre(String url,String uname,String pwd) 
{
	ChromeOptions opt=new ChromeOptions();
	opt.addArguments("--disable-notifications");
	driver=new ChromeDriver(opt);
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	driver.findElement(By.id("username")).sendKeys(uname);
	driver.findElement(By.id("password")).sendKeys(pwd);
	driver.findElement(By.id("Login")).click();
	
	
}
	
	@Test(retryAnalyzer = FailedRetry.class)
	public void tc2() {
		
	//2. Click on toggle menu button from the left corner
	
	driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();

	
	//3.Click view All and click Sales from App Launcher
	
	driver.findElement(By.xpath("//button[text()='View All']")).click();
	
	driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("Sales");
	driver.findElement(By.xpath("//p[@title='Manage your sales process with accounts, leads, opportunities, and more']")).click();
	 
	
	//4.Click on Accounts tab
	
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/span[text()='Accounts']")));
	WebElement accounts = driver.findElement(By.xpath("//a/span[text()='Accounts']"));
	
	 JavascriptExecutor jse=(JavascriptExecutor)driver;
	 jse.executeScript("arguments[0].click();",accounts);
	 
	 //5. Click on New button
	 
	 driver.findElement(By.xpath("//div[@title='New']")).click();
	 
	 //6. Enter 'your name' as account name
	 
	 driver.findElement(By.xpath("//input[@name='Nam']")).sendKeys("Vishal");
	 
	 //7. Select Ownership as Public
	 
	 WebElement ownership = driver.findElement(By.xpath("//button[@aria-label='Ownership']"));
	 
	 
	 jse.executeScript("arguments[0].scrollIntoView(true);",ownership);
	 jse.executeScript("arguments[0].click();",ownership);
	 
	 driver.findElement(By.xpath("//span[text()='Public']")).click();
	 
	 //8.Click save and verify Account name
	 
	 driver.findElement(By.xpath("(//button[text()='Save'])[2]")).click();
}
	
	@AfterMethod
	public void post(ITestResult result) throws IOException {
		if(!result.isSuccess()) 
		{
			String	 newDest="./ScreenShotFolder/failed.png";
			
			TakesScreenshot tks=(TakesScreenshot)driver;

			       File src = tks.getScreenshotAs(OutputType.FILE);
			       File dest=new File(newDest);
			       FileUtils.copyFile(src,dest);
			      
		}
		driver.quit();
		
	}
}
