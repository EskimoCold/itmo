package ru.ifmo.se.handlers;

import ru.ifmo.se.commands.Command;

public class CommandHandler {
    public static void process(String input, CollectionHandler collectionHandler, boolean fromFile){
        String[] args = input.split("\\s+");
        String commandName = args[0].strip();

        if (commandName.isEmpty()){
            IOHandler.println("Command cannot be empty!");
            return;
        }

        Command command = PackageParser.getCommand(commandName);

        if (command == null) {
            IOHandler.println("No such command: " + commandName);
            return;
        }

        command.execute(collectionHandler, args);
    }
}
