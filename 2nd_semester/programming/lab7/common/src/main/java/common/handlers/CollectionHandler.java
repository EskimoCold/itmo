package common.handlers;

import common.collections.LabWork;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayDeque;

public interface CollectionHandler {
    final LocalDateTime dateCreated = LocalDateTime.now();
    ArrayDeque<LabWork> collection = null;
    final String filepath = null;

    void add(LabWork lw);
    String info();

    ArrayDeque<LabWork> getCollection();
    void setCollection(ArrayDeque<LabWork> collection);
}
