package ru.ifmo.se.handlers;

import lombok.Getter;
import lombok.Setter;
import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.XMLManager.*;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class CollectionHandler {
    private final LocalDateTime dateCreated = LocalDateTime.now();
    @Getter
    @Setter
    private ArrayDeque<LabWork> collection = new ArrayDeque<LabWork>();
    private final String filepath = System.getenv("LAB5_FILEPATH");

    public CollectionHandler() {
        try {
            ArrayList<LabWork> labs = XMLReader.read(filepath);

            for (LabWork lw : labs) {
                try {
                    LabWork.validate(lw);
                    this.collection.add(lw);

                } catch (Exception e) {
                    IOHandler.println(e.getMessage());
                    IOHandler.println("Skipping this LabWork...");
                }
            }

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
        }
    }

    public void add(LabWork lw) {
        this.collection.add(lw);
    }

    public String info() {
        return "Collection " + this.collection.getClass().getSimpleName() + ".\n" +
                "Containing " + this.collection.size() + " of object LabWork.\n" +
                "Collection created on " + dateCreated + ".\n" +
                "Collection stored at " + filepath + ".\n";
    }
}
