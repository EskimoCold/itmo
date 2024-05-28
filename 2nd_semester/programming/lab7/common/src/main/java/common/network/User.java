package common.network;

import common.handlers.IOHandler;
import lombok.Data;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@Data
public class User implements Serializable {

    private String username;
    private String password;

    public User(){
        Scanner s = new Scanner(System.in);

        IOHandler.print("Username>>");
        username = s.nextLine();

        try {
            User.validateName(username);
        } catch (Exception e){
            IOHandler.println(e.getMessage());
            return;
        }

        IOHandler.print("Password>>");
        password = s.nextLine();

        try {
            User.validateFilled(password, "Password");
        } catch (Exception e){
            IOHandler.println(e.getMessage());
        }
    }

    public User (String username, String password){
        this.username = username;
        this.password = password;
    }

    private static void validateName(String name) throws InvalidParameterException {
        User.validateFilled(name, "Username");

        if (name.split("\\s+").length != 1){
            throw new InvalidParameterException("Username must not contain whitespaces");
        }
    }

    private static void validateFilled(String parameter, String parameterName) throws InvalidParameterException {
        if (parameter.trim().isEmpty() || parameter.trim().equals("null")){
            throw new InvalidParameterException(parameterName + " parameter of cannot be null");
        }
    }

    @Override
    public String toString() {
        return "User:  " + this.username + " " + this.password;
    }
}
