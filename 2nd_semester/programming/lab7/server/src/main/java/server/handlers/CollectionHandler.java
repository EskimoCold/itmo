package server.handlers;

import common.handlers.IOHandler;
import lombok.Getter;
import lombok.Setter;
import common.collections.LabWork;

import java.time.LocalDateTime;
import java.util.ArrayDeque;

public class CollectionHandler implements common.handlers.CollectionHandler {
    private final LocalDateTime dateCreated = LocalDateTime.now();
    @Getter
    @Setter
    private ArrayDeque<LabWork> collection = new ArrayDeque<>();
    private final String filepath = System.getenv("LAB5_FILEPATH");
    private final Object lock = new Object();

    public CollectionHandler(DBHandler dbHandler) {
        try {
            this.collection = dbHandler.loadCollectionToMemory();
        } catch (Exception e) {
            IOHandler.println(e.getMessage());
        }
    }

    public void add(LabWork lw) {
        synchronized (lock) {
            this.collection.add(lw);
        }
    }

    public String info() {
        synchronized (lock) {
            return "Collection " + this.collection.getClass().getSimpleName() + ".\n" +
                    "Containing " + this.collection.size() + " of object LabWork.\n" +
                    "Collection created on " + dateCreated + ".\n" +
                    "Collection stored at " + filepath + ".\n";
        }
    }
}
