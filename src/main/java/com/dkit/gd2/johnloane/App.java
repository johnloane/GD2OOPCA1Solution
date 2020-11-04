package com.dkit.gd2.johnloane;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App
{
    private static final int IRISH = 1;
    private static final int ENGLISH = 2;
    private static final int MATHS = 3;

    private static ArrayList<StudentResult> students = new ArrayList<>();

    public static void main( String[] args )
    {
        App jcResultsApp = new App();
        jcResultsApp.start();
    }

    private void start()
    {
        readStudentsFromFile(students);
        removeCSPEGrades(students);
        populateAllStudentsFiveGrades(students);
        calculateAverageForAllStudents(students);
        displayStudentsAverage(students);
    }

    private void readStudentsFromFile(ArrayList<StudentResult> students)
    {
        //Use try with resources and BufferedReader
        try(Scanner studentFile = new Scanner(new BufferedReader(new FileReader("JC_Results.txt"))))
        {
            while(studentFile.hasNextLine())
            {
                String input = studentFile.nextLine();
                String[] data = input.split(",");
                int studentNumber = Integer.parseInt(data[0]);
                ArrayList<Integer> subjectCodes = readStudentSubjectCodes(data);
                ArrayList<Integer> subjectMarks = readStudentSubjectMarks(data);
                StudentResult tempStudentResult = new StudentResult(studentNumber, subjectCodes, subjectMarks);
                students.add(tempStudentResult);
            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println(fne.getMessage());
        }
    }

    private ArrayList<Integer> readStudentSubjectCodes(String[] data)
    {
        ArrayList<Integer> codes = new ArrayList<>();
        //Codes are at 1,3,5,7.....
        for(int i = 1; i < data.length; i += 2)
        {
            codes.add(Integer.parseInt(data[i]));
        }
        return codes;
    }

    private ArrayList<Integer> readStudentSubjectMarks(String[] data)
    {
        ArrayList<Integer> marks = new ArrayList<>();
        //Marks are at 2,4,6,8.....
        for(int i = 2; i < data.length; i += 2)
        {
            marks.add(Integer.parseInt(data[i]));
        }
        return marks;
    }

    private void removeCSPEGrades(ArrayList<StudentResult> students)
    {
        for(StudentResult student : students)
        {
            if(student.getSubjectCodes().indexOf(StudentResult.CSPE) != -1)
            {
                student.removeCSPE();
            }
        }
    }

    private void populateAllStudentsFiveGrades(ArrayList<StudentResult> students)
    {
        for(StudentResult student : students)
        {
            //selectFiveGrades uses an int[] so need to convert, need JDK>=8
            int[] codes = student.getSubjectCodes().stream().mapToInt(i->i).toArray();
            int[] marks = student.getSubjectMarks().stream().mapToInt(i->i).toArray();
            student.setSelectedFiveGrades(selectFiveGrades(codes, marks));
        }
    }

    public int[] selectFiveGrades(int[] codes, int[] marks)
    {
        int[] selectedFiveGrades = new int[5];
        selectIrishEnglishMaths(codes, marks, selectedFiveGrades);
        selectNextTwoHighest(codes, marks, selectedFiveGrades);
        return selectedFiveGrades;
    }

    private void selectIrishEnglishMaths(int[] codes, int[] marks, int[] selectedFiveGrades)
    {
        int j = 0;
        for(int i=0; i < codes.length; i++)
        {
            if(codes[i] == IRISH || codes[i] == ENGLISH || codes[i] == MATHS)
            {
                selectedFiveGrades[j] = marks[i];
                j++;
            }
        }
    }

    private void selectNextTwoHighest(int[] codes, int[] marks, int[] selectedFiveGrades)
    {
        ArrayList<Integer> codesToSortExIEM = new ArrayList<>();
        ArrayList<Integer> marksToSortExIEM = new ArrayList<>();
        int j = 3;
        for(int i=0; i < codes.length;i++)
        {
            if(codes[i] != IRISH && codes[i] != ENGLISH && codes[i] != MATHS)
            {
                codesToSortExIEM.add(codes[i]);
                marksToSortExIEM.add(marks[i]);
            }
        }
        Collections.sort(marksToSortExIEM);
        selectedFiveGrades[j] = marksToSortExIEM.get(marksToSortExIEM.size()-2);
        selectedFiveGrades[j+1] = marksToSortExIEM.get(marksToSortExIEM.size()-1);
    }

    private void calculateAverageForAllStudents(ArrayList<StudentResult> students)
    {
        for(StudentResult student : students)
        {
            student.setFiveGradeAverage(calculateAverage(student.getSelectedFiveGrades()));
        }
    }

    protected double calculateAverage(int[] selectedFiveGrades)
    {
        int total = 0;
        double average = 0;
        for(int i=0; i < selectedFiveGrades.length; i++)
        {
            total += selectedFiveGrades[i];
        }
        try
        {
            average = (double) total / selectedFiveGrades.length;
        }
        catch(ArithmeticException ae)
        {
            System.out.println(ae.getMessage());
        }
        return average;
    }

    private void displayStudentsAverage(ArrayList<StudentResult> students)
    {
        for(StudentResult student : students)
        {
            System.out.println(student.getStudentNumber() + ": " + student.getFiveGradeAverage());
        }
    }
}
