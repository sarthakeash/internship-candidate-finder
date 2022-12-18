
package br.ufg.inf.oop.internshipcandidatefinder.models.system;

import br.ufg.inf.oop.internshipcandidatefinder.models.entities.Student;
import java.sql.SQLException;
import java.util.List;

//for future iterations
public class studentSystem implements System<Student> {
    
    public void insertAdd(Student t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    
    public void update(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); .
    }
    
    public Student search(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    
    public List<Student> searchAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void remover(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    
    public String getNamesTable() {
        return "student";
    }
    
}
