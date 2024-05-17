package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

public class Add extends CommandWithElement {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                             - add new LabWork to collection\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, DBHandler dbHandler) {
        return null;
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, LabWork lab, DBHandler dbHandler) {
        LabWork labWork = dbHandler.createLab(lab, this.getUser().getUsername(), true);

        if (labWork != null) {
            collectionHandler.add(labWork);
        }

        return new Response(null, "Added");
    }
}
