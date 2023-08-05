package Project.Libary.Data.Repositories.librarian;

import Project.Libary.Data.Entity.Librarian;
import Project.Libary.Data.Util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class librarianRepositoriesImpl implements librarianRepositories{

    @Override
    public boolean addLibrarian(Librarian librarian) {
        boolean isTrue = false;
        String Query = "INSERT INTO librarian(name,password,email,address,city,contact) VALUES (?,?,?,?,?,?)";
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query)){
            preparedStatement.setString(1,librarian.getName());
            preparedStatement.setString(2,librarian.getPassword());
            preparedStatement.setString(3,librarian.getEmail());
            preparedStatement.setString(4,librarian.getAddress());
            preparedStatement.setString(5,librarian.getCity());
            preparedStatement.setString(6,librarian.getContact());
            preparedStatement.executeUpdate();
            isTrue = true;
        } catch (SQLException e) {
            isTrue = false;
            throw new RuntimeException(e);
        }
        return isTrue;
    }
    @Override
    public Librarian[] getViewLibrarian() {
        String Query = "SELECT * FROM librarian";
        try(Connection connection = DatabaseUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query)){
            ArrayList<Librarian>Data = new ArrayList<>();
            while(resultSet.next()){
                Librarian librarian = new Librarian();
                librarian.setId(resultSet.getInt("id"));
                librarian.setName(resultSet.getString("name"));
                librarian.setPassword(resultSet.getString("password"));
                librarian.setEmail(resultSet.getString("email"));
                librarian.setAddress(resultSet.getString("address"));
                librarian.setCity(resultSet.getString("city"));
                librarian.setContact(resultSet.getString("contact"));
                Data.add(librarian);
            }
            return Data.toArray(new Librarian[]{});
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteLibrarian(int id) {
        boolean isTrue = false;
        if(checkID(id)){
            String Query = "DELETE FROM librarian WHERE id = ?";
            try(Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Query)){
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
            isTrue = true;
            } catch (SQLException e) {
            throw new RuntimeException(e);
            }
        }
        return isTrue;
    }
    
    private boolean checkID(int id){
        String Query = "SELECT id FROM librarian WHERE id = ?";
        boolean isExist = false;
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query)){
            preparedStatement.setInt(1,id);
            try(ResultSet rs = preparedStatement.executeQuery()){
                if(rs.next()){
                    isExist = true;
                }
                else{
                    isExist = false;
                }
            }
          
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExist;
    }

    @Override
    public boolean librarianLogin(String username, String password) {
        boolean isLogin = false;
        String Query = "SELECT name,password FROM librarian WHERE name = ? AND password = ?";
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query)){
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            try(ResultSet rs = preparedStatement.executeQuery()){
                if(rs.next()){
                    isLogin = true;
                }
                else{
                    isLogin = false;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return isLogin;
    }
}
