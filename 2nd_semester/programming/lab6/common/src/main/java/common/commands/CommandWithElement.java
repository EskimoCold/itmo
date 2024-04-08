package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import lombok.Getter;
import lombok.Setter;

public abstract class CommandWithElement extends Command {
     @Getter
     @Setter
     protected LabWork lab;

     public void executeFromFile(){}
}
