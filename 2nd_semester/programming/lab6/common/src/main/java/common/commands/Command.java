package common.commands;

import common.handlers.CollectionHandler;
import common.network.Response;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.io.Serial;
import java.io.Serializable;

public abstract class Command implements Serializable {
    @Getter
    @Setter
    protected String[] args = null;
    abstract public String getName();

    abstract public String getDescription();

    abstract public Response execute(CollectionHandler collectionHandler);
}
