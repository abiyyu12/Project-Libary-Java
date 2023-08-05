package Project.Libary.Data.Repositories;

import Project.Libary.Data.Entity.Admin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class adminLogin {
    @Test
    void loginAdmin(){
        AdminRepositories adminRepositories = new AdminRepositoresImpl();
        boolean isTrue = adminRepositories.getAdmin(new Admin("admin","admin123"));
        Assertions.assertTrue(isTrue);
    }

}
