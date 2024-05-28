package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Objects;

public class RemoveById extends CollectionCommand {
    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return this.getName() + " id                 - remove element by id\n";
    }

    @Override
    public Response execute(String[] args) {
        ArrayDeque<LabWork> collection = this.getCollectionHandler().getCollection();

        try {
            long targetId = Integer.parseInt(args[0]);

            boolean shouldDelete = collection.stream()
                    .anyMatch(lw -> (Objects.equals(this.getUser().getUsername(), lw.getUsername())) && lw.getId() == targetId);

            if (!shouldDelete) {
                return new Response(null, "Labwork with this id is not present");
            }

            try {
                LabWork toDelete = collection.stream()
                        .filter(lab -> Objects.equals(this.getUser().getUsername(), lab.getUsername()))
                        .filter(lw -> lw.getId() == targetId)
                        .findFirst()
                        .get();

                this.getCollectionHandler().remove(toDelete);
                this.getCollectionHandler().getDbHandler().removeLab(toDelete);

                return new Response(null, "Removed");

            } catch (NoSuchElementException e) {
                return new Response(null, "Labwork with this id is not present");
            }

        } catch (NumberFormatException e) {
            return new Response(null, "Invalid id provided!");
        }
    }
}
