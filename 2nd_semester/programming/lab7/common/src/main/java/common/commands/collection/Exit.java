package common.commands.collection;

import common.commands.Command;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.Objects;

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
        DBHandler.removeAllUserLabs(this.getUser());

        collectionHandler.getCollection().stream()
                .filter(lab -> Objects.equals(lab.getUsername(), this.getUser().getUsername()))
                .forEach(lab -> DBHandler.createLab(lab, this.getUser(), false));

        return new Response(null, "Exited");
    }
}