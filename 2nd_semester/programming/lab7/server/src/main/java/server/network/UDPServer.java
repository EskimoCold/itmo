package server.network;

import common.collections.LabWork;
import common.commands.Command;
import common.commands.collection.CommandWithElement;
import common.network.Request;
import server.handlers.CollectionHandler;
import common.network.Response;
import server.handlers.IOManager;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
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
                String[] args = request.getArgs();
                LabWork lab = request.getLab();
                logger.log(Level.INFO, "Command received: " + command.getName());

                Response response;
                if (lab != null) {
                    response = ((CommandWithElement) command).execute(args, collectionHandler, lab);

                } else {
                    response = command.execute(args, collectionHandler);
                }

                IOManager.output(socket, packet, response);
                logger.log(Level.INFO, "Response sent: " + response);


            } catch (ClassNotFoundException | IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }
}