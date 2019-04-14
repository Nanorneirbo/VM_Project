package sample;

//Ronan O'Brien - 13050044

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;


import java.io.File;


public class Login extends Application {

    //USername needs to carry throughout the GUI - so Static
    protected static String Username = "";
    protected static String Password = "";

    //Background image
    Image image = new Image((new File("Login.jpg").toURI().toString()));
    BackgroundImage myBI = new BackgroundImage((image),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);


    @Override
    public void start(Stage primaryStage) {


        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.setBackground(new Background(myBI));



        Label nameLabel = new Label();
        nameLabel.setStyle("-fx-font-weight: bold");
        nameLabel.setText("Enter Name");
        nameLabel.setTextFill(Color.BLUE);

        Label passwordLabel = new Label();
        passwordLabel.setStyle("-fx-font-weight: bold");
        passwordLabel.setText("Enter Password");
        passwordLabel.setTextFill(Color.BLUE);

        pane.add(nameLabel,0,0);
        TextField nameField = new TextField();
        pane.add(nameField, 1, 0);
        PasswordField passwordField = new PasswordField();
        pane.add(passwordLabel,0,2);
        pane.add(passwordField, 1, 2);

        Button login = new Button("Login");
        pane.add(login, 1, 3);
        GridPane.setHalignment(login, HPos.RIGHT);

        //Checkbox for admin
        CheckBox adminCheckBox = new CheckBox("Login as Admin");

        pane.add(adminCheckBox, 0, 4);


        Scene scene = new Scene(pane,1280,720);
        primaryStage.setTitle("Ronan O'Brien ---- 13050044"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage


        /**
         * Login button on action - Checks login from admin if admin box is ticked - from user if it isn't.
         */
        login.setOnAction(e -> {
            Username = nameField.getText();
            Password = passwordField.getText();
            if (adminCheckBox.isSelected()) {


                sample.AdminScreen adminScreen = new sample.AdminScreen();
                sample.Admin admin = new sample.Admin(nameField.getText(), passwordField.getText());
                if(admin.indexofUser() ==-1){
                    Label userError = new Label (" No such User - please try again!");
                    userError.setTextFill(Color.BLACK);
                    userError.setFont(Font.font("Verdana", 16));
                    pane.add(userError, 0, 5);
                }
                else {
                    if (admin.checkLogin()) {
                        adminScreen.start(primaryStage);
                    } else {
                        Label userError = new Label("Incorrect password - please try again!");
                        userError.setTextFill(Color.BLACK);
                        userError.setFont(Font.font("Verdana", 16));
                        pane.add(userError, 0, 5);

                    }
                }

            } else {
                sample.UserScreen userScreen = new sample.UserScreen();
                sample.Customer customer = new sample.Customer(nameField.getText(), passwordField.getText());
                if (customer.indexofUser() == -1) {
                    Label userError = new Label("No such User - please try again!");
                    userError.setTextFill(Color.BLACK);
                    userError.setFont(Font.font("Verdana", 16));
                    pane.add(userError, 0, 5);
                } else {
                    if (customer.checkLogin()) {
                        userScreen.start(primaryStage);
                    } else {
                        Label userError = new Label("Incorrect password - please try again!");
                        userError.setFont(Font.font("Verdana", 16));
                        userError.setTextFill(Color.BLACK);
                        pane.add(userError, 0, 5);

                    }
                }
            }
        });


    }
}