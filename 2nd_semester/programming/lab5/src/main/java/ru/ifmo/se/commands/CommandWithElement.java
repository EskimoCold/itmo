package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;

interface CommandWithElement extends Command {
     void executeFromFile(CollectionHandler collectionHandler, LabWork lw, String[] args);
}
