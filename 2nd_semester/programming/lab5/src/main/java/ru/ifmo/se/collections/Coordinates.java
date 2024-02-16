package ru.ifmo.se.collections;

import ru.ifmo.se.exceptions.InvalidParameterException;
import ru.ifmo.se.handlers.IOHandler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Coordinates {
    private Long x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private BufferedReader reader;

    public Coordinates() {
        IOHandler.println("Input parameters of Coordinates:");
        BufferedInputStream inputStream = new BufferedInputStream(System.in);
        this.reader = new BufferedReader(new InputStreamReader(inputStream));

        inputX();
        inputY();
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

    public String toXml(){
        return "\t\t<coordinates>\n" +
                        "\t\t\t<x>" + this.x + "</x>\n" +
                        "\t\t\t<y>" + this.y + "</y>\n" +
                    "\t\t</coordinates>\n";
    }

    @Override
    public String toString() {
        return "Coordinates(x: " + this.x + ", y: " + this.y + ")";
    }
}
