package client.data;

import common.collections.Discipline;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

public class DisplayDiscipline extends Discipline {
    private final String name;
    private final Long selfStudyHours;

    @Getter
    private transient StringProperty nameProperty;
    @Getter
    private transient LongProperty selfStudyHoursProperty;

    public DisplayDiscipline(Discipline discipline){
        this.name = discipline.getName();
        this.selfStudyHours = discipline.getSelfStudyHours();

        nameProperty = new SimpleStringProperty(name);
        selfStudyHoursProperty = new SimpleLongProperty(selfStudyHours);
    }

    public void bindProperties() {
        nameProperty.bindBidirectional(new SimpleStringProperty(name));
        selfStudyHoursProperty.bindBidirectional(new SimpleLongProperty(selfStudyHours));
    }
}
