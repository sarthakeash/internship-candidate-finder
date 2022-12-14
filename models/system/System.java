package br.ufg.inf.oop.internshipcandidatefinder.models.system;

import br.ufg.inf.oop.internshipcandidatefinder.exceptions.NotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

public interface System<T> {

    void insertAdd(T t) throws SQLException;

    void update(T t) throws SQLException;

    T search(int id) throws SQLException, NotFoundException, Exception;

    List<T> searchAll() throws SQLException, Exception;

    void remover(int id) throws SQLException;

    String getNamesTable();

    default int getNumberOfInsertedRecords() throws SQLException {
        int numberOfInstertedRegisters;
        String SQL = String.format("SELECT MAX(id) FROM \"%s\"", getNamesTable());

        PreparedStatement preparedStatement = ConnectionFactory.getConnectionFactory().prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        numberOfInstertedRegisters = resultSet.getInt("max");

        return numberOfInstertedRegisters;
    }

}
