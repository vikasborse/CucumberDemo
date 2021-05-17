package autoapp.automation.pages;

import autoapp.automation.pages.registration.RegisterPage;
import autoapp.automation.utility.BrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class BasePage extends BrowserDriver{

	public BasePage(BrowserDriver driver){
		super(driver);
	}

	public static int TIME_OUT_IN_SECONDS = 10;

	public static int generateRandomIntIntRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}


	public  static WebElement findElement(WebDriver driver, By by) {
		WebElement element = null;
		try {
			element = driver.findElement(by);
		}catch (Exception exception){}

		return element;
	}
	public  static WebElement findElement(WebElement webElement, By by) {
		WebElement element = null;
		try {
			element = webElement.findElement(by);
		}catch (Exception exception){
			System.err.println("Unable to find WebElement with By:"+by.toString());
		}
		return element;
	}

	public  static List<WebElement> findElements(WebDriver driver, By by) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(by);
		}catch (Exception exception){
			System.err.println("Unable to find WebElements with By:"+by.toString());
		}
		return elements;
	}
	
	public  static boolean waitForElementToBePresent(WebDriver driver, WebElement element, int waitTime) {
		try{
			WebDriverWait wait = new WebDriverWait(driver, waitTime);

		wait.until(ExpectedConditions.visibilityOf(element));
		}catch (Exception exc){
			System.err.println("Error while waiting."+exc.getMessage());
		}
		return true;
	}
	public  static boolean waitForElementToBeVisible(WebDriver driver, By by, int waitTime) {
		try{
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
		    wait.until(ExpectedConditions.visibilityOf(findElement(driver,by)));
		}catch (Exception exc){
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static String getElementAttribute(WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}

	public static WebDriver switchToIframe(WebDriver driver, WebElement element) {
		WebDriver iFrame = driver.switchTo().frame(element);
		return iFrame;
	}

	public static void clickButton(WebElement buttonToBeClicked) {
		buttonToBeClicked.click();
	}

	public static void enterText(WebElement element, String textToBeEntered) {
		element.sendKeys(textToBeEntered);
	}

	public static String getText(WebElement textPresent) {
		return textPresent.getText();
	}

	public static boolean isPresent(WebDriver driver, By locator) {
		driver.findElements(locator);
		return true;
	}

	public static void enterKey(WebElement element, String keyValue) {
		element.sendKeys(keyValue);
	}

	public static void clearTextField(WebElement element) {
		element.clear();
	}

	public static boolean waitForElementClickable(WebDriver driver, WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(element));
		return true;
	}

	public static void clickButtonInsideOverlay(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public static Object getHiddenElementValue(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return executor.executeScript("arguments[0].value();", element);
	}


	public static boolean pageSourceCode(WebDriver driver, String verifyText) {
		return driver.getPageSource().contains(verifyText);
	}


	public static String getParamFromProperties( String paramName){
		String param = "";
		try (InputStream input = RegisterPage.class.getClassLoader().getResourceAsStream("config.properties")) {

			Properties prop = new Properties();

			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
				param = "";
			}

			//load a properties file from class path, inside static method
			prop.load(input);

			//get the property value and print it out
			param = prop.getProperty(paramName);
			System.out.println(param);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return param;

	}
}
