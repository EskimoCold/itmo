package common.commands.collection;

import common.commands.Command;
import common.handlers.CollectionHandler;
import common.network.Response;

public class Exit extends CollectionCommand {
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
        return new Response(null, "Exited");
    }
}