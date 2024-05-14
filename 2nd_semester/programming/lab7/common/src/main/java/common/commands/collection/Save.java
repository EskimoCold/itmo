package common.commands.collection;

import common.commands.Command;
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
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        DBHandler.removeAllUserLabs(this.getUser());

        collectionHandler.getCollection().stream()
                .filter(lab -> Objects.equals(lab.getUsername(), this.getUser().getUsername()))
                .forEach(lab -> DBHandler.createLab(lab, this.getUser(), false));

        return new Response(null, "Saved");
    }
}
