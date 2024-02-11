package ru.ifmo.se;

import org.apache.log4j.BasicConfigurator;
import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.CommandHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CollectionHandler collectionHandler = new CollectionHandler();

        while (true) {
            System.out.print(">> ");
            String command = reader.readLine().strip();
            CommandHandler.process(command, collectionHandler, false);
        }
    }
}