package com.example.ramzigym.controller;

import com.example.ramzigym.Model.Athlete;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private HBox addAt;
    @FXML
    private HBox market;
    @FXML
    private BorderPane rootPane;

    @FXML
    private TableView<Athlete> athletesTable;

    @FXML
    private TableColumn<Athlete, String> idColumn;

    @FXML
    private TableColumn<Athlete, String> nameColumn;

    @FXML
    private TableColumn<Athlete, String> sexColumn;

    @FXML
    private TableColumn<Athlete, String> timeColumn;

    @FXML
    private TableColumn<Athlete, String> typeColumn;

    @FXML
    private TableColumn<Athlete, String> operatorColumn;


    @FXML
    private Button clientsButton;

    @FXML
    private Button consumption;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button debt;

    @FXML
    private Button logOut;

    @FXML
    private VBox menuContainer;

    @FXML
    private Button offers;

    @FXML
    private Button settings;

    @FXML
    private Button users;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case F2:
                            loadSubScene("Addathlete"); // Load 'Addathlete' scene when F2 is pressed
                            break;
                        case F3:
                            loadSubScene("minimarket"); // Load 'minimarket' scene when F3 is pressed
                            break;
                        default:
                            // Handle other keys if necessary or leave empty
                            break;
                    }
                });
            }
        });

        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        sexColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSex()));
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTime()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        operatorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOperator()));

        idColumn.setCellFactory(column -> new TextFieldTableCell<>(new DefaultStringConverter()));
        nameColumn.setCellFactory(column -> new TextFieldTableCell<>(new DefaultStringConverter()));
        sexColumn.setCellFactory(column -> new TextFieldTableCell<>(new DefaultStringConverter()));
        timeColumn.setCellFactory(column -> new TextFieldTableCell<>(new DefaultStringConverter()));
        typeColumn.setCellFactory(column -> new TextFieldTableCell<>(new DefaultStringConverter()));
        operatorColumn.setCellFactory(column -> new TextFieldTableCell<>(new DefaultStringConverter()));

        // Sample Data
        athletesTable.getItems().add(new Athlete("001234014", "Mohammed", "Male", "08:12 PM", "Bodybuilding", "01"));
        athletesTable.getItems().add(new Athlete("001234015", "John", "Male", "09:00 AM", "CrossFit", "02"));
        athletesTable.getItems().add(new Athlete("001234015", "John", "Male", "09:00 AM", "CrossFit", "02"));
        athletesTable.getItems().add(new Athlete("001234015", "John", "Male", "09:00 AM", "CrossFit", "02"));
        athletesTable.getItems().add(new Athlete("001234015", "John", "Male", "09:00 AM", "CrossFit", "02"));
        athletesTable.getItems().add(new Athlete("001234015", "John", "Male", "09:00 AM", "CrossFit", "02"));
        athletesTable.getItems().add(new Athlete("001234015", "John", "Male", "09:00 AM", "CrossFit", "02"));

        dashboardButton.setOnAction(event -> loadNewScene("mainMenu"));
        clientsButton.setOnAction(event -> loadNewScene("listOfClients"));
        offers.setOnAction(event -> loadNewScene("Offers"));
        consumption.setOnAction(event -> loadNewScene("Consumption"));
        debt.setOnAction(event -> loadNewScene("Debt"));
        users.setOnAction(event -> loadNewScene("Users"));
        settings.setOnAction(event -> loadNewScene("Settings"));
        logOut.setOnAction(event -> loadNewScene("mainMenu"));



        // Calculate the percentage width for each column
        idColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.145)); // 12.5% of table width
        nameColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.255)); // 18.75% of table width
        sexColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.125)); // 12.5% of table width
        timeColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.125)); // 12.5% of table width
        typeColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.1875)); // 18.75% of table width

        operatorColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.125)); // 12.5% of table width
        addAt.setOnMouseClicked(event -> {
            // Action-like behavior here
            loadSubScene("Addathlete");
        });
        market.setOnMouseClicked(event ->{
            loadSubScene("minimarket");
        });
    }

    private void loadNewScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ramzigym/" + fxmlFile + ".fxml"));
            Parent newRoot = loader.load();

            if (rootPane == null || rootPane.getScene() == null) {
                throw new IllegalStateException("rootPane is not initialized properly.");
            }

            Scene scene = rootPane.getScene();
            scene.setRoot(newRoot);

            // Ensure maximized state remains unchanged
            Stage stage = (Stage) scene.getWindow();
            if (!stage.isMaximized()) {
                stage.setMaximized(true);
            }

            // Update stylesheet if it exists
            URL stylesheet = getClass().getResource("/com/example/ramzigym/styles/" + fxmlFile + ".css");
            scene.getStylesheets().clear(); // Clear previous styles
            if (stylesheet != null) {
                scene.getStylesheets().add(stylesheet.toExternalForm());
            } else {
                System.out.println("Warning: Stylesheet not found for " + fxmlFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void loadSubScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ramzigym/subscreen/" + fxmlFile + ".fxml"));
            Parent newRoot = loader.load();

            if (rootPane == null || rootPane.getScene() == null) {
                throw new IllegalStateException("rootPane is not initialized properly.");
            }

            Scene scene = rootPane.getScene();
            scene.setRoot(newRoot);

            // Ensure maximized state remains unchanged
            Stage stage = (Stage) scene.getWindow();
            if (!stage.isMaximized()) {
                stage.setMaximized(true);
            }

            // Update stylesheet if it exists
            URL stylesheet = getClass().getResource("/com/example/ramzigym/styles/" + fxmlFile + ".css");
            scene.getStylesheets().clear(); // Clear previous styles
            if (stylesheet != null) {
                scene.getStylesheets().add(stylesheet.toExternalForm());
            } else {
                System.out.println("Warning: Stylesheet not found for " + fxmlFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
