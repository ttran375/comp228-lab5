package org.example.johsmith_comp228lab5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField addressField;
    private TextField postalCodeField;
    private TextField provinceField;
    private TextField phoneNumberField;
    private TextField playerIdField;
    private TextField gameTitleField;
    private TextField gameScoreField;
    private TextField datePlayedField;

    @Override
    public void start(Stage primaryStage) {
        GridPane displayInfoPane = new GridPane();

        // Labels for player information
        Label playerInfoLabel = new Label("Player's Information:");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label addressLabel = new Label("Address:");
        Label provinceLabel = new Label("Province:");
        Label postalCodeLabel = new Label("Postal Code:");
        Label phoneNumberLabel = new Label("Phone Number:");

        // TextFields for player information
        firstNameField = new TextField();
        lastNameField = new TextField();
        addressField = new TextField();
        provinceField = new TextField();
        postalCodeField = new TextField();
        phoneNumberField = new TextField();

        // Adding labels and fields to the displayInfoPane
        displayInfoPane.add(playerInfoLabel, 0, 0, 1, 1);
        displayInfoPane.add(firstNameLabel, 0, 1);
        displayInfoPane.add(lastNameLabel, 0, 2);
        displayInfoPane.add(addressLabel, 0, 3);
        displayInfoPane.add(provinceLabel, 0, 4);
        displayInfoPane.add(postalCodeLabel, 0, 5);
        displayInfoPane.add(phoneNumberLabel, 0, 6);
        displayInfoPane.add(firstNameField, 1, 1);
        displayInfoPane.add(lastNameField, 1, 2);
        displayInfoPane.add(addressField, 1, 3);
        displayInfoPane.add(provinceField, 1, 4);
        displayInfoPane.add(postalCodeField, 1, 5);
        displayInfoPane.add(phoneNumberField, 1, 6);

        // Label and TextField for updating player by ID
        Label playerIdLabel = new Label("Update Player by ID:");
        playerIdField = new TextField();
        Button updateButton = new Button("Update");

        // Adding player ID elements to displayInfoPane
        displayInfoPane.add(playerIdLabel, 3, 0);
        displayInfoPane.add(playerIdField, 4, 0);
        displayInfoPane.add(updateButton, 5, 0);

        // Labels and TextFields for game information
        Label gameInfoLabel = new Label("Game Information:");
        Label gameTitleLabel = new Label("Game Title:");
        Label gameScoreLabel = new Label("Game Score:");
        Label datePlayedLabel = new Label("Date Played:");
        gameTitleField = new TextField();
        gameScoreField = new TextField();
        datePlayedField = new TextField();

        // Adding game information elements to displayInfoPane
        displayInfoPane.add(gameInfoLabel, 3, 3, 1, 1);
        displayInfoPane.add(gameTitleLabel, 3, 4);
        displayInfoPane.add(gameScoreLabel, 3, 5);
        displayInfoPane.add(datePlayedLabel, 3, 6);
        displayInfoPane.add(gameTitleField, 4, 4);
        displayInfoPane.add(gameScoreField, 4, 5);
        displayInfoPane.add(datePlayedField, 4, 6);

        // Buttons for creating and displaying players
        Button createPlayersButton = new Button("Create Players");
        Button displayPlayersButton = new Button("Display All Players");

        // Adding buttons to displayInfoPane
        displayInfoPane.add(createPlayersButton, 4, 9);
        displayInfoPane.add(displayPlayersButton, 5, 9);

        // Setting up the layout with BorderPane
        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(displayInfoPane);

        // Setting the scene and primary stage
        Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Player Information");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
