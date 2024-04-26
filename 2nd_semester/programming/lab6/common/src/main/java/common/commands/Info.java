package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.network.Response;

import java.util.ArrayDeque;

public class Info extends Command {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - show information about collection\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        return new Response(collectionHandler.info());
    }
}
