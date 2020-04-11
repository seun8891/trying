package com.project.Helper;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BaseClass 
{
    public static   WebDriver driver;
    private static Select select;
    private static String OS;
    private static Actions action;
    private static WebDriverWait wait;
    
    static 
    {
    	driver = null;
    	select = null;
    	OS = System.getProperty("os.name").toLowerCase();
    	action = null;
    	wait = null;
    }
    
    public static void waitForElementToBeVisible(String id) throws Exception
    {
    	wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }
    
    public static void hoveroverElement(WebElement element) throws Exception 
    {
    	action = new Actions(driver);
    	action.moveToElement(element).build().perform();
    }
    
    public static void launchBrowser(String browserName) throws Exception
    {
    	switch (browserName.toLowerCase())
    	{
    	case "chrome":
    		driver= initChrome();
    		break;
    	case "firefox":
    		driver = initFirefox();
    		break;
    		
    		default:
    			System.out.println(browserName + "is not recognised so chrome is launched");
    			driver = initChrome();
    			
    	}
    	
    	driver.manage().window().maximize();
    }
    
    public static void launchUrl(String url) throws Exception
    {
    	driver.navigate().to(url);
    }
    
    public static void closeBrowser() throws Exception 
    {
    	driver.manage().deleteAllCookies();
    	driver.quit();
    }
    
    private static WebDriver initChrome() throws Exception
    {
    	if(OS.contains("win"))
    	{
    		System.setProperty("webdriver.chrome.driver", "chromedriverroot");
    		
    	}else if (OS.contains("mac"));
    	{
    		System.setProperty("webdriver.chrome.driver","/use/local/bin/chromedriver");
    	}
    	
    	return new ChromeDriver();
    }
    
    private static WebDriver initFirefox() throws Exception
    {
    	
    		System.setProperty("webdriver.chrome.driver", "firefoxroot");
    		
    	return new FirefoxDriver();
    }
    
    public static void selectByText(WebElement element, String text) throws Exception
    {
    	select = new Select(element);
    	select.selectByVisibleText(text);
    }
    
    public static void selectByValue(WebElement element, String Value) throws Exception
    {
    	select = new Select(element);
    	select.selectByValue(Value);
    }
    
    public static void selectByIndex(WebElement element, int index) throws Exception
    {
    	select = new Select(element);
    	select.selectByIndex(index);
    }
    
    private static File takeScreenshot() throws Exception
    {
    	return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }
    
    public static void saveScreenshot() throws Exception 
    {
    	String dateNow = new SimpleDateFormat("ddMMyy").format(new GregorianCalendar().getTime());
    	String timeNow = new SimpleDateFormat("HHmmss").format(new GregorianCalendar().getTime());
    	
    	String fileName =String.format("\\Screenshot\\%s\\screenshot_%s", dateNow,timeNow);
    	
    	File screenshot = takeScreenshot();
    	
    	FileUtils.copyFile(screenshot, new File(fileName));
    }
    
    
    public static WebElement getElementById(String id) throws Exception
    {
    	By locator = By.id(id);
    	return getElement(locator);
    }
    
    
    public static List<WebElement> getElementsById(String id) throws Exception
    {
    	By locator = By.id(id);
    	return getElements(locator);
    }
    
    public static WebElement getElementByClassName(String className) throws Exception
    {
    	By locator = By.className(className);
    	return getElement(locator);
    }
    
    
    public static List<WebElement> getElementsByClassName(String className) throws Exception
    {
    	By locator = By.className(className);
    	return getElements(locator);
    }
    
    public static WebElement getElementByName(String Name) throws Exception
    {
    	By locator = By.name(Name);
    	return getElement(locator);
    }
    
    
    public static List<WebElement> getElementsByName(String Name) throws Exception
    {
    	By locator = By.name(Name);
    	return getElements(locator);
    }
    
    public static WebElement getElementByXpath(String Xpath) throws Exception
    {
    	By locator = By.xpath(Xpath);
    	return getElement(locator);
    }
    
    
    public static List<WebElement> getElementsByXpath(String Xpath) throws Exception
    {
    	By locator = By.xpath(Xpath);
    	return getElements(locator);
    }
    
    public static WebElement getElementByCssSelector(String cssSelector) throws Exception
    {
    	By locator = By.cssSelector(cssSelector);
    	return getElement(locator);
    }
    
    
    public static List<WebElement> getElementsByCssSelector(String cssSelector) throws Exception
    {
    	By locator = By.cssSelector(cssSelector);
    	return getElements(locator);
    }
    
    
    
    
    
    private static WebElement getElement(By locator) throws Exception
    {
    	WebElement element = null;
    	int tryCount = 0;
    	
    	while(element == null)
    	{
    		try
    		{
    			element = driver.findElement(locator);
    		}
    		
    		catch(Exception e)
    		{
    			if(tryCount == 3)
    			{
    				saveScreenshot();
    				throw e;
    			}
    		}
    		
    		Thread.sleep(2000);
    		tryCount++;
    	
    	
    	}
    	System.out.println(element.toString()+ "is now retrieved");
    	
    	
    	
    	
    	
    	
    	return element;
     }
    
    private static List<WebElement> getElements(By locator) throws Exception
    {
    	List<WebElement> element = null;
    	int tryCount = 0;
    	
    	while(element == null)
    	{
    		try
    		{
    			element = driver.findElements(locator);
    		}
    		
    		catch(Exception e)
    		{
    			if(tryCount == 3)
    			{
    				saveScreenshot();
    				throw e;
    			}
    		}
    		
    		Thread.sleep(2000);
    		tryCount++;
    	
    	
    	}
    	System.out.println(element.toString()+ "is now retrieved");
    	
    	
    	
    	
    	
    	
    	return element;
    	
    	
    	
   }
    
    
    
 







}
    
    

