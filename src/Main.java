import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;

public class Main extends Application {

    private TextField userTextField;
    private PasswordField passwordField;
    private Button buttonSignIn;

    @Override
    public void start(Stage primaryStage) throws SQLException, Exception {
        primaryStage.setTitle("Authorization");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("Welcome");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(title, 0, 0, 2, 1);

        Label userName = new Label("Login:");
        gridPane.add(userName, 0, 1);

        userTextField = new TextField();
        gridPane.add(userTextField, 1, 1);

        Label password = new Label("Password:");
        gridPane.add(password, 0, 2);

        passwordField = new PasswordField();
        gridPane.add(passwordField, 1, 2);

        buttonSignIn = new Button("Sign in");
        HBox hbbtn = new HBox(10);
        hbbtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbbtn.getChildren().add(buttonSignIn);
        gridPane.add(hbbtn, 1, 4);

        buttonSignIn.setOnAction(new EventHandler<ActionEvent>() {
                                     @Override
                                     public void handle(ActionEvent event) {
                                         try {
                                             if (ConnToDatabase.CheckLoginPass(userTextField, passwordField)) {
                                                 enteredSys();
                                             } else errLoginOrPass();

                                         } catch (SQLException e) {
                                             e.printStackTrace();
                                         }


                                     }
                                 }
        );

        Scene scene = new Scene(gridPane, 300, 225);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Alert enteredSys() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Welcome");
        alert.setHeaderText(null);
        alert.setContentText("You entered the system");
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
        ;
        return alert;
    }

    private Alert errLoginOrPass() {
        Alert erroralert1 = new Alert(Alert.AlertType.ERROR);
        erroralert1.setTitle("Error");
        erroralert1.setHeaderText("Incorrect login or password");
        erroralert1.setContentText("Please, try again");
        erroralert1.initStyle(StageStyle.UTILITY);
        erroralert1.showAndWait();
        return erroralert1;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
