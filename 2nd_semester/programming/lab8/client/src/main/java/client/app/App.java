package client.app;

import client.gui.controllers.AuthController;
import client.network.UDPClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class App extends Application {
    private Locale currentLocale = Locale.forLanguageTag("en-UK");
    private ResourceBundle bundle = ResourceBundle.getBundle("auth", currentLocale);
    private final UDPClient udpClient;

    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("client.properties");
        Properties prop = System.getProperties();
        try {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int port = Integer.parseInt(prop.getProperty("CLIENT_PORT"));
        this.udpClient = new UDPClient(port);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Auth.fxml"), bundle);
        AuthController authController = new AuthController(udpClient, currentLocale);
        fxmlLoader.setController(authController);

    }
}
