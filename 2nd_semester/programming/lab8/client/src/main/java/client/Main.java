package client;

import client.app.App;
import client.collections.CommandArgsDTO;
import client.handlers.CommandHandler;
import client.network.ClientExecuteScript;
import client.network.UDPClient;
import common.collections.LabWork;
import common.commands.Command;
import common.commands.auth.AuthCommand;
import common.commands.collection.CollectionCommand;
import common.commands.collection.CommandWithElement;
import common.commands.collection.ExecuteScript;
import common.commands.collection.Exit;
import common.exceptions.NotLoggedInException;
import common.handlers.IOHandler;
import common.network.Request;
import common.network.Response;
import common.network.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App.launch(App.class, args);

//        try {
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            InputStream resourceAsStream = classLoader.getResourceAsStream("client.properties");
//            Properties prop = System.getProperties();
//            prop.load(resourceAsStream);
//            int port = Integer.parseInt(prop.getProperty("CLIENT_PORT"));
//
//            Scanner scanner = new Scanner(System.in);
//            UDPClient client = new UDPClient(port);
//
//            while (true) {
//                System.out.println("Client Shell>>");
//                String input = scanner.nextLine();
//
//                try {
//                    CommandArgsDTO commandWithArgs = CommandHandler.process(input);
//                    Command command = commandWithArgs.command();
//                    String[] commandArgs = commandWithArgs.args();
//
//                    if (command instanceof Exit) {
//                        if (client.getSocket() != null) {
//                            client.closeConnection();
//                        }
//                        System.exit(0);
//                    }
//
//                    client.createConnection();
//
//                    Request request;
//                    if (command instanceof CollectionCommand && client.getUser() != null) {
//                        ((CollectionCommand) command).setUser(client.getUser());
//
//                        if (command instanceof ExecuteScript) {
//                            ClientExecuteScript.retrieveCommands(commandArgs, client);
//                        } else {
//                            if (command instanceof CommandWithElement) {
//                                LabWork lab = new LabWork(false);
//                                lab.setUsername(client.getUser().getUsername());
//                                request = new Request(null, command, commandArgs, lab);
//                            } else {
//                                request = new Request(null, command, commandArgs);
//                            }
//
//                            client.sendRequest(request);
//                            Response response = client.getResponse(true);
//                            IOHandler.println(response);
//                        }
//
//                    } else if (command instanceof AuthCommand) {
//                        User user = new User();
//                        ((AuthCommand) command).setUser(user);
//                        request = new Request(null, command, commandArgs);
//
//                        client.createConnection();
//                        client.sendRequest(request);
//                        Response response = client.getResponse(true);
//                        IOHandler.println(response);
//
//                    } else {
//                        throw new NotLoggedInException("You haven't logged in yet! Type login or register!");
//                    }
//
//                } catch (Exception e) {
//                    IOHandler.println(e.getClass().getSimpleName() + ": " + e.getMessage());
//                }
//            }
//        } catch (IOException e) {
//            IOHandler.println(e.getMessage());
//        }
    }
}
