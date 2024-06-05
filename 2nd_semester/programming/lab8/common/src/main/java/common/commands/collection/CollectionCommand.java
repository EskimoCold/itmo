package common.commands.collection;

import common.commands.Command;
import common.network.User;
import lombok.Getter;
import lombok.Setter;

public abstract class CollectionCommand extends Command {
    @Getter
    @Setter
    private User user;
}
