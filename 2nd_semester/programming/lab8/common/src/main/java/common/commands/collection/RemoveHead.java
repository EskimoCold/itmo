package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Objects;

public class RemoveHead extends CollectionCommand {
    @Override
    public String getName() {
        return "remove_first";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                    - remove first element from collection\n";
    }

    @Override
    public Response execute(String[] args) {
        ArrayDeque<LabWork> collection = this.getCollectionHandler().getCollection();

        try {
            LabWork toDelete = collection.stream()
                    .filter(lab -> Objects.equals(this.getUser().getUsername(), lab.getUsername()))
                    .findFirst()
                    .get();

            this.getCollectionHandler().remove(toDelete);

            return new Response(null, "Removed");

        } catch (NoSuchElementException e) {
            return new Response(null, "Labwork is not present");
        }
    }
}
