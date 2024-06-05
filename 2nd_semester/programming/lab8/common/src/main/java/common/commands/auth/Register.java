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
        int code;
        User user = null;

        if (this.getCollectionHandler().checkUserPresence(this.getUser()) != null) {
            code = 1; // "User already exists";
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

                user = this.getCollectionHandler().createUser(this.getUser());

                if(user != null){
                    code = 0; // "User successfully created";
                } else {
                    code = 3; // "SQL error";
                }
            } catch (UserException e) {
                code = 3; // = e.getMessage();
            }
        }

        return new Response(user, code);
    }
}
