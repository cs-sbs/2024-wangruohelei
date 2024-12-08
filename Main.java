import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final UserManager userManager = new UserManager();
    private static final BookManager bookManager = new BookManager();
    private static User currentUser;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        bookManager.loadBooksFromFile(); // Load books from file on startup
        showMenu();
    }

    private static void showMenu() {
        while (true) {
            System.out.println("\nWelcome to the Book Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. View Books");
            System.out.println("4. Search Books");
            System.out.println("5. Add a Book (Admin Only)");
            System.out.println("6. Delete a Book (Admin Only)");
            System.out.println("7. Logout");
            System.out.println("8. Exit");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1: register(); break;
                case 2: login(); break;
                case 3: viewBooks(); break;
                case 4: searchBooks(); break;
                case 5: addBook(); break;
                case 6: deleteBook(); break;
                case 7: logout(); break;
                case 8: exit(); break;
                default: System.out.println("Invalid choice! Please try again."); break;
            }
        }
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (USER/ADMIN): ");
        UserRole role = UserRole.valueOf(scanner.nextLine().toUpperCase());
        userManager.registerUser(username, password, role);
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        currentUser = userManager.loginUser(username, password);
    }

    private static void viewBooks() {
        if (currentUser == null) {
            System.out.println("Please login first!");
            return;
        }
        bookManager.viewBooks();
    }

    private static void searchBooks() {
        if (currentUser == null) {
            System.out.println("Please login first!");
            return;
        }
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        bookManager.searchBooks(keyword);
    }

    private static void addBook() {
        if (currentUser == null || currentUser.getRole() != UserRole.ADMIN) {
            System.out.println("Permission denied!");
            return;
        }
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter publish date (yyyy-mm-dd): ");
        String publishDateStr = scanner.nextLine();
        Date publishDate = null; // You need to parse the date string here using SimpleDateFormat or similar method.
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter programming language: ");
        String programmingLanguage = scanner.nextLine();
        bookManager.addBook(new ComputerBook(title, author, publishDate, isbn, programmingLanguage));
    }

    private static void deleteBook() {
        if (currentUser == null || currentUser.getRole() != UserRole.ADMIN) {
            System.out.println("Permission denied!");
            return;
        }
        System.out.print("Enter ISBN of the book to delete: ");
        String isbn = scanner.nextLine();
        bookManager.deleteBook(isbn);
    }

    private static void logout() {
        currentUser = null;
        System.out.println("Logged out successfully!");
    }

    private static void exit() {
        bookManager.saveBooksToFile(); // Save books to file before exiting
        System.exit(0);
    }
}
