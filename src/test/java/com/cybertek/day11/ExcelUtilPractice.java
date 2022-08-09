package com.cybertek.day11;

import com.cybertek.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ExcelUtilPractice {

    @Test
    public void test1(){

        // how to use excelUtil file?
        // it accepts two arguments
        // argument1 :location of the dile(path)
        // argument2 : sheet that we want to open
        ExcelUtil vytrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx","QA3-short");

        // method for returning list of Map
        List<Map<String,String>> dataList = vytrackFile.getDataList();
        for (Map<String, String> rowMap : dataList) {
            System.out.println(rowMap);
        }

    }

}