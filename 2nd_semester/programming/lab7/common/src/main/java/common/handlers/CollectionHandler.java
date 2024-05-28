package common.handlers;

import common.collections.LabWork;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;

public interface CollectionHandler {
    void add(LabWork lw);
    void remove(LabWork lw);
    String info();
    ArrayDeque<LabWork> getCollection();
    void setCollection(ArrayDeque<LabWork> collection);
    DBHandler getDbHandler();
}
