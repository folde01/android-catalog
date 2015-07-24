package com.example.android.rouxacademy;

public class Course {

    private int courseNumber;
    private String title;
    private String description;
    private double credits;

    public Course(int courseNumber, String title, String description, double credits) {
        this.courseNumber = courseNumber;
        this.title = title;
        this.description = description;
        this.credits = credits;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return title;
    }
}
