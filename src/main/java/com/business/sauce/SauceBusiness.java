package com.business.sauce;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sauce.pages.CheckoutOverviewPage;
import com.sauce.pages.CheckoutUserInfoPage;
import com.sauce.pages.LoginPage;
import com.sauce.pages.ProductsHomePage;
import com.sauce.pages.ShoppingCartPage;
import com.utility.sauce.utilities;

public class SauceBusiness extends utilities{
	
	String[] usernames;
	String[] passwords;

    public void launchTheBrowser() throws Exception{
    	
    	System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		  
	     driver = new ChromeDriver();
	      
	     driver.navigate().to("https://www.saucedemo.com/");
	     driver.manage().window().maximize();
	    
    }
    
    public void login() throws Exception {
         waitForElementDisplayed(LoginPage.objUserName, 30);
	     
	     String userName = getText(LoginPage.objUserName);
	     usernames = userName.split("\n");
	     String password = getText(LoginPage.objPassword);
	     passwords = password.split("\n");
	     
	     findElement(LoginPage.objUserNameField).sendKeys(usernames[1]);
	     findElement(LoginPage.objPasswordField).sendKeys(passwords[1]);
	     click(LoginPage.objLoginButton, "Login Button");
    }
    
    public void validationOfProductDetails(String productTitle) throws Exception {
         
	     waitForElementDisplayed(ProductsHomePage.objProductName, 30);
	     String productTitleInHomePage = getText(ProductsHomePage.objProductTitlethroughAddToCart(1));
	     String productDescInHomePage = getText(ProductsHomePage.objProductDescthroughAddToCart(1));
	     String productPriceInHomePage = getText(ProductsHomePage.objProductPricethroughAddToCart(1));
	     
	     click(ProductsHomePage.objAddToCartButtonThroughTitle(productTitle), "Add to cart button");
	     click(ProductsHomePage.objShoppingCartIcon, "Shopping Cart icon");
	     click(ShoppingCartPage.objCheckOut, "Checkout");
	     
	     findElement(CheckoutUserInfoPage.objFirstName).sendKeys(usernames[1]);
	     findElement(CheckoutUserInfoPage.objLastName).sendKeys(passwords[1]);
	     findElement(CheckoutUserInfoPage.objZipNumber).sendKeys("583119");
	     click(CheckoutUserInfoPage.objContinueButton, "Continue button");
	     
	     String productNameInCheckOutOverViewPage = findElement(CheckoutOverviewPage.objProductName).getText();
	     String productDescInCheckOutOverViewPage = findElement(CheckoutOverviewPage.objProductDesc).getText();
	     String productPriceInCheckOutOverViewPage = findElement(CheckoutOverviewPage.objProductPrice).getText();
	     
	     if(productTitleInHomePage.equalsIgnoreCase(productNameInCheckOutOverViewPage)) {
	    	 logger.info("Product name '"+productTitleInHomePage+"' in Products home page is same as Product name '"+productNameInCheckOutOverViewPage+"' in Checkout overview page");
	    	 extentLoggerPass("Product Name","Product name '"+productTitleInHomePage+"' in Products home page is same as Product name '"+productNameInCheckOutOverViewPage+"' in Checkout overview page");
	     }else {
	    	logger.error("Product name '"+productTitleInHomePage+"' in Products home page is not same as Product name '"+productNameInCheckOutOverViewPage+"' in Checkout overview page"); 
	    	extentLoggerFail("Product Name","Product name '"+productTitleInHomePage+"' in Products home page is not same as Product name '"+productNameInCheckOutOverViewPage+"' in Checkout overview page");
	     }
	     
	     if(productDescInHomePage.equalsIgnoreCase(productDescInCheckOutOverViewPage)) {
	    	 logger.info("Product desc '"+productDescInHomePage+"' in Products home page is same as Product desc '"+productDescInCheckOutOverViewPage+"' in Checkout overview page");
	    	 extentLoggerPass("Product description","Product desc '"+productDescInHomePage+"' in Products home page is same as Product desc '"+productDescInCheckOutOverViewPage+"' in Checkout overview page");
	     }else {
	    	 logger.error("Product desc '"+productDescInHomePage+"' in Products home page is not same as Product desc '"+productDescInCheckOutOverViewPage+"' in Checkout overview page"); 
	    	 extentLoggerFail("Product description","Product desc '"+productDescInHomePage+"' in Products home page is not same as Product desc '"+productDescInCheckOutOverViewPage+"' in Checkout overview page");
	     }
	     
	     if(productPriceInHomePage.equalsIgnoreCase(productPriceInCheckOutOverViewPage)) {
	    	 logger.info("Product price '"+productPriceInHomePage+"' in Products home page is same as Product price '"+productPriceInCheckOutOverViewPage+"' in Checkout overview page");
	    	 extentLoggerPass("Product price","Product price '"+productPriceInHomePage+"' in Products home page is same as Product price '"+productPriceInCheckOutOverViewPage+"' in Checkout overview page");
	     }else {
	    	 logger.error("Product price '"+productPriceInHomePage+"' in Products home page is not same as Product price '"+productPriceInCheckOutOverViewPage+"' in Checkout overview page"); 
	    	 extentLoggerFail("Product price","Product price '"+productPriceInHomePage+"' in Products home page is not same as Product price '"+productPriceInCheckOutOverViewPage+"' in Checkout overview page");
	     }
	     
	     click(CheckoutOverviewPage.objCancelButton, "Cancel Button");
	     click(ProductsHomePage.objRemoveButtonThroughTitle(productTitle), "Remove button");
    }
    
