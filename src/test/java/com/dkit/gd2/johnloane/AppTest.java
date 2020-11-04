package com.dkit.gd2.johnloane;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void selectFiveGradesAllNormal()
    {
        App testApp = new App();
        int[] subjects = {1,2,3,4,5,6,7,8};
        int[] grades = {10,20,30,40,50,60,70,80};
        int[] result = testApp.selectFiveGrades(subjects, grades);
        int[] expectedResult = {10,20,30, 70, 80};
        Assert.assertArrayEquals(expectedResult, result);
    }

    @Test
    public void selectFiveGradesCheckSort()
    {
        App testApp = new App();
        int[] subjects = {1,2,3,4,5,6,7,8};
        int[] grades = {80,70,60,50,40,30,20,10};
        int[] result = testApp.selectFiveGrades(subjects, grades);
        int[] expectedResult = {80,70,60, 40, 50};
        Assert.assertArrayEquals(expectedResult, result);
    }

    @Test
    public void selectFiveGradesRepeatedMark()
    {
        App testApp = new App();
        int[] subjects = {1,2,3,4,5,6,7,8};
        int[] grades = {80,70,60,100,100,100,100,100};
        int[] result = testApp.selectFiveGrades(subjects, grades);
        int[] expectedResult = {80,70,60, 100, 100};
        Assert.assertArrayEquals(expectedResult, result);
    }

    @Test
    public void calculateAverage()
    {
        App testApp = new App();
        int[] testValues = {80,70,60, 100, 100};
        double result = testApp.calculateAverage(testValues);
        Assert.assertEquals(82, result, 0.000001);
    }
}
