package common.commands.collection;

import common.collections.LabWork;
import common.commands.Command;
import common.handlers.CollectionHandler;
import common.network.Response;

public abstract class CommandWithElement extends CollectionCommand {
     public Response execute(String[] args, CollectionHandler collectionHandler, LabWork lab) {
          return null;
     }
}
