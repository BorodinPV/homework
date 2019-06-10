package lesson10.task01_02;

import lesson10.task01_02.service.ClientConnected;

/**
 * Created by Pavel Borodin on 2019-06-10
 */
public class Client {
    public static String ipAddr = "localhost";
    public static int port = 4999;

    public static void main(String[] args) {
        new ClientConnected(ipAddr, port);
    }
}
