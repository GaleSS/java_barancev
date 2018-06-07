package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void distance01_ZeroSelfTest()
    {
        Point p = new Point(0,0);
        Assert.assertEquals(p.distance(p),0.0);
    }

    @Test
    public void distance02_fromZeroToOne()
    {
        Point p00 = new Point(0,0);
        Point p01 = new Point(0,1);
        Point p10 = new Point(1,0);
        Point p_10 = new Point(-1,0);
        Point p0_1 = new Point(0,-1);
        Assert.assertEquals(p00.distance(p01),1.0);
        Assert.assertEquals(p00.distance(p10),1.0);
        Assert.assertEquals(p00.distance(p0_1),1.0);
        Assert.assertEquals(p00.distance(p_10),1.0);
        Assert.assertEquals(p_10.distance(p00),1.0);
        Assert.assertEquals(p0_1.distance(p00),1.0);
    }

    @Test
    public void distance03_fromMinusOne()
    {
        Point p_1_1 = new Point(-1,-1);
        Point p00 = new Point(0,0);
        Point p01 = new Point(0,1);
        Point p10 = new Point(1,0);
        Point p_10 = new Point(-1,0);
        Point p0_1 = new Point(0,-1);
        Point p11 = new Point(1,1);
        Assert.assertEquals(p_1_1.distance(p00),1.0);
        Assert.assertEquals(p_1_1.distance(p10),Math.sqrt(3));
        Assert.assertEquals(p_1_1.distance(p0_1),1.0);
        Assert.assertEquals(p_1_1.distance(p_10),1.0);
        Assert.assertEquals(p_1_1.distance(p01),Math.sqrt(3));
        Assert.assertEquals(p1_1.distance(p11),2.0);
    }
}
