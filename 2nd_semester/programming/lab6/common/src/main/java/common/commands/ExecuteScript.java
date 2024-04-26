package common.commands;

import common.handlers.CollectionHandler;
import common.network.Response;


public class ExecuteScript extends Command {
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return getName() + " file_name        - read and execute script from provided file\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler){
        return new Response("Executing commands...");
    }
}
