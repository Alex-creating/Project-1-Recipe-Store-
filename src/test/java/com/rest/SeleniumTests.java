package com.rest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
	private int port = 8080;
	private WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}
	
	@Test
	public void testAdd() throws InterruptedException {
		this.driver.get("localhost:" + port);
		
		this.driver.findElement(By.id("recipeName")).sendKeys("Chicken Pie");
		this.driver.findElement(By.id("serving")).sendKeys("5");
		this.driver.findElement(By.id("howLong")).sendKeys("120");
		this.driver.findElement(By.id("mainSubmit")).click();
		assertEquals("Chicken Pie", this.driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/table/tbody/tr[1]/td[1]")).getText());   
	}

}
