package common.commands;

import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.io.Serializable;

public abstract class Command implements Serializable {
    abstract public String getName();

    abstract public String getDescription();

    abstract public Response execute(String[] args, CollectionHandler collectionHandler, DBHandler dbHandler);

    public String toString() {
        return this.getName();
    }
}
