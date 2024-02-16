package ru.ifmo.se.collections;

import ru.ifmo.se.exceptions.InvalidParameterException;
import ru.ifmo.se.handlers.IOHandler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LabWork {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double minimalPoint; //Значение поля должно быть больше 0
    private int tunedInWorks;
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private Discipline discipline; //Поле может быть null
    private BufferedReader reader;
    private static final List<Long> usedIds = new ArrayList<>();

    public LabWork() {
        this.id = this.generateId();
        this.fillFields();
    }

    private void fillFields() {
        IOHandler.println("Input parameters for LabWork:");

        BufferedInputStream inputStream = new BufferedInputStream(System.in);
        this.reader = new BufferedReader(new InputStreamReader(inputStream));

        this.creationDate = LocalDateTime.now();

        this.inputName();

        this.coordinates = new Coordinates();

        this.inputMinimalPoint();
        this.inputTunedInWorks();
        this.inputAveragePoint();
        this.inputDifficulty();

        this.discipline = new Discipline();
    }

    private long generateId() {
        Collections.sort(usedIds);

        long i = 1;
        while (usedIds.contains(i)) {
            i++;
        }

        return i;
    }

    private void inputName() {
        IOHandler.print("name parameter of LabWork >> ");

        try {
            String input = this.reader.readLine();

            if (input.isEmpty() || input.isBlank()) {
                throw new InvalidParameterException("name can't be null or contains only whitespaces");
            }

            this.name = input;
        } catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputName();
        }
    }

    private void inputMinimalPoint() {
        IOHandler.print("minimal point parameter of LabWork >> ");

        try {
            String input = this.reader.readLine();

            if (input.isEmpty()) {
                throw new InvalidParameterException("name can't be null or contains only whitespaces");
            }

            double parsed = Double.parseDouble(input);
            if (parsed <= 0) {
                throw new InvalidParameterException("minimal point must be > 0");
            }

            this.minimalPoint = parsed;

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputMinimalPoint();
        }
    }

    private void inputTunedInWorks() {
        IOHandler.print("tuned in works parameter of LabWork >> ");

        try {
            String input = this.reader.readLine();
            this.tunedInWorks = Integer.parseInt(input);

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputTunedInWorks();
        }
    }

    private void inputAveragePoint() {
        IOHandler.print("average point parameter of LabWork >> ");

        try {
            String input = this.reader.readLine();

            if (input.isBlank()) {
                this.averagePoint = null;
                return;
            }

            double parsed = Double.parseDouble(input);

            if (parsed <= 0) {
                throw new InvalidParameterException("minimal point must be > 0");
            }

            this.averagePoint = parsed;

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputTunedInWorks();
        }
    }

    private void inputDifficulty() {
        IOHandler.println(Difficulty.values());
        IOHandler.print("difficulty parameter of LabWork >> ");

        try {
            String input = this.reader.readLine();
            this.difficulty = Difficulty.parseDifficulty(input);

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputDifficulty();
        }
    }

    public String toXml() {
        return "<labWork id=\"" + this.id + "\">\n" +
                        "\t<name>" + this.name + "</name>\n" +
                        this.coordinates.toXml() +
                        "\t<creationDate>" + this.creationDate + "</creationDate>\n" +
                        "\t<minimalPoint>" + this.minimalPoint + "</minimalPoint>\n" +
                        "\t<tunedInWorks>" + this.tunedInWorks + "</tunedInWorks>\n" +
                        "\t<averagePoint>" + this.averagePoint + "</averagePoint>\n" +
                        "\t<difficulty>" + this.difficulty + "</difficulty>\n" +
                        this.discipline.toXml() +
                    "</labWork>\n";
    }
}
