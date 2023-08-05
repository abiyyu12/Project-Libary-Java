package Project.Libary.Data.Repositories.Books;

import Project.Libary.Data.Entity.Books;

public interface BooksRepositories {
    public boolean addBook(Books books);
    public Books[] viewBooks();
    public boolean incrementQty(String callno);
    public boolean incrementIssued(String callno);
    public boolean decrementQty(String callno);
    public boolean decrementIssued(String callno);
    public int getBooksId(String callno);

}
