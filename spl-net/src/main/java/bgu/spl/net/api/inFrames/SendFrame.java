package bgu.spl.net.api.inFrames;

import bgu.spl.net.api.outFrame.MessageFrame;
import bgu.spl.net.srv.Connections;
import bgu.spl.net.srv.ServerData;
import bgu.spl.net.srv.User;

public class SendFrame {
    private String topic;
    private String msgBody;
    private ServerData serverData;


    private void initialize (String[] message){
        char delimiter = ':';
        topic = message[1].substring(message[1].indexOf(delimiter) + 1);
        msgBody =  message[2].substring(message[2].indexOf(delimiter) + 1);
        serverData = ServerData.getInstance();
    }

    public void process(String[] message, int connectionId, Connections<String> connections) {
        initialize(message);
        User currUser = serverData.getActiveUsers().get(connectionId);
        String subscriptionId = currUser.getSubscriptionId(topic);
        MessageFrame messageFrame = new MessageFrame(subscriptionId,topic,msgBody,connectionId,connections);
        messageFrame.process();


    }


}
