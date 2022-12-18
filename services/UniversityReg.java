package br.ufg.inf.oop.internshipcandidatefinder.services;

import br.ufg.inf.oop.internshipcandidatefinder.exceptions.InvalidInputFromUserException;
import br.ufg.inf.oop.internshipcandidatefinder.exceptions.NotFoundException;
import br.ufg.inf.oop.internshipcandidatefinder.models.system.AddressSys;
import br.ufg.inf.oop.internshipcandidatefinder.models.system.UniversitySystem;
import br.ufg.inf.oop.internshipcandidatefinder.models.entities.Company;
import br.ufg.inf.oop.internshipcandidatefinder.models.entities.University;
import br.ufg.inf.oop.internshipcandidatefinder.views.MainView;
import java.security.InvalidParameterException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniversityReg {

    private static UniversityReg instance;

    UniversitySystem universitySystem;
    AddressSys addressSys;

    private UniversityReg() throws SQLException {
        this.universitySystem = new UniversitySystem();
        this.addressSys = new AddressSys();
    }

    public static synchronized UniversityReg getInstance() throws Exception {
        if (instance == null) {
            instance = new UniversityReg();
        }
        return instance;
    }


    public void insertUniversity(University university, Company company) throws InvalidParameterException,
            InvalidInputFromUserException, Exception {

        validateInsertedUniversity(university);

        addressSys.insertAdd(company);

        universitySystem.insertAdd(university);

    }

    public void validateInsertedUniversity(University university) throws InvalidParameterException,
            InvalidInputFromUserException, IllegalArgumentException, Exception {

        if (nullUniv(university)) {
            throw new InvalidParameterException("Attempt to enter a null University.");
        }

        if (university.getUniversity_id() <= 0) {
            throw new InvalidInputFromUserException("\n" +
                    "The University id must be greater than 0.");
        }

        if (university.getName() == null || university.getName().isBlank()) {
            throw new InvalidInputFromUserException("\n" +
                    "The University name is invalid");
        }

        if (university.getInitials() == null || university.getInitials().isBlank()) {
            throw new InvalidInputFromUserException("University initials are invalid.");
        }

        if (university.getCnpj() == null || university.getCnpj().isBlank()) {
            throw new InvalidInputFromUserException("The University's cnpj is invalid.");
        }

        if (university.getTelephone() == null || university.getTelephone().isBlank()) {
            throw new InvalidInputFromUserException("The University phone number is invalid.");
        }

        if (university.getAddress() == null) {
            throw new InvalidInputFromUserException("The University address is invalid.");
        }

        if (university.getAddress() == null) {
            throw new InvalidInputFromUserException("The UF initials entered is invalid.");
        }

        try {
            getUnivById(university.getUniversity_id());

        } catch (NotFoundException ex) {
        }

        if (contains(university.getUniversity_id())) {
            MainView.reportInternalError(null, new IllegalArgumentException("Já existe uma University com o id "
                    + university.getUniversity_id()));
        }
    }

    public boolean contains(int id) throws Exception {
        try {
            getUnivById(id);
            return true;
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public University getUnivById(int id) throws NotFoundException, Exception {
        University university = universitySystem.search(id);

        return university;
    }

    public List<University> searchUnivByName(String name) throws Exception, NotFoundException {
        List<University> universitySearch = new ArrayList<>();

        if (name.isBlank()) {
            return searchAllUniversitys();
        }

        universitySearch = universitySystem.searchbyName(name);

        if (universitySearch.isEmpty()) {
            throw new NotFoundException("No University found.");
        }

        return universitySearch;
    }

    public List<University> searchAllUniversitys() throws NotFoundException, Exception {
        List<University> allUniversities;

        allUniversities = universitySystem.searchAll();

        if (allUniversities.isEmpty()) {
            throw new NotFoundException("There is no University registered.");
        }

        return allUniversities;
    }

    public void updateUniversity(University university) throws IllegalArgumentException, Exception {
        if (!contains(university.getUniversity_id())) {
            throw new IllegalArgumentException("There is no University with the id"
                    + university.getUniversity_id());
        }

        addressSys.update(university.getAddress());
        universitySystem.update(university);
    }

    public void removeUnivById(int id) throws IllegalArgumentException, Exception {
        try {
            University universityRemoved = getUnivById(id);
            Company companyRemoved = addressSys.search(universityRemoved.getUniversity_id());

            universitySystem.remover(id);
            addressSys.remover(companyRemoved.getCompany_id());

        } catch (Exception ex) {
            throw ex;
        }

    }

    public boolean nullUniv(University university) {
        return university == null;
    }
}
