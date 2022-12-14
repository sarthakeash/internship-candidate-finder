package br.ufg.inf.oop.internshipcandidatefinder.models.entities;

import java.util.List;

public class Course implements Addressable {

    public static int numberOfCreatedObjects = 0;

    private int id;
    private String name;
    private String initials;
    private int numberOfVacancies;
    private short year;
    private Company company;
    private List<Student> students;

    public Course() {

    }

    public Course(String name, String initials, String grade, int numberOfVacancies,
                  short year, Company company) {

        Course.numberOfCreatedObjects++;

        this.id = numberOfCreatedObjects;
        this.name = name;
        this.initials = initials;
        this.numberOfVacancies = numberOfVacancies;
        this.year = year;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getNumberOfVacancies() {
        return numberOfVacancies;
    }

    public void setNumberOfVacancies(int numberOfVacancies) {
        this.numberOfVacancies = numberOfVacancies;
    }


    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }


    public Company getAddress() {
        return company;
    }

    public void setAddress(Company company) {
        this.company = company;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {

        students.add(student);
    }

    @Override
    public String toString() {
        return "Course{"
                + "name='" + name + '\''
                + ", initials='" + initials + '\''
                + ", numberOfVacancies=" + numberOfVacancies
                + ", year=" + year
                + ", address=" + company
                + ", students=" + students
                + '}';
    }


    public int getId() {
        return id;
    }
}
