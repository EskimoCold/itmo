package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.IOHandler;
import common.network.Response;

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
    public Response execute(CollectionHandler collectionHandler) {
        long count = collectionHandler.getCollection().stream()
                .filter(lab -> this.lab.getTunedInWorks() > lab.getTunedInWorks())
                .count();

        return new Response("Count: " + count);
    }

    public void executeFromFile(CollectionHandler collectionHandler, LabWork lw, String[] args) {
        int count = 0;
        for (LabWork lab: collectionHandler.getCollection()) {
            if (lw.getTunedInWorks() > lab.getTunedInWorks()) {
                count += 1;
            }
        }

        IOHandler.println(count);
    }
}
