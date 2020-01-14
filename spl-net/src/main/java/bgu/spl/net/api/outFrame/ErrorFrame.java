package bgu.spl.net.api.outFrame;

import bgu.spl.net.api.StompMessagingProtocolImpl;
import bgu.spl.net.srv.Connections;
import bgu.spl.net.srv.ConnectionsImpl;
import bgu.spl.net.srv.ServerData;
import bgu.spl.net.srv.User;

public class ErrorFrame {
    private String receiptId;
    private String message;
    private ServerData serverData;

    public ErrorFrame(String message) {
        this.message = message;
        serverData = ServerData.getInstance();

    }

    public void process(int connectionId,  Connections<String> connections, StompMessagingProtocolImpl protocol) {
        int messageNumber = ServerData.getInstance().incrementAndGetMsgCounter();
        receiptId = "message-" + messageNumber;
        connections.send(connectionId,toString());
        User currUser = serverData.getActiveUsers().get(connectionId);
        if(currUser != null) // in case of already logged in user we want to disconnect him
            currUser.logout();
        protocol.terminate();
    }

    @Override
    public String toString() {
        String output = "ERROR" + '\n'
                + "receipt-id:" +  receiptId +  '\n'
                + "message: " + message + '\n' + '\n' +
                "The message: " + message + '\n'; // the last line represent the message body if some client reads it
        return output;
    }
}
