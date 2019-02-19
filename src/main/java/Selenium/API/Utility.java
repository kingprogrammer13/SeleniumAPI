package Selenium.API;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Utility {

	private WebDriver driver;
	JavascriptExecutor jse;
	ExtentTest logger;
	String webelements;

	public Utility(WebDriver d, ExtentTest logger) {
		this.driver = d; 
		this.logger = logger; 
		jse = (JavascriptExecutor) driver;
	}

	public void URL(String URL) throws FileNotFoundException, IOException {
		driver.get(URL);
		
		logger.log(Status.INFO, "URL is Passed " + URL);
	}
	
	private By locatorValue(String locatorType, String value) {
        By by;
        switch (locatorType) {
            case "id":
                by = By.id(value);
                break;
            case "name":
                by = By.name(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "css":
                by = By.cssSelector(value);
                break;
            case "className":
                by = By.className(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            case "tagName":
                by = By.tagName(value);
                break;
            default:
                by = null;
                break;
        }
        return by;
    }
	
	public void sendKey(String webelementType, String webelement, int waitTime, String Values) {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement SendKey = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		SendKey.sendKeys(Values);
		
		logger.log(Status.INFO, "Value " + Values + " is Passed to this " + locator + " Element");
	}
	
	public void sendKeyEnter(String webelementType, String webelement, int waitTime, String Values) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement SendKeyEntr = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		SendKeyEntr.sendKeys(Values);		
		SendKeyEntr.sendKeys(Keys.ENTER);
				
		logger.log(Status.INFO, "Value " + Values + " is Passed to this " + locator + " Element");
	}
	
	public void Click(String webelementType, String webelement, int waitTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement SendKey = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		SendKey.click();
		
		logger.log(Status.INFO, "Element " + locator + " is Clicked");
	}
	
	public void scrollToElement(String webelementType, String webelement, int waitTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		
		logger.log(Status.INFO, "Page scroll to this " + locator + " Element");
	}
	
	public void documentUpload(String webelementType, String webelement,int waitTime, String Values) {

		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));		
		WebElement docUpload = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		docUpload.sendKeys(Values);
		
		logger.log(Status.INFO, "Document is Uploaded");
	}
	
	public String getText(String webelementType, String webelement,int waitTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));				
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		String Text = element.getText();
		
		logger.log(Status.INFO, "Text " + Text + " is get from this " + locator + " Element");
		return Text;
	}
	
	public int elementCount(String webelementType, String webelement, int waitTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));				
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
		int Temp = driver.findElements((locator)).size();
		
		logger.log(Status.INFO, "Given Element " + locator + " Count is :"+Temp);
		return Temp;
	}
	
	public void elementVisible(String webelementType, String webelement, int waitTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));				
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		driver.findElement(locator).isDisplayed();
		logger.log(Status.INFO, "Element " + locator + " is Visible");
	}
	
	public void elementEnabled(String webelementType, String webelement, int waitTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));				
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		driver.findElement(locator).isEnabled();
		logger.log(Status.INFO, "Element " + locator + " is Enable");
	}
	
	public void elementSelected(String webelementType, String webelement, int waitTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));				
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		driver.findElement(locator).isSelected();
		logger.log(Status.INFO, "Element " + locator + " is Selected");
	}
	
	public void waitUntilVisible(String webelementType, String webelement, int waitTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));				
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.log(Status.INFO, "Wait for this " + locator + " Element Visibility");
	}
	
	public void loaderInvisible(String webelementType, String webelement, int waitTime) {
		
        By locator = locatorValue(webelementType, webelement);

		boolean loader = driver.findElement(locator).isDisplayed();
		if(loader)
		{
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		logger.log(Status.INFO, "Wait for this "+locator+" Loader InVisibility");
		}
	}
	
	public void waitUntilInVisible(String webelementType, String webelement,int waitTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));				
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		logger.log(Status.INFO, "Wait for this " + locator + " Element Visibility");
	}
	
	public void waitUntilPresent(String webelementType, String webelement,int waitTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		logger.log(Status.INFO, "Wait for this " + locator + " Element Presents");
	}
	
	public void selectOption(String webelementType, String webelement,int waitTime, String Values) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        By locator = locatorValue(webelementType, webelement);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));				
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		WebElement Option = driver.findElement(locator);
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(Option));
		Option.click();
		Select select = new Select(Option);
		select.selectByVisibleText(Values);
		logger.log(Status.INFO, "Option " + Values + " is Select from the Element" + locator);
	}
	
	public String propertyData(String Path) throws FileNotFoundException, IOException {
		Properties Property = new Properties();
		Property.load(new FileInputStream("./config.properties"));
		String InputData = Property.getProperty(Path);
		return InputData;
	}
	
	public String screenShot(WebDriver driver, String method) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String ProPath = propertyData("ReportImg");
		String path = (ProPath + method + ".png");
		FileUtils.copyFile(src, new File(path));

		try {
			logger.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("file://" + path).build());
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;		
	}
}
