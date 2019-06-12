package lesson10.task01_02.service;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * Created by Pavel Borodin on 2019-06-10
 */
public class ClientConnected {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private String host;
    private int port;

    private static final Logger log = Logger.getLogger(ClientConnected.class);

    public ClientConnected(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Создаем коннект
     * @author Pavel Borodin
     */
    public void connected() {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            log.info("Socket failed");
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            new ClientConnected.ReadMsg().start();
            new ClientConnected.WriteMsg().start();
            this.getNickName();
        } catch (Exception e) {
            ClientConnected.this.downService();
        }
    }

    private void getNickName() {
        try {
            log.info("Введите никнейм");
            sendMessage(inputUser.readLine());
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

    /**
     * Нить для чтения
     * @author Pavel Borodin
     */
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

    /**
     * Отправка сообщения
     * @author Pavel Borodin
     */
    private void sendMessage(String message) {
        try {
            out.write(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Нить для записи
     * @author Pavel Borodin
     */
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
                        sendMessage(userWord + "\n");
                    }
                } catch (IOException e) {
                    ClientConnected.this.downService();
                }
            }
        }
    }
}

