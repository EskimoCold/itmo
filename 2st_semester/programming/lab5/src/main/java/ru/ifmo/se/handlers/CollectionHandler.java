package ru.ifmo.se.handlers;

import ru.ifmo.se.collections.LabWork;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Collections;

public class CollectionHandler {
    private ArrayDeque<LabWork> collection = new ArrayDeque<LabWork>();
    private final String filepath = System.getenv("LAB5_FILEPATH");

    public CollectionHandler() {
        if (filepath == null) {
            IOHandler.println("There is no environment variable with collection file path. It must be named: <LAB5_FILEPATH>");
            return;
        }

        File file = FileHandler.process(filepath);
    }
}
