package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class RemoveGreater extends CollectionCommand{
    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return this.getName() + " {element}        - remove all elements from collection that is greater than given\n";
    }

    @Override
    public Response execute(String[] args) {
        ArrayList<LabWork> toDelete = this.getCollectionHandler().getCollection().stream()
                .filter(labWork -> Objects.equals(this.getUser().getUsername(), labWork.getUsername()))
                .filter(labWork -> labWork.getAveragePoint() > Double.parseDouble(args[0]))
                .collect(Collectors.toCollection(ArrayList::new));

        for (LabWork lw: toDelete) {
            this.getCollectionHandler().remove(lw);
        }

        return new Response(null, "Removed");
    }
}
