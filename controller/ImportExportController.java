package com.example.ramzigym.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class ImportExportController {
    @FXML
    private TextField importPathField;

    @FXML
    private TextField exportPathField;

    @FXML
    private Label importStatus;

    @FXML
    private Label exportStatus;

    @FXML
    private void handleImportBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Import File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(getStage());
        if (selectedFile != null) {
            importPathField.setText(selectedFile.getAbsolutePath());
            importStatus.setText("");
        }
    }

    @FXML
    private void handleExportBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Export Location");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );

        File selectedFile = fileChooser.showSaveDialog(getStage());
        if (selectedFile != null) {
            exportPathField.setText(selectedFile.getAbsolutePath());
            exportStatus.setText("");
        }
    }

    @FXML
    private void handleImport() {
        if (importPathField.getText().isEmpty()) {
            importStatus.setText("Please select a file to import");
            importStatus.getStyleClass().add("error");
            return;
        }

        try {
            // Add your file import logic here
            importStatus.setText("Import successful!");
            importStatus.getStyleClass().add("success");
        } catch (Exception e) {
            importStatus.setText("Error importing file: " + e.getMessage());
            importStatus.getStyleClass().add("error");
        }
    }

    @FXML
    private void handleExport() {
        if (exportPathField.getText().isEmpty()) {
            exportStatus.setText("Please select an export location");
            exportStatus.getStyleClass().add("error");
            return;
        }

        try {
            // Add your file export logic here
            exportStatus.setText("Export successful!");
            exportStatus.getStyleClass().add("success");
        } catch (Exception e) {
            exportStatus.setText("Error exporting file: " + e.getMessage());
            exportStatus.getStyleClass().add("error");
        }
    }

    private Stage getStage() {
        return (Stage) importPathField.getScene().getWindow();
    }
}