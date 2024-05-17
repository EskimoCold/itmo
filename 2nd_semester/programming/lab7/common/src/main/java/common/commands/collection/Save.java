package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.Objects;

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
    public Response execute(String[] args, CollectionHandler collectionHandler, DBHandler dbHandler) {
        dbHandler.removeAllLabs();

        collectionHandler.getCollection()
                .forEach(lab -> dbHandler.createLab(lab, lab.getUsername(), false));

        return new Response(null, "Saved");
    }
}
