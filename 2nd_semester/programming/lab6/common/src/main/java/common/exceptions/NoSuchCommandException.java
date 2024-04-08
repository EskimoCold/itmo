package common.exceptions;

public class NoSuchCommandException extends Exception{
    public NoSuchCommandException(){
        super("Command was not found");
    }
    public NoSuchCommandException(String message){
        super(message);
    }
}
