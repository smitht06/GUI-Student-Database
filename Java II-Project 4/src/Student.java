/*
 * File:    Student.java
 * Author:  Anthony Smith
 * Date:    15 December 2018
 * Purpose: template for a student containing name, major. also
 * calculates quality points for a class based on the number of credits it is
 */
public class Student {
    private String name;
    private double qualityPoints;
    private String major;
    private int credits;
    private boolean completedCourse = false;

    //constructor
    public Student(String name, String major) {
        this.name = name;
        qualityPoints = 0;
        this.major = major;
        credits = 0;
    }

    //method to show course completed and to calulate grade points based on grad
    public void courseCompleted(String grade, int courseCredits) {
        int gradeInCourse = 0;

        if ("A".equals(grade)) {
            gradeInCourse = 4;
        } else if ("B".equals(grade)) {
            gradeInCourse = 3;
        } else if ("C".equals(grade)) {
            gradeInCourse = 2;
        } else if ("D".equals(grade)) {
            gradeInCourse = 1;
        }
        credits+=courseCredits;
        qualityPoints += (gradeInCourse * courseCredits);
        completedCourse = true;
    }
    //to string to print student information
    @Override
    public String toString() {
        if (!completedCourse) {
            return "\n" + "Name: " + name + "\n" + "Major: " + major +
                    "\n" + "GPA: N/A";
        }
        double gpa = qualityPoints/credits;
        return "\n" + "Name: " + name + "\n" + "Major: " + major + "\n" +
                "GPA: " + gpa;
    }
}


