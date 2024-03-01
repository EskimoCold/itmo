package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.FileHandler;
import ru.ifmo.se.handlers.IOHandler;
import ru.ifmo.se.handlers.PackageParser;

import java.util.ArrayList;
import java.util.Objects;


public class ExecuteScript implements Command{
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return getName() + " file_name        - read and execute script from provided file\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        try {
            String scriptName = args[1];

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
                    int stop = i + 10;
                    ArrayList<String> lineArgs = new ArrayList<String>();

                    for (int j = i; j <= stop; j++) {
                        i++;
                        lineArgs.add(lines[i]);
                    }

                    try {
                        LabWork lw = new LabWork(lineArgs);
                        LabWork.validate(lw);
                        ((CommandWithElement) command).executeFromFile(collectionHandler, lw);

                    } catch (Exception e) {
                        IOHandler.println(e.getMessage());
                    }

                } else {
                    String[] lineArgs = new String[split.length - 1];

                    for (int j = 1; j < split.length - 1; j++) {
                        if (!Objects.equals(split[j], commandName)) {
                            lineArgs[j - 1] = split[j];
                        }
                    }

                    command.execute(collectionHandler, lineArgs);
                }
            }

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
        }
    }
}
