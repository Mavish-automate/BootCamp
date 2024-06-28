package com.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataPro 
{
	
	public RemoteWebDriver driver;
	
	@DataProvider(name="getdata")
	public String[][] data() 
	{
		String[][] data=new String[2][2];
		data[0][0]="vish";
		data[0][1]="jhg";
		
		data[1][0]="Vishal@testleaf.com";
		data[1][1]="trustmavish@2024";
		
		return data;
		}
	
	
	
	@Parameters({"url"})
@BeforeMethod
public void pre(String url) 
{
	ChromeOptions opt=new ChromeOptions();
	opt.addArguments("--disable-notifications");
	driver=new ChromeDriver(opt);
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
}

@Test(dataProvider = "getdata")
public void tc3(String uname,String pwd) 
{
	driver.findElement(By.id("username")).sendKeys(uname);
	driver.findElement(By.id("password")).sendKeys(pwd);
	driver.findElement(By.id("Login")).click();
}

@AfterMethod
public void post(ITestResult result) throws IOException 
{
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
