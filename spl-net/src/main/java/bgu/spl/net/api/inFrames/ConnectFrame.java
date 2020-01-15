package bgu.spl.net.api.inFrames;

import bgu.spl.net.api.StompMessagingProtocolImpl;
import bgu.spl.net.api.outFrame.ConnectedFrame;
import bgu.spl.net.api.outFrame.ErrorFrame;
import bgu.spl.net.srv.*;

public class ConnectFrame {
    private String version;
    private String host;
    private String userName;
    private String password;
    private ServerData serverData;


    public ConnectFrame() {}

    private void initialize (String[] message){
        char delimiter = ':';
        version = message[1].substring(message[1].indexOf(delimiter) + 1);
        host = message[2].substring(message[2].indexOf(delimiter) + 1);
        userName = message[3].substring(message[3].indexOf(delimiter) + 1);
        password = message[4].substring(message[4].indexOf(delimiter) + 1);
        serverData = ServerData.getInstance();
    }




    public void process(String[] message, int connectionId, Connections<String> connections, StompMessagingProtocolImpl protocol) {
        initialize(message);
        User currUser = serverData.getRegisteredUsers().get(userName);
        if (currUser == null) { // user does not exist
            currUser = new User(userName, password);
            serverData.getRegisteredUsers().put(userName, currUser);
            currUser.login(connectionId);
            ConnectedFrame connectedFrame = new ConnectedFrame(version);
            connectedFrame.process(connectionId,connections);
        }
        // user exist
        else if (password.equals(currUser.getPassword())) { // correct password
            if (currUser.getConnectionId() == -1) {  // the user is not logged in
                currUser.login(connectionId);
                ConnectedFrame connectedFrame = new ConnectedFrame(version);
                connectedFrame.process(connectionId,connections);

            }
            else { // the user is already logged in
                ErrorFrame errorFrame = new ErrorFrame("User already logged in");
                errorFrame.process(connectionId,connections, protocol);
            }
        }
        else { // wrong password
            ErrorFrame errorFrame = new ErrorFrame("Wrong password");
            errorFrame.process(connectionId,connections, protocol);
        }
    }


}


