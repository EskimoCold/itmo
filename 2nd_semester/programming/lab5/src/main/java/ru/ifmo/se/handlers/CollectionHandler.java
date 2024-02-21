package ru.ifmo.se.handlers;

import ru.ifmo.se.collections.LabWork;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Collections;

public class CollectionHandler {
    private final LocalDateTime dateCreated = LocalDateTime.now();
    private ArrayDeque<LabWork> collection = new ArrayDeque<LabWork>();
    private final String filepath = System.getenv("LAB5_FILEPATH");

    public CollectionHandler() {
        String content = FileHandler.read(filepath);
    }

    public String info() {
        return "Collection " + this.collection.getClass().getSimpleName() + ".\n" +
                "Containing " + this.collection.size() + " of object LabWork.\n" +
                "Collection created on " + dateCreated + ".\n" +
                "Collection stored at " + filepath + ".\n";
    }

    public ArrayDeque<LabWork> getCollection() {
        return this.collection;
    }

    public void updateCollection(ArrayDeque<LabWork> collection) {
        this.collection = collection;
    }
}
