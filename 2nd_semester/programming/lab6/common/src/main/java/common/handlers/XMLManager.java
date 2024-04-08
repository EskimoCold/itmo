package common.handlers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import common.collections.Coordinates;
import common.collections.Discipline;
import common.collections.LabWork;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLManager {
    public static class XMLReader {
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
    }

    public static class XMLWriter {
        public static void write(ArrayList<LabWork> labs, String filepath) throws Exception {
            StringBuilder output = new StringBuilder("<labWorks>\n");

            for (LabWork lw : labs) {
                output.append(lw.toXml());
            }

            output.append("</labWorks>");

            FileHandler.save(filepath, output.toString());
        }
    }
}
