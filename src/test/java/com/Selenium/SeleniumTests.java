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
	
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		this.driver = new ChromeDriver(options);
		this.driver.manage().window().setSize(new Dimension(1600, 700));
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}
	
	@Test
	public void testAdd() throws InterruptedException {
		this.driver.get("http://localhost:" + port + "/RecipeStore");
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 
		
		this.driver.findElement(By.id("recipeName")).sendKeys("Chicken Pie");
		this.driver.findElement(By.id("serving")).sendKeys("5");
		this.driver.findElement(By.id("howLong")).sendKeys("120");
		this.driver.findElement(By.id("mainSubmit")).click();
		Thread.sleep(1000);
		String alertMessage = this.driver.switchTo().alert().getText();
		System.out.println(alertMessage);
		assertEquals("You have added Chicken Pie to your store!", alertMessage);
		this.driver.switchTo().alert().accept();
		Thread.sleep(1000);
		assertEquals("Chicken Pie", this.driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/table/tbody/tr[1]/td[1]")).getText());
		
		this.driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/table/tbody/tr[1]/td[1]")).click();
		this.driver.findElement(By.id("editButton")).click();
		
		this.driver.findElement(By.id("editRecipeName")).clear();
		this.driver.findElement(By.id("editRecipeName")).sendKeys("Beef Pie");
		this.driver.findElement(By.id("editRecipeServing")).clear();
		this.driver.findElement(By.id("editRecipeServing")).sendKeys("25");
		this.driver.findElement(By.id("editRecipeLength")).clear();
		this.driver.findElement(By.id("editRecipeLength")).sendKeys("60");
		
		Thread.sleep(2000);
		this.driver.findElement(By.id("methodEdit")).clear();
		this.driver.findElement(By.id("methodEdit")).sendKeys("Cook it");
		
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		Thread.sleep(2000);
		this.driver.findElement(By.id("editSubmitButton")).click();
		Thread.sleep(1000);
		assertEquals("Beef Pie", this.driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/table/tbody/tr[1]/td[1]")).getText());

		this.driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/table/tbody/tr[1]/td[1]")).click();
		
		this.driver.findElement(By.id("deleteButton")).click();
		this.driver.switchTo().alert().accept();
		Thread.sleep(1000);
		this.driver.switchTo().alert().accept();
		Thread.sleep(1000);
		assertFalse(this.driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/table/tbody/tr[1]/td[1]")).isSelected());
		
	}
	


}