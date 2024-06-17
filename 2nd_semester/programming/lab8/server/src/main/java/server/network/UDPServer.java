package server.network;

import common.collections.LabWork;
import common.commands.Command;
import common.commands.collection.CommandWithElement;
import common.network.Request;
import common.network.Response;
import server.handlers.CollectionHandler;
import server.handlers.IOManager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServer {
    private DatagramSocket socket;
    private final byte[] buf = new byte[65536];
    private final Logger logger = Logger.getLogger("logger");
    private final ExecutorService requestPool = Executors.newCachedThreadPool();
    private final ExecutorService processPool = Executors.newCachedThreadPool();
    private final ForkJoinPool responsePool = new ForkJoinPool();

    public UDPServer(int port) {
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            logger.log(Level.SEVERE, "Не удалось подключиться к клиенту: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void run(CollectionHandler collectionHandler) {
        logger.log(Level.INFO, "RUNNING");

        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            try {
                socket.receive(packet);
                logger.log(Level.INFO, "Packet received from: " + packet.getAddress() + ":" + packet.getPort());
                requestPool.submit(() -> handleRequest(packet, collectionHandler));
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    private void handleRequest(DatagramPacket packet, CollectionHandler collectionHandler) {
        try {
            logger.log(Level.INFO, "Handling request...");
            Request request = IOManager.input(socket, packet);

            if (request == null) {
                logger.log(Level.SEVERE, "Request is null");
                return;
            }

            logger.log(Level.INFO, "Request parsed, submitting for processing");
            submitForProcessing(request, packet, collectionHandler);

        } catch (ClassNotFoundException | IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void submitForProcessing(Request request, DatagramPacket packet, CollectionHandler collectionHandler) {
        processPool.submit(() -> {
            try {
                Command command = request.getCommand();
                String[] args = request.getArgs();
                LabWork lab = request.getLab();
                logger.log(Level.INFO, "Command received: " + command.getName());

                command.setCollectionHandler(collectionHandler);
                Response response;
                if (lab != null) {
                    response = ((CommandWithElement) command).execute(args, lab);
                } else {
                    response = command.execute(args);
                }

                if (response == null) {
                    logger.log(Level.SEVERE, "Response is null");
                    return;
                }
                logger.log(Level.INFO, "Command executed, preparing response");
                submitForResponse(response, packet);

            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        });
    }

    private void submitForResponse(Response response, DatagramPacket packet) {
        responsePool.submit(() -> {
            try {
                logger.log(Level.INFO, "Sending response...");
                IOManager.output(socket, packet, response);
                logger.log(Level.INFO, "Response sent: " + response);
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        });
    }
}
