/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufg.inf.oop.internshipcandidatefinder.services;

import br.ufg.inf.oop.internshipcandidatefinder.exceptions.InvalidInputFromUserException;
import br.ufg.inf.oop.internshipcandidatefinder.exceptions.NotFoundException;
import br.ufg.inf.oop.internshipcandidatefinder.models.entities.Company;
import br.ufg.inf.oop.internshipcandidatefinder.models.system.AddressSys;

import java.sql.SQLException;


public class CompanyReg {

    AddressSys addressSys;

    public CompanyReg() throws Exception {
        this.addressSys = new AddressSys();
    }

    public void insertaddress(Company company) throws IllegalArgumentException,
            InvalidInputFromUserException, SQLException {

        validateRecord(company);

        addressSys.insertAdd(company);

        Company.numberOfCreatedObjects++;
    }

    public void validateRecord(Company company) throws IllegalArgumentException,
            InvalidInputFromUserException {

        if (company == null) {
            throw new IllegalArgumentException("Attempt to enter a null address");
        }
    }

    public boolean contains(int id) throws Exception {
        try {
            fetchAddress(id);
            return true;
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public Company fetchAddress(int id) throws NotFoundException, Exception {
        Company company = addressSys.search(id);

        return company;
    }
}
