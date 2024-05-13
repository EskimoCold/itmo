package common.commands.auth;

import common.exceptions.UserException;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;
import common.network.User;

public class Login extends AuthCommand {
    @Override
    public String getName() {
        return "login";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        String info;
        User user = DBHandler.checkUserPresence(this.getUser());

        if (user != null){
            try{
                if(DBHandler.checkUserPassword(this.getUser())){
                    info = "Found user " + this.getUser().getUsername();
                } else {
                    info = "Passwords does not match";
                }
            } catch (UserException e){
                info = e.getMessage();
            }
        } else {
            info = "User does not exist";
        }

        return new Response(this.getUser(), info);
    }
}
