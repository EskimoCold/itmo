package common.handlers;

import common.collections.LabWork;
import common.exceptions.UserException;
import common.network.User;

import java.sql.Connection;
import java.util.ArrayDeque;

public interface DBHandler {
    Connection getConnection();
    User checkUserPresence(User user);
    User createUser(User user) throws UserException;
    boolean checkUserPassword(User userToCheck) throws UserException;
    LabWork createLab(LabWork lab, String username, boolean setFields);
    void removeLab(LabWork lab);
    ArrayDeque<LabWork> loadCollectionToMemory();
}
