package common.commands;

import common.handlers.CollectionHandler;
import common.handlers.IOHandler;
import common.network.Response;

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
    public Response execute(CollectionHandler collectionHandler) {
        return new Response(collectionHandler.info());
    }
}
