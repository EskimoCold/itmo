package common.collections;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import common.exceptions.InvalidParameterException;
import common.handlers.IOHandler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Scanner;

@XmlRootElement(name = "Discipline")
public class Discipline implements Serializable {
    @XmlElement
    @Getter
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlElement
    @Getter
    private Long selfStudyHours; //Поле может быть null
    public Discipline() {

    }

    public Discipline(Boolean fromFile) {
        if (!fromFile) {
            IOHandler.println("Input parameters of Discipline:");
            Scanner scanner = new Scanner(System.in);
            inputName(scanner);
            inputSelfStudyHours(scanner);
        }
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

    private void inputName(Scanner scanner) {
        IOHandler.print("name parameter of Discipline >> ");

        try {
            String input = scanner.nextLine();

            if (input.isEmpty() || input.isBlank()) {
                throw new InvalidParameterException("name can't be null or contains only whitespaces");
            }

            this.name = input;
        }

        catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputName(scanner);
        }
    }

    private void inputSelfStudyHours(Scanner scanner) {
        IOHandler.print("selfStudyHours parameter of Discipline >> ");

        try {
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                throw new InvalidParameterException("selfStudyHours can't be null");
            }

            this.selfStudyHours = Long.parseLong(input);
        }

        catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputSelfStudyHours(scanner);
        }
    }

    public static void validate(Discipline obj) throws InvalidParameterException {
        if (obj.getName() == null || obj.getName().isBlank()) {
            throw new InvalidParameterException("name can't be null");
        }

        if (obj.getSelfStudyHours() == null) {
            throw new InvalidParameterException("selfStudyHours can't be null");
        }
    }

    @Override
    public String toString() {
        return "Discipline " + this.name + ": " + this.selfStudyHours + " hours";
    }
}
