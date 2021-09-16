package com.utility.sauce;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import com.sauce.extent.ExtentReporter;


public class utilities extends ExtentReporter{
	
	static WebDriverWait wait;
	private SoftAssert softAssert = new SoftAssert();
	private int timeout;
	
	public int getTimeout() {
		return 30;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	/** The Constant logger. */
	public final static Logger logger = Logger.getLogger("rootLogger");
		 
	public void initDriver() {
		wait = new WebDriverWait(driver, getTimeout());	
	}

	public WebElement findElement(By byLocator) throws Exception {
		wait = new WebDriverWait(driver, getTimeout());
		try{
	    	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		    return element;
		}catch(Exception e){
			return null;
		}
	}
	
	public boolean verifyElementExist(By byLocator, String str) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			if (element.isDisplayed()) {
				extentLoggerPass("checkElementPresent", "" + str + " is displayed");
				logger.info("" + str + " is displayed");
				return true;
			}
		} catch (Exception e) {
			extentLoggerFail("checkElementPresent", "" + str + " is not displayed");
			logger.info(str + " is not displayed");
			return false;
		}
		return false;
	}
	
	public boolean verifyElementPresent(By byLocator, String validationtext) throws Exception {

		try {
			WebElement element = findElement(byLocator);
			softAssert.assertEquals(element.isDisplayed(), true, "" + validationtext + " " + " is displayed");
			logger.info(validationtext + " is displayed");
			extentLoggerPass("checkElementPresent", validationtext + " is displayed");
			return true;
		} catch (Exception e) {
			softAssert.assertEquals(false, true, validationtext + " " + " is not displayed");
//			softAssert.assertAll();
			logger.error(validationtext + " is not displayed");
			extentLoggerFail("checkElementPresent", validationtext + " is not displayed");
			return false;
		}
	}
	
	public void waitTime(int x) {
		try {
			Thread.sleep(x);
		} catch (Exception e) {
//			logger.error(e);
		}
	}
	
	public void click(By byLocator, String validationtext) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			element.click();
			logger.info("Clicked on " + validationtext);
			extentLogger("click", "Clicked on " + validationtext);
		} catch (Exception e) {
			//screencapture();
		}
	}
	
	public String getText(By byLocator) throws Exception {
		    String Value = null;
			WebElement element = findElement(byLocator);
			Value = element.getText();
		    return Value;
	}
	
	public static boolean waitForElementDisplayed(By byLocator, int iTimeOut) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}



