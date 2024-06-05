package client.gui.controllers;

import client.data.DisplayLabwork;
import client.network.UDPClient;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class TableController {
    Locale locale;

    UDPClient udpClient;

    ResourceBundle bundle;

    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button removeGreaterButton;

    @FXML
    private TableView<DisplayLabwork> tableView;

    @FXML
    private TableColumn<DisplayLabwork, String> idColumn;
    @FXML
    private TableColumn<DisplayLabwork, String> nameColumn;
    @FXML
    private TableColumn<DisplayLabwork, String> xColumn;
    @FXML
    private TableColumn<DisplayLabwork, String> yColumn;
    @FXML
    private TableColumn<DisplayLabwork, LocalDateTime> creationDateColumn;
    @FXML
    private TableColumn<DisplayLabwork, String> minimalPointColumn;
    @FXML
    private TableColumn<DisplayLabwork, String> tunedInWorksColumn;
    @FXML
    private TableColumn<DisplayLabwork, String> averagePointColumn;
    @FXML
    private TableColumn<DisplayLabwork, String> difficultyColumn;
    @FXML
    private TableColumn<DisplayLabwork, String> disciplineNameColumn;
    @FXML
    private TableColumn<DisplayLabwork, String> disciplineSelfStudyHoursColumn;
    @FXML
    private TableColumn<DisplayLabwork, String> usernameColumn;

    @FXML
    private HBox bottomHBox;

    public TableController(UDPClient udpClient, Locale locale){
        this.locale = locale;
        this.udpClient = udpClient;
        bundle =  ResourceBundle.getBundle("table", locale);
    }

    public void initialize() {
        Label label = new Label(this.udpClient.getUser().getUsername());
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: 24;");
        bottomHBox.getChildren().add(label);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ArrayDeque<DisplayLabwork> collection = udpClient.loadCollection().stream()
                        .map(DisplayLabwork::new)
                        .collect(Collectors.toCollection(ArrayDeque::new));

                var labs = FXCollections.observableArrayList(collection);

                idColumn.setCellValueFactory(data -> data.getValue().getIdProperty().asString());
                nameColumn.setCellValueFactory(data -> data.getValue().getNameProperty());
                xColumn.setCellValueFactory(data -> data.getValue().getCoordinatesProperty().getValue().getXProperty().asString());
                yColumn.setCellValueFactory(data -> data.getValue().getCoordinatesProperty().getValue().getYProperty().asString());
                creationDateColumn.setCellValueFactory(data -> data.getValue().getCreationDateProperty());
                minimalPointColumn.setCellValueFactory(data -> data.getValue().getMinimalPointProperty().asString());
                tunedInWorksColumn.setCellValueFactory(data -> data.getValue().getTunedInWorksProperty().asString());
                averagePointColumn.setCellValueFactory(data -> data.getValue().getAveragePointProperty().asString());
                difficultyColumn.setCellValueFactory(data -> data.getValue().getDifficultyProperty());
                disciplineNameColumn.setCellValueFactory(data -> data.getValue().getDisciplineProperty().getValue().getNameProperty());
                disciplineSelfStudyHoursColumn.setCellValueFactory(data -> data.getValue().getDisciplineProperty().getValue().getSelfStudyHoursProperty().asString());
                usernameColumn.setCellValueFactory(data -> data.getValue().getUsernameProperty());

                tableView.setItems(labs);
            }
        }, 0, 5000);
    }

    @FXML
    private void handleLogoutButton(ActionEvent event) throws IOException {

    }

    private void changeLocale(Locale newLocale, Stage stage, Scene scene) {
        if (!newLocale.equals(locale)) {
            locale = newLocale;
            ResourceBundle newBundle = ResourceBundle.getBundle("auth", locale);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Auth.fxml"), newBundle);
            AuthController authController = new AuthController(udpClient, locale);
            fxmlLoader.setController(authController);

            try {
                Parent root = fxmlLoader.load();
                HBox buttonBox = gethBox(stage, scene);

                if (root instanceof Pane) {
                    ((Pane) root).getChildren().addAll(buttonBox);
                }

                scene.setRoot(root);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private HBox gethBox(Stage stage, Scene scene) {
        Button englishButton = new Button("English");
        englishButton.setOnAction(event -> changeLocale(Locale.forLanguageTag("en-UK"), stage, scene));

        Button russianButton = new Button("Русский");
        russianButton.setOnAction(event -> changeLocale(new Locale("ru_RU"), stage, scene));

        HBox buttonBox = new HBox(10, englishButton, russianButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        return buttonBox;
    }

    @FXML
    private void handleUpdate(ActionEvent event){

    }

    @FXML
    private void handleVisualizationButton(ActionEvent event) {

    }

    @FXML
    private void handleShuffle() {

    }

    @FXML
    private void handleReorder() {

    }

    @FXML
    private void handleRemoveGreater() {

    }

    @FXML
    private void handleRemoveById() {

    }

    @FXML
    private void handleAddButton() {

    }


}
