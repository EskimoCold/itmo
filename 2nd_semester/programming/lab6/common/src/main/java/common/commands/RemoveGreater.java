package common.commands;

import common.collections.LabWork;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class RemoveGreater extends CommandWithElement{
    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return this.getName() + " {element}        - remove all elements from collection that is greater than given\n";
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection) {
        return null;
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection, LabWork lab) {
        ArrayDeque<LabWork> updatedCollection = collection.stream()
                .filter(labWork -> lab.compareTo(labWork) <= 0)
                .collect(Collectors.toCollection(ArrayDeque::new));

        return new Response(updatedCollection);
    }
}
