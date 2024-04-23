package common.commands;

import common.collections.LabWork;
import common.network.Response;

import java.util.ArrayDeque;

public class AddIfMin extends CommandWithElement {
    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getDescription() {
        return this.getName() + " {element}            - add if minimalPoint greater than minimum element\n";
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection) {
        return null;
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection, LabWork lab) {
        boolean shouldAdd = collection.stream()
                .anyMatch(lw -> lab.getAveragePoint() > lw.getAveragePoint());

        return new Response(shouldAdd);
    }
}
