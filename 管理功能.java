import java.util.*;
import java.io.*;

class BookManager {
    private List<Book> books = new ArrayList<>();
    private static final String BOOKS_FILE = "books.dat";

    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile();
        System.out.println("Book added successfully!");
    }

    public void deleteBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        saveBooksToFile();
        System.out.println("Book deleted successfully!");
    }

    public void viewBooks() {
        for (Book book : books) {
            book.displayInfo();
        }
    }

    public void searchBooks(String keyword) {
        for (Book book : books) {
            if (book.getTitle().contains(keyword) || book.getIsbn().contains(keyword)) {
                book.displayInfo();
            }
        }
    }

    public void loadBooksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE))) {
            books = (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing books found or error loading books.");
        }
    }

    public void saveBooksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books to file.");
        }
    }
}
