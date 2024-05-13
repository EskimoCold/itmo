package client.network;

import common.collections.LabWork;
import common.commands.Command;
import common.commands.CommandWithElement;
import common.commands.ExecuteScript;
import common.handlers.FileHandler;
import common.handlers.IOHandler;
import common.handlers.PackageParser;
import common.network.Request;
import common.network.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class ClientExecuteScript {
    public static void retrieveCommands(String[] args, UDPClient client) {
        try {
            String scriptName = args[0];

            String content = FileHandler.read(scriptName);

            if (content.contains(scriptName)) {
                IOHandler.println("Script cannot call itself or have an infinite loop");
                return;
            }

            String[] lines = content.split("\n");

            for (int i = 0; i < lines.length; i++) {
                String[] split = lines[i].split("\\s+");
                String commandName = split[0];
                String[] commandArgs = Arrays.copyOfRange(split, 1, split.length);

                Command command = PackageParser.getCommand(commandName);

                if (command == null) {
                    IOHandler.println(commandName + ": this command does not exist");
                    continue;
                }

                if (command instanceof CommandWithElement) {
                    Set<Command> allCommands = PackageParser.getAllCommands();
                    ArrayList<String> commandsNames = new ArrayList<String>();

                    for (Command c : allCommands) {
                        commandsNames.add(c.getName());
                    }

                    ArrayList<String> element = new ArrayList<String>();

                    i++;

                    while (i < lines.length && !commandsNames.contains(lines[i])) {
                        element.add(lines[i]);
                        i++;
                    }

                    i--;

                    try {
                        LabWork lw = new LabWork(element);
                        lw.setId(LabWork.generateId());
                        LabWork.validateWithOutId(lw);
                        Request request = new Request(command, commandArgs, lw);
                        IOHandler.println("Sending command " + command.getName());
                        client.sendRequest(request);
                        Response response = client.getResponse(false);
                        IOHandler.println(response);

                    } catch (Exception e) {
                        IOHandler.println(e.getMessage());
                    }
                } else {
                    if (command instanceof ExecuteScript) {
                        ClientExecuteScript.retrieveCommands(commandArgs, client);
                    } else {
                        IOHandler.println("Sending command " + command.getName());
                        client.sendRequest(new Request(command, commandArgs));
                        Response response = client.getResponse(false);
                        IOHandler.println(response);
                    }
                }
            }

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
        }
    }
}
