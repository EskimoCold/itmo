package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayDeque;

public abstract class Command implements Serializable {
    abstract public String getName();

    abstract public String getDescription();

    abstract public Response execute(String[] args, CollectionHandler collectionHandler, DBHandler dbHandler);

    public String toString() {
        return this.getName();
    }
}
