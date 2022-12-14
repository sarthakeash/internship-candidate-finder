package br.ufg.inf.oop.internshipcandidatefinder.models.entities;

import java.util.Date;

public class Student implements Addressable {

    public static int numberOfCreatedObjects = 0;
    private int id;
    private String codigoDeMatricula;
    private String name;
    private Date dataDeNascimento;
    private String email;
    private String telephone;
    private Date dataDeMatricula;
    private Company company;

    public Student() {

    }

    public Student(String codigoDeMatricula, String name, Date dataDeNascimento, String email,
            String telephone, Date dataDeMatricula, Company companyDeMatricula) {

        Student.numberOfCreatedObjects++;

        this.id = numberOfCreatedObjects;
        this.codigoDeMatricula = codigoDeMatricula;
        this.name = name;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.telephone = telephone;
        this.dataDeMatricula = dataDeMatricula;
        this.company = companyDeMatricula;
    }

    public String getCodigoDeMatricula() {
        return codigoDeMatricula;
    }

    public void setCodigoDeMatricula(String codigoDeMatricula) {
        this.codigoDeMatricula = codigoDeMatricula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
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

    public Date getDataDeMatricula() {
        return dataDeMatricula;
    }

    public void setDataDeMatricula(Date dataDeMatricula) {
        this.dataDeMatricula = dataDeMatricula;
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
                + "codigoDeMatricula='" + codigoDeMatricula + '\''
                + ", name='" + name + '\''
                + ", dataDeNascimento=" + dataDeNascimento
                + ", email='" + email + '\''
                + ", telephone='" + telephone + '\''
                + ", dataDeMatricula=" + dataDeMatricula
                + ", address=" + company
                + '}';
    }
}
