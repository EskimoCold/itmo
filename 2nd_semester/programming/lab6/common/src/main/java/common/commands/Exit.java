package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.network.Response;

import java.util.ArrayDeque;

public class Exit extends Command{
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - exit application without saving\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        System.exit(0);
        return new Response("Exited");
    }
}