    public void availabilityOfProduct(String productName) throws Exception {
    	waitForElementDisplayed(ProductsHomePage.objProductName, 30);
    	int noOfProducts = driver.findElements(ProductsHomePage.objProductName).size();
    	logger.info("Number of Products: "+noOfProducts);
    	extentLogger("Products size", "Number of Products: "+noOfProducts);
    	
    	ArrayList<String> productNames = new ArrayList<String>();
    	for(int i =1; i<=noOfProducts; i++) {
    		String title = driver.findElement(By.xpath("(//div[@class='inventory_item_name'])["+i+"]")).getText();
    		productNames.add(title);
    	}
    	
    	logger.info("Products list: "+productNames);
    	extentLogger("Product List", "Products list: "+productNames);
    	
    	boolean flag = false;
    	for(int i=0; i<noOfProducts; i++) {
    		if(productNames.get(i).equalsIgnoreCase(productName)) {
    			flag = true;
    			logger.info("'"+productName+"' is available in product list");
    			extentLoggerPass("Product availability", "'"+productName+"' is available in product list");
    			break;
    		}
    	}
    	if(flag == false) {
    		logger.error("'"+productName+"' is not available in product list");
    		extentLoggerFail("Product availability", "'"+productName+"' is not available in product list");
    	}
    	
    }
    
    public void validationOfProductPrice() throws Exception {
    	waitForElementDisplayed(ProductsHomePage.objProductPriceInHomePage, 30);
     	int noOfProductPrices = driver.findElements(ProductsHomePage.objProductPriceInHomePage).size();
     	logger.info("Number of Product price: "+noOfProductPrices);
    	extentLogger("Products size", "Number of Product price: "+noOfProductPrices);
    	
     	ArrayList<String> productPrices = new ArrayList<String>();
     	
     	for(int i =1; i<=noOfProductPrices; i++) {
     		String productName = driver.findElement(By.xpath("(//div[@class='inventory_item_name'])["+i+"]")).getText();
     		String cost = findElement(ProductsHomePage.objPriceOfProduct(productName)).getText();
     		String price = cost.replace("$", "");
     		productPrices.add(price);
     	}
     	
     	logger.info("Product prices: "+productPrices);
     	extentLogger("Product price", "Product prices: "+productPrices);
     	
     	for(int i = 0; i<noOfProductPrices; i++) {
     		String title = driver.findElement(By.xpath("(//div[@class='inventory_item_name'])["+(i+1)+"]")).getText();
    		findElement(By.xpath("(//div[@class='inventory_item_name'])["+(i+1)+"]")).click();
    		String cost = findElement(ProductsHomePage.objProductPriceInProductPage).getText();
    		if(cost.contains(productPrices.get(i))) {
    			logger.info("Product price in product indiviual page is properly displayed as "+cost+" for a product '"+title+"'");
    			extentLoggerPass("Product price", "Product price in product indiviual page is properly displayed as "+cost+" for a product '"+title+"'");
    		}else {
    			logger.error("Product price in product indiviual page is not properly displayed as "+cost+" for a product '"+title+"'");
    			extentLoggerFail("Product price", "Product price in product indiviual page is not properly displayed as "+cost+" for a product '"+title+"'");
    		}
    		click(ProductsHomePage.objBackButtonInProductDetailsPage, "Back to Products page button");
    	}
    }
    
    public void errorMessageInLoginPage() throws Exception {
    	findElement(LoginPage.objUserNameField).sendKeys("standard-sauc");
	    findElement(LoginPage.objPasswordField).sendKeys("secret_sauce");
	    click(LoginPage.objLoginButton, "Login Button");
    	verifyElementPresent(LoginPage.objErrormessageInLoginPage, "Error message");
    }
    
    public void tearDown() {
		driver.quit();
	}
}
