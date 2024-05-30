package common.handlers;

import common.collections.LabWork;
import common.exceptions.UserException;
import common.network.User;
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
    User checkUserPresence(User user);
    boolean checkUserPassword(User userToCheck) throws UserException;
    User createUser(User user) throws UserException;
}
