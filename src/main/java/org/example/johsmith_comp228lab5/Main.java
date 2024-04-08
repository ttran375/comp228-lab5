package org.example.johsmith_comp228lab5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main extends Application {
    Connection dbConnection;
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
        dbConnection = connectToDatabase();
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

        // Button actions for creating players
        createPlayersButton.setOnAction(event -> createPlayer());
    }

    private void createPlayer() {
        try {
            // Retrieving input data from text fields
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String address = addressField.getText();
            String postalCode = postalCodeField.getText();
            String province = provinceField.getText();
            String phoneNumber = phoneNumberField.getText();
            String gameTitle = gameTitleField.getText();
            String datePlayed = datePlayedField.getText();
            String gameScore = gameScoreField.getText();

            // Inserting player information into the database
            PreparedStatement preparedStatement1 = dbConnection.prepareStatement(
                    "INSERT INTO PLAYER (PLAYER_ID, FIRST_NAME, LAST_NAME, ADDRESS, POSTAL_CODE, PROVINCE, PHONE_NUMBER) VALUES (player_id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)");
            preparedStatement1.setString(1, firstName);
            preparedStatement1.setString(2, lastName);
            preparedStatement1.setString(3, address);
            preparedStatement1.setString(4, postalCode);
            preparedStatement1.setString(5, province);
            preparedStatement1.setString(6, phoneNumber);
            preparedStatement1.executeUpdate();

            // Inserting game information into the database
            PreparedStatement preparedStatement2 = dbConnection.prepareStatement(
                    "INSERT INTO GAME (game_id, game_title) VALUES (game_id_seq.NEXTVAL, ?)");
            preparedStatement2.setString(1, gameTitle);
            preparedStatement2.executeUpdate();

            // Inserting player-game relationship into the database
            PreparedStatement preparedStatement3 = dbConnection.prepareStatement(
                    "INSERT INTO PLAYERANDGAME (PLAYER_GAME_ID, GAME_ID, PLAYER_ID, PLAYING_DATE, SCORE) VALUES (player_game_id_seq.NEXTVAL, game_id_seq.CURRVAL, player_id_seq.CURRVAL, ?, ?)");
            preparedStatement3.setString(1, datePlayed);
            preparedStatement3.setString(2, gameScore);
            preparedStatement3.executeUpdate();

            // Displaying success message and clearing fields
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Player created successfully!", ButtonType.OK);
            alert.showAndWait();
            clearFields();
        } catch (SQLException e) {
            // Handling database errors
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error creating player!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        addressField.clear();
        postalCodeField.clear();
        provinceField.clear();
        phoneNumberField.clear();
        gameTitleField.clear();
        gameScoreField.clear();
        datePlayedField.clear();
        playerIdField.clear();
    }

    public Connection connectToDatabase() {
        Connection connection = null;
        try {
            System.out.println("> Start Program ...");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("> Driver Loaded successfully.");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@199.212.26.208:1521:SQLD", "COMP228_W24_sy_68",
                    "password");
            System.out.println("Database connected successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        launch();
    }
}
