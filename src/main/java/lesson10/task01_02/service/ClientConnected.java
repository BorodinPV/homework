package lesson10.task01_02.service;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Pavel Borodin on 2019-06-10
 */
public class ClientConnected {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private String nickname;
    private static final Logger log = Logger.getLogger(ClientConnected.class);

    public ClientConnected(String addr, int port) {
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            log.info("Socket failed");
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.getNickName();
            new ClientConnected.ReadMsg().start();
            new ClientConnected.WriteMsg().start();
        } catch (Exception e) {
            ClientConnected.this.downService();
        }
    }

    private void getNickName() {
        log.info("Введите свой ник: ");
        try {
            nickname = inputUser.readLine();
            sendMessage("Привет " + nickname + "\n");
        } catch (IOException ignored) {
        }

    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {
        }
    }

    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("stop")) {
                        ClientConnected.this.downService();
                        break;
                    }
                    log.info(str);
                }
            } catch (IOException e) {
                ClientConnected.this.downService();
            }
        }
    }

    private void sendMessage(String message) {
        try {
            out.write(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    userWord = inputUser.readLine();
                    if (userWord.equals("stop")) {
                        out.write("stop" + "\n");
                        ClientConnected.this.downService();
                        break;
                    } else {
                        sendMessage("(" + LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:ss")) + ") " +
                                nickname + ": " + userWord + "\n");
                    }
                } catch (IOException e) {
                    ClientConnected.this.downService();
                }
            }
        }
    }
}

