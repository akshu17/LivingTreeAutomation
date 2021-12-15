package com.livingtree.tests;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.livingtree.Web.ApplicationFuncs;

import controllers.InitMethod;
import listeners.CustomListener;
import utils.ConfigReader;
import utils.ExcelTestDataReader;
@Listeners(CustomListener.class)
public class LQ5334 extends ApplicationFuncs
{	

	@Test(testName="LA-14588",dataProvider="getExcelTestData",description =" When you click \"save and add another\" in calendar it takes you back to the main page.")
	public void test_LQ5334(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
		    doLogin(rowMap.get("UserName"),rowMap.get("Password"));
		    eventPost(rowMap.get("eventType"),rowMap.get("Location"),rowMap.get("Descrpt"));
		    clickOnHomePage();
		    pause(1000);
		   eventPost(rowMap.get("eventType"),rowMap.get("Location"),rowMap.get("Descrpt"));
			logout();
		} catch (Exception e) {
			InitMethod.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@DataProvider
	public Iterator<Object[]> getExcelTestData() 
	{
		String sheetname = this.getClass().getSimpleName();
		ExcelTestDataReader excelReader = new ExcelTestDataReader();
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),sheetname.split("_")[0]);
		return dataBeans.iterator();
	}
	
}
