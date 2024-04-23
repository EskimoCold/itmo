package common.commands;

import common.collections.LabWork;
import common.network.Response;

import java.util.ArrayDeque;

public class PrintAveragePointDesc extends Command {
    @Override
    public String getName() {
        return "average_point_desc";
    }

    @Override
    public String getDescription() {
        return this.getName() + "              - print all average point desc\n";
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection) {
        ArrayDeque<LabWork> sortedCollection = (ArrayDeque<LabWork>) collection.stream().sorted();

        StringBuilder output = new StringBuilder();

        for (LabWork lw: sortedCollection) {
            output.append(lw.getAveragePoint()).append("\n");
        }

        return new Response(output.toString());
    }
}
