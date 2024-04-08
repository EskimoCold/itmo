package server.network;

import common.commands.Command;
import common.handlers.CollectionHandler;
import common.network.Response;

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
                socket.receive(packet);
                byte[] data = packet.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);

                try {
                    Command command = (Command) is.readObject();

                    logger.log(Level.INFO, "Command received: " + command.getName());
                    Response response = command.execute(collectionHandler);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    ObjectOutputStream os = new ObjectOutputStream(outputStream);
                    os.writeObject(response);
                    byte[] bufResponse = outputStream.toByteArray();
                    DatagramPacket responsePacket = new DatagramPacket(bufResponse, bufResponse.length, packet.getAddress(), packet.getPort());
                    socket.send(responsePacket);
                    logger.log(Level.INFO, "Response sent: " + response);

                } catch (ClassNotFoundException e) {
                    logger.log(Level.SEVERE, e.getMessage());
                }

            } catch (IOException ioe) {
                logger.log(Level.SEVERE,"Не удалось получить данные: " + ioe.getMessage(), ioe.getMessage());
            }
        }
    }
}