package com.sauce.pages;

import org.openqa.selenium.By;

public class CheckoutOverviewPage {

	public static By objProductName = By.xpath("//div[@class='inventory_item_name']");
	public static By objProductDesc = By.xpath("//div[@class='inventory_item_desc']");
	public static By objProductPrice = By.xpath("//div[@class='inventory_item_price']");
	public static By objCancelButton = By.xpath("//button[@id='cancel']");
}
