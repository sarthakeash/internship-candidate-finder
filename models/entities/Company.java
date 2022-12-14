package br.ufg.inf.oop.internshipcandidatefinder.models.entities;

import java.util.ArrayList;

public class Company {

    public static int numberOfCreatedObjects = 0;

    private int company_id;
    private String poc_name;
    private String address;
    private String field;
    private String companyName;
private ArrayList<String> jobAddress;
    public Company() {

    }

    public Company(String poc_name, String address, String field, String companyName
                  ) {

        Company.numberOfCreatedObjects++;

        this.company_id = numberOfCreatedObjects;
        this.poc_name = poc_name;
        this.address = address;
        this.field = field;
        this.companyName = companyName;
        
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getPoc_name() {
        return poc_name;
    }

    public void setPoc_name(String poc_name) {
        this.poc_name = poc_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

  
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Company company = (Company) o;

        return poc_name.equals(company.poc_name);
    }

    @Override
    public int hashCode() {
        return poc_name.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s. %s. %s. %s.", address, field, companyName, poc_name);
    }
}
