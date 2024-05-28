package common.commands.auth;

import common.exceptions.UserException;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.handlers.IOHandler;
import common.network.Response;
import common.network.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public Response execute(String[] args) {
        String info;
        User user = this.getCollectionHandler().getDbHandler().checkUserPresence(this.getUser());

        if (user != null) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");

                byte[] encodedPassword = digest.digest(this.getUser().getPassword().getBytes(StandardCharsets.UTF_8));

                StringBuilder hexString = new StringBuilder();
                for (byte b : encodedPassword) {
                    String hex = String.format("%02x", b);
                    hexString.append(hex);
                }

                String password = hexString.toString();

                this.getUser().setPassword(password);

            } catch (NoSuchAlgorithmException e){
                IOHandler.println(e.getMessage());
            }

            try{
                if(this.getCollectionHandler().getDbHandler().checkUserPassword(this.getUser())) {
                    info = "Found user " + this.getUser().getUsername();
                } else {
                    info = "Passwords does not match";
                }
            } catch (UserException e) {
                info = e.getMessage();
            }
        } else {
            info = "User does not exist";
        }

        return new Response(user, info);
    }
}
