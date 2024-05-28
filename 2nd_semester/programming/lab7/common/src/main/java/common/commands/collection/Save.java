package common.commands.collection;

import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
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
    public Response execute(String[] args) {
        this.getCollectionHandler().getDbHandler().removeAllLabs();

        this.getCollectionHandler().getCollection()
                .forEach(lab -> this.getCollectionHandler().getDbHandler().createLab(lab, lab.getUsername(), false));

        return new Response(null, "Saved");
    }
}
