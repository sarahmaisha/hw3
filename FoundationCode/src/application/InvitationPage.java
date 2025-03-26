package application;


import databasePart1.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * InvitePage class represents the page where an admin can generate an invitation code.
 * The invitation code is displayed upon clicking a button.
 */

public class InvitationPage {

	/**
     * Displays the Invite Page in the provided primary stage.
     * 
     * @param databaseHelper An instance of DatabaseHelper to handle database operations.
     * @param primaryStage   The primary stage where the scene will be displayed.
     */
    public void show(DatabaseHelper databaseHelper,Stage primaryStage) {
    	VBox layout = new VBox();
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // Label to display the title of the page
	    Label userLabel = new Label("Invite Page");									// Added "Page"
	    userLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    
	    // Button to generate the invitation code
	    Button showCodeButton = new Button("Generate Invitation Code for Admin");	// added "for admin"
	    
	    // Label to display the generated invitation code
	    Label inviteCodeLabel = new Label(""); ;
        inviteCodeLabel.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");
        
        
        // New Stuff
        Button StudentButton = new Button("Generate Invitation Code for Student");	// Student button
        Label StudentLabel = new Label("");
        StudentLabel.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");
        
	    Button instructorButton = new Button("Generate Invitation Code for Instructor"); // Instructor Button
        Label InstructorLabel = new Label("");
        InstructorLabel.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");
        
	    Button StaffButton = new Button("Generate Invitation Code for Staff"); 			//Staff Button
        Label StaffLabel = new Label("");
        StaffLabel.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");
        
	    Button ReviewerButton = new Button("Generate Invitation Code for Reviewer"); 	//  Reviewer Button
        Label ReviewerLabel = new Label("");
        ReviewerLabel.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");
        // End of new stuff
        
        
        showCodeButton.setOnAction(a -> {
        	// Generate the invitation code using the databaseHelper and set it to the label
            String invitationCode = databaseHelper.generateInvitationCode();
            inviteCodeLabel.setText(invitationCode);
        });
	    
        
        // New Stuff
        StudentButton.setOnAction(a -> {
        	// Generate the invitation code using the databaseHelper and set it to the label
            String invitationCode = databaseHelper.generateInvitationCode();
            StudentLabel.setText(invitationCode);
        });
        
        instructorButton.setOnAction(a -> {
        	// Generate the invitation code using the databaseHelper and set it to the label
            String invitationCode = databaseHelper.generateInvitationCode();
            InstructorLabel.setText(invitationCode);
        });
        
        StaffButton.setOnAction(a -> {
        	// Generate the invitation code using the databaseHelper and set it to the label
            String invitationCode = databaseHelper.generateInvitationCode();
            StaffLabel.setText(invitationCode);
        });
        
        ReviewerButton.setOnAction(a -> {
        	// Generate the invitation code using the databaseHelper and set it to the label
            String invitationCode = databaseHelper.generateInvitationCode();
            ReviewerLabel.setText(invitationCode);
        });
        // End of new stuff
        

        layout.getChildren().addAll(userLabel, showCodeButton, inviteCodeLabel,			// Added new stuff to children
        		StudentButton, StudentLabel, instructorButton, InstructorLabel, 
        		StaffButton, StaffLabel, ReviewerButton, ReviewerLabel);
	    Scene inviteScene = new Scene(layout, 800, 400);

	    // Set the scene to primary stage
	    primaryStage.setScene(inviteScene);
	    primaryStage.setTitle("Invite Page");
    	
    }
}