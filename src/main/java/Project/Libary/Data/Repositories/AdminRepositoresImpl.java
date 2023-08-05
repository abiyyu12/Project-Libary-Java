package Project.Libary.Data.Repositories;

import Project.Libary.Data.Entity.Admin;
import Project.Libary.Data.Util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoresImpl implements AdminRepositories{
    @Override
    public boolean getAdmin(Admin admin) {
        boolean isTrue = false;
        String Query = "SELECT username,password FROM admin WHERE username = ? AND password = ?";
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query)){
            preparedStatement.setString(1,admin.getUsername());
            preparedStatement.setString(2,admin.getPassword());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    // Jika data nya ada
                    isTrue = true;
                }
                else {
                    isTrue = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTrue;
    }
}
