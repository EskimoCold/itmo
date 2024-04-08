package client;

import common.collections.LabWork;
import common.commands.Command;
import common.commands.CommandWithElement;
import common.commands.ExecuteScript;
import common.commands.Exit;
import common.handlers.CommandHandler;
import common.handlers.IOHandler;
import common.network.Response;
import common.network.UDPClient;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UDPClient client = new UDPClient(9000);

        while (true) {
            System.out.println("Client Shell>>");
            String input = scanner.nextLine();

            try {
                Command command = CommandHandler.process(input);

                if (command instanceof Exit) {
                    client.closeConnection();
                    System.exit(0);
                }

                if (command instanceof CommandWithElement) {
                    LabWork lab = new LabWork(false);
                    ((CommandWithElement) command).setLab(lab);
                }

                if (command instanceof ExecuteScript) {
                    ((ExecuteScript) command).retrieveCommands(client);
                } else {
                    client.sendRequest(command);
                    Response response = client.getResponse();
                    IOHandler.println(response);
                }
            } catch (Exception e) {
                IOHandler.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }
}