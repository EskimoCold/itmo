package common.commands;

import common.collections.LabWork;
import common.network.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;

public abstract class CommandWithElement extends Command {
     abstract public Response execute(ArrayDeque<LabWork> collection, LabWork lab);
}
