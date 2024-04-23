package common.commands;

import common.collections.LabWork;
import common.network.Response;

import java.util.ArrayDeque;

public class RemoveHead extends Command{
    @Override
    public String getName() {
        return "remove_first";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                    - remove first element from collection\n";
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection) {
        return new Response("Removed");
    }
}
