package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.IOHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class FilterContainsName extends Command {
    @Override
    public String getName() {
        return "filter_by_name";
    }

    @Override
    public String getDescription() {
        return this.getName() + " {substring}      - print all elements contain this substring\n";
    }

    @Override
    public Response execute(CollectionHandler collectionHandler) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();
        String substring = args[0].toLowerCase();

        String output = collection.stream()
                .filter(lw -> lw.getName().toLowerCase().contains(substring))
                .map(LabWork::toString)
                .collect(Collectors.joining("\n"));

        return new Response(output);
    }

}
