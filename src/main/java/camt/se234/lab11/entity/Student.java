package camt.se234.lab11.entity;

import java.util.Objects;

public class Student {
    String studentId;
    String name;
    String surName;
    Double gpa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!studentId.equals(student.studentId)) return false;
        if (!name.equals(student.name)) return false;
        if (!surName.equals(student.surName)) return false;
        return gpa.equals(student.gpa);
    }

    @Override
    public int hashCode() {
        int result = studentId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surName.hashCode();
        result = 31 * result + gpa.hashCode();
        return result;
    }

    public Student(String studentId, String name, String surName, Double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.surName = surName;
        this.gpa = gpa;
    }
  public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Double getGpa() {
        return gpa;
    }
    
}
