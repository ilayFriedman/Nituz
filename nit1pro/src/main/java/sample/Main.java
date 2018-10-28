package sample;

import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }



    private static String url = "jdbc:sqlite:/home/saar/Desktop/java/FifthSem/PlayingWithGitHub/";

    /**
     * Connect to a sample database
     *
     * @param fileName the database file name
     */
    public static void createNewDatabase(String fileName) {
        url += fileName;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewDatabase("test.db");
        addTableToDB(User.createUsersTableSQL());
        User myUser = new User("myNameHere", "hbjhb", new Date(1990, 5, 2), "Yofi", "Tofi", "Nesher");
        addUserToDB(myUser);
        selectFromDB();


    }

    private static void selectFromDB() {
        String sql = "SELECT * FROM tbl_users;";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString(1) + ", " +
                        rs.getString(2) + ", "+
                        rs.getString(3) + ", "+
                        rs.getString(4) + ", "+
                        rs.getString(5) + ", "+
                        rs.getString(6) + ", "
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addUserToDB(User user) {
        String sql = "INSERT INTO tbl_users(username, pwd, birthday, privateName, lastName, city) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPwd());
            pstmt.setString(3, user.getBirthday().toString());
            pstmt.setString(4, user.getPrivateName());
            pstmt.setString(5, user.getLastName());
            pstmt.setString(6, user.getCity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addTableToDB(String usersTableSQL) {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(usersTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}


