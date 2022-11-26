package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {


    @FXML
    private BorderPane BorderPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
            BorderPane.setCenter(buildLineChart());
    }


    private BarChart buildBarChart(){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Product");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantity sold");
        BarChart barChart = new BarChart<>(xAxis, yAxis);


        XYChart.Series data = new XYChart.Series();
        data.setName("Products sold");
        data.getData().add(new XYChart.Data("Product A", 3000));
        data.getData().add(new XYChart.Data("Product B", 2500));
        data.getData().add(new XYChart.Data("Product C", 4200));


        barChart.getData().add(data);
        barChart.setLegendVisible(false);


        return  barChart;
    }

    private PieChart buildPieChart(){
        ObservableList<PieChart.Data>pieChartData= FXCollections.observableArrayList(
                new PieChart.Data("Product A", 3000),
                new PieChart.Data("Product B", 2500),
                new PieChart.Data("Product C", 3500)
        );

        PieChart pieChart=new PieChart(pieChartData);
        pieChart.setTitle("Producs sold");
        pieChart.setClockwise(true); //Sentido dos dados horário ou anti-horário.

        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);


        ContextMenu contextMenu =new ContextMenu();
        MenuItem miSwitchToBarChart= new MenuItem("Switch to BAr Chart");
        contextMenu.getItems().add(miSwitchToBarChart);

        pieChart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton()== MouseButton.SECONDARY){
                    contextMenu.show(pieChart,event.getScreenX(),event.getScreenY());
                }
            }
        });

        miSwitchToBarChart.setOnAction((ActionEvent acv)->{
            BorderPane.setCenter(buildBarChart());
        });

        return pieChart;
    }

    private LineChart buildLineChart(){
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Product");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantity sold");


        XYChart.Series series=new XYChart.Series<>();
        series.setName("Sales");

        series.getData().add(new XYChart.Data<>(1,23));
        series.getData().add(new XYChart.Data<>(2,30));
        series.getData().add(new XYChart.Data<>(3,13));
        series.getData().add(new XYChart.Data<>(4,28));

        LineChart lineChart=new LineChart<>(xAxis,yAxis);

        lineChart.getData().add(series);



        return lineChart;
    }

    @FXML
    void handleBarChart(ActionEvent event) {

        BorderPane.setCenter(buildBarChart());

    }

    @FXML
    void handlePieChart(ActionEvent event) {

        BorderPane.setCenter(buildPieChart());

    }


    @FXML
    private void handleUpdate(ActionEvent event) {
        Node node= BorderPane.getCenter();

        if(node instanceof PieChart pc){
            pc.getData().get(0).setPieValue(750);
        }
    }



}
