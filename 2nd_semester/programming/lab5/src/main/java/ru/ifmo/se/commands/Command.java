package ru.ifmo.se.commands;

import ru.ifmo.se.handlers.CollectionHandler;

public interface Command {
    String getName();
    String getDescription();
    void execute(CollectionHandler collectionHandler, String[] args);
}
