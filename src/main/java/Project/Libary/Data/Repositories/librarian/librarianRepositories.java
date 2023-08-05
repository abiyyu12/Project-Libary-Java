package Project.Libary.Data.Repositories.librarian;

import Project.Libary.Data.Entity.Librarian;

public interface librarianRepositories {
    public boolean addLibrarian(Librarian librarian);
    public Librarian[] getViewLibrarian();
    public boolean deleteLibrarian(int id);
    public boolean librarianLogin(String username,String password);
    
}
