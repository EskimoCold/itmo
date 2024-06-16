package client.gui.controllers;

import client.data.DisplayLabwork;
import client.network.UDPClient;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
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
    private final Map<Long, Circle> circlesMap = new HashMap<>();
    private final Map<String, Color> userColors = new HashMap<>();
    private final List<Color> availableColors = Arrays.asList(
            Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.PURPLE, Color.YELLOW, Color.PINK, Color.BROWN
    );

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
                        udpClient.createConnection();
                        ArrayDeque<DisplayLabwork> collection = udpClient.loadCollection().stream()
                                .map(DisplayLabwork::new)
                                .collect(Collectors.toCollection(ArrayDeque::new));
                        udpClient.closeConnection();

                        updateCircles(collection);

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

    public void updateCircles(ArrayDeque<DisplayLabwork> collection) {
        Pane parent = (Pane) mapView.getParent();

        Set<Long> existingIds = new HashSet<>(circlesMap.keySet());
        Set<Long> newIds = collection.stream().map(DisplayLabwork::getId).collect(Collectors.toSet());

        existingIds.stream()
                .filter(id -> !newIds.contains(id))
                .forEach(id -> {
                    Circle circle = circlesMap.remove(id);
                    parent.getChildren().remove(circle);
                });

        for (DisplayLabwork lab : collection) {
            if (circlesMap.containsKey(lab.getId())) {
                moveCircle(lab, collection);
            } else {
                drawCircle(lab, collection);
            }
        }
    }

    public void drawCircle(DisplayLabwork lab, ArrayDeque<DisplayLabwork> collection) {
        double[] coords = calculateCirclePosition(lab, collection);
        double circleX = coords[0];
        double circleY = coords[1];

        double size = lab.getAveragePoint();
        if (size > 30) size = 30;

        Color color = getUserColor(lab.getUsername());
        Circle circle = new Circle(circleX, circleY, size, color);
        Pane parent = (Pane) mapView.getParent();
        circle.setTranslateZ(100);

        long labId = lab.getId();
        circle.setId(Long.toString(labId));

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
        circlesMap.put(lab.getId(), circle);
    }

    public void moveCircle(DisplayLabwork lab, ArrayDeque<DisplayLabwork> collection) {
        double[] coords = calculateCirclePosition(lab, collection);
        double circleX = coords[0];
        double circleY = coords[1];

        Circle circle = circlesMap.get(lab.getId());

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), circle);
        translateTransition.setToX(circleX - circle.getCenterX());
        translateTransition.setToY(circleY - circle.getCenterY());
        translateTransition.play();
    }

    public double[] calculateCirclePosition(DisplayLabwork lab, ArrayDeque<DisplayLabwork> collection) {
        double maxX = collection.stream().max(Comparator.comparing(lw -> lw.getCoordinates().getX())).get().getCoordinates().getX();
        double maxY = collection.stream().max(Comparator.comparing(lw -> lw.getCoordinates().getY())).get().getCoordinates().getY();

        double x = (double) lab.getCoordinates().getX();
        double y = (double) lab.getCoordinates().getY();

        double viewX = mapView.getFitWidth();
        double viewY = mapView.getFitHeight();

        double circleX = x / maxX * viewX;
        double circleY = y / maxY * viewY;

        if (circleX < 0) circleX = 0;
        if (circleY < 0) circleY = 0;
        if (circleX > mapView.getFitWidth()) circleX = mapView.getFitWidth();
        if (circleY > mapView.getFitHeight()) circleY = mapView.getFitHeight();

        return new double[]{circleX, circleY};
    }

    public DisplayLabwork getLab(ArrayDeque<DisplayLabwork> collection, int id){
        for (DisplayLabwork lab : collection) {
            if (lab.getId() == id) {
                return lab;
            }
        }
        return null;
    }

    private Color getUserColor(String username) {
        if (!userColors.containsKey(username)) {
            Color color = availableColors.get(userColors.size() % availableColors.size());
            userColors.put(username, color);
        }
        return userColors.get(username);
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
