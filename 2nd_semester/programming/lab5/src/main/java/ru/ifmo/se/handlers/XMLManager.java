package ru.ifmo.se.handlers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import ru.ifmo.se.collections.Coordinates;
import ru.ifmo.se.collections.Discipline;
import ru.ifmo.se.collections.LabWork;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLManager {
    public static ArrayList<LabWork> read(String filepath) throws Exception {
        ArrayList<LabWork> labs = new ArrayList<LabWork>();

        String xml = FileHandler.read(filepath);

        ArrayList<String> labsXml = new ArrayList<String>();
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
                labs.add(labWork);

            } catch (Exception e) {
                IOHandler.println(e.getMessage());
                IOHandler.println("Skipping this LabWork...");
            }
        }

        return labs;
    }

    public static void write(ArrayList<LabWork> labs, String filepath) throws Exception {
        StringBuilder output = new StringBuilder("<labWorks>\n");

        for (LabWork lw : labs) {
            output.append(lw.toXml());
        }

        output.append("</labWorks>");

        FileHandler.save(filepath, output.toString());
    }
}
