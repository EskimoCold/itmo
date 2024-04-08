package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.IOHandler;
import common.handlers.XMLManager;
import common.network.Response;

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
    public Response execute(CollectionHandler collectionHandler) {
        String savePath = System.getenv("LAB5_FILEPATH");
        ArrayList<LabWork> labs = new ArrayList<LabWork>(collectionHandler.getCollection());

        try {
            XMLManager.XMLWriter.write(labs, savePath);
        } catch (Exception e) {
            IOHandler.println(e.getMessage());
        }

        return new Response("Saved");
    }
}
