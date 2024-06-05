package common.commands.auth;

import common.exceptions.UserException;
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
        boolean loggedIn;
        User user = this.getCollectionHandler().checkUserPresence(this.getUser());

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
                if(this.getCollectionHandler().checkUserPassword(this.getUser())) {
                    loggedIn = true;
                } else {
                    loggedIn = false;
                }
            } catch (UserException e) {
                loggedIn = false;
            }
        } else {
            loggedIn = false;
        }

        return new Response(user, loggedIn);
    }
}
