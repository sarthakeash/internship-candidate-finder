package br.ufg.inf.oop.internshipcandidatefinder.models.entities;

import java.util.Date;

public class Student implements Addressable {

    public static int numberOfCreatedObjects = 0;
    private int id;
    private String regCode;
    private String name;
    private Date dob;
    private String email;
    private String telephone;
    private Date enrollmentDate;
    private Company company;

    public Student() {

    }

    public Student(String regCode, String name, Date dob, String email,
                   String telephone, Date enrollmentDate, Company company) {

        Student.numberOfCreatedObjects++;

        this.id = numberOfCreatedObjects;
        this.regCode = regCode;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.telephone = telephone;
        this.enrollmentDate = enrollmentDate;
        this.company = company;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public Company getAddress() {
        return company;
    }

    @Override
    public void setAddress(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "student{"
                + "regCode='" + regCode + '\''
                + ", name='" + name + '\''
                + ", dob=" + dob
                + ", email='" + email + '\''
                + ", telephone='" + telephone + '\''
                + ", enrollmentDate=" + enrollmentDate
                + ", address=" + company
                + '}';
    }
}
