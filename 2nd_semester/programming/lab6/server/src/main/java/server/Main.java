package server;

import common.commands.Save;
import common.handlers.*;
import server.network.UDPServer;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("logger");

        CollectionHandler collectionHandler = new CollectionHandler();
        UDPServer server = new UDPServer(9000);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.log(Level.INFO, "Shutdown hook invoked. Saving collection to file...");
            System.out.println("Shutdown hook invoked. Saving collection to file...");

            // TODO: save
            Save saveCommand = new Save();
//            saveCommand.save(collectionHandler);
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
    }
}