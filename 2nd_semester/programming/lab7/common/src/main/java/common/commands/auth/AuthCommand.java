package common.commands.auth;

import common.commands.Command;
import common.network.User;
import lombok.Getter;
import lombok.Setter;

public abstract class AuthCommand extends Command {
    @Getter
    @Setter
    private User user;
}
