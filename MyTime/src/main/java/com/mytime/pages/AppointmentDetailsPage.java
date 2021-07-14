package com.mytime.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppointmentDetailsPage {

	WebDriver driver;
	
	public AppointmentDetailsPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//span[contains(@class,'variation-name')]")
	WebElement name;
	
	@FindBy(xpath="//span[text()=\"Men's Haircut\"]/../..//span[@class='normal-price']")
	WebElement price;
	
	@FindBy(xpath="//span[@class='Select-value-label']")
	WebElement staff;
	
	@FindBy(xpath="//div[@class='opentime-item responsive-padding']")
	List<WebElement> timeslot;
	
	public boolean verifyAtleastTwoTimeSlot(){
		boolean flag=false;
		int count=timeslot.size();
		if(count>2){
			flag=true;
		}
		return flag;
	}
	
	public String getPrice(){
		return price.getText();
	}
	
	public String getName(){
		return name.getText();
	}
	
	public String getStaffName(){
		return staff.getText();
	}
	
	
	
}
