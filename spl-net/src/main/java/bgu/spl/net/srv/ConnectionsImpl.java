package bgu.spl.net.srv;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImpl implements Connections {
    private ConcurrentHashMap<Integer, ConnectionHandler> activeConnections;

    public ConnectionsImpl() {
        activeConnections = new ConcurrentHashMap<>();
    }

    @Override
    public boolean send(int connectionId, Object msg) {
        if (msg != null && activeConnections.contains(connectionId)) {
            activeConnections.get(connectionId).send(msg);
            return true;
        }
        return false;
    }

    @Override
    public void send(String channel, Object msg) {
        if(msg != null){
            List<User> genre = serverData.getInstance().getGenreFollowers().get(channel);
            if (genre != null) {
                for (User currUser : genre)
                    send(currUser.getConnectionId(), msg);
            }
        }
    }

    public void connect(int connectionId, ConnectionHandler handler) {
        activeConnections.put(connectionId, handler);
    }

    @Override
    public void disconnect(int connectionId) {
        activeConnections.remove(connectionId);
    }
}
