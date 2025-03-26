package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

import databasePart1.*;

/**
 * The SetupAdmin class handles the setup process for creating an administrator account.
 * This is intended to be used by the first user to initialize the system with admin credentials.
 */
public class AdminSetupPage {
	
    private final DatabaseHelper databaseHelper;

    public AdminSetupPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void show(Stage primaryStage) {
    	// Input fields for userName and password
        TextField userNameField = new TextField();
        userNameField.setPromptText("Enter Admin userName");
        userNameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(250);
        
        /* New Stuff */
        Label errorLabel = new Label();  // Error label to display if username or password are wrong
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12px;"); // Style of other label 
        /* End of new stuff */

        Button setupButton = new Button("Setup");
        
        setupButton.setOnAction(a -> {
        	// Retrieve user input
            String userName = userNameField.getText();
            String password = passwordField.getText();
            
            /*  Beginning of new stuff*/
            String nameError = UserNameRecognizer.checkForValidUserName(userName); // results of UserNameRecognizer
            String passwordError = PasswordEvaluator.evaluatePassword(password); // results of PasswordEvaluator
            
            // If no errors, just display them
            if (!nameError.isEmpty()) {
                System.out.println(nameError); // Error message
                errorLabel.setText(nameError); // Label error message
                return;  
            }

            if (!passwordError.isEmpty()) {
                System.out.println(passwordError); // Error message
                errorLabel.setText(passwordError); // Label error message
                return;  	
            }           
            /*End of new stuff*/
            
            try {
            	// Create a new User object with admin role and register in the database
            	User user=new User(userName, password, "admin");
                databaseHelper.register(user);
                System.out.println("Administrator setup completed.");
                
                // Navigate to the Welcome Login Page
                new WelcomeLoginPage(databaseHelper).show(primaryStage,user);
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
                e.printStackTrace();
            }
        });

        VBox layout = new VBox(10, userNameField, passwordField, setupButton, errorLabel); // Added error label
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        primaryStage.setScene(new Scene(layout, 800, 400));
        primaryStage.setTitle("Administrator Setup");
        primaryStage.show();
    }
}