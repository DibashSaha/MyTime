package com.mytime.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilterPage {
	
	WebDriver driver;
	
	public FilterPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[contains(@aria-label,'Book Service Men')]")
	WebElement mensBookButton;

	@FindBy(xpath="//button[contains(text(),'view more')]")
	WebElement viewmore;
	
	@FindBy(xpath="//h4[text()='Haircut']/..//span[@class='variation-title-content']")
	WebElement mensService;
	
	@FindBy(xpath="(//fieldset[@class='filter-form-selection']/label[3]/span)[2]")
	WebElement secondstaff;
	
	@FindBy(xpath="//h4[text()='Haircut']/..//span[@class='normal-price']")
	WebElement price;
	
	@FindBy(xpath="(//button[text()='Select Time'])[3]")
	WebElement selectTime;
	
	
	

	public void clickFilterLink(String val){
		driver.findElement(By.xpath("//span[text()='"+val+"']")).click();
	}
	

	public void clickSecondStaff(){
		secondstaff.click();
	}
	
	public String getSecondStaffName(){
		return secondstaff.getText();
	}
	
	public String getPrice(){
		return price.getText();
	}
	
	
	
	public void clickMensBookButton(){
		mensBookButton.click();
	}
	
	public void clickSelectTime(){
		try{Thread.sleep(5000);}catch(Exception e){}
		selectTime.click();
	}
	
	
	public String getServiceName(){
		return mensService.getText();
	}
}
