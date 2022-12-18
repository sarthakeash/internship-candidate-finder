package br.ufg.inf.oop.internshipcandidatefinder.models.system;

import br.ufg.inf.oop.internshipcandidatefinder.exceptions.NotFoundException;
import br.ufg.inf.oop.internshipcandidatefinder.models.entities.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressSys implements System<Company> {

    private Connection connection;

    public AddressSys() throws SQLException {
        this.connection = ConnectionFactory.getConnectionFactory();
    }

    @Override
    public void insertAdd(Company company) {
        String SQL = String.format("INSERT INTO \"%s\" values (?, ?, ?, ?, ?, ?)", getNamesTable());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, company.getCompany_id());
            preparedStatement.setString(2, company.getPoc_name());
            preparedStatement.setString(3, company.getAddress());
            preparedStatement.setString(4, company.getField());
            preparedStatement.setString(5, company.getCompanyName());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(Company company) throws SQLException {
        String SQL = String.format("UPDATE \"%s\" SET poc_name = ?, field = ?, companyName = ?, address = ?, "
                + "where company_id = ?", getNamesTable());

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, company.getPoc_name());
        preparedStatement.setString(2, company.getAddress());
        preparedStatement.setString(3, company.getField());
        preparedStatement.setString(4, company.getCompanyName());
        preparedStatement.setInt(6, company.getCompany_id());
        
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public Company search(int id) throws SQLException, NotFoundException {
        Company company = null;

        String SQL = String.format("SELECT * FROM \"%s\" WHERE id = ?", getNamesTable());

        PreparedStatement preparedStatement = connection.prepareStatement(SQL,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.first()) {
            company = new Company();

            company.setCompany_id(resultSet.getInt("company_id"));

            company.setPoc_name(resultSet.getString("poc_name"));
            company.setAddress(resultSet.getString("address"));

            company.setField(resultSet.getString("field"));

            company.setCompanyName(resultSet.getString("companyName"));

        }

        resultSet.close();
        preparedStatement.close();

        if (company == null) {
            throw new NotFoundException("No address found with id" + id + " .");
        }

        return company;
    }

    @Override
    public List<Company> searchAll() {
        return null;
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
        return "Address";
    }
}
