package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.IOHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Save extends Command {
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - save collection to file\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        return new Response("Saved");
    }
}
