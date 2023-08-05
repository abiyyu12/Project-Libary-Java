package Project.Libary.Data.Repositories.Students;

import Project.Libary.Data.Entity.Students;

public interface StudentsRepositories {
    public boolean insertStudent(Students students);
    public Students[] viewIssuedBooks();
    public boolean returnBooks(String callno,int studentsID);
}
