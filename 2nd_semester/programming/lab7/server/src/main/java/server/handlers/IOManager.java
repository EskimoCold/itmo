package server.handlers;

import common.network.Request;
import common.network.Response;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class IOManager {
    public static Request input(DatagramSocket socket, DatagramPacket packet) throws IOException, ClassNotFoundException {
        socket.receive(packet);
        byte[] data = packet.getData();
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return (Request) is.readObject();
    }

    public static void output(DatagramSocket socket, DatagramPacket packet, Response response) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(outputStream);
        os.writeObject(response);
        byte[] bufResponse = outputStream.toByteArray();
        DatagramPacket responsePacket =  new DatagramPacket(bufResponse, bufResponse.length, packet.getAddress(), packet.getPort());
        socket.send(responsePacket);
    }
}
