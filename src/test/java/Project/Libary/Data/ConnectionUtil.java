package Project.Libary.Data;

import Project.Libary.Data.Util.DatabaseUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {

    @Test
    void TestConnection(){
        Connection connection = DatabaseUtil.getConnection();
        try {
            if(connection == null){
                System.out.println("Connection IS Null");
            }
            else{
                System.out.println("Connection not null");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
