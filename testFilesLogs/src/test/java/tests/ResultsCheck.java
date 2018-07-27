package tests;/*
1. Создать новый проект.
2. Подключить зависимости JUnit, (Maven or Gradle)
3. Написать тест проверяющий следующие условия для каждой строки из файла - Object_task_up_input.log
a=b=c rez 4
a+b+c > 1000 rez 5
a+b+c < 1000 rez 6

Значения для проверки в файле Object_task_up_results.log
Каждую строку из файла Object_task_up_input.log представить как обьект с тремя полями.

*/

import data.ABCData;
import filemanager.FileManager;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class ResultsCheck {

    private FileManager fm = new FileManager();
    private static ArrayList<ABCData> inputData;
    private static ArrayList<Integer> outputData;

    @BeforeSuite
    public void setUp() throws IOException {
        fm.setPath();
        inputData = fm.loadInputs(fm.getPath());
        outputData = fm.loadOutputs(fm.getPath());
    }

    @Test
    public void AssertionTest() {

        Assert.assertEquals(inputData.size(),outputData.size(),"Size of Input data and Output Data differs!");

        for (int i=0;i<inputData.size();i++)
        {
            int actualResult = inputData.get(i).result();
            int expectedResult = outputData.get(i);
            Assert.assertEquals(actualResult,expectedResult,String.format("File string %s: For %s ",i+2,inputData.get(i).toString()));
        }

    }
}


