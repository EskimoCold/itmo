package common.commands;

import common.collections.LabWork;
import common.network.Response;

import java.util.ArrayDeque;

public class Add extends CommandWithElement {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                             - add new LabWork to collection\n";
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection) {
        return null;
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection, LabWork lab) {
        return new Response("Added");
    }
}
