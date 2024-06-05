package common.collections;

import common.exceptions.InvalidParameterException;
import common.handlers.IOHandler;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import java.io.Serializable;
import java.util.Scanner;

@XmlRootElement(name = "Coordinates")
public class Coordinates implements Serializable {
    @XmlElement
    @Getter
    private Long x; //Поле не может быть null
    @XmlElement
    @Getter
    private Long y; //Поле не может быть null
    public Coordinates() {

    }

    public Coordinates(Boolean fromFile) {
        if (!fromFile) {
            IOHandler.println("Input parameters of Coordinates:");
            Scanner scanner = new Scanner(System.in);
            inputX(scanner);
            inputY(scanner);
        }
    }

    public Coordinates(Long x, Long y) throws InvalidParameterException {
        if (x == null) {
            throw new InvalidParameterException("x can't be null");
        }

        if (y == null) {
            throw new InvalidParameterException("y can't be null");
        }

        this.x = x;
        this.y = y;
    }

    private void inputX(Scanner scanner) {
        IOHandler.print("x parameter of Coordinates >> ");

        try {
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                throw new InvalidParameterException("x can't be null");
            }

            this.x = Long.parseLong(input);
        }

        catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputX(scanner);
        }
    }

    private void inputY(Scanner scanner) {
        IOHandler.print("y parameter of Coordinates >> ");

        try {
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                throw new InvalidParameterException("y can't be null");
            }

            this.y = Long.parseLong(input);
        }

        catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputY(scanner);
        }
    }

    @Override
    public String toString() {
        return "Coordinates(x: " + this.x + ", y: " + this.y + ")";
    }

    public static void validate(Coordinates obj) throws InvalidParameterException {
        if (obj.getX() == null){
            throw new InvalidParameterException("Coordinates x can't be null");
        }

        if (obj.getY() == null) {
            throw new InvalidParameterException("Coordinates y can't be null");
        }
    }
}
