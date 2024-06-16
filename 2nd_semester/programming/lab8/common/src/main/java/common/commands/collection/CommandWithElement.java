package common.commands.collection;

import common.collections.LabWork;
import common.network.Response;

public abstract class CommandWithElement extends CollectionCommand {
     public Response execute(String[] args, LabWork lab) {
          return null;
     }
}
