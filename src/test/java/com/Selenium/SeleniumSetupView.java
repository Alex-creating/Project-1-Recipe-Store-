package com.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumSetupView {
	
	private WebDriver driver;
	
	public SeleniumSetupView(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div/table/tbody/tr[1]/td[1]")
	private WebElement nameField;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div/table/tbody/tr/td[3]")
	private WebElement servingField;
	
	@FindBy (xpath = "/html/body/div[1]/div[2]/div/table/tbody/tr/td[4]")
	private WebElement lengthField;
	
	@FindBy (id="editRecipeName")
	private WebElement editName;
	
	@FindBy (id="editRecipeServing")
	private WebElement editServing;
	
	@FindBy (id="editRecipeLength")
	private WebElement editLength;
	
	@FindBy (id="methodEdit")
	private WebElement editMethod;
	
	@FindBy (id="editButton")
	private WebElement editButton;
	
	@FindBy (id="deleteButton")
	private WebElement deleteButton;
	
	@FindBy (id="editSubmitButton")
	private WebElement submitNewInfo;
	
	public WebElement checkExistance() {
		return nameField;
	}
	
	public String getNameFromTable() {
		return nameField.getText();
	}
	
	public String getServingFromTable() {
		return servingField.getText();
	}
	
	public String getLengthFromTable() {
		return lengthField.getText();
	}
	
	public void goToViewPage() {
		servingField.click();
	}
	
	public void clearEditFields() {
		editName.clear();
		editLength.clear();
		editServing.clear();
		editMethod.clear();
	}
	
	public void addNewDetails(String newName, String newServing, String newLength, String newMethod) {
		editName.sendKeys(newName);
		editLength.sendKeys(newLength);
		editServing.sendKeys(newServing);
		editMethod.sendKeys(newMethod);
	}
	
	public void submitEdit() {
		submitNewInfo.click();
	}
	
	public void goToEditPage() {
		editButton.click();
	}
	
	public void deleteRecipe() {
		deleteButton.click();
	}

}
