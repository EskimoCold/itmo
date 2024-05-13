package client;

import client.collections.CommandArgsDTO;
import client.handlers.CommandHandler;
import client.network.ClientExecuteScript;
import client.network.UDPClient;
import common.collections.LabWork;
import common.commands.Command;
import common.commands.CommandWithElement;
import common.commands.ExecuteScript;
import common.commands.Exit;
import common.handlers.IOHandler;
import common.network.Request;
import common.network.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream("client.properties");
            Properties prop = System.getProperties();
            prop.load(resourceAsStream);
            int port = Integer.parseInt(prop.getProperty("port"));

            Scanner scanner = new Scanner(System.in);

            while (true) {
                UDPClient client = new UDPClient(port);
                System.out.println("Client Shell>>");
                String input = scanner.nextLine();

                try {
                    CommandArgsDTO commandWithArgs = CommandHandler.process(input);
                    Command command = commandWithArgs.command();
                    String[] commandArgs = commandWithArgs.args();

                    if (command instanceof Exit) {
                        client.closeConnection();
                        System.exit(0);
                    }

                    Request request;
                    if (command instanceof ExecuteScript) {
                        ClientExecuteScript.retrieveCommands(commandArgs, client);
                    } else {
                        request = new Request(command, commandArgs);

                        if (command instanceof CommandWithElement) {
                            LabWork lab = new LabWork(false);
                            request = new Request(command, commandArgs, lab);
                        }
                        client.sendRequest(request);
                        Response response = client.getResponse(true);
                        IOHandler.println(response);
                    }
                } catch (Exception e) {
                    IOHandler.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            IOHandler.println(e.getMessage());
        }
    }
}

// todo: check if server is up
