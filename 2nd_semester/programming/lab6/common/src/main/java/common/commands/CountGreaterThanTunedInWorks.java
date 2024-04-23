package common.commands;

import common.collections.LabWork;
import common.handlers.IOHandler;
import common.network.Response;

import java.util.ArrayDeque;

public class CountGreaterThanTunedInWorks extends CommandWithElement {
    @Override
    public String getName() {
        return "greater_tuned2work";
    }

    @Override
    public String getDescription() {
        return getName() + "              - print count of elements that have a greater tuned in works\n";
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection) {
        return null;
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection, LabWork lab) {
        long count = collection.stream()
                .filter(lw -> lab.getTunedInWorks() > lw.getTunedInWorks())
                .count();

        return new Response("Count: " + count);
    }
}
