package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.ArrayDeque;

public class PrintAveragePointDesc extends CollectionCommand {
    @Override
    public String getName() {
        return "average_point_desc";
    }

    @Override
    public String getDescription() {
        return this.getName() + "              - print all average point desc\n";
    }

    @Override
    public Response execute(String[] args) {
        ArrayDeque<LabWork> sortedCollection = (ArrayDeque<LabWork>) this.getCollectionHandler().getCollection().stream().sorted();

        StringBuilder output = new StringBuilder();

        for (LabWork lw: sortedCollection) {
            output.append(lw.getAveragePoint()).append("\n");
        }

        return new Response(null, output.toString());
    }
}
