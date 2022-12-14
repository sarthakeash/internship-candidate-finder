/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufg.inf.oop.internshipcandidatefinder.services;

import br.ufg.inf.oop.internshipcandidatefinder.models.entities.Company;
import br.ufg.inf.oop.internshipcandidatefinder.models.system.UniversitySystem;
import br.ufg.inf.oop.internshipcandidatefinder.models.system.studentSystem;
import br.ufg.inf.oop.internshipcandidatefinder.models.system.CourseTaken;
import br.ufg.inf.oop.internshipcandidatefinder.models.system.AddressSys;
import br.ufg.inf.oop.internshipcandidatefinder.models.entities.Student;
import br.ufg.inf.oop.internshipcandidatefinder.models.entities.Course;
import br.ufg.inf.oop.internshipcandidatefinder.models.entities.University;

/**
 *
 * 
 */
public class AppService {

    private UniversitySystem universityReg;
    private CourseTaken courseTaken;
    private studentSystem studentReg;
    private AddressSys addressSys;

    public void synchronizeAppWithDatabase() throws Exception {
        universityReg = new UniversitySystem();
        courseTaken = new CourseTaken();
        studentReg = new studentSystem();
        addressSys = new AddressSys();

        University.numberOfCreatedObjects = universityReg.getNumberOfInsertedRecords();
        Course.numberOfCreatedObjects = courseTaken.getNumberOfInsertedRecords();
        Student.numberOfCreatedObjects = studentReg.getNumberOfInsertedRecords();
        Company.numberOfCreatedObjects = addressSys.getNumberOfInsertedRecords();
    }

}
