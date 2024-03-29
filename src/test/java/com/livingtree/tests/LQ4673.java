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
public class LQ4673 extends ApplicationFuncs
{	

	@Test(testName="LQ4673",dataProvider="getExcelTestData",description ="Web:While creating a new layer in district/school, add a layer functionality option not available.")
	public void test_LQ4673(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Distric_admin"),rowMap.get("Password"));
			//String SchoolName,String UserName,String new_user_value_n,String button_name_value,String filepath,String new_user_value_y
			navigateToAddlayer_new(rowMap.get("SchoolName"),rowMap.get("UserName"),rowMap.get("new_user_value_n"),rowMap.get("button_name_value_other"),rowMap.get("button_name_value_teacher"),USERDIR+rowMap.get("filepath"),rowMap.get("new_user_value_y"));
			pause(3000);
			logout();
			if(rowMap.get("env").equalsIgnoreCase("maple"))
			{
			doLogin(rowMap.get("Username_email"),rowMap.get("Password"));
			checkleftbarschool(rowMap.get("SchoolName"));
			logout();
			}
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