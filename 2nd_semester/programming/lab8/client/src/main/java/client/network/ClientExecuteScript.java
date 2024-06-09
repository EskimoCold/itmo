package client.network;

import common.collections.LabWork;
import common.commands.Command;
import common.commands.auth.AuthCommand;
import common.commands.collection.CollectionCommand;
import common.commands.collection.CommandWithElement;
import common.commands.collection.ExecuteScript;
import common.handlers.FileHandler;
import common.handlers.IOHandler;
import common.handlers.PackageParser;
import common.network.Request;
import common.network.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class ClientExecuteScript {
    public static ArrayList<Response> retrieveCommands(String[] args, UDPClient client) {
        ArrayList<Response> r = new ArrayList<>();

        try {
            String scriptName = args[0];

            String content = FileHandler.read(scriptName);

            if (content.contains(scriptName)) {
                r.add(new Response(null, "Script cannot call itself or have an infinite loop"));
                return r;
            }

            String[] lines = content.split("\n");

            for (int i = 0; i < lines.length; i++) {
                lines[i] = lines[i].strip();
            }

            for (int i = 0; i < lines.length; i++) {
                String[] split = lines[i].split("\\s+");
                String commandName = split[0];
                String[] commandArgs = Arrays.copyOfRange(split, 1, split.length);

                Command command = PackageParser.getCommand(commandName);

                if (command == null) {
                    r.add(new Response(null, commandName + ": this command does not exist"));
                    continue;
                }

                if (command instanceof AuthCommand) {
                    r.add(new Response(null, commandName + " are not available to use in scripts"));
                }

                if (command instanceof CollectionCommand) {
                    ((CollectionCommand) command).setUser(client.getUser());
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
                        lw.setId(0);
                        LabWork.validateWithOutId(lw);
                        lw.setUsername(client.getUser().getUsername());
                        Request request = new Request(client.getUser(), command, commandArgs, lw);
                        IOHandler.println("Sending command " + command.getName());
                        client.sendRequest(request);
                        Response response = client.getResponse(false);
                        IOHandler.println(response);
                        r.add(response);

                    } catch (Exception e) {
                        e.printStackTrace();
                        r.add(new Response(null, e.getMessage()));
                    }
                } else {
                    if (command instanceof ExecuteScript) {
                        ClientExecuteScript.retrieveCommands(commandArgs, client);
                    } else {
                        IOHandler.println("Sending command " + command.getName());
                        client.sendRequest(new Request(client.getUser(), command, commandArgs));
                        Response response = client.getResponse(false);
                        IOHandler.println(response);
                        r.add(response);
                    }
                }
            }

            return r;

        } catch (Exception e) {
            r.add(new Response(null, e.getMessage()));
        }
        return r;
    }
}
