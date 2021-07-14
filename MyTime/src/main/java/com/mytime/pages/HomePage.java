package com.mytime.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="show_search_filters")
	WebElement searchFilter;

	@FindBy(id="search-query")
	WebElement businessService;

	@FindBy(id="search-location")
	WebElement searchLocation;

	
	@FindBy(xpath="//button[text()='Search']")
	WebElement searchBtn;

	
	public void clickSearchFilter(){
		searchFilter.click();
	}
	
	public void enterSearchDetails(String businessName,String loc ){
		
		businessService.clear();
		businessService.sendKeys(businessName);
		
		searchLocation.clear();
		searchLocation.sendKeys(loc);
		
		searchBtn.click();
	}

	
}
