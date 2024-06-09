package client.gui.controllers;

import client.data.DisplayLabwork;
import client.network.UDPClient;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class VisualizationController {
    @FXML private ImageView mapView;
    @FXML private Button backToTable;
    private ResourceBundle bundle;

    UDPClient udpClient;
    Locale locale;

    public VisualizationController(UDPClient udpClient, Locale locale){
        this.locale = locale;
        this.udpClient = udpClient;
        this.bundle = ResourceBundle.getBundle("visualization", locale);
    }

    public void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("map.png")).toExternalForm());
        setImage(image);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        removeAllCircles();

                        udpClient.createConnection();
                        ArrayDeque<DisplayLabwork> collection = udpClient.loadCollection().stream()
                                .map(DisplayLabwork::new)
                                .collect(Collectors.toCollection(ArrayDeque::new));
                        udpClient.closeConnection();

                        for (DisplayLabwork labwork : collection) {
                            drawCircles(labwork, collection);
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

            }
        }, 0, 5000);
    }

    public void setImage(Image image) {
        mapView.setImage(image);
    }

    public void drawCircles(DisplayLabwork lab, ArrayDeque<DisplayLabwork> collection) {
        double maxX = collection.stream().max(Comparator.comparing(lw -> lw.getCoordinates().getX())).get().getCoordinates().getX();
        double maxY = collection.stream().max(Comparator.comparing(lw -> lw.getCoordinates().getY())).get().getCoordinates().getY();

        double x = (double) lab.getCoordinates().getX();
        double y = (double) lab.getCoordinates().getY();

        double viewX = mapView.getFitWidth();
        double viewY = mapView.getFitHeight();

        double circleX = x / maxX * viewX;
        double circleY = y / maxY * viewY;

        Circle circle = new Circle(circleX, circleY, 10, Color.RED);
        Pane parent = (Pane) mapView.getParent();
        circle.setTranslateZ(100);

        long personId = lab.getId();
        circle.setId(Long.toString(personId));

        circle.setOnMouseClicked(event -> {

            String circleId = ((Circle) event.getSource()).getId();

            DisplayLabwork clickedLab = getLab(collection, Integer.parseInt(circleId));

            Popup popup = new Popup();
            BorderPane borderPane = new BorderPane();
            borderPane.setStyle("-fx-background-color: white; -fx-padding: 10px;");
            borderPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            Label header = new Label(clickedLab.getName());
            header.setStyle("-fx-font-weight: bold; -fx-padding: 5px;");
            borderPane.setTop(header);

            Label content = new Label("ID: " + clickedLab.getId() + "\n" + bundle.getString("creator") + clickedLab.getUsername());
            content.setStyle("-fx-padding: 5px;");
            borderPane.setCenter(content);

            popup.getContent().add(borderPane);

            popup.setX(event.getScreenX());
            popup.setY(event.getScreenY());
            popup.setAutoHide(true);
            popup.show(parent.getScene().getWindow());
        });

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), circle);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        parent.getChildren().add(circle);
    }

    public DisplayLabwork getLab(ArrayDeque<DisplayLabwork> collection, int id){
        for (DisplayLabwork lab : collection) {
            if (lab.getId() == id) {
                return lab;
            }
        }
        return null;
    }

    public void removeAllCircles(){
        Pane parent = (Pane) mapView.getParent();
        ArrayList<Object> toRemove = parent.getChildren().stream()
                .filter(el -> el.getClass().equals(Circle.class))
                .collect(Collectors.toCollection(ArrayList<Object>::new));
        for (Object circle: toRemove) {
            parent.getChildren().remove(circle);
        }
    }

    @FXML
    public void handleBackToTable(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Table.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("table", locale);
        loader.setController(new TableController(udpClient, locale));
        loader.setResources(bundle);
        Parent root;
        try {
            root = loader.load();
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene nextScene = new Scene(root);
            primaryStage.setScene(nextScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
