package Project.Libary.Data.Repositories;

import Project.Libary.Data.Entity.Books;
import Project.Libary.Data.Repositories.Books.BooksRepositories;
import Project.Libary.Data.Repositories.Books.BooksRepositoriesImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BooksRepositoriesTest {
    @Test
    void insertTest(){
        BooksRepositories booksRepositories = new BooksRepositoriesImpl();
        boolean isInserted = booksRepositories.addBook(new Books("A@B","Java","CJ","Steven",2));
        Assertions.assertTrue(isInserted);
    }
    @Test
    void viewTest(){
        BooksRepositories booksRepositories = new BooksRepositoriesImpl();
        Books[] books = booksRepositories.viewBooks();
        for (var temp:books){
            System.out.println("ID        : "+temp.getId());
            System.out.println("CallNo    : "+temp.getCallno());
            System.out.println("Name      : "+temp.getName());
            System.out.println("Auth      : "+temp.getAuth());
            System.out.println("Publisher : "+temp.getPublisher());
            System.out.println("Qty       : "+temp.getQty());
            System.out.println("Issued    : "+temp.getIssued());
            System.out.println("Date Add  : "+temp.getDate());
        }
    }

    @Test
    void AddQtyBooks(){
        BooksRepositories booksRepositories = new BooksRepositoriesImpl();
        boolean isUpdated = booksRepositories.incrementQty("A@4");
        Assertions.assertTrue(isUpdated);
    }

    @Test
    void testIssedBooks() {
        BooksRepositories booksRepositories = new BooksRepositoriesImpl();
        boolean isUpdated = booksRepositories.incrementIssued("A@4");
        Assertions.assertTrue(isUpdated);
    }

    @Test
    void testGetBooksId(){
        BooksRepositories booksRepositories = new BooksRepositoriesImpl();
        int idBooks = booksRepositories.getBooksId("A@4");
        System.out.println(idBooks);
    }
}
