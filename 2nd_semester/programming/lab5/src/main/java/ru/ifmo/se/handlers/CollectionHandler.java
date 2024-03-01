package ru.ifmo.se.handlers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.Getter;
import lombok.Setter;
import ru.ifmo.se.collections.Coordinates;
import ru.ifmo.se.collections.Discipline;
import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.exceptions.InvalidParameterException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CollectionHandler {
    private final LocalDateTime dateCreated = LocalDateTime.now();
    @Getter
    @Setter
    private ArrayDeque<LabWork> collection = new ArrayDeque<LabWork>();
    private final String filepath = System.getenv("LAB5_FILEPATH");

    public CollectionHandler() {
        try {
            String xml = FileHandler.read(filepath);

            List<String> labsXml = new ArrayList<String>();
            Matcher m = Pattern.compile("<LabWork>(.*?)</LabWork>", Pattern.DOTALL).matcher(xml);

            while (m.find()) {
                labsXml.add(m.group());
            }

            for (String labXml : labsXml) {
                try {
                    JAXBContext context = JAXBContext.newInstance(LabWork.class, Coordinates.class, Discipline.class);
                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    StringReader reader = new StringReader(labXml);
                    LabWork labWork = (LabWork) unmarshaller.unmarshal(reader);

                    LabWork.validate(labWork);
                    this.collection.add(labWork);

                } catch (Exception e) {
                    IOHandler.println(e.getMessage());
                    IOHandler.println("Skipping this LabWork...");
                }
            }

        } catch (Exception e) {
            IOHandler.println(e.getMessage());
        }
    }

    public String info() {
        return "Collection " + this.collection.getClass().getSimpleName() + ".\n" +
                "Containing " + this.collection.size() + " of object LabWork.\n" +
                "Collection created on " + dateCreated + ".\n" +
                "Collection stored at " + filepath + ".\n";
    }
}
