package ru.ifmo.se.collections;

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
    private static final List<Long> usedIds  = new ArrayList<>();

    public LabWork() {
        this.id = this.generateId();
        this.fillFields();
    }

    private void fillFields() {
        IOHandler.println("Input parameters for LabWork:");

        BufferedInputStream inputStream = new BufferedInputStream(System.in);
        this.reader = new BufferedReader(new InputStreamReader(inputStream));

        this.creationDate = LocalDateTime.now();

        this.coordinates = new Coordinates();
        this.discipline = new Discipline();
    }

    private long generateId() {
        Collections.sort(usedIds);

        long i = 1;
        while (usedIds.contains(i)){
            i++;
        }

        return i;
    }
}
