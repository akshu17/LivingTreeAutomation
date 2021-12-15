package com.livingtree.tests;

import com.livingtree.Web.ApplicationFuncs;
import controllers.InitMethod;
import listeners.CustomListener;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.ExcelTestDataReader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

@Listeners(CustomListener.class)
public class LQ4094 extends ApplicationFuncs {

    @Test(testName = "LA_11993", dataProvider = "getExcelTestData", description = "Verify ownership transfer")
    public void test_LQ4094(HashMap<String, String> rowMap) {
        try {
            openLoginPage();
            doLogin(rowMap.get("Teacher"), rowMap.get("Password"));
            transfrownershipnew(rowMap.get("Invite"));
            transfrownershipnew(rowMap.get("Invite1"));
            logout();
        } catch (Exception e) {
            InitMethod.ErrorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @DataProvider
    public Iterator<Object[]> getExcelTestData() {
        String sheetname = this.getClass().getSimpleName();
        ExcelTestDataReader excelReader = new ExcelTestDataReader();
        LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR + ConfigReader.getValue("TestData"), sheetname);
        return dataBeans.iterator();
    }
}


			


