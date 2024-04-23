package server.network;

import common.collections.LabWork;
import common.commands.*;
import common.handlers.IOHandler;
import common.network.Request;
import server.handlers.CollectionHandler;
import common.network.Response;
import server.handlers.IOManager;
import server.handlers.XMLManager;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServer {
    DatagramSocket socket = null;
    private final byte[] buf = new byte[4096];
    private final Logger logger = Logger.getLogger("logger");

    public UDPServer(int port) {
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            logger.log(Level.SEVERE,"Не удалось подключиться к клиенту: " + e.getMessage(), e.getMessage());
        }
    }

    public void run(CollectionHandler collectionHandler) {
        logger.log(Level.INFO, "RUNNING");

        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            try {
                Request request = IOManager.input(socket, packet);
                Command command = request.getCommand();
                LabWork lab = request.getLab();
                logger.log(Level.INFO, "Command received: " + command.getName());

                ArrayDeque<LabWork> collection = collectionHandler.getCollection();

                if (lab != null) {
                    if (command instanceof Add) {
                        lab.setId(LabWork.generateId());
                        collection.add(lab);
                        collectionHandler.setCollection(collection);
                        Response response = ((Add) command).execute(collection, lab);
                        IOManager.output(socket, packet, response);
                        logger.log(Level.INFO, "Response sent: " + response);
                    }

                    else if (command instanceof AddIfMin) {
                        Response commandResponse = ((AddIfMin) command).execute(collection, lab);

                        if (commandResponse.getObj().equals(true)) {
                            collection.add(lab);
                            collectionHandler.setCollection(collection);
                            Response response = new Response("Added");
                            IOManager.output(socket, packet, response);
                            logger.log(Level.INFO, "Response sent: " + response);

                        } else {
                            Response response = new Response("Not added");
                            IOManager.output(socket, packet, response);
                            logger.log(Level.INFO, "Response sent: " + response);
                        }
                    }

                    else if (command instanceof RemoveById | command instanceof Update) {
                        Response commandResponse = command.execute(collection);

                        if (commandResponse.getObj().equals(1)) {
                            Response response = new Response("Invalid id provided!");
                            IOManager.output(socket, packet, response);
                            logger.log(Level.INFO, "Response sent: " + response);

                        } if (commandResponse.getObj().equals(2)) {
                            Response response = new Response("Element with provided id not found in collection");
                            IOManager.output(socket, packet, response);
                            logger.log(Level.INFO, "Response sent: " + response);

                        } else {
                            collectionHandler.setCollection((ArrayDeque<LabWork>) commandResponse.getObj());
                            Response response = new Response("Done");
                            IOManager.output(socket, packet, response);
                            logger.log(Level.INFO, "Response sent: " + response);
                        }

                    }

                    else if (command instanceof RemoveGreater) {
                        Response commandResponse = command.execute(collection);
                        collectionHandler.setCollection((ArrayDeque<LabWork>) commandResponse.getObj());
                        Response response = new Response("Done");
                        IOManager.output(socket, packet, response);
                        logger.log(Level.INFO, "Response sent: " + response);
                    }

                    else {
                        Response response = ((CommandWithElement) command).execute(collection, lab);
                        IOManager.output(socket, packet, response);
                        logger.log(Level.INFO, "Response sent: " + response);
                    }

                } else if (command instanceof RemoveHead) {
                    Response response = command.execute(collection);
                    collection.removeFirst();
                    collectionHandler.setCollection(collection);
                    IOManager.output(socket, packet, response);
                    logger.log(Level.INFO, "Response sent: " + response);

                } else {
                    Response response = command.execute(collection);
                    IOManager.output(socket, packet, response);
                    logger.log(Level.INFO, "Response sent: " + response);
                }

            } catch (ClassNotFoundException | IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }
}