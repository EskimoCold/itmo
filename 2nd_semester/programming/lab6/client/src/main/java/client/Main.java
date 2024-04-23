package client;

import common.collections.LabWork;
import common.commands.Command;
import common.commands.CommandWithElement;
import common.commands.ExecuteScript;
import common.commands.Exit;
import common.handlers.CommandHandler;
import common.handlers.IOHandler;
import common.network.Request;
import common.network.Response;
import common.network.UDPClient;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            UDPClient client = new UDPClient(9000);
            System.out.println("Client Shell>>");
            String input = scanner.nextLine();

            try {
                Command command = CommandHandler.process(input);

                if (command instanceof Exit) {
                    client.closeConnection();
                    System.exit(0);
                }

                if (command instanceof ExecuteScript) {
                    ((ExecuteScript) command).retrieveCommands(client);
                } else {
                    if (command instanceof CommandWithElement) {
                        LabWork lab = new LabWork(false);
                        Request request = new Request(command, lab);
                        client.sendRequest(request);
                    } else {
                        Request request = new Request(command);
                        client.sendRequest(request);
                    }

                    Response response = client.getResponse(true);
                    IOHandler.println(response);
                }
            } catch (Exception e) {
                IOHandler.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }
}