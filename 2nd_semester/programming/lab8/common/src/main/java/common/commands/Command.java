package common.commands;

import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public abstract class Command implements Serializable {
    @Setter
    @Getter
    private CollectionHandler collectionHandler = null;

    abstract public String getName();

    abstract public String getDescription();

    abstract public Response execute(String[] args);

    public String toString() {
        return this.getName();
    }
}
