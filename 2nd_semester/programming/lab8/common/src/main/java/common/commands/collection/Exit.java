package common.commands.collection;

import common.network.Response;

public class Exit extends CollectionCommand {
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - exit application without saving\n";
    }

    @Override
    public Response execute(String[] args) {
        return new Response(null, "Exited");
    }
}