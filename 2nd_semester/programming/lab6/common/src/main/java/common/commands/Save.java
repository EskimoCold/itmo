package common.commands;

import common.collections.LabWork;
import common.handlers.IOHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Save extends Command {
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - save collection to file\n";
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection) {
        return new Response("Saved");
    }
}
