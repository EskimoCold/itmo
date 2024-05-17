package common.commands.collection;

import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

public class Info extends CollectionCommand {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - show information about collection\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, DBHandler dbHandler) {
        return new Response(null, collectionHandler.info());
    }
}
