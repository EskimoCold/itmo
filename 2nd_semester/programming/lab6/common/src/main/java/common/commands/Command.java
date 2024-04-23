package common.commands;

import common.collections.LabWork;
import common.network.Response;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayDeque;

public abstract class Command implements Serializable {
    @Getter
    @Setter
    protected String[] args = null;
    abstract public String getName();

    abstract public String getDescription();

    abstract public Response execute(ArrayDeque<LabWork> collection);
}
