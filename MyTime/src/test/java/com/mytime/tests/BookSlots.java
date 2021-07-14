package com.mytime.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.mytime.utils.BaseTest;

public class BookSlots extends BaseTest {
	
	@Test(priority=1)
	public void verifyAtleastThreeSearchResults(){
		
		test = extent.createTest("verifyAtleastThreeSearchResults");
		
		homePageObj.clickSearchFilter();
		
		homePageObj.enterSearchDetails(serviceName, location);
		
		commonPageObj.waitForPageToload();
		
		//verify the Multiple Search Result, It shoud be more than three
		boolean actualsearchResult=serachPageObj.verifyMultipleSearchResult();
		Assert.assertEquals(actualsearchResult, true);
		test.log(Status.PASS, "SearchResults passed");
		
	}

	@Test(priority=2)
	public void verifilterResults(){
		
		test = extent.createTest("verifyFilterResults");
		
		serachPageObj.clickBusinessName(businessName);
		
		commonPageObj.waitForPageToload();
		filterPageObj.clickFilterLink("All services");
		filterPageObj.clickSecondStaff();
		
		String actualServiceName=filterPageObj.getServiceName();
				
		String actualStaffName=filterPageObj.getSecondStaffName();
		
		String actualPrice=filterPageObj.getPrice();
		
		filterPageObj.clickMensBookButton();
		filterPageObj.clickSelectTime();
		
		commonPageObj.waitForPageToload();
		
		//verify the list of slot available, it should be atleast two
		boolean actualTimeSlotDisplayed=appointmentDetailsPageObj.verifyAtleastTwoTimeSlot();
		Assert.assertEquals(actualTimeSlotDisplayed, true);
		test.log(Status.PASS, "verify time slot passed");
		
		String expectedStaffName=appointmentDetailsPageObj.getStaffName();
		
		String expectedPrice=appointmentDetailsPageObj.getPrice();
		
		String expectedServiceName=appointmentDetailsPageObj.getName();
		
		//verify the service name
		Assert.assertEquals(actualServiceName, expectedServiceName);
		test.log(Status.PASS, "ServiceName passed");
		
		//verify the service price
		Assert.assertEquals(actualPrice, expectedPrice);
		test.log(Status.PASS, "Service Price passed");
		
		//verify the staff name
		Assert.assertEquals(actualStaffName, expectedStaffName);
		test.log(Status.PASS, "Service Staff Name passed");
	
		
	}
	
}
