package client.gui.controllers;

import client.network.UDPClient;
import common.commands.auth.AuthCommand;
import common.commands.auth.Login;
import common.commands.auth.Register;
import common.network.Request;
import common.network.Response;
import common.network.User;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
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
    @FXML
    private StackPane popupPaneRegister;
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
    public void showErrorPopupRegister() {
        popupPaneRegister.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(event -> popupPaneRegister.setVisible(false));
        visiblePause.play();
    }


    @FXML
    public void showTable(ActionEvent event){
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

    @FXML
    public void handleButton(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        User user = new User(login, password);

        if (event.getSource() == loginButton) {
            AuthCommand loginCommand = new Login();
            loginCommand.setUser(user);
            Request request = new Request(user, loginCommand, new String[]{});

            this.udpClient.createConnection();
            this.udpClient.sendRequest(request);
            Response response = this.udpClient.getResponse(true);

            if ((boolean) response.getObj()) {
                showTable(event);
            } else {
                showErrorPopup();
            }
        }

        if (event.getSource() == registerButton) {
            AuthCommand registerCommand = new Register();
            registerCommand.setUser(user);
            Request request = new Request(user, registerCommand, new String[]{});

            this.udpClient.createConnection();
            this.udpClient.sendRequest(request);
            Response response = this.udpClient.getResponse(true);

            if ((int) response.getObj() == 0) {
                showTable(event);
            } else {
                showErrorPopupRegister();
            }
        }
    }
}
