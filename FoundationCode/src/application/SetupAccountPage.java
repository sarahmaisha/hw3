package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

import databasePart1.*;


/**
 * SetupAccountPage class handles the account setup process for new users.
 * Users provide their userName, password, and a valid invitation code to register.
 */
public class SetupAccountPage {
	
    private final DatabaseHelper databaseHelper;
    // DatabaseHelper to handle database operations.
    public SetupAccountPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    /**
     * Displays the Setup Account page in the provided stage.
     * @param primaryStage The primary stage where the scene will be displayed.
     */
    public void show(Stage primaryStage) {
    	// Input fields for userName, password, and invitation code
        TextField userNameField = new TextField();
        userNameField.setPromptText("Enter userName");
        userNameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(250);
        
        TextField inviteCodeField = new TextField();
        inviteCodeField.setPromptText("Enter InvitationCode");
        inviteCodeField.setMaxWidth(250);
        
        // Label to display error messages for invalid input or registration issues
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
        

        Button setupButton = new Button("Setup");
        
        Label space = new Label("");
        Button URB = new Button("Reset Password");

        
        setupButton.setOnAction(a -> {
        	// Retrieve user input
            String userName = userNameField.getText();
            String password = passwordField.getText();
            String code = inviteCodeField.getText();
            
            String nameError = UserNameRecognizer.checkForValidUserName(userName); // Stores result of UserNameRecognizer
            String passwordError = PasswordEvaluator.evaluatePassword(password); // Result of PasswordEvaluator
            
            // If no errors cont. like normal
            if (!nameError.isEmpty()) {
                System.out.println(nameError); // Print error
                errorLabel.setText(nameError); // Label error
                return;  
            }

            if (!passwordError.isEmpty()) {
                System.out.println(passwordError); // Print error
                errorLabel.setText(passwordError); // Label error
                return; 
            }
            
            // Finish this: Add to this to make sure password resets match
            try {
            	// Check if the user already exists
            	if(!databaseHelper.doesUserExist(userName)) {
            		
            		// Validate the invitation code
            		if(databaseHelper.validateInvitationCode(code)) {
            			
            			// Create a new user and register them in the database
		            	User user=new User(userName, password, "user");
		                databaseHelper.register(user);
		                
		                // New Stuff
		                ResetPassword.setResetPassword(null);
		                
		             // Navigate to the Welcome Login Page
		                new WelcomeLoginPage(databaseHelper).show(primaryStage,user);
            		}
            		else {
            			errorLabel.setText("Please enter a valid invitation code");
            		}
            	}
            	else {
            		errorLabel.setText("This useruserName is taken!!.. Please use another to setup an account");
            	}
            	
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
                e.printStackTrace();
            }
        });
        
        URB.setOnAction(a -> {
        	// Generate the invitation code using the databaseHelper and set it to the label
        	new ResetUserPage().show(primaryStage);
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(userNameField, passwordField,inviteCodeField, setupButton, errorLabel, space, URB);

        primaryStage.setScene(new Scene(layout, 800, 400));
        primaryStage.setTitle("Account Setup");
        primaryStage.show();
    }
}
