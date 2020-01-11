package bgu.spl.net.api.outFrame;

import bgu.spl.net.srv.Connections;
import bgu.spl.net.srv.ServerData;

public class MessageFrame {
    private String subscriptionId;
    private String messageId;
    private String topic;
    private String msgBody;
    private ServerData serverData;
    private int connectionId;
    private Connections<String> connections;

    public MessageFrame(String subscriptionId, String topic, String msgBody, int connectionId, Connections<String> connections) {
        this.subscriptionId = subscriptionId;
        this.topic = topic;
        this.msgBody = msgBody;
        this.connectionId = connectionId;
        this.connections = connections;
        this.serverData = ServerData.getInstance();
    }

    public void process() {
        connections.send(connectionId,toString());

    }

    @Override
    public String toString() {
        String output = "MESSAGE" + '\n' +
                "subscription:" + subscriptionId + '\n' +
                "Message-id:" + serverData.incrementAndGetMsgCounter() + '\n' +
                "destination:" + topic + '\n' + '\n' +
                msgBody +  '\n' + '\u0000';
        return output;
    }
}
