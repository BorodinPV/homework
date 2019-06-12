package lesson10.task01_02.service;

/**
 * Created by Pavel Borodin on 2019-06-12
 */
public class LockUserChat {
    private boolean lock;
    private String nickName;
    private ServerConnected connected;

    public LockUserChat(boolean lock, String nickName, ServerConnected connected) {
        this.lock = lock;
        this.nickName = nickName;
        this.connected = connected;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public ServerConnected getConnected() {
        return connected;
    }

    public void setConnected(ServerConnected connected) {
        this.connected = connected;
    }
}
