package client.data;

import common.collections.*;
import javafx.beans.property.*;
import lombok.Getter;

import java.time.LocalDateTime;

public class DisplayLabwork extends LabWork {
    @Getter
    private final Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    @Getter
    private final DisplayCoordinates coordinates; //Поле не может быть null
    private final LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final double minimalPoint; //Значение поля должно быть больше 0
    private final int tunedInWorks;
    private final Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    private final Difficulty difficulty; //Поле не может быть null
    private final DisplayDiscipline discipline; //Поле может быть null
    @Getter
    private final String username;

    @Getter
    private transient LongProperty idProperty = new SimpleLongProperty();
    @Getter
    private transient StringProperty nameProperty = new SimpleStringProperty();
    @Getter
    private transient ObjectProperty<DisplayCoordinates> coordinatesProperty = new SimpleObjectProperty<>();
    @Getter
    private transient ObjectProperty<LocalDateTime> creationDateProperty = new SimpleObjectProperty<>();
    @Getter
    private transient DoubleProperty minimalPointProperty = new SimpleDoubleProperty();
    @Getter
    private transient IntegerProperty tunedInWorksProperty = new SimpleIntegerProperty();
    @Getter
    private transient DoubleProperty averagePointProperty = new SimpleDoubleProperty();
    @Getter
    private transient StringProperty difficultyProperty = new SimpleStringProperty();
    @Getter
    private transient ObjectProperty<DisplayDiscipline> disciplineProperty = new SimpleObjectProperty<>();
    @Getter
    private transient StringProperty usernameProperty = new SimpleStringProperty();

    public DisplayLabwork(LabWork labWork) {
        this.id = labWork.getId();
        this.name = labWork.getName();
        this.coordinates = new DisplayCoordinates(labWork.getCoordinates());
        this.creationDate = labWork.getCreationDate();
        this.minimalPoint = labWork.getMinimalPoint();
        this.tunedInWorks = labWork.getTunedInWorks();
        this.averagePoint = labWork.getAveragePoint();
        this.difficulty = labWork.getDifficulty();
        this.discipline = new DisplayDiscipline(labWork.getDiscipline());
        this.username = labWork.getUsername();

        this.bindProperties();
    }

    public void bindProperties() {
        coordinates.bindProperties();
        discipline.bindProperties();
        coordinatesProperty.bindBidirectional(new SimpleObjectProperty<>(coordinates));
        disciplineProperty.bindBidirectional(new SimpleObjectProperty<>(discipline));
        idProperty.bindBidirectional(new SimpleLongProperty(id));
        nameProperty.bindBidirectional(new SimpleStringProperty(name));
        creationDateProperty.bindBidirectional(new SimpleObjectProperty<>(creationDate));
        minimalPointProperty.bindBidirectional(new SimpleDoubleProperty(minimalPoint));
        tunedInWorksProperty.bindBidirectional(new SimpleIntegerProperty(tunedInWorks));
        averagePointProperty.bindBidirectional(new SimpleDoubleProperty(averagePoint));
        difficultyProperty.bindBidirectional(new SimpleStringProperty(difficulty.toString()));
        usernameProperty.bindBidirectional(new SimpleStringProperty(username));
    }
}
