package bgu.spl.net.srv;

import java.util.concurrent.ConcurrentHashMap;

public class User {
    private String userName;
    private String password;
    private int connectionId; // -1 represent disconnected user
    private ConcurrentHashMap<String,String> Subscriptions;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        connectionId = -1;
        Subscriptions = new ConcurrentHashMap<>();
    }

    public void addSubscription(String id, String topic){
        Subscriptions.putIfAbsent(id,topic);
    }



    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }
}
