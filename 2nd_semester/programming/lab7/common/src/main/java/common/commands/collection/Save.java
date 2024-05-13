package common.commands.collection;

import common.commands.Command;
import common.handlers.CollectionHandler;
import common.network.Response;

public class Save extends CollectionCommand {
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
        return new Response(null, "Saved");
    }
}
