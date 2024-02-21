package ru.ifmo.se.collections;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.w3c.dom.Document;
import ru.ifmo.se.exceptions.InvalidParameterException;
import ru.ifmo.se.handlers.IOHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement
public class LabWork {
    @XmlElement
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @XmlElement
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlElement
    private Coordinates coordinates; //Поле не может быть null
    @XmlElement
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @XmlElement
    private double minimalPoint; //Значение поля должно быть больше 0
    @XmlElement
    private int tunedInWorks;
    @XmlElement
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    @XmlElement
    private Difficulty difficulty; //Поле не может быть null
    @XmlElement
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
        String xmlString = null;
        try {
            JAXBContext context = JAXBContext.newInstance(LabWork.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            marshaller.marshal(this, document);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty("omit-xml-declaration", "yes");
            transformer.setOutputProperty("indent", "yes");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            xmlString = writer.toString();
        } catch (Exception e) {
            IOHandler.println(e.getMessage());
        }
        return xmlString;
    }
}
