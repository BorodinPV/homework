package lesson10.task01_02.service;

import java.io.*;
import java.net.Socket;
import java.util.Map;

/**
 * Created by Pavel Borodin on 2019-06-10
 */
public class ThreadClient extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private Map<String, Socket> users;

    public ThreadClient(Socket socket, Map<String, Socket> users) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.users = users;
        start();
    }

    @Override
    public void run() {
        String word;
        try {
            while (true) {
                word = in.readLine();
                if (word.equals("stop")) {
                    break;
                }
                send(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {
        }
    }

    public Map<String, Socket> getUsers() {
        return users;
    }
}
