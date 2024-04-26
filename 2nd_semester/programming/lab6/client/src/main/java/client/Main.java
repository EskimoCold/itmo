package client;

import common.collections.CommandArgsDTO;
import common.collections.LabWork;
import common.commands.Command;
import common.commands.CommandWithElement;
import common.commands.ExecuteScript;
import common.commands.Exit;
import common.handlers.CommandHandler;
import common.handlers.IOHandler;
import common.network.Request;
import common.network.Response;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            UDPClient client = new UDPClient(9000);
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
                    if (command instanceof CommandWithElement) {
                        LabWork lab = new LabWork(false);
                        request = new Request(command, commandArgs, lab);
                    } else {
                         request = new Request(command, commandArgs);
                    }
                    client.sendRequest(request);
                    Response response = client.getResponse(true);
                    IOHandler.println(response);
                }
            } catch (Exception e) {
                IOHandler.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }
}