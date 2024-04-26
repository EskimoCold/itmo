package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.network.Response;

import java.util.ArrayDeque;

public class Add extends CommandWithElement {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                             - add new LabWork to collection\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        return null;
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, LabWork lab) {
        lab.setId(LabWork.generateId());
        collectionHandler.add(lab);
        return new Response("Added");
    }
}
