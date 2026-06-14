import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {

        try {
            // Create the connection
            String url = "jdbc:mysql://localhost:3306/snippets";
            String user = "root";
            String password = "root";

            Connection co = DriverManager.getConnection(url, user, password);

            // Create a connection statement
            Statement stmt = co.createStatement();

            String title;
            String language;
            String description;
            String code;
            // Create the Scanner
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("==== DEVVAULT ====");
                System.out.println("""
                        
                        1. Add a snippet
                        2. Displays all snippets
                        3. Retrieve a snippet
                        4. Delete a snippet
                        5. Quit
                        """);

                int choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        scanner.nextLine();

                        System.out.println("Title of your snippet : ");
                        title = scanner.nextLine();

                        System.out.println("Language used : ");
                        language = scanner.nextLine();

                        System.out.println("Description : ");
                        description = scanner.nextLine();

                        System.out.println("Code : ");
                        code = scanner.nextLine();

                        String query = "INSERT INTO snippets (title, language, description, code) VALUES (?, ?, ?, ?)";

                        PreparedStatement pstmt = co.prepareStatement(query);

                        pstmt.setString(1, title);
                        pstmt.setString(2, language);
                        pstmt.setString(3, description);
                        pstmt.setString(4, code);

                        pstmt.executeUpdate();


                        break;

                    case 2:
                        ResultSet res = stmt.executeQuery("SELECT * FROM snippets");

                        while (res.next()) {
                            System.out.println("ID : " + res.getInt("id"));
                            System.out.println("Title : " + res.getString("title"));
                            System.out.println("Language : " + res.getString("language"));
                            System.out.println("Description : " + res.getString("description"));
                            System.out.println("Code : " + res.getString("code"));
                            System.out.println("------------------------");
                        }

                        res.close();
                        break;

                    case 3:
                        break;

                    case 4:
                        break;

                    case 5:
                        scanner.close();
                        co.close();
                        return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}