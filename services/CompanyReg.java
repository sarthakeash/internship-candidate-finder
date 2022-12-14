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

    public void inseriraddress(Company company) throws IllegalArgumentException,
            InvalidInputFromUserException, SQLException {

        validarInsercaoaddress(company);

        addressSys.insertAdd(company);

        Company.numberOfCreatedObjects++;
    }

    public void validarInsercaoaddress(Company company) throws IllegalArgumentException,
            InvalidInputFromUserException {

        if (company == null) {
            throw new IllegalArgumentException("Tentativa de inserir um address nulo.");
        }
    }

    public boolean contains(int id) throws Exception {
        try {
            buscaraddressPorId(id);
            return true;
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public Company buscaraddressPorId(int id) throws NotFoundException, Exception {
        Company company = addressSys.search(id);

        return company;
    }
}
