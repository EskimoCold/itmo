package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.stream.Collectors;

public class Clear extends CollectionCommand {
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                           - clear the collection\n";
    }

    @Override
    public Response execute(String[] args) {
        ArrayDeque<LabWork> collection = this.getCollectionHandler().getCollection();

        ArrayDeque<LabWork> newCollection = collection.stream()
                .filter(lab -> !Objects.equals(this.getUser().getUsername(), lab.getUsername()))
                .collect(Collectors.toCollection(ArrayDeque::new));

        this.getCollectionHandler().setCollection(newCollection);

        return new Response(null, "Cleared");
    }
}
