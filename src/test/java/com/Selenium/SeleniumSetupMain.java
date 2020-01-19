package com.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumSetupMain {
	
	private WebDriver driver;
	
	public SeleniumSetupMain(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,  this);
	}
	
	@FindBy(id= "recipeName")
	private WebElement enterName;
	
	@FindBy(id="serving")
	private WebElement enterServing;
	
	@FindBy(id="howLong")
	private WebElement enterLength;
	
	@FindBy(id="mainSubmit")
	private WebElement submitButton;
	
	
	public void submitName(String adding){
		enterName.sendKeys(adding);
	}
	
	public void submitServing(String adding) {
		enterServing.sendKeys(adding);
	}
	
	public void submitLength(String adding) {
		enterLength.sendKeys(adding);
	}
	
	public void submitForm() {
		submitButton.click();
	}
}
