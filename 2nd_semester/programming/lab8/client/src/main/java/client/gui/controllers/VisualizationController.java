package client.gui.controllers;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import client.data.DisplayLabwork;
import client.network.UDPClient;
import common.collections.Discipline;
import common.collections.LabWork;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;

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
        udpClient.createConnection();
        ArrayDeque<DisplayLabwork> collection = udpClient.loadCollection().stream()
                .map(DisplayLabwork::new)
                .collect(Collectors.toCollection(ArrayDeque::new));
        udpClient.closeConnection();

        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("map.png")).toExternalForm());

        setImage(image);

        for (DisplayLabwork labwork : collection) {
            drawCircles(labwork, collection);
        }
    }

    public void setImage(Image image) {
        mapView.setImage(image);
    }

    public void drawCircles(DisplayLabwork person, ArrayDeque<DisplayLabwork> collection) {
        double x = (double) person.getCoordinates().getX();
        double y = (double) person.getCoordinates().getY();

        double viewX = mapView.getLayoutX();
        double viewY = mapView.getLayoutY();

        double circleX = x - viewX;
        double circleY = y - viewY;

        Circle circle = new Circle(circleX, circleY, 10, Color.RED);
        Pane parent = (Pane) mapView.getParent();
        circle.setTranslateZ(100);

        long personId = person.getId();
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

            Label content = new Label("ID: " + clickedLab.getId() + "\n" + bundle.getString("Owner: ") + clickedLab.getUsername());
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
