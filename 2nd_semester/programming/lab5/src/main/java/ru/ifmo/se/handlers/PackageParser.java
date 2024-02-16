package ru.ifmo.se.handlers;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ru.ifmo.se.commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PackageParser {
    public static Set<Command> getAllCommands() {
        Set<Command> commands = new HashSet<>();

        Reflections reflections = new Reflections("ru.ifmo.se.commands", new SubTypesScanner(false));
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);

        for (Class<?> clazz : allClasses) {
            if (Command.class.isAssignableFrom(clazz) && !clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers())) {
                try {
                    Command command = (Command) clazz.getConstructor().newInstance();
                    commands.add(command);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    IOHandler.println(e.getMessage());
                }
            }
        }

        return commands;
    }

    public static Command getCommand(String commandName) {
        Set<Command> allCommands = getAllCommands();

        for (Command command : allCommands) {
            if (command.getName().equalsIgnoreCase(commandName)) {
                return command;
            }
        }

        return null;
    }
}
