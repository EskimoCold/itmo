package server.handlers;

import common.collections.LabWork;
import common.handlers.IOHandler;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayDeque;

public class CollectionHandler implements common.handlers.CollectionHandler {
    private final LocalDateTime dateCreated = LocalDateTime.now();
    private ArrayDeque<LabWork> collection = new ArrayDeque<>();
    @Getter @Setter
    private common.handlers.DBHandler dbHandler;
    private final String filepath = System.getenv("LAB5_FILEPATH");
    private final Object lock = new Object();

    public CollectionHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;

        try {
            this.collection = dbHandler.loadCollectionToMemory();
        } catch (Exception e) {
            IOHandler.println(e.getMessage());
        }
    }

    public ArrayDeque<LabWork> getCollection(){
        synchronized (lock) {
            return collection;
        }
    }

    public void setCollection(ArrayDeque<LabWork> collection){
        synchronized (lock) {
            this.collection = collection;
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
