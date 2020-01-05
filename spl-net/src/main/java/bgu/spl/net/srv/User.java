package bgu.spl.net.srv;

import java.util.concurrent.ConcurrentHashMap;

public class User {
    private String userName;
    private String password;
    private int connectionId; // -1 represent disconnected user
    private ConcurrentHashMap<String,String> subscriptionsById; // key: subscription id, value: topic name
    private ConcurrentHashMap<String,String> subscriptionsByTopic; // key: topic name value: subscription




    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        connectionId = -1;
        subscriptionsById = new ConcurrentHashMap<>();
        subscriptionsByTopic = new ConcurrentHashMap<>();
    }

    public void addSubscription(String id, String topic){
        subscriptionsById.putIfAbsent(id,topic);
        subscriptionsByTopic.putIfAbsent(topic,id);
    }
    public String getSubscriptionId(String topic){
        return subscriptionsByTopic.get(topic);
    }
    public String getSubscriptionTopic(String id){
        return subscriptionsById.get(id);
    }

    public void removeSubscription(String id, String topic){
        subscriptionsById.remove(id);
        subscriptionsByTopic.remove(topic);
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

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }
}
