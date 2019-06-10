package lesson10.task01_02;

import lesson10.task01_02.service.ServerConnected;
import lesson10.task01_02.service.Story;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by Pavel Borodin on 2019-06-10
 */
public class Server {
    public static final int PORT = 4999;
    public static LinkedList<ServerConnected> serverList = new LinkedList<>();
    public static Story story;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        story = new Story();
        System.out.println("Server Started");
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerConnected(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}

