package JavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaFXExamples extends Application {

    @Override
    public void start(Stage primaryStage) {

        //Create GUI Elements
        FlowPane root = new FlowPane(Orientation.HORIZONTAL, 5, 5);
        Button addButton = new Button();
        TextField xValue = new TextField();
        TextField yValue = new TextField();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);

        //Set up the line chart data
        lineChart.setTitle("Value Tracker");
        XYChart.Series series = new XYChart.Series();
        series.setName("My values");
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 20));
        series.getData().add(new XYChart.Data(3, 26));
        series.getData().add(new XYChart.Data(4, 29));
        lineChart.getData().add(series);

        //Configure Button information and handlers
        addButton.setText("Add Value");
        addButton.setId("#button");
        addButton.setOnAction(event -> series.getData().add(
                new XYChart.Data(
                        Integer.valueOf(xValue.getText()),
                        Integer.valueOf(yValue.getText())
        )));

        //Add all values to the scene, configure style, and display
        root.getChildren().addAll(xValue, yValue, addButton, lineChart);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("/JavaFX/main.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void run(String[] args) {
        launch(args);
    }
}
