package Project.Libary.Data.Repositories;

import Project.Libary.Data.Entity.Students;
import Project.Libary.Data.Repositories.Students.StudentsRepositories;
import Project.Libary.Data.Repositories.Students.StudentsRepositoriesImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentRepositoriesTest {

    @Test
    void testInserted(){
        StudentsRepositories studentsRepositories = new StudentsRepositoriesImpl();
        boolean isInserted = studentsRepositories.insertStudent(new Students("A@B",400,"Abiyyu","090212"));
        Assertions.assertTrue(isInserted);
    }
    @Test
    void testView(){
        StudentsRepositories studentsRepositories = new StudentsRepositoriesImpl();
        Students[] students =studentsRepositories.viewIssuedBooks();
        for (var temp:students){
            System.out.println("ID        : "+temp.getId());
            System.out.println("CallNo    : "+temp.getCallno());
            System.out.println("Student ID: "+temp.getStudentid());
            System.out.println("Name      : "+temp.getName());
            System.out.println("Contact   : "+temp.getContact());
            System.out.println("Issue Date: "+temp.getDATE());
        }
    }
    @Test
    void returnTestBoooks(){
        StudentsRepositories studentsRepositories = new StudentsRepositoriesImpl();
        boolean isSuccess = studentsRepositories.returnBooks("A@4",400);
        Assertions.assertTrue(isSuccess);
    }

}
