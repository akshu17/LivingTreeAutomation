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
public class LA14846 extends ApplicationFuncs
{	

	@Test(testName="LA14846",dataProvider="getExcelTestData",description = "Student not able to update date of birth")
	public void test_LA14846(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
		    doLogin(rowMap.get("UserName"),rowMap.get("Password"));
		    StudentUpdateBirth(rowMap.get("Birthday1"));
			logout();
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			StudentUpdateBirth(rowMap.get("Birthday2"));
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
