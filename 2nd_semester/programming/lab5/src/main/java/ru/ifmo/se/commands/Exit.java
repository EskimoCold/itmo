package ru.ifmo.se.commands;

import ru.ifmo.se.handlers.CollectionHandler;

public class Exit implements Command{
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - exit application without saving\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        System.exit(0);
    }
}