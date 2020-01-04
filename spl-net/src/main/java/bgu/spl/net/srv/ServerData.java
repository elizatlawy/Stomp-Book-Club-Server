package bgu.spl.net.srv;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ServerData {

    private static class serverDataHolder {
        private static ServerData instance = new ServerData();
    }

    private ConcurrentHashMap<String, List<User>> genreFollowers;
    private ConcurrentHashMap<String, User> registeredUsers;
    private  ConnectionsImpl connections;
    private int messageCounter;


    private ServerData() {
        genreFollowers = new ConcurrentHashMap<>();
        registeredUsers = new ConcurrentHashMap<>();
        connections =  new ConnectionsImpl();
        messageCounter = 0;
    }

    public static ServerData getInstance(){
        return serverDataHolder.instance;

    }

    public ConcurrentHashMap<String, List<User>> getGenreFollowers() {
        return genreFollowers;
    }

    public ConcurrentHashMap<String, User> getRegisteredUsers() {
        return registeredUsers;
    }

    public ConnectionsImpl getConnections() {
        return connections;
    }

    public int getMessageCounter() {
        return messageCounter;
    }
    public void messageCounterIncrement(){
        messageCounter++;
    }

}
