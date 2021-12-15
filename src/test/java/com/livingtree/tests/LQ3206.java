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
public class LQ3206 extends ApplicationFuncs
{	

	@Test(testName="LA_11993",dataProvider="getExcelTestData",description ="If parent is having two children ,remove one child and post a message to parents from district head")
	public void test_LQ3206(HashMap<String, String> rowMap)
	{
		try {			
			openLoginPage_Edlio();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postMessage(subject ,rowMap.get("Message"),rowMap.get("GroupName") );
			delete_parent_from_group(rowMap.get("GroupName"));
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			searchUserinDirectory(rowMap.get("ParentName"));
			isnavigationtoMainPage_edlio();
			logout();
			doLogin(rowMap.get("Parent"),rowMap.get("Password"));
			//serachfamily(rowMap.get("Family"));
			//deletegorup(rowMap.get("GroupName"));
			//isnavigationtoMainPage_edlio();
			isPostDisplay(subject);
			searchClassinDirectory(rowMap.get("GroupName"));
			isnavigationtoMainPage_edlio();
			logout();
			/*doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			commentSearchPostnotavailablpost(subject);		
			logout();*/
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//verifyPostFESScore(subject, rowMap.get("CountFES2"));
			//check for now
			delete1stPost(subject);
			inviteNotificationnew(rowMap.get("Parent"),rowMap.get("GroupName"));
			logout();
			login(rowMap.get("Parent"),rowMap.get("Password"));
			acceptinvitation(rowMap.get("ChildFirstName"),rowMap.get("ChildLastName"),rowMap.get("groupname"));
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
