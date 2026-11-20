
package com.schoolnavigation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Label statusLabel;
    private Label selectedSchoolLabel;
    private Label restaurantLabel;

    @Override
    public void start(Stage stage) {

        // Main application layout
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f3f6fa;");

        // ---------------- TOP HEADER ----------------

        Label title = new Label("School Navigation");
        title.setStyle(
                "-fx-font-size: 23px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );

        Label subtitle = new Label("Edmonton School, Transit and Food Finder");
        subtitle.setStyle("-fx-text-fill: #dbeafe; -fx-font-size: 12px;");

        VBox headerText = new VBox(4, title, subtitle);

        HBox header = new HBox(headerText);
        header.setPadding(new Insets(18, 25, 18, 25));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #1e3a5f;");

        root.setTop(header);

        // ---------------- LEFT SIDEBAR ----------------

        Label schoolHeading = new Label("Select School");
        schoolHeading.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");

        ComboBox<String> schoolSelector = new ComboBox<>();
        schoolSelector.getItems().addAll(
                "Sample School A",
                "Sample School B",
                "Sample School C"
        );
        schoolSelector.setPromptText("Choose a school");
        schoolSelector.setMaxWidth(Double.MAX_VALUE);

        Label layersHeading = new Label("Map Layers");
        layersHeading.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        CheckBox etsCheck = new CheckBox("ETS Bus Routes");
        CheckBox schoolBusCheck = new CheckBox("School Buses");
        CheckBox restaurantCheck = new CheckBox("Restaurants");

        etsCheck.setSelected(true);
        schoolBusCheck.setSelected(true);
        restaurantCheck.setSelected(true);

        Label actionsHeading = new Label("Navigation");
        actionsHeading.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button findFoodButton = new Button("Find Nearby Food");
        Button returnButton = new Button("Return to School");

        findFoodButton.setMaxWidth(Double.MAX_VALUE);
        returnButton.setMaxWidth(Double.MAX_VALUE);

        findFoodButton.setStyle(
                "-fx-background-color: #2563eb;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        returnButton.setStyle(
                "-fx-background-color: #e2e8f0;" +
                        "-fx-text-fill: #1e293b;"
        );

        VBox leftSidebar = new VBox(
                14,
                schoolHeading,
                schoolSelector,
                new Separator(),
                layersHeading,
                etsCheck,
                schoolBusCheck,
                restaurantCheck,
                new Separator(),
                actionsHeading,
                findFoodButton,
                returnButton
        );

        leftSidebar.setPadding(new Insets(20));
        leftSidebar.setPrefWidth(235);
        leftSidebar.setStyle("-fx-background-color: white;");

        root.setLeft(leftSidebar);

        // ---------------- CENTRE MAP AREA ----------------

        Label mapTitle = new Label("Map View");
        mapTitle.setStyle("-fx-font-size: 19px; -fx-font-weight: bold;");

        Label mapMessage = new Label(
                "Your interactive Edmonton map will appear here.\n\n" +
                        "School locations, bus routes and restaurants\n" +
                        "will be displayed on the map."
        );

        mapMessage.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-text-fill: #64748b;" +
                        "-fx-text-alignment: center;"
        );

        StackPane mapPlaceholder = new StackPane(mapMessage);
        mapPlaceholder.setStyle(
                "-fx-background-color: #e8eef5;" +
                        "-fx-border-color: #cbd5e1;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;"
        );

        VBox mapArea = new VBox(12, mapTitle, mapPlaceholder);
        mapArea.setPadding(new Insets(20));
        VBox.setVgrow(mapPlaceholder, Priority.ALWAYS);

        root.setCenter(mapArea);

        // ---------------- RIGHT SIDEBAR ----------------

        Label detailsHeading = new Label("School Information");
        detailsHeading.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");

        selectedSchoolLabel = new Label("No school selected");
        selectedSchoolLabel.setWrapText(true);
        selectedSchoolLabel.setStyle("-fx-text-fill: #475569;");

        Separator separator = new Separator();

        Label foodHeading = new Label("Nearby Food");
        foodHeading.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        restaurantLabel = new Label(
                "Select a school to explore nearby restaurants."
        );
        restaurantLabel.setWrapText(true);
        restaurantLabel.setStyle("-fx-text-fill: #475569;");

        VBox rightSidebar = new VBox(
                14,
                detailsHeading,
                selectedSchoolLabel,
                separator,
                foodHeading,
                restaurantLabel
        );

        rightSidebar.setPadding(new Insets(20));
        rightSidebar.setPrefWidth(245);
        rightSidebar.setStyle("-fx-background-color: white;");

        root.setRight(rightSidebar);

        // ---------------- BOTTOM STATUS BAR ----------------

        statusLabel = new Label("Ready. Select a school to begin.");

        HBox statusBar = new HBox(statusLabel);
        statusBar.setPadding(new Insets(10, 20, 10, 20));
        statusBar.setStyle(
                "-fx-background-color: #e2e8f0;" +
                        "-fx-border-color: #cbd5e1;"
        );

        root.setBottom(statusBar);

        // ---------------- BUTTON ACTIONS ----------------

        schoolSelector.setOnAction(event -> {
            String selectedSchool = schoolSelector.getValue();

            if (selectedSchool != null) {
                selectedSchoolLabel.setText(selectedSchool);
                restaurantLabel.setText(
                        "Restaurant information will be loaded for "
                                + selectedSchool + "."
                );
                statusLabel.setText("Selected school: " + selectedSchool);
            }
        });

        findFoodButton.setOnAction(event -> {
            if (schoolSelector.getValue() == null) {
                statusLabel.setText("Please select a school first.");
            } else {
                statusLabel.setText(
                        "Searching for nearby restaurants..."
                );
            }
        });

        returnButton.setOnAction(event -> {
            if (schoolSelector.getValue() == null) {
                statusLabel.setText("Please select a school first.");
            } else {
                statusLabel.setText(
                        "Preparing walking directions back to school..."
                );
            }
        });

        // ---------------- DISPLAY WINDOW ----------------

        Scene scene = new Scene(root, 1250, 750);

        stage.setTitle("School Navigation App");
        stage.setMinWidth(950);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

