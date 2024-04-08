package common.commands;

import common.handlers.CollectionHandler;
import common.network.Response;

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
    public Response execute(CollectionHandler collectionHandler) {
        System.exit(0);
        return new Response("Exited");
    }
}