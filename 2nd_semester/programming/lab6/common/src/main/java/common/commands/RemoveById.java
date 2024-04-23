package common.commands;

import common.collections.LabWork;
import common.network.Response;

import java.util.ArrayDeque;

public class RemoveById extends Command{
    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return this.getName() + " id                 - remove element by id\n";
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection) {
        ArrayDeque<LabWork> newCollection = new ArrayDeque<LabWork>();

        try {
            long targetId = Integer.parseInt(args[0]);

            collection.stream()
                    .filter(route -> route.getId() == targetId)
                    .findFirst()
                    .ifPresent(collection::remove);

            return new Response(newCollection);

        } catch (NumberFormatException e) {
            return new Response(1);
        }
    }
}
