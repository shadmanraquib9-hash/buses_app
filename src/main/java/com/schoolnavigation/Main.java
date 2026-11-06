package com.schoolnavigation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Label message = new Label("School Navigation App");

        StackPane root = new StackPane();
        root.getChildren().add(message);

        Scene scene = new Scene(root, 1000, 700);

        stage.setTitle("School Navigation App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
