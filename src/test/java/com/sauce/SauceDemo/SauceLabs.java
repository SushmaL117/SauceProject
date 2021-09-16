package com.sauce.SauceDemo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import com.business.sauce.SauceBusiness;

public class SauceLabs {
	
	private static SauceBusiness sauceBusiness;
	
	@BeforeTest
	public void sauce() throws Exception {
		sauceBusiness = new SauceBusiness();
	}
	
	@Test(priority=1)
	@Parameters({ "ProductTitle" })
	public void ProductDetails_Validation(String productTitle) throws Exception {
		sauceBusiness.launchTheBrowser();
		sauceBusiness.login();
		sauceBusiness.validationOfProductDetails(productTitle);	 
	}
	
	@Test(priority=2)
	@Parameters({ "ProductName" })
	public void Product_Availability(String productName) throws Exception {
		sauceBusiness.launchTheBrowser();
		sauceBusiness.login();
		sauceBusiness.availabilityOfProduct(productName);
		
	}
	
	@Test(priority=3)
	public void ProductPrice_Validation() throws Exception {
		sauceBusiness.launchTheBrowser();
		sauceBusiness.login();
		sauceBusiness.validationOfProductPrice();
	}
	
	@Test(priority=4)
	public void ErrorMessage_Validation() throws Exception {
		sauceBusiness.launchTheBrowser();
		sauceBusiness.errorMessageInLoginPage();
	}
	
	@AfterMethod
	public void tearDown() {
		sauceBusiness.tearDown();
	}
}

