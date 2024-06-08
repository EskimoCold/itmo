package client.gui.controllers;

import client.data.DisplayLabwork;
import client.network.UDPClient;
import common.collections.Difficulty;
import common.collections.LabWork;
import common.commands.collection.*;
import common.exceptions.InvalidParameterException;
import common.network.Request;
import common.network.Response;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.*;

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
                try {
                    udpClient.createConnection();
                    ArrayDeque<DisplayLabwork> collection = udpClient.loadCollection().stream()
                            .map(DisplayLabwork::new)
                            .collect(Collectors.toCollection(ArrayDeque::new));
                    udpClient.closeConnection();

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
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }, 0, 5000);
    }

    @FXML
    private void handleLogoutButton(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("auth", locale);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Auth.fxml"));
        loader.setController(new AuthController(udpClient, locale));
        loader.setResources(bundle);
        Parent root;
        try {
            root = loader.load();
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene nextScene = new Scene(root);

            HBox buttonBox = getBox(primaryStage, nextScene);

            if (root instanceof Pane) {
                ((Pane) root).getChildren().addAll(buttonBox);
            }
            primaryStage.setScene(nextScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    private HBox getBox(Stage primaryStage, Scene nextScene) {
        Button englishButton = new Button("English");
        englishButton.setOnAction(pressEvent -> changeLocale(Locale.forLanguageTag("en-UK"), primaryStage, nextScene));

        Button russianButton = new Button("Русский");
        russianButton.setOnAction(pressEvent -> changeLocale(new Locale("ru_RU"), primaryStage, nextScene));

        HBox buttonBox = new HBox(10, englishButton, russianButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        return buttonBox;
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
        HBox buttonBox = getBox(stage, scene);
        return buttonBox;
    }

    @FXML
    private void handleUpdate(ActionEvent event){
        Dialog<LabWork> dialog = new Dialog<>();
        dialog.setTitle(bundle.getString("addButtonText"));
        dialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField nameField = new TextField();
        nameField.setPromptText(bundle.getString("name"));
        TextField xCoordField = new TextField();
        xCoordField.setPromptText(bundle.getString("coordinatesX"));
        TextField yCoordField = new TextField();
        yCoordField.setPromptText(bundle.getString("coordinatesY"));
        TextField minPointField = new TextField();
        minPointField.setPromptText(bundle.getString("minPoints"));
        TextField tunedInField = new TextField();
        tunedInField.setPromptText(bundle.getString("tunedIn"));
        TextField averagePointsField = new TextField();
        averagePointsField.setPromptText(bundle.getString("averagePoints"));
        ChoiceBox<Difficulty> difficultyBox = new ChoiceBox<>(FXCollections.observableArrayList(Difficulty.IMPOSSIBLE, Difficulty.INSANE, Difficulty.TERRIBLE, Difficulty.VERY_HARD));
        difficultyBox.getSelectionModel().selectFirst();
        TextField discNameField = new TextField();
        discNameField.setPromptText(bundle.getString("discName"));
        TextField selfStudyField = new TextField();
        selfStudyField.setPromptText(bundle.getString("selfStudy"));

        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label(bundle.getString("name") + ":"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label(bundle.getString("coordinates") + ":"), 0, 2);
        grid.add(xCoordField, 1, 2);
        grid.add(yCoordField, 2, 2);
        grid.add(new Label(bundle.getString("minPoints") + ":"), 0, 3);
        grid.add(minPointField, 1, 3);
        grid.add(new Label(bundle.getString("tunedIn") + ":"), 0, 4);
        grid.add(tunedInField, 1, 4);
        grid.add(new Label(bundle.getString("averagePoints") + ":"), 0, 5);
        grid.add(averagePointsField, 1, 5);
        grid.add(new Label(bundle.getString("difficulty") + ":"), 0, 6);
        grid.add(difficultyBox, 1, 6);
        grid.add(new Label(bundle.getString("discipline") + ":"), 0, 7);
        grid.add(discNameField, 1, 8);
        grid.add(selfStudyField, 2, 8);

        ButtonType addButton = new ButtonType(bundle.getString("updateButtonText"), ButtonData.APPLY);
        ButtonType cancelButton = new ButtonType(bundle.getString("cancel"), ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                try {
                    long id = Long.parseLong(idField.getText());
                    String name = nameField.getText();
                    long xCoord = Integer.parseInt(xCoordField.getText());
                    long yCoord = Long.parseLong(yCoordField.getText());
                    double minPoints = Double.parseDouble(minPointField.getText());
                    int tunedIn = Integer.parseInt(tunedInField.getText());
                    double average = Double.parseDouble(averagePointsField.getText());
                    Difficulty difficulty = difficultyBox.getValue();
                    String discName = discNameField.getText();
                    long selfStudy = Long.parseLong(selfStudyField.getText());

                    LabWork labWork = new LabWork(
                            id,
                            name,
                            xCoord, yCoord,
                            LocalDateTime.now(),
                            minPoints,
                            tunedIn,
                            average,
                            difficulty.toString(),
                            discName, selfStudy,
                            this.udpClient.getUser().getUsername()
                    );
                    LabWork.validateWithOutId(labWork);

                    return labWork;

                } catch (NumberFormatException | DateTimeParseException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid input values. Please enter valid input values.");
                    alert.showAndWait();
                    return null;
                } catch (InvalidParameterException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                    return null;
                }
            } else {
                return null;
            }
        });

        Optional<LabWork> result = dialog.showAndWait();
        result.ifPresent(labWork -> {
            CommandWithElement add = new Update();
            add.setUser(this.udpClient.getUser());
            this.udpClient.createConnection();
            this.udpClient.sendRequest(new Request(this.udpClient.getUser(), add, new String[]{idField.getText()}, labWork));
            Response response = this.udpClient.getResponse(true);

            if (response.getObj() != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFO");
                alert.setHeaderText(null);
                alert.setContentText(response.getObj().toString());
                alert.showAndWait();
            }
        });
    }

    @FXML
    private void handleVisualizationButton(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Map.fxml"));
        ResourceBundle newBundle = ResourceBundle.getBundle("visualization", locale);
        loader.setController(new VisualizationController(udpClient, locale));
        loader.setResources(newBundle);
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

    @FXML
    private void handleCount() {
        TextInputDialog value = new TextInputDialog();
        value.setHeaderText(null);
        value.setContentText(bundle.getString("countPrompt"));
        Optional<String> res = value.showAndWait();

        if (res.isEmpty() || res.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("countPrompt"));
            alert.showAndWait();
            return;
        }

        CollectionCommand removeById = new CountGreaterThanTunedInWorks();
        removeById.setUser(this.udpClient.getUser());
        udpClient.createConnection();
        udpClient.sendRequest(new Request(this.udpClient.getUser(), removeById, new String[]{res.get()}));
        Response response = udpClient.getResponse(true);

        if (response.getObj() != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFO");
            alert.setHeaderText(null);
            alert.setContentText(">> " + response.getObj().toString());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleClear() {
        CollectionCommand clear = new Clear();
        clear.setUser(this.udpClient.getUser());
        udpClient.createConnection();
        udpClient.sendRequest(new Request(this.udpClient.getUser(), clear, new String[]{}));
        Response response = udpClient.getResponse(true);

        if (response.getObj() != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFO");
            alert.setHeaderText(null);
            alert.setContentText(response.getObj().toString());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleRemoveGreater() {
        TextInputDialog toRemove = new TextInputDialog();
        toRemove.setHeaderText(null);
        toRemove.setContentText(bundle.getString("greaterPrompt"));
        Optional<String> res = toRemove.showAndWait();

        if (res.isEmpty() || res.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("greaterPrompt"));
            alert.showAndWait();
            return;
        }

        CollectionCommand removeById = new RemoveGreater();
        removeById.setUser(this.udpClient.getUser());
        udpClient.createConnection();
        udpClient.sendRequest(new Request(this.udpClient.getUser(), removeById, new String[]{res.get()}));
        Response response = udpClient.getResponse(true);

        if (response.getObj() != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFO");
            alert.setHeaderText(null);
            alert.setContentText(response.getObj().toString());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleRemoveById() {
        TextInputDialog toRemove = new TextInputDialog();
        toRemove.setHeaderText(null);
        toRemove.setContentText(bundle.getString("idPrompt"));
        Optional<String> res = toRemove.showAndWait();

        if (res.isEmpty() || res.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("idPrompt"));
            alert.showAndWait();
            return;
        }

        CollectionCommand removeById = new RemoveById();
        removeById.setUser(this.udpClient.getUser());
        udpClient.createConnection();
        udpClient.sendRequest(new Request(this.udpClient.getUser(), removeById, new String[]{res.get()}));
        Response response = udpClient.getResponse(true);

        if (response.getObj() != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFO");
            alert.setHeaderText(null);
            alert.setContentText(response.getObj().toString());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddButton() {
        Dialog<LabWork> dialog = new Dialog<>();
        dialog.setTitle(bundle.getString("addButtonText"));
        dialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText(bundle.getString("name"));
        TextField xCoordField = new TextField();
        xCoordField.setPromptText(bundle.getString("coordinatesX"));
        TextField yCoordField = new TextField();
        yCoordField.setPromptText(bundle.getString("coordinatesY"));
        TextField minPointField = new TextField();
        minPointField.setPromptText(bundle.getString("minPoints"));
        TextField tunedInField = new TextField();
        tunedInField.setPromptText(bundle.getString("tunedIn"));
        TextField averagePointsField = new TextField();
        averagePointsField.setPromptText(bundle.getString("averagePoints"));
        ChoiceBox<Difficulty> difficultyBox = new ChoiceBox<>(FXCollections.observableArrayList(Difficulty.IMPOSSIBLE, Difficulty.INSANE, Difficulty.TERRIBLE, Difficulty.VERY_HARD));
        difficultyBox.getSelectionModel().selectFirst();
        TextField discNameField = new TextField();
        discNameField.setPromptText(bundle.getString("discName"));
        TextField selfStudyField = new TextField();
        selfStudyField.setPromptText(bundle.getString("selfStudy"));

        grid.add(new Label(bundle.getString("name") + ":"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label(bundle.getString("coordinates") + ":"), 0, 1);
        grid.add(xCoordField, 1, 1);
        grid.add(yCoordField, 2, 1);
        grid.add(new Label(bundle.getString("minPoints") + ":"), 0, 2);
        grid.add(minPointField, 1, 2);
        grid.add(new Label(bundle.getString("tunedIn") + ":"), 0, 3);
        grid.add(tunedInField, 1, 3);
        grid.add(new Label(bundle.getString("averagePoints") + ":"), 0, 4);
        grid.add(averagePointsField, 1, 4);
        grid.add(new Label(bundle.getString("difficulty") + ":"), 0, 5);
        grid.add(difficultyBox, 1, 5);
        grid.add(new Label(bundle.getString("discipline") + ":"), 0, 6);
        grid.add(discNameField, 1, 6);
        grid.add(selfStudyField, 2, 6);

        ButtonType addButton = new ButtonType(bundle.getString("addButtonText"), ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType(bundle.getString("cancel"), ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                try {
                    String name = nameField.getText();
                    long xCoord = Integer.parseInt(xCoordField.getText());
                    long yCoord = Long.parseLong(yCoordField.getText());
                    double minPoints = Double.parseDouble(minPointField.getText());
                    int tunedIn = Integer.parseInt(tunedInField.getText());
                    double average = Double.parseDouble(averagePointsField.getText());
                    Difficulty difficulty = difficultyBox.getValue();
                    String discName = discNameField.getText();
                    long selfStudy = Long.parseLong(selfStudyField.getText());

                    LabWork labWork = new LabWork(
                            0,
                            name,
                            xCoord, yCoord,
                            LocalDateTime.now(),
                            minPoints,
                            tunedIn,
                            average,
                            difficulty.toString(),
                            discName, selfStudy,
                            this.udpClient.getUser().getUsername()
                    );
                    LabWork.validateWithOutId(labWork);

                    return labWork;

                } catch (NumberFormatException | DateTimeParseException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid input values. Please enter valid input values.");
                    alert.showAndWait();
                    return null;
                } catch (InvalidParameterException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                    return null;
                }
            } else {
                return null;
            }
        });

        Optional<LabWork> result = dialog.showAndWait();
        result.ifPresent(labWork -> {
            CommandWithElement add = new Add();
            add.setUser(this.udpClient.getUser());
            this.udpClient.createConnection();
            this.udpClient.sendRequest(new Request(this.udpClient.getUser(), add, new String[]{}, labWork));
            Response response = this.udpClient.getResponse(true);

            if (response.getObj() != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFO");
                alert.setHeaderText(null);
                alert.setContentText(response.getObj().toString());
                alert.showAndWait();
            }
        });
    }

    @FXML
    private void handleAddIfMinButton() {
        Dialog<LabWork> dialog = new Dialog<>();
        dialog.setTitle(bundle.getString("addButtonText"));
        dialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText(bundle.getString("name"));
        TextField xCoordField = new TextField();
        xCoordField.setPromptText(bundle.getString("coordinatesX"));
        TextField yCoordField = new TextField();
        yCoordField.setPromptText(bundle.getString("coordinatesY"));
        TextField minPointField = new TextField();
        minPointField.setPromptText(bundle.getString("minPoints"));
        TextField tunedInField = new TextField();
        tunedInField.setPromptText(bundle.getString("tunedIn"));
        TextField averagePointsField = new TextField();
        averagePointsField.setPromptText(bundle.getString("averagePoints"));
        ChoiceBox<Difficulty> difficultyBox = new ChoiceBox<>(FXCollections.observableArrayList(Difficulty.IMPOSSIBLE, Difficulty.INSANE, Difficulty.TERRIBLE, Difficulty.VERY_HARD));
        difficultyBox.getSelectionModel().selectFirst();
        TextField discNameField = new TextField();
        discNameField.setPromptText(bundle.getString("discName"));
        TextField selfStudyField = new TextField();
        selfStudyField.setPromptText(bundle.getString("selfStudy"));

        grid.add(new Label(bundle.getString("name") + ":"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label(bundle.getString("coordinates") + ":"), 0, 1);
        grid.add(xCoordField, 1, 1);
        grid.add(yCoordField, 2, 1);
        grid.add(new Label(bundle.getString("minPoints") + ":"), 0, 2);
        grid.add(minPointField, 1, 2);
        grid.add(new Label(bundle.getString("tunedIn") + ":"), 0, 3);
        grid.add(tunedInField, 1, 3);
        grid.add(new Label(bundle.getString("averagePoints") + ":"), 0, 4);
        grid.add(averagePointsField, 1, 4);
        grid.add(new Label(bundle.getString("difficulty") + ":"), 0, 5);
        grid.add(difficultyBox, 1, 5);
        grid.add(new Label(bundle.getString("discipline") + ":"), 0, 6);
        grid.add(discNameField, 1, 6);
        grid.add(selfStudyField, 2, 6);

        ButtonType addButton = new ButtonType(bundle.getString("addButtonText"), ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType(bundle.getString("cancel"), ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                try {
                    String name = nameField.getText();
                    long xCoord = Integer.parseInt(xCoordField.getText());
                    long yCoord = Long.parseLong(yCoordField.getText());
                    double minPoints = Double.parseDouble(minPointField.getText());
                    int tunedIn = Integer.parseInt(tunedInField.getText());
                    double average = Double.parseDouble(averagePointsField.getText());
                    Difficulty difficulty = difficultyBox.getValue();
                    String discName = discNameField.getText();
                    long selfStudy = Long.parseLong(selfStudyField.getText());

                    LabWork labWork = new LabWork(
                            0,
                            name,
                            xCoord, yCoord,
                            LocalDateTime.now(),
                            minPoints,
                            tunedIn,
                            average,
                            difficulty.toString(),
                            discName, selfStudy,
                            this.udpClient.getUser().getUsername()
                    );
                    LabWork.validateWithOutId(labWork);

                    return labWork;

                } catch (NumberFormatException | DateTimeParseException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid input values. Please enter valid input values.");
                    alert.showAndWait();
                    return null;
                } catch (InvalidParameterException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                    return null;
                }
            } else {
                return null;
            }
        });

        Optional<LabWork> result = dialog.showAndWait();
        result.ifPresent(labWork -> {
            CommandWithElement add = new AddIfMin();
            add.setUser(this.udpClient.getUser());
            this.udpClient.createConnection();
            this.udpClient.sendRequest(new Request(this.udpClient.getUser(), add, new String[]{}, labWork));
            Response response = this.udpClient.getResponse(true);

            if (response.getObj() != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFO");
                alert.setHeaderText(null);
                alert.setContentText(response.getObj().toString());
                alert.showAndWait();
            }
        });
    }
}
