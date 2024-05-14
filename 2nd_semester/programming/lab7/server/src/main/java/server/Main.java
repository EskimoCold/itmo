package server;

import common.collections.LabWork;
import common.commands.collection.Save;
import server.handlers.CollectionHandler;
import server.handlers.XMLManager;
import server.network.UDPServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("logger");

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream("server.properties");
            Properties prop = System.getProperties();
            prop.load(resourceAsStream);
            int port = Integer.parseInt(prop.getProperty("SERVER_PORT"));

            CollectionHandler collectionHandler = new CollectionHandler();
            UDPServer server = new UDPServer(port);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.log(Level.INFO, "Shutdown hook invoked. Saving collection to file...");

                // todo: save

            }));

            try {
                new Thread(() -> server.run(collectionHandler)).start();

                while (true) {
                    System.out.println("Server Shell>>");
                    Scanner s = new Scanner(System.in);
                    String input = s.nextLine().trim();
                    String commandName = input.split("\\s+")[0].trim();

                    if (commandName.equals("save")) {
                        System.exit(0);
                    }
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}