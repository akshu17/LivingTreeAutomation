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
public class LQ5398 extends ApplicationFuncs
{

    @Test(testName="LA-14823",dataProvider="getExcelTestData",description = "Potential issue with students being able to message students")
    public void test_LQ5398(HashMap<String, String> rowMap) {
        try {
            openLoginPage();
            doLogin(rowMap.get("StudentUserName"),rowMap.get("Password"));
            isStudentAbleToSendMessageToStudent(rowMap.get("TeacherName"),rowMap.get("StudentName"));
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
