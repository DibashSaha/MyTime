package com.mytime.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {
	
	WebDriver driver;
	
	public SearchResultsPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='search-result-content']/h2")
	List<WebElement> serachResults;

	@FindBy(xpath="search-query")
	WebElement businessService;

	
	public boolean verifyMultipleSearchResult(){
		boolean flag=false;
		int count=serachResults.size();
		if(count>3){
			flag=true;
		}
		return flag;
	}
	
	public void clickBusinessName(String name){
		driver.findElement(By.linkText(name)).click();
	}
	


	
}
