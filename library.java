import java.io.Serializable;
import java.util.Date;

// 基类 Book
abstract class Book implements Serializable {
    private String title;
    private String author;
    private Date publishDate;
    private String isbn;

    public Book(String title, String author, Date publishDate, String isbn) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.isbn = isbn;
    }

    public abstract void displayInfo();

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
}

// 子类 ComputerBook
class ComputerBook extends Book {
    private String programmingLanguage;

    public ComputerBook(String title, String author, Date publishDate, String isbn, String programmingLanguage) {
        super(title, author, publishDate, isbn);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public void displayInfo() {
        System.out.println("Computer Book: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publish Date: " + getPublishDate());
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Programming Language: " + programmingLanguage);
    }
}
