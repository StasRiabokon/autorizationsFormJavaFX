import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

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

    public static int addNewUser(TextField userTextField, PasswordField passwordField, PasswordField passwordField2) throws SQLException {

        Set<String> set = new HashSet<>();

        if (userTextField.getText().equals("") || passwordField.getText().equals("")) {
            return -3;
        }

        if (!passwordField.getText().equals(passwordField2.getText())) {
            return -1;
        }
        try (Connection conn = getConnection()) {

            Statement statement = conn.createStatement();

            try (ResultSet resultSet = statement.executeQuery("SELECT Login FROM Autoriz ")) {
                while (resultSet.next()) {
                    set.add(resultSet.getString(1).trim());
                }
            }
            if (set.contains(userTextField.getText())) {
                return -2;
            } else {
                statement.executeUpdate("INSERT INTO Autoriz VALUES('" + userTextField.getText() + "', '" + passwordField.getText() + "')");
            }
        }
        return 0;
    }


}
