package client.gui.controllers;

import client.network.UDPClient;
import common.network.User;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthController {
    Locale locale;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private StackPane popupPane;
    private UDPClient udpClient;

    public AuthController(UDPClient udpClient, Locale locale) {
        this.locale = locale;
        this.udpClient = udpClient;
    }

    @FXML
    public void showErrorPopup() {
        popupPane.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(event -> popupPane.setVisible(false));
        visiblePause.play();
    }


    @FXML
    public void showTable(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Table.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("table", locale);
//        loader.setController(new TableController(_tcpClient, locale));
//        loader.setResources(bundle);
//        Parent root;
//        try {
//            root = loader.load();
//            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene nextScene = new Scene(root);
//            primaryStage.setScene(nextScene);
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    public void handleButton(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        User user = new User(login, password);
    }
}
