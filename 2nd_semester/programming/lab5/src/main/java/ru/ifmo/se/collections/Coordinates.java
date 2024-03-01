package ru.ifmo.se.collections;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import ru.ifmo.se.exceptions.InvalidParameterException;
import ru.ifmo.se.handlers.IOHandler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@XmlRootElement(name = "Coordinates")
public class Coordinates {
    @XmlElement @Getter
    private Long x; //Поле не может быть null
    @XmlElement @Getter
    private Long y; //Поле не может быть null
    private BufferedReader reader;

    public Coordinates() {

    }

    public Coordinates(Boolean fromFile) {
        if (!fromFile) {
            IOHandler.println("Input parameters of Coordinates:");
            BufferedInputStream inputStream = new BufferedInputStream(System.in);
            this.reader = new BufferedReader(new InputStreamReader(inputStream));

            inputX();
            inputY();
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

    private void inputX() {
        IOHandler.print("x parameter of Coordinates >> ");

        try {
            String input = this.reader.readLine();

            if (input.isEmpty()) {
                throw new InvalidParameterException("x can't be null");
            }

            this.x = Long.parseLong(input);
        }

        catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputX();
        }
    }

    private void inputY() {
        IOHandler.print("y parameter of Coordinates >> ");

        try {
            String input = this.reader.readLine();

            if (input.isEmpty()) {
                throw new InvalidParameterException("y can't be null");
            }

            this.y = Long.parseLong(input);
        }

        catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputY();
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
