package common.commands.auth;

import common.exceptions.UserException;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;
import common.network.User;

public class Register extends AuthCommand{
    @Override
    public String getName() {
        return "register";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, DBHandler dbHandler) {
        String info;
        User user = null;

        if(dbHandler.checkUserPresence(this.getUser()) != null){
            info = "User already exists";
        } else {
            try{
                user = dbHandler.createUser(this.getUser());

                if(user != null){
                    info = "User successfully created";
                } else {
                    info = "SQL error";
                }
            } catch (UserException e){
                info = e.getMessage();
            }
        }

        return new Response(user, info);
    }
}
