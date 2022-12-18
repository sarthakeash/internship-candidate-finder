package br.ufg.inf.oop.internshipcandidatefinder.models.entities;

import java.util.ArrayList;
import java.util.List;

public class University implements Addressable {
    public static int numberOfCreatedObjects = 0;

    private int university_id;
    private String name;
    private String initials;
    private String cnpj;
    private String telephone;
    private Company company;
    private List<Course> courses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();



    public University() {

    }

    public University(String name, String initials, String cnpj, String telephone, Company company) {
        University.numberOfCreatedObjects++;

        this.university_id = numberOfCreatedObjects;
        this.name = name;
        this.initials = initials;
        this.cnpj = cnpj;
        this.telephone = telephone;
        this.company = company;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public Company getAddress() {
        return company;
    }

    @Override
    public void setAddress(Company company) {
        this.company = company;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        if (courses.contains(course)) {
            throw new IllegalArgumentException("Attempting to add an existing course to the\n"
                    + "course list.");
        }

        courses.add(course);
    }

    @Override
    public String toString() {
        return String.format("%s - %s.\nCNPJ: %s.\nTelephone: %s.\nAddress: %s\nNumber of courses: %d",
                name, initials, cnpj, telephone, company, courses.size());
    }
    public void addStudent(Student s){
        students.add(s);
    }
}
