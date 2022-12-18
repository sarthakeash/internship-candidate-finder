package br.ufg.inf.oop.internshipcandidatefinder.models.system;

import br.ufg.inf.oop.internshipcandidatefinder.exceptions.NotFoundException;
import br.ufg.inf.oop.internshipcandidatefinder.models.entities.University;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniversitySystem implements System<University> {

    private Connection connection;
    private AddressSys addressSys;
    private int university_id;
    private int name;


    public UniversitySystem() throws SQLException {
        this.connection = ConnectionFactory.getConnectionFactory();
        this.addressSys = new AddressSys();
    }

    @Override
    public void insertAdd(University university) throws SQLException {
        String SQL = String.format("INSERT INTO \"%s\" (id, name, initials, cnpj, telephone, "
                + "address_id) values (?, ?, ?, ?, ?, ?)", getNamesTable());

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, university.getUniversity_id());
        preparedStatement.setString(2, university.getName());
        preparedStatement.setString(3, university.getInitials());
        preparedStatement.setString(4, university.getCnpj());
        preparedStatement.setString(5, university.getTelephone());
        preparedStatement.setInt(6, university.getAddress().getCompany_id());

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public void update(University university) throws SQLException {
        String SQL = String.format("UPDATE \"%s\" SET name = ?, initials = ?, cnpj = ?, telephone = ? where id = ?",
                getNamesTable());

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, university.getName());
        preparedStatement.setString(2, university.getInitials());
        preparedStatement.setString(3, university.getCnpj());
        preparedStatement.setString(4, university.getTelephone());
        preparedStatement.setInt(5, university.getUniversity_id());

        preparedStatement.executeUpdate();

        preparedStatement.close();

    }

    @Override
    public University search(int id) throws SQLException, NotFoundException, Exception {
        University university = null;

        String SQL = String.format("SELECT * FROM \"%s\" WHERE id = ?", getNamesTable());

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            university = new University();

            university.setUniversity_id(resultSet.getInt("id"));
            university.setName(resultSet.getString("name"));
            university.setInitials(resultSet.getString("initials"));
            university.setCnpj(resultSet.getString("cnpj"));
            university.setTelephone(resultSet.getString("telephone"));
            university.setAddress(addressSys.search(resultSet.getInt("address_id")));

        }

        resultSet.close();
        preparedStatement.close();

        if (university == null) {
            throw new NotFoundException("\n" + "No University was found with the id " + id + " .");
        }

        return university;
    }

    @Override
    public List<University> searchAll() throws SQLException, Exception {
        List<University> universitys = new ArrayList<>();
        University university;

        String SQL = String.format("SELECT * FROM \"%s\"", getNamesTable());

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            university = new University();
            university.setUniversity_id(resultSet.getInt("id"));
            university.setName(resultSet.getString("name"));
            university.setInitials(resultSet.getString("initials"));
            university.setCnpj(resultSet.getString("cnpj"));
            university.setTelephone(resultSet.getString("telephone"));

            university.setAddress(addressSys.search(resultSet.getInt("address_id")));

            universitys.add(university);
        }

        return universitys;
    }

    @Override
    public void remover(int id) throws SQLException {
        String SQL = String.format("DELETE FROM \"%s\" where id = ?", getNamesTable());

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setInt(1, id);

        preparedStatement.execute();

        preparedStatement.close();

    }

    @Override
    public String getNamesTable() {
        return "University";
    }

    public List<University> buscarPorName(String name) throws SQLException, Exception {
        List<University> universitys = new ArrayList<>();
        University university;

        String SQL = String.format("SELECT * FROM \"%s\" WHERE name ILIKE '%%%s%%'", getNamesTable(), name);

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            university = new University();
            university.setUniversity_id(resultSet.getInt("id"));
            university.setName(resultSet.getString("name"));
            university.setInitials(resultSet.getString("initials"));
            university.setCnpj(resultSet.getString("cnpj"));
            university.setTelephone(resultSet.getString("telephone"));
            university.setAddress(addressSys.search(resultSet.getInt("address_id")));
            universitys.add(university);
        }

        return universitys;

    }

    /*public int getNumberOfInsertedRecords() throws SQLException {
        int numberOfInstertedRegisters = 0;
        String SQL = String.format("SELECT MAX(id) FROM \"%s\"", getNameDaTabela());

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        
        numberOfInstertedRegisters = resultSet.getInt("max");

        return numberOfInstertedRegisters;
    }*/
}
