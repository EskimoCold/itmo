package ru.ifmo.se.collections;

import ru.ifmo.se.exceptions.InvalidParameterException;
import ru.ifmo.se.handlers.IOHandler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Discipline {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long selfStudyHours; //Поле может быть null
    private BufferedReader reader;

    public Discipline() {
        IOHandler.println("Input parameters of Discipline:");
        BufferedInputStream inputStream = new BufferedInputStream(System.in);
        this.reader = new BufferedReader(new InputStreamReader(inputStream));

        inputName();
        inputSelfStudyHours();
    }

    public Discipline(String name, Long selfStudyHours) throws InvalidParameterException{
        if (name == null || name.isBlank()) {
            throw new InvalidParameterException("name can't be null");
        }

        if (selfStudyHours == null) {
            throw new InvalidParameterException("selfStudyHours can't be null");
        }

        this.name = name;
        this.selfStudyHours = selfStudyHours;
    }

    private void inputName() {
        IOHandler.print("name parameter of Discipline >> ");

        try {
            String input = this.reader.readLine();

            if (input.isEmpty() || input.isBlank()) {
                throw new InvalidParameterException("name can't be null or contains only whitespaces");
            }

            this.name = input;
        }

        catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputName();
        }
    }

    private void inputSelfStudyHours() {
        IOHandler.print("selfStudyHours parameter of Discipline >> ");

        try {
            String input = this.reader.readLine();

            if (input.isEmpty()) {
                throw new InvalidParameterException("selfStudyHours can't be null");
            }

            this.selfStudyHours = Long.parseLong(input);
        }

        catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputSelfStudyHours();
        }
    }

    @Override
    public String toString() {
        return "Discipline " + this.name + ": " + this.selfStudyHours + " hours";
    }
}
