package Project.Libary.Data.Repositories;

import Project.Libary.Data.Entity.Librarian;
import Project.Libary.Data.Repositories.librarian.librarianRepositories;
import Project.Libary.Data.Repositories.librarian.librarianRepositoriesImpl;
import org.junit.jupiter.api.Test;

public class LibarianRepositoriesTest {
    @Test
    void viewLibarian(){
        librarianRepositories librarian = new librarianRepositoriesImpl();
        Librarian[] viewLibrarian = librarian.getViewLibrarian();
        for (var Temp:viewLibrarian){
            System.out.println("id : "+Temp.getId());
            System.out.println("Nama : "+Temp.getName());
            System.out.println("Password : "+Temp.getPassword());
            System.out.println("Email : "+Temp.getEmail());
            System.out.println("Address : "+Temp.getAddress());
            System.out.println("City : "+Temp.getCity());
            System.out.println("Contact : "+Temp.getContact());
        }
    }

    @Test
    void addLibrarian(){
        librarianRepositories librarianRepositories = new librarianRepositoriesImpl();
        boolean isTrue = librarianRepositories.addLibrarian(new Librarian("Ghani","Ghani123","Ghano@gmail.com",
                "Pulo Utama","Jakarta","0909201"));
        if(isTrue == true){
            System.out.println("Data Inserted");
        }
    }

    @Test
    void deleteLibarian(){
        librarianRepositories librarianRepositories = new librarianRepositoriesImpl();
        boolean isTrue = librarianRepositories.deleteLibrarian(3);
        if(isTrue == true){
            System.out.println("Data Deleted");
        }
        else{
            System.out.println("Data Not Found");
        }
    }
}
