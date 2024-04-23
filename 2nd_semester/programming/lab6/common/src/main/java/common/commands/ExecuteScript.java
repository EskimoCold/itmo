package common.commands;

import common.collections.LabWork;
import common.handlers.FileHandler;
import common.handlers.IOHandler;
import common.handlers.PackageParser;
import common.network.Request;
import common.network.Response;
import common.network.UDPClient;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Set;


public class ExecuteScript extends Command {
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return getName() + " file_name        - read and execute script from provided file\n";
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection){
        return new Response("Executing commands...");
    }

    public void retrieveCommands(UDPClient client) {
        try {
            String scriptName = this.args[0];

            String content = FileHandler.read(scriptName);

            if (content.contains(scriptName)) {
                IOHandler.println("Script cannot call itself or have an infinite loop");
                return;
            }

            String[] lines = content.split("\n");

            for (int i = 0; i < lines.length; i++) {
                String[] split = lines[i].split("\\s+");
                String commandName = split[0];

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

                    try {
                        LabWork lw = new LabWork(element);
                        LabWork.validate(lw);
                        Request request = new Request(command, lw);
                        client.sendRequest(request);

                    } catch (Exception e) {
                        IOHandler.println(e.getMessage());
                    }
                } else {
                    if (command instanceof ExecuteScript) {
                        ((ExecuteScript) command).retrieveCommands(client);
                    } else {
                        IOHandler.println("Sending command" + command.getName());
                        client.sendRequest(new Request(command));
                    }
                }

            }

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
        }
    }
}
