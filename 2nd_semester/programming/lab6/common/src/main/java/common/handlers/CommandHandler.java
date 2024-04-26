package common.handlers;

import common.collections.CommandArgsDTO;
import common.commands.Command;
import common.exceptions.InvalidParameterException;
import common.exceptions.NoSuchCommandException;

import java.util.Arrays;

public class CommandHandler {
    public static CommandArgsDTO process(String input) throws NoSuchCommandException {
        String[] args = input.split("\\s+");
        String commandName = args[0].strip();

        if (commandName.isEmpty()){
            throw new NoSuchCommandException("Command cannot be empty!");
        }

        Command command = PackageParser.getCommand(commandName);

        if (command == null || commandName.equalsIgnoreCase("save")) {
            throw new NoSuchCommandException("No such command: " + commandName);
        }

        return new CommandArgsDTO(command, Arrays.copyOfRange(args, 1, args.length));
    }
}
