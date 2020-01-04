package bgu.spl.net.api.inFrames;

import bgu.spl.net.api.outFrame.ConnectedFrame;
import bgu.spl.net.api.outFrame.ErrorFrame;
import bgu.spl.net.srv.*;

public class ConnectFrame<T> {
    private String version;
    private String host;
    private String userName;
    private String password;
    private int connectionId;
    private ServerData serverData;
    private ConnectionsImpl connections;


    public ConnectFrame(String[] message, int connectionId, Connections<T> connections) {
        char delimiter = ':';
        version = message[1].substring(message[1].indexOf(delimiter) + 1);
        host = message[2].substring(message[2].indexOf(delimiter) + 1);
        userName = message[3].substring(message[3].indexOf(delimiter) + 1);
        password = message[4].substring(message[4].indexOf(delimiter) + 1);
        serverData = ServerData.getInstance();
        this.connectionId = connectionId;
        this.connections = (ConnectionsImpl) connections;
    }

    public void process() {
        User currUser = serverData.getRegisteredUsers().get(userName);
        if (currUser == null) { // user does not exist
            currUser = new User(userName, password);
            serverData.getRegisteredUsers().put(userName, currUser);
            currUser.setConnectionId(connectionId);
            ConnectedFrame connectedFrame = new ConnectedFrame(version);
            connections.send(connectionId,connectedFrame.toString());
        }
        // user exist
        else if (password.equals(currUser.getPassword())) { // correct password
            if (currUser.getConnectionId() == -1) {  // the user is not logged in
                currUser.setConnectionId(connectionId);

            }
            else { // the user is already logged in
                ErrorFrame errorFrame = new ErrorFrame("User already logged in");
                connections.send(connectionId,errorFrame.toString());
            }
        }
        else { // wrong password
            ErrorFrame errorFrame = new ErrorFrame("Wrong password");
            connections.send(connectionId,errorFrame.toString());
        }
    }


}


