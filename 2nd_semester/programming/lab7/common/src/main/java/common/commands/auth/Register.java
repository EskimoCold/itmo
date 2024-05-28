package common.commands.auth;

import common.exceptions.UserException;
import common.handlers.IOHandler;
import common.network.Response;
import common.network.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public Response execute(String[] args) {
        String info;
        User user = null;

        if (this.getCollectionHandler().getDbHandler().checkUserPresence(this.getUser()) != null) {
            info = "User already exists";
        } else {
            try{
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

                user = this.getCollectionHandler().getDbHandler().createUser(this.getUser());

                if(user != null){
                    info = "User successfully created";
                } else {
                    info = "SQL error";
                }
            } catch (UserException e) {
                info = e.getMessage();
            }
        }

        return new Response(user, info);
    }
}
