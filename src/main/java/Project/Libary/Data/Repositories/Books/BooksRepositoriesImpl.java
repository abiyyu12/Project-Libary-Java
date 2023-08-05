package Project.Libary.Data.Repositories.Books;

import Project.Libary.Data.Entity.Books;
import Project.Libary.Data.Util.DatabaseUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.awt.print.Book;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

 public class BooksRepositoriesImpl implements BooksRepositories{
    @Override
    public boolean addBook(Books books) {
        boolean isSuccess = false;
        String Query = "INSERT INTO books(callno,name,auth,publisher,qty,issued,dateadd) VALUES (?,?,?,?,?,?,?)";
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query);){
            preparedStatement.setString(1,books.getCallno());
            preparedStatement.setString(2,books.getName());
            preparedStatement.setString(3,books.getAuth());
            preparedStatement.setString(4,books.getPublisher());
            preparedStatement.setInt(5,books.getQty());
            preparedStatement.setInt(6,0);
            preparedStatement.setString(7,LocalDate.now().toString());
            preparedStatement.executeUpdate();
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    @Override
    public Books[] viewBooks() {
        String Query = "SELECT * FROM books";
        try(Connection connection = DatabaseUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query)){
            ArrayList<Books>books = new ArrayList<>();
            while(resultSet.next()){
                Books tempBooks = new Books();
                tempBooks.setId(resultSet.getInt("id"));
                tempBooks.setCallno(resultSet.getString("callno"));
                tempBooks.setName(resultSet.getString("name"));
                tempBooks.setAuth(resultSet.getString("auth"));
                tempBooks.setPublisher(resultSet.getString("publisher"));
                tempBooks.setQty(resultSet.getInt("qty"));
                tempBooks.setIssued(resultSet.getInt("issued"));
                tempBooks.setDate(resultSet.getString("dateadd"));
                books.add(tempBooks);
            }
            return books.toArray(new Books[]{});
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean incrementQty(String callno) {
        int qty = getQty(callno);
        boolean isUpdated = false;
        qty++;
        if(qty!=0){
            String Query = "UPDATE books SET qty = ? WHERE callno = ?";
            try(Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Query)){
                preparedStatement.setInt(1,qty);
                preparedStatement.setString(2,callno);
                preparedStatement.executeUpdate();
                isUpdated = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return isUpdated;
    }

    @Override
    public boolean incrementIssued(String callno) {
        int Issued = getIssued(callno);
        boolean isUpdated = false;
        Issued++;
        if(Issued!=0){
            String Query = "UPDATE books SET issued = ? WHERE callno = ?";
            try(Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Query)){
                preparedStatement.setInt(1,Issued);
                preparedStatement.setString(2,callno);
                preparedStatement.executeUpdate();
                isUpdated = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return isUpdated;
    }

     @Override
     public int getBooksId(String callno) {
         int id = -1;
         String Query = "SELECT id FROM books WHERE callno = ?";
         try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query)){
             preparedStatement.setString(1,callno);
             try(ResultSet resultSet = preparedStatement.executeQuery()){
                 if(resultSet.next()){
                     id = resultSet.getInt("id");
                 }
             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return id;
     }

     private int getQty(String callno){
        int Qty = -1;
        String Query = "SELECT qty FROM books WHERE callno = ?";
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query)){
            preparedStatement.setString(1,callno);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    Qty = resultSet.getInt("qty");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Qty;
    }

    public int getIssued(String callno){
        int Issued = -1;
        String Query = "SELECT issued FROM books WHERE callno = ?";
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query)){
            preparedStatement.setString(1,callno);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    Issued = resultSet.getInt("issued");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Issued;
    }

    @Override
    public boolean decrementQty(String callno) {
        int Qty = getQty(callno);
        boolean isDecreased = false;
        Qty--;
        if(Qty!=0){
            String Query = "UPDATE books SET qty = ? WHERE callno = ?";
             try(Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Query)){
                preparedStatement.setInt(1,Qty);
                preparedStatement.setString(2,callno);
                preparedStatement.executeUpdate();
                isDecreased = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return isDecreased;
    }

    @Override
    public boolean decrementIssued(String callno) {
        int Issued = getIssued(callno);
        boolean isDecrement = false;
        Issued--;
        if(Issued!=0){
            String Query = "UPDATE books SET issued = ? WHERE callno = ?";
            try(Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Query)){
                preparedStatement.setInt(1,Issued);
                preparedStatement.setString(2,callno);
                preparedStatement.executeUpdate();
                isDecrement = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return isDecrement;
    }
}
