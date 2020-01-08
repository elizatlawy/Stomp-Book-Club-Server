package bgu.spl.net.api.inFrames;

import bgu.spl.net.api.StompMessagingProtocolImpl;
import bgu.spl.net.api.outFrame.ReceiptFrame;
import bgu.spl.net.srv.Connections;
import bgu.spl.net.srv.ServerData;
import bgu.spl.net.srv.User;

public class DisconnectFrame {
    private String receipt;
    private ServerData serverData;


    private void initialize (String[] message){
        char delimiter = ':';
        receipt = message[1].substring(message[1].indexOf(delimiter) + 1);
        serverData = ServerData.getInstance();
    }

    public void process(String[] message, int connectionId,  Connections<String> connections, StompMessagingProtocolImpl protocol ) {
        initialize(message);
        User currUser = serverData.getActiveUsers().get(connectionId);
        currUser.logout();
        ReceiptFrame receiptFrame = new ReceiptFrame(receipt);
        receiptFrame.process(connectionId, connections);
        protocol.terminate();
    }


}

