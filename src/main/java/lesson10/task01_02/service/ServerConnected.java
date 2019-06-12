package lesson10.task01_02.service;

import lesson10.task01_02.Server;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * Created by Pavel Borodin on 2019-06-10
 */
public class ServerConnected extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private String nickName;
    private LockUserChat lockUserChat;

    private static final Logger log = Logger.getLogger(ServerConnected.class);

    public ServerConnected(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
        lockUserChat = new LockUserChat(false, null, null);
    }

    @Override
    public void run() {
        String message;
        try {
            message = in.readLine();
            nickName = message;
            log.info("Подключился новый пользователь: " + message);
            send(nickName);
            try {
                while (true) {
                    if (!lockUserChat.isLock()) {
                        getJobUnlock();
                    } else {
                        getJobLock();
                    }
                }
            } catch (NullPointerException e) {
                log.info("Socket failed");

            }

        } catch (IOException e) {
            this.downService();
        }
    }

    /**
     * Отправка сообщений всем пользователям
     * @author Pavel Borodin
     */
    private void getJobLock() {
        String message = null;
        try {
            message = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(this.lockUserChat.getNickName())) {
            this.lockUserChat.setNickName(message);
            addConnectLockUser(message);
        }
        if (Objects.nonNull(this.lockUserChat.getConnected())) {
            this.lockUserChat.getConnected().send(message);
        }
        if (message.equals("stop send user")) {
            lockUserChat.setLock(false);
        }
    }

    /**
     * Лочим объект для отправки сообщени одному пользователю
     * @author Pavel Borodin
     */
    private void addConnectLockUser(String message) {
        for (ServerConnected vr : Server.serverList) {
            if (vr.getNickName().equals(message)) {
                lockUserChat.setConnected(vr);
            }
        }
    }

    /**
     * Отправка сообщений всем пользователям
     * @author Pavel Borodin
     */
    private String getJobUnlock() throws IOException {
        String message;
        message = in.readLine();
        if (message.equals("stop")) {
            this.downService();
            return null;
        }
        if (message.equals("send user")) {
            lockUserChat.setLock(true);
            send("Введите ник пользователя");
            return null;
        }
        if (message.equals("user list")) {
            Server.serverList.forEach(serverConnected -> send(serverConnected.getNickName()));
        }
        log.info(nickName + ": " + message);
        for (ServerConnected vr : Server.serverList) {
            vr.send(message);
        }
        return message;
    }

    /**
     * Отправка сообщения
     * @author Pavel Borodin
     */
    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {
        }

    }

    /**
     * Чистим чписрк коннектов
     * @author Pavel Borodin
     */
    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerConnected vr : Server.serverList) {
                    if (vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {
        }
    }

    public String getNickName() {
        return nickName;
    }
}
