package common.commands;

import common.network.Response;
import common.collections.LabWork;
import common.handlers.IOHandler;

import java.util.ArrayDeque;
import java.util.Optional;

public class Update extends CommandWithElement{
    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return this.getName() + " id {element}             - update collection element id\n";
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection) {
        return null;
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection, LabWork lab) {
        try {
            long targetId = Integer.parseInt(args[0]);

            Optional<LabWork> labWork = collection.stream()
                    .filter(l -> l.getId() == targetId)
                    .findFirst();

            if (labWork.isPresent()) {
                collection.remove(labWork.get());
                lab.setId(targetId);
                collection.add(lab);
                return new Response(collection);
            }

            return new Response(2);

        } catch (NumberFormatException e) {
            return new Response(1);
        }
    }
}
