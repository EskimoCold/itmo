package client;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.commands.Command;
import common.network.Request;
import common.network.Response;

public class UDPClient {
    private int port;
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf;
    Logger logger = Logger.getLogger("logger");

    public UDPClient(int port) {
        this.port = port;

        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
        } catch (SocketException | UnknownHostException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void sendRequest(Request request) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(request);
            buf = outputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, address, this.port);
            socket.send(sendPacket);

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public Response getResponse(boolean close) {
        byte[] bufResponse = new byte[4096];

        while (true) {
            DatagramPacket packet = new DatagramPacket(bufResponse, bufResponse.length);

            try {
                socket.receive(packet);
                byte[] data = packet.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);

                try {
                    Response response = (Response) is.readObject();

                    if (close) {
                        this.closeConnection();
                    }

                    return response;

                } catch (ClassNotFoundException e){
                    logger.log(Level.SEVERE, e.getMessage());
                }

            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    public void closeConnection() {
        socket.close();
    }
}