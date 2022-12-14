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

    /*public UniversityService() throws Exception {
        this.universitySystem = new UniversityDAO();
        this.addressDAO = new addressDAO();
    }*/
    public void insertUniversity(University university, Company company) throws InvalidParameterException,
            InvalidInputFromUserException, Exception {

        validarInsercaoUniversity(university);

        addressSys.insertAdd(company);

        universitySystem.insertAdd(university);

        //address.numberOfCreatedObjects++;
        //University.numberOfCreatedObjects++;
    }

    public void validarInsercaoUniversity(University university) throws InvalidParameterException,
            InvalidInputFromUserException, IllegalArgumentException, Exception {

        if (universityENula(university)) {
            throw new InvalidParameterException("Tentativa de inserir uma University nula.");
        }

        if (university.getUniversity_id() <= 0) {
            throw new InvalidInputFromUserException("O id da University deve ser maior do que 0.");
        }

        if (university.getName() == null || university.getName().isBlank()) {
            throw new InvalidInputFromUserException("O name da University é invalido.");
        }

        if (university.getInitials() == null || university.getInitials().isBlank()) {
            throw new InvalidInputFromUserException("A initials da University é invalida.");
        }

        if (university.getCnpj() == null || university.getCnpj().isBlank()) {
            throw new InvalidInputFromUserException("O cnpj da University é invalido.");
        }

        if (university.getTelephone() == null || university.getTelephone().isBlank()) {
            throw new InvalidInputFromUserException("O telephone da University é invalido.");
        }

        if (university.getAddress() == null) {
            throw new InvalidInputFromUserException("O address da University é invalido.");
        }

        if (university.getAddress() == null) {
            throw new InvalidInputFromUserException("A initials da UF digitada é inválida.");
        }

        try {
            buscarUniversityPorId(university.getUniversity_id());

        } catch (NotFoundException ex) {
            // Nao faz nada, ja que a `university` nao existe no banco de dados.
        }

        if (contains(university.getUniversity_id())) {
            MainView.reportInternalError(null, new IllegalArgumentException("Já existe uma University com o id "
                    + university.getUniversity_id()));
        }
    }

    public boolean contains(int id) throws Exception {
        try {
            buscarUniversityPorId(id);
            return true;
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public University buscarUniversityPorId(int id) throws NotFoundException, Exception {
        University university = universitySystem.search(id);

        return university;
    }

    public List<University> buscarUniversityPorName(String name) throws Exception, NotFoundException {
        List<University> universitysBuscadas = new ArrayList<>();

        if (name.isBlank()) {
            return buscarTodasUniversitys();
        }

        universitysBuscadas = universitySystem.buscarPorName(name);

        if (universitysBuscadas.isEmpty()) {
            throw new NotFoundException("Não foi encontrada nenhuma University.");
        }

        return universitysBuscadas;
    }

    public List<University> buscarTodasUniversitys() throws NotFoundException, Exception {
        List<University> todasUniversitys;

        todasUniversitys = universitySystem.searchAll();

        if (todasUniversitys.isEmpty()) {
            throw new NotFoundException("Não há nenhuma University cadastrada.");
        }

        return todasUniversitys;
    }

    public void updateUniversity(University university) throws IllegalArgumentException, Exception {
        if (!contains(university.getUniversity_id())) {
            throw new IllegalArgumentException("Não existe nenhuma University com o id "
                    + university.getUniversity_id());
        }

        addressSys.update(university.getAddress());
        universitySystem.update(university);
    }

    public void removerUniversityPorId(int id) throws IllegalArgumentException, Exception {
        try {
            University universityASerRemovida = buscarUniversityPorId(id);
            Company companyASerRemovido = addressSys.search(universityASerRemovida.getUniversity_id());

            universitySystem.remover(id);
            addressSys.remover(companyASerRemovido.getCompany_id());

        } catch (Exception ex) {
            throw ex;
        }

    }

    public boolean universityENula(University university) {
        return university == null;
    }
}
