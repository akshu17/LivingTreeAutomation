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
public class LQ4201 extends ApplicationFuncs
{	

	@Test(testName="LA_11431",dataProvider="getExcelTestData",description ="Verify that the invite any one in a group using groupcode")
	public void test_LQ4201(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			login(rowMap.get("UserName"),rowMap.get("Password"));
			//deleteAllPosts();
			inviteInGroup(rowMap.get("GroupCode"),rowMap.get("ChildFirstName"),rowMap.get("ChildLastName"));
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
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),sheetname);
		return dataBeans.iterator();
	}
}