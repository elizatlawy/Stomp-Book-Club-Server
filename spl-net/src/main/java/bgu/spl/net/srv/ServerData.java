package bgu.spl.net.srv;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerData {

    private static class serverDataHolder {
        private static ServerData instance = new ServerData();
    }

    private ConcurrentHashMap<String, LinkedList<User>> genreFollowers;
    private ConcurrentHashMap<String, User> registeredUsers; // key: userName value: User
    private ConcurrentHashMap<Integer, User> activeUsers; // key: connectionId value: User
    private  ConnectionsImpl connections;
    private AtomicInteger messageCounter;


    private ServerData() {
        genreFollowers = new ConcurrentHashMap<>();
        registeredUsers = new ConcurrentHashMap<>();
        connections =  new ConnectionsImpl();
        activeUsers = new ConcurrentHashMap<>();
        messageCounter = new AtomicInteger();
        messageCounter.set(0);
    }

    public static ServerData getInstance(){
        return serverDataHolder.instance;

    }

    public void addFollower(String topic, User user) {
        genreFollowers.putIfAbsent(topic, new LinkedList<>());
        LinkedList topicList = genreFollowers.get(topic);
        synchronized (topicList) {
            topicList.add(user);
        }
    }

    public void removeFollower (String topic, User user){
        LinkedList topicList = genreFollowers.get(topic);
        synchronized (topicList) {
            topicList.remove(user);
        }
    }


    public ConcurrentHashMap<String, LinkedList<User>> getGenreFollowers() {
        return genreFollowers;
    }

    public ConcurrentHashMap<String, User> getRegisteredUsers() {
        return registeredUsers;
    }

    public ConnectionsImpl getConnections() {
        return connections;
    }

    public int incrementAndGetMsgCounter() {
        return  messageCounter.incrementAndGet();
    }

    public ConcurrentHashMap<Integer, User> getActiveUsers() {
        return activeUsers;
    }

}
