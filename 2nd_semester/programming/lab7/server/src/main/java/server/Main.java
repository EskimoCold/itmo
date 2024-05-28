package server;

import common.commands.collection.Save;
import server.handlers.CollectionHandler;
import server.handlers.DBHandler;
import server.network.UDPServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("logger");

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream("server.properties");
            Properties prop = System.getProperties();
            prop.load(resourceAsStream);
            int port = Integer.parseInt(prop.getProperty("SERVER_PORT"));

            final String jdbcUrl = System.getenv("DATABASE_URL");
            final String username = System.getenv("DATABASE_USERNAME");
            final String password = System.getenv("DATABASE_PASSWORD");

            DBHandler dbHandler = new DBHandler(jdbcUrl, username, password);
            CollectionHandler collectionHandler = new CollectionHandler(dbHandler);
            UDPServer server = new UDPServer(port);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.log(Level.INFO, "Shutdown hook invoked. Saving collection to db...");

                Save saveCommand = new Save();
                saveCommand.setCollectionHandler(collectionHandler);
                saveCommand.execute(new String[]{});
                logger.log(Level.FINE, "Collection was saved");
            }));

            try {
                new Thread(() -> {
                    server.run(collectionHandler, dbHandler);
                }).start();

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