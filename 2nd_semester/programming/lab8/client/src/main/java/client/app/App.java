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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class App extends Application {
    private Locale currentLocale = Locale.forLanguageTag("en-AU");
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
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Auth.fxml"), bundle);
        AuthController authController = new AuthController(udpClient, currentLocale);
        fxmlLoader.setController(authController);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        HBox buttonBox = getButtonBox(primaryStage, scene);

        if (root instanceof Pane) {

            ((Pane) root).getChildren().addAll(buttonBox);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void changeLocale(Locale newLocale, Stage stage, Scene scene) {
        if (!newLocale.equals(currentLocale)) {
            currentLocale = newLocale;
            bundle = ResourceBundle.getBundle("auth", currentLocale);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Auth.fxml"), bundle);
            AuthController authController = new AuthController(udpClient, currentLocale);
            fxmlLoader.setController(authController);

            try {
                Parent root = fxmlLoader.load();

                HBox buttonBox = getButtonBox(stage, scene);

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

    private HBox getButtonBox(Stage stage, Scene scene) {
        Button englishButton = new Button("English");
        englishButton.setOnAction(event -> changeLocale(Locale.forLanguageTag("en-AU"), stage, scene));

        Button russianButton = new Button("Русский");
        russianButton.setOnAction(event -> changeLocale(new Locale("ru_RU"), stage, scene));

        HBox buttonBox = new HBox(10, englishButton, russianButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        return buttonBox;
    }
}
