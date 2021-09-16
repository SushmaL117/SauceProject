package com.sauce.pages;

import org.openqa.selenium.By;

public class ProductsHomePage {
	
	public static By objProductsHomePageheader = By.xpath("//span[@class = 'title' and contains(text(),'Products')]");
  
	public static By objProductTitlethroughAddToCart(int i) {
    	return By.xpath("(//button[@class='btn btn_primary btn_small btn_inventory']/parent::*/preceding-sibling::*/child::a/child::div[@class='inventory_item_name'])["+i+"]");
    }
	
	public static By objProductDescthroughAddToCart(int i) {
    	return By.xpath("(//button[@class='btn btn_primary btn_small btn_inventory']/parent::*/preceding-sibling::*/child::div[@class='inventory_item_desc'])["+i+"]");
    } 
   
    public static By objAddToCartButtonThroughTitle(String title) {
    	return By.xpath("//div[contains(text(),'"+title+"')]/parent::*/parent::*/following-sibling::*/child::button[contains(text(),'Add to cart')]");
    }
    
    public static By objRemoveButtonThroughTitle(String title) {
    	return By.xpath("//div[contains(text(),'"+title+"')]/parent::*/parent::*/following-sibling::*/child::button[contains(text(),'Remove')]");
    }
    
    public static By objProductPricethroughAddToCart(int i) {
    	return By.xpath("(//button[@class='btn btn_primary btn_small btn_inventory']/preceding-sibling::div[@class='inventory_item_price'])["+i+"]");
    } 
    
    public static By objShoppingCartIcon = By.xpath("//a[@class='shopping_cart_link']");
    
    public static By objProductName = By.xpath("//div[@class='inventory_item_name']");
    
    public static By objProductPriceInHomePage = By.xpath("//div[@class='inventory_item_price']");
    
    public static By objProductPriceInProductPage = By.xpath("//div[@class='inventory_details_price']");
    
    public static By objPriceOfProduct(String title) {
    	return By.xpath("//div[contains(text(),'"+title+"')]/parent::*/parent::*/following-sibling::*/child::div[@class='inventory_item_price']");
    }
    
    public static By objBackButtonInProductDetailsPage = By.xpath("//button[@id='back-to-products']");

}
