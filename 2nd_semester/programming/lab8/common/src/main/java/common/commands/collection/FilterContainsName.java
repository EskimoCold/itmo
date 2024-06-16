package common.commands.collection;

import common.collections.LabWork;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class FilterContainsName extends CollectionCommand {
    @Override
    public String getName() {
        return "filter_by_name";
    }

    @Override
    public String getDescription() {
        return this.getName() + " {substring}      - print all elements contain this substring\n";
    }

    @Override
    public Response execute(String[] args) {
        String substring = args[0].toLowerCase();
        ArrayDeque<LabWork> collection = this.getCollectionHandler().getCollection();

        String output = collection.stream()
                .filter(lw -> lw.getName().toLowerCase().contains(substring))
                .map(LabWork::toString)
                .collect(Collectors.joining("\n"));

        return new Response(null, output);
    }

}
