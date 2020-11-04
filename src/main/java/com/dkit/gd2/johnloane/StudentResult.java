package com.dkit.gd2.johnloane;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentResult
{
    private int studentNumber;
    private ArrayList<Integer> subjectCodes;
    private ArrayList<Integer> subjectMarks;
    private int[] selectedFiveGrades;
    private double fiveGradeAverage;

    protected static final int CSPE = 218;

    public StudentResult(int studentNumber, ArrayList<Integer> subjectCodes, ArrayList<Integer> subjectMarks)
    {
        this.studentNumber = studentNumber;
        this.subjectCodes = subjectCodes;
        this.subjectMarks = subjectMarks;
        this.selectedFiveGrades = new int[5];
        this.fiveGradeAverage = 0;
    }

    public int getStudentNumber()
    {
        return studentNumber;
    }

    public ArrayList<Integer> getSubjectCodes()
    {
        return subjectCodes;
    }

    public ArrayList<Integer> getSubjectMarks()
    {
        return subjectMarks;
    }

    public int[] getSelectedFiveGrades()
    {
        return selectedFiveGrades;
    }

    public double getFiveGradeAverage()
    {
        return fiveGradeAverage;
    }

    public void setSelectedFiveGrades(int[] selectedFiveGrades)
    {
        this.selectedFiveGrades = selectedFiveGrades;
    }

    public void setFiveGradeAverage(double fiveGradeAverage)
    {
        this.fiveGradeAverage = fiveGradeAverage;
    }

    @Override
    public String toString()
    {
        return "StudentResult{" +
                "studentNumber=" + studentNumber +
                ", subjectCodes=" + subjectCodes +
                ", subjectMarks=" + subjectMarks +
                ", selectedFiveGrades=" + Arrays.toString(selectedFiveGrades) +
                ", fiveGradeAverage=" + fiveGradeAverage +
                '}';
    }

    public void removeCSPE()
    {
        int index = this.subjectCodes.indexOf(CSPE);
        this.subjectCodes.remove(index);
        this.subjectMarks.remove(index);
    }
}
