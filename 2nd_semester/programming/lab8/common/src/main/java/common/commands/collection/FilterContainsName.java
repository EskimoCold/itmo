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

        StringBuilder output = new StringBuilder();

        for (LabWork lw: collection) {
            boolean id = String.valueOf(lw.getId()).toLowerCase().contains(substring);
            boolean name = String.valueOf(lw.getName()).toLowerCase().contains(substring);
            boolean x = String.valueOf(lw.getCoordinates().getX()).toLowerCase().contains(substring);
            boolean y = String.valueOf(lw.getCoordinates().getY()).toLowerCase().contains(substring);
            boolean date = String.valueOf(lw.getCreationDate()).toLowerCase().contains(substring);
            boolean minimal = String.valueOf(lw.getMinimalPoint()).toLowerCase().contains(substring);
            boolean tuned = String.valueOf(lw.getTunedInWorks()).toLowerCase().contains(substring);
            boolean average = String.valueOf(lw.getAveragePoint()).toLowerCase().contains(substring);
            boolean difficulty = String.valueOf(lw.getDifficulty()).toLowerCase().contains(substring);
            boolean discpline = String.valueOf(lw.getDiscipline().getName()).toLowerCase().contains(substring);
            boolean selfstudy = String.valueOf(lw.getDiscipline().getSelfStudyHours()).toLowerCase().contains(substring);
            boolean owner = String.valueOf(lw.getUsername()).toLowerCase().contains(substring);

            if (id || name || x || y || date || minimal || tuned || average ||difficulty || discpline || selfstudy || owner) {
                output.append(lw).append("\n\n");
            }
        }

        return new Response(null, output.toString());
    }

}
