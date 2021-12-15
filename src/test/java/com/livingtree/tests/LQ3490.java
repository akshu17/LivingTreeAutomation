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
public class LQ3490 extends ApplicationFuncs
{	

	@Test(testName="LA_12894",dataProvider="getExcelTestData",description ="check all option in parent account after publish the event")
	public void test_LQ3490(HashMap<String, String> rowMap){
		try {
			openLoginPage();
			
			doLogin(rowMap.get("TeacherName"),rowMap.get("Password"));
			String subject=getRandomString1(rowMap.get("eventType"));
			eventPost_disable_comment(subject,rowMap.get("location"),rowMap.get("Descrpt"),false);
			logout();
			doLogin(rowMap.get("ParentName1"),rowMap.get("Password"));
			verifycalenderall_translation(rowMap.get("Translate Subject"));
			//changePassword(rowMap.get("NewPassword"),rowMap.get("OldPassword"));//change to old one password
			//gmailLogin(rowMap.get("Subject"));//having to check in gmail so check it later
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