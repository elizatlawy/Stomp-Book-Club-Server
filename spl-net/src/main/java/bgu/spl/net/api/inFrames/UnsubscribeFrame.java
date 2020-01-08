package bgu.spl.net.api.inFrames;

import bgu.spl.net.api.outFrame.ReceiptFrame;
import bgu.spl.net.srv.Connections;
import bgu.spl.net.srv.ServerData;
import bgu.spl.net.srv.User;

public class UnsubscribeFrame {
    private String id;
    private String receipt;
    private ServerData serverData;

    private void initialize (String[] message){
        char delimiter = ':';
        id = message[1].substring(message[1].indexOf(delimiter) + 1);
        receipt =  message[2].substring(message[2].indexOf(delimiter) + 1);
        serverData = ServerData.getInstance();
    }

    public void process(String[] message, int connectionId,  Connections<String> connections) {
        initialize(message);
        User currUser = serverData.getActiveUsers().get(connectionId);
        String topic = currUser.getSubscriptionTopic(id);
        serverData.removeFollower(topic,currUser);
        currUser.removeSubscription(id,topic);
        ReceiptFrame receiptFrame = new ReceiptFrame(receipt);
        receiptFrame.process(connectionId, connections);
    }
}
