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
public class LQ5410 extends ApplicationFuncs
{

    @Test(testName="LA-14844",dataProvider="getExcelTestData",description = "LA-14844: Manually added Admins have Read Only Relationships.")
    public void test_LQ5410(HashMap<String, String> rowMap) {
        try {
            openLoginPage();
            doLogin(rowMap.get("UserName"),rowMap.get("Password"));
            isInvitedAdminAbleSelectGroup();
            logout();
            openLoginPage();
            login(rowMap.get("AdminUserName"),rowMap.get("Password"));
            pause(3000);
            AdminAcceptInvitation();
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
