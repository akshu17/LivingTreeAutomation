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
public class LQ1935 extends ApplicationFuncs
{	
	@Test(testName="LQ1935",dataProvider="getExcelTestData",description ="Co-admin of school should be able to message Students" )
	public void test_LQ1935(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Co-admin"),rowMap.get("Password"));
			String subject= getRandomString(rowMap.get("Subject"));
			sentMessagetoPrinciple(rowMap.get("student"),subject, rowMap.get("Message"),rowMap.get("FilePath"));
			logout();
			doLogin(rowMap.get("studentMail"),rowMap.get("Password"));
			replyMessagetouser(subject,rowMap.get("Co-adminName"));
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






