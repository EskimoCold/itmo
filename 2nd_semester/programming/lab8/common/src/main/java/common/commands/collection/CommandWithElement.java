package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public abstract class CommandWithElement extends CollectionCommand {
     public Response execute(String[] args, LabWork lab) {
          return null;
     }
}
