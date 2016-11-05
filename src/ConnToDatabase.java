import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class ConnToDatabase {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/AutotorizationDatabase", "root", "root");
    }


    public static boolean CheckLoginPass(TextField userTextField, PasswordField passwordField) throws SQLException {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Autoriz ")) {
                while (resultSet.next()) {
                    if (resultSet.getString(1).trim().equals(userTextField.getText()) &&
                            resultSet.getString(2).trim().equals(passwordField.getText())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
