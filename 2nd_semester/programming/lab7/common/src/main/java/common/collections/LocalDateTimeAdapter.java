package common.collections;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> implements Serializable {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public LocalDateTime unmarshal(String str) {
        return LocalDateTime.parse(str, dateTimeFormatter);
    }

    @Override
    public String marshal(LocalDateTime ldt) {
        return ldt.format(dateTimeFormatter);
    }
}
