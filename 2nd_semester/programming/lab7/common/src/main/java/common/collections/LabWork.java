package common.collections;

import common.network.User;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;
import common.exceptions.InvalidParameterException;
import common.handlers.IOHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

@XmlRootElement(name = "LabWork")
public class LabWork implements Serializable, Comparable<LabWork>{
    @XmlElement
    @Getter
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @XmlElement
    @Getter
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Getter
    @XmlElement
    private Coordinates coordinates; //Поле не может быть null
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @Getter @Setter
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @XmlElement
    @Getter
    private double minimalPoint; //Значение поля должно быть больше 0
    @XmlElement
    @Getter
    private int tunedInWorks;
    @XmlElement
    @Getter
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    @XmlElement
    @Getter
    private Difficulty difficulty; //Поле не может быть null
    @XmlElement
    @Getter
    private Discipline discipline; //Поле может быть null
    @Getter
    private static final List<Long> usedIds = new ArrayList<>();
    @Getter @Setter
    private String username;

    public LabWork() {

    }

    public LabWork(long id) throws InvalidParameterException {
        if (usedIds.contains(id)) {
            throw new InvalidParameterException("this id is already used!");
        }

        this.id = id;
        this.creationDate = LocalDateTime.now();
        this.fillFields();
    }

    public LabWork(Boolean fromFile) {
        if (!fromFile) {
            this.id = generateId();
            this.creationDate = LocalDateTime.now();
            this.fillFields();
        }
    }

    public LabWork(ArrayList<String> elements) throws Exception {
        this.id = generateId();
        this.creationDate = LocalDateTime.now();
        this.name = elements.get(0);
        this.coordinates = new Coordinates(Long.parseLong(elements.get(1)), Long.parseLong(elements.get(2)));
        this.minimalPoint = Double.parseDouble(elements.get(3));
        this.tunedInWorks = Integer.parseInt(elements.get(4));
        this.averagePoint = Double.parseDouble(elements.get(5));
        this.difficulty = Difficulty.parseDifficulty(elements.get(6));
        this.discipline = new Discipline(elements.get(7), Long.parseLong(elements.get(8)));
    }

    public LabWork(long id,
                   String name,
                   Long x, Long y,
                   LocalDateTime creationDate,
                   double minimalPoint,
                   int tunedInWorks,
                   double averagePoint,
                   String difficulty,
                   String disciplineName, Long selfStudyHours,
                   String username) throws InvalidParameterException {
        this.id = id;
        this.name = name;
        this.coordinates = new Coordinates(x, y);
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.tunedInWorks = tunedInWorks;
        this.averagePoint = averagePoint;
        this.difficulty = Difficulty.parseDifficulty(difficulty);
        this.discipline = new Discipline(disciplineName, selfStudyHours);
        this.username = username;

    }

    public void setId(long id) {
        this.id = id;
    }

    public static void addId(long id) {
        usedIds.add(id);
    }

    private void fillFields() {
        IOHandler.println("Input parameters for LabWork:");
        Scanner scanner = new Scanner(System.in);
        this.inputName(scanner);

        this.coordinates = new Coordinates(Boolean.FALSE);

        this.inputMinimalPoint(scanner);
        this.inputTunedInWorks(scanner);
        this.inputAveragePoint(scanner);
        this.inputDifficulty(scanner);

        this.discipline = new Discipline(Boolean.FALSE);
    }

    public static long generateId() {
        Collections.sort(usedIds);

        long i = 1;
        while (usedIds.contains(i)) {
            i++;
        }

        usedIds.add(i);

        return i;
    }

    public static void removeId(long id){
        usedIds.remove(id);
    }

    private void inputName(Scanner scanner) {
        IOHandler.print("name parameter of LabWork >> ");

        try {
            String input = scanner.nextLine();

            if (input.isEmpty() || input.isBlank()) {
                throw new InvalidParameterException("name can't be null or contains only whitespaces");
            }

            this.name = input;
        } catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputName(scanner);
        }
    }

    private void inputMinimalPoint(Scanner scanner) {
        IOHandler.print("minimal point parameter of LabWork >> ");

        try {
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                throw new InvalidParameterException("minimal point can not be null");
            }

            double parsed = Double.parseDouble(input);
            if (parsed <= 0) {
                throw new InvalidParameterException("minimal point must be > 0");
            }

            this.minimalPoint = parsed;

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputMinimalPoint(scanner);
        }
    }

    private void inputTunedInWorks(Scanner scanner) {
        IOHandler.print("tuned in works parameter of LabWork >> ");

        try {
            String input = scanner.nextLine();
            this.tunedInWorks = Integer.parseInt(input);

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputTunedInWorks(scanner);
        }
    }

    private void inputAveragePoint(Scanner scanner) {
        IOHandler.print("average point parameter of LabWork >> ");

        try {
            String input = scanner.nextLine();

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
            inputTunedInWorks(scanner);
        }
    }

    private void inputDifficulty(Scanner scanner) {
        Object[] possibleValues = Difficulty.class.getEnumConstants();
        IOHandler.print("difficulty parameter of LabWork" + Arrays.toString(possibleValues) + " >> ");

        try {
            String input = scanner.nextLine();
            this.difficulty = Difficulty.parseDifficulty(input);

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
            inputDifficulty(scanner);
        }
    }

    public String toXml() {
        String xmlString = null;
        try {
            JAXBContext context = JAXBContext.newInstance(LabWork.class, Coordinates.class, Discipline.class);
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

    public static void validate(LabWork lw) throws InvalidParameterException {
        if (usedIds.contains(lw.getId())) {
            throw new InvalidParameterException("this id is already taken");
        }

        Coordinates.validate(lw.getCoordinates());
        Discipline.validate(lw.getDiscipline());

        if (lw.getName().isEmpty() || lw.getName().isBlank()) {
            throw new InvalidParameterException("name can't be null or contains only whitespaces");
        }

        if (lw.getMinimalPoint() <= 0) {
            throw new InvalidParameterException("minimal point must be > 0");
        }

        if (lw.getAveragePoint() != null && lw.getAveragePoint() <= 0) {
            throw new InvalidParameterException("minimal point must be > 0");
        }
    }

    public static void validateWithOutId(LabWork lw) throws InvalidParameterException {
        Coordinates.validate(lw.getCoordinates());
        Discipline.validate(lw.getDiscipline());

        if (lw.getName().isEmpty() || lw.getName().isBlank()) {
            throw new InvalidParameterException("name can't be null or contains only whitespaces");
        }

        if (lw.getMinimalPoint() <= 0) {
            throw new InvalidParameterException("minimal point must be > 0");
        }

        if (lw.getAveragePoint() != null && lw.getAveragePoint() <= 0) {
            throw new InvalidParameterException("minimal point must be > 0");
        }
    }

    @Override
    public int compareTo(LabWork lw) {
        return Double.compare(lw.getAveragePoint(), this.getAveragePoint());
    }

    @Override
    public String toString() {
        return "id: " + this.id + "\n" +
                "name: " + this.name + "\n" +
                "coordinates: " + this.coordinates.toString() + "\n" +
                "creation date: " + this.creationDate.toString() + "\n" +
                "minimal point: " + this.minimalPoint + "\n" +
                "tuned in works: " + this.tunedInWorks + "\n" +
                "average point: " + this.averagePoint + "\n" +
                "difficulty: " + this.difficulty + "\n" +
                "discipline: " + this.discipline;
    }
}
