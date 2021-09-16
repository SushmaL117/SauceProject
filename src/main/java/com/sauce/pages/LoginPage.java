package com.sauce.pages;

import org.openqa.selenium.By;

public class LoginPage {

	public static By objUserName = By.xpath("//*[@id='login_credentials']");
	public static By objPassword = By.xpath("//*[@class='login_password']");
	public static By objUserNameField = By.xpath("//input[@placeholder='Username']");
	public static By objPasswordField = By.xpath("//input[@placeholder='Password']");
	public static By objLoginButton = By.xpath("//input[@type='submit']");
	public static By objErrormessageInLoginPage = By.xpath("//h3[@data-test='error']");
}
