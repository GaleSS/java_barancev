package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareEquationTest {

    @Test
    public void test0(){
        SquareEquation equation = new SquareEquation(1,0,1);
        Assert.assertEquals(equation.quantityDesicions(),0);
    }

    @Test
    public void test1(){
        SquareEquation equation = new SquareEquation(1,2,1);
        Assert.assertEquals(equation.quantityDesicions(),1);
    }

    @Test
    public void test2(){
        SquareEquation equation = new SquareEquation(1,5,1);
        Assert.assertEquals(equation.quantityDesicions(),2);
    }
}
