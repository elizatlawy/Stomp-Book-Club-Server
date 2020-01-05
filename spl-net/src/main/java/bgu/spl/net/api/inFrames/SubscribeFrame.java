package bgu.spl.net.api.inFrames;

import bgu.spl.net.api.outFrame.ReceiptFrame;
import bgu.spl.net.srv.Connections;
import bgu.spl.net.srv.ConnectionsImpl;
import bgu.spl.net.srv.ServerData;
import bgu.spl.net.srv.User;

public class SubscribeFrame {
    private String topic;
    private String id;
    private String receipt;
    private ServerData serverData;



    public SubscribeFrame() {

    }

    private void initialize (String[] message){
        char delimiter = ':';
        topic = message[1].substring(message[1].indexOf(delimiter) + 1);
        id =  message[2].substring(message[2].indexOf(delimiter) + 1);
        receipt =  message[3].substring(message[3].indexOf(delimiter) + 1);
        serverData = ServerData.getInstance();
    }

    public void process(String[] message, int connectionId,  Connections<String> connections) {
        initialize(message);
        User currUser = serverData.getActiveUsers().get(connectionId);
        serverData.addFollower(topic,currUser);
        currUser.addSubscription(id,topic);
        ReceiptFrame receiptFrame = new ReceiptFrame(receipt);
        receiptFrame.process(connectionId, connections);
    }
}
