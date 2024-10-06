import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database"; // Update with your DB URL
        String username = "your_username"; // Update with your DB username
        String password = "your_password"; // Update with your DB password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 Scanner scanner = new Scanner(System.in)) {

                User user = new User(scanner, connection);
                Accounts accounts = new Accounts(connection, scanner);
                Account_Manager accountManager = new Account_Manager(connection, scanner);

                mainMenu(scanner, user, accounts, accountManager);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mainMenu(Scanner scanner, User user, Accounts accounts, Account_Manager accountManager) {
        while (true) {
            System.out.println("***WELCOME TO BANKING SYSTEM***");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice1 = scanner.nextInt();
            switch (choice1) {
                case 1:
                    user.register();
                    break;
                case 2:
                    handleUserLogin(scanner, user, accounts, accountManager);
                    break;
                case 3:
                    System.out.println("THANK YOU FOR USING OUR BANKING SYSTEM!");
                    return;
                default:
                    System.out.println("Enter a valid choice!");
                    break;
            }
        }
    }

    private static void handleUserLogin(Scanner scanner, User user, Accounts accounts, Account_Manager accountManager) {
        String email = user.login();
        if (email != null) {
            System.out.println("User Logged In!");
            // Existing logic for account handling...
        } else {
            System.out.println("Incorrect Email or Password!");
        }
    }
}
