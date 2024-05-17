package common.commands.collection;

import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;


public class ExecuteScript extends CollectionCommand {
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return getName() + " file_name        - read and execute script from provided file\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, DBHandler dbHandler){
        return new Response(null, "Executing commands...");
    }
}
