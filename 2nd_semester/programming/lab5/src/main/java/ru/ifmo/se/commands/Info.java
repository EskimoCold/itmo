package ru.ifmo.se.commands;

import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.IOHandler;

public class Info implements Command {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - show information about collection\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        IOHandler.println(collectionHandler.info());
    }
}
