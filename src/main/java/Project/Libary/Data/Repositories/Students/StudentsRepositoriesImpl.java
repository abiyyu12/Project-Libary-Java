package Project.Libary.Data.Repositories.Students;

import Project.Libary.Data.Entity.Students;
import Project.Libary.Data.Repositories.Books.BooksRepositories;
import Project.Libary.Data.Repositories.Books.BooksRepositoriesImpl;
import Project.Libary.Data.Util.DatabaseUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentsRepositoriesImpl implements StudentsRepositories{

    private BooksRepositories booksRepositories;

    public StudentsRepositoriesImpl(){
        booksRepositories = new BooksRepositoriesImpl();
    }

    @Override
    public boolean insertStudent(Students students) {
        boolean isInserted = false;
        int id_books = booksRepositories.getBooksId(students.getCallno());
        booksRepositories.incrementIssued(students.getCallno());
        booksRepositories.decrementQty(students.getCallno());
        if(id_books!= -1){
            String Query = "INSERT INTO students (id_books,studentid,name,contact,issuedate) VALUES (?,?,?,?,?)";
            try(Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Query)){
                preparedStatement.setInt(1,id_books);
                preparedStatement.setInt(2,students.getStudentid());
                preparedStatement.setString(3,students.getName());
                preparedStatement.setString(4,students.getContact());
                preparedStatement.setString(5,LocalDate.now().toString());
                preparedStatement.executeUpdate();
                isInserted = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return isInserted;
    }

    @Override
    public Students[] viewIssuedBooks() {
        String Query = "SELECT students.id,books.callno,students.studentid,students.name,students.contact,students.issuedate FROM students INNER JOIN books ON students.id_books = books.id";
        try(Connection connection = DatabaseUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query)){
            ArrayList<Students>data = new ArrayList<>();
            while(resultSet.next()){
                Students students = new Students();
                students.setId(resultSet.getInt("students.id"));
                students.setCallno(resultSet.getString("books.callno"));
                students.setStudentid(resultSet.getInt("students.studentid"));
                students.setName(resultSet.getString("students.name"));
                students.setContact(resultSet.getString("students.contact"));
                students.setDATE(resultSet.getString("students.issuedate"));
                data.add(students);
            }
            return data.toArray(new Students[]{});
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean returnBooks(String callno, int studentsID) {
        boolean isDeletedStudent = false;
        String Query = "DELETE FROM students WHERE studentid = ?";
        
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query)){
            preparedStatement.setInt(1,studentsID);
            preparedStatement.executeUpdate();
            isDeletedStudent = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(isDeletedStudent == true){
            boolean isUpdatedQty = booksRepositories.incrementQty(callno);
            booksRepositories.decrementIssued(callno);
            if(isUpdatedQty == true){
                return true;
            }
            else {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}
