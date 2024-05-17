package common.handlers;

import common.collections.LabWork;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayDeque;

public interface CollectionHandler {
    ArrayDeque<LabWork> collection = null;

    void add(LabWork lw);
    String info();

    ArrayDeque<LabWork> getCollection();
    void setCollection(ArrayDeque<LabWork> collection);
}
