package com.Selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.RecipeStoreApp;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = RecipeStoreApp.class)
public class SeleniumTests {
	
	@LocalServerPort
	private int port;
	private WebDriver driver;
	private WebDriverWait wait;
	
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions();
//		options.setHeadless(true);
		this.driver = new ChromeDriver(options);
		this.driver.manage().window().setSize(new Dimension(1600, 700));
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.wait = new WebDriverWait(driver, 3);
		
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}
	
	@Test
	public void testCRUD() throws InterruptedException {		
		
		SeleniumSetupMain mainPage = new SeleniumSetupMain(driver);
		SeleniumSetupView viewPage = new SeleniumSetupView(driver);
		String recipeName = "Chicken Pie";
		String recipeLength = "120";
		String recipeServing = "12";
		
		String editName = "Beef Stew";
		String editLength = "65";
		String editServing = "5";
		String editMethod = "Stew it";
		
		this.driver.get("http://localhost:" + port + "/RecipeStore");

		
		mainPage.submitName(recipeName);
		mainPage.submitServing(recipeServing);
		mainPage.submitLength(recipeLength);
		mainPage.submitForm();
		
		this.wait.until(ExpectedConditions.alertIsPresent());
		
		String alertMessage = this.driver.switchTo().alert().getText();
		assertEquals("You have added " + recipeName + " to your store!", alertMessage);
		
		this.driver.switchTo().alert().accept();
		assertEquals(recipeName, viewPage.getNameFromTable());
		assertEquals(recipeLength + " minutes", viewPage.getLengthFromTable());
		assertEquals(recipeServing + " people", viewPage.getServingFromTable());
		
		
		viewPage.goToViewPage();
		viewPage.goToEditPage();
		
		viewPage.clearEditFields();
		viewPage.addNewDetails(editName, editServing, editLength, editMethod);
		viewPage.submitEdit();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/table/tbody/tr[1]/td[1]")));
		assertEquals(editName, viewPage.getNameFromTable());
		assertEquals(editLength + " minutes", viewPage.getLengthFromTable());
		assertEquals(editServing + " people", viewPage.getServingFromTable());

		viewPage.goToViewPage();
		viewPage.deleteRecipe();
		this.wait.until(ExpectedConditions.alertIsPresent());
		this.driver.switchTo().alert().accept();
		this.wait.until(ExpectedConditions.alertIsPresent());
		this.driver.switchTo().alert().accept();
		assertFalse(viewPage.checkExistance().isSelected());
		
	}
	@Test
	public void testLogic() {
		
		SeleniumSetupMain mainPage = new SeleniumSetupMain(driver);
		
		this.driver.get("http://localhost:" + port + "/RecipeStore");
		
		String recipeName = "";
		String recipeWorkingName = "Chicken";
		String recipeServing = "5";
		String recipeLength = "6000";

		mainPage.submitName(recipeName);
		mainPage.submitForm();
		this.wait.until(ExpectedConditions.alertIsPresent());
		String alertMessage = this.driver.switchTo().alert().getText();
		assertEquals("Please enter a name longer than 2 characters", alertMessage);
		this.driver.switchTo().alert().accept();
		
		mainPage.submitName(recipeWorkingName);
		mainPage.submitLength(recipeLength);
		mainPage.submitServing(recipeServing);
		mainPage.submitForm();
		
		this.wait.until(ExpectedConditions.alertIsPresent());
		String alertMessage2 = this.driver.switchTo().alert().getText();
		this.driver.switchTo().alert().accept();
		assertEquals("Please enter a time less than 600 minutes", alertMessage2);
	}
	


}