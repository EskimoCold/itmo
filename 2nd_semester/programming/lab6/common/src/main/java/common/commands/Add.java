package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.network.Response;

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
    public Response execute(CollectionHandler collectionHandler) {
        collectionHandler.add(this.lab);
        return new Response("Added\n");
    }

    public void executeFromFile(CollectionHandler collectionHandler, LabWork lw, String[] args) {
        collectionHandler.add(lw);
    }
}
