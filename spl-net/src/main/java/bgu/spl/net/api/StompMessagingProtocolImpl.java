package bgu.spl.net.api;

import bgu.spl.net.api.inFrames.*;
import bgu.spl.net.srv.Connections;
import bgu.spl.net.srv.ConnectionsImpl;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {
    private int connectionId;
    private Connections<String> connections;
    private boolean shouldTerminate;


    @Override
    public void start(int connectionId, Connections<String> connections) {
        this.connectionId = connectionId;
        this.connections = connections;
        shouldTerminate = false;
    }

    @Override
    public void process(String message) {
        //System.out.println("Message received from client");
        if(message.length() > 0){
            if(message.charAt(0) == '\n')
                message = message.substring(1);
            String[] msg = message.split("\\r?\\n"); // split the message by end line
            //System.out.println("Server Received: " + msg[0]);
            switch (msg[0]) {
                case "CONNECT":
                    ConnectFrame connect = new ConnectFrame();
                    connect.process(msg, connectionId, connections, this);
                    break;
                case "SUBSCRIBE":
                    SubscribeFrame subscribe = new SubscribeFrame();
                    subscribe.process(msg, connectionId, connections);
                    break;
                case "UNSUBSCRIBE":
                    UnsubscribeFrame unsubscribeFrame = new UnsubscribeFrame();
                    unsubscribeFrame.process(msg, connectionId, connections);
                    break;
                case "SEND":
                    //System.out.println("Message body: " +  msg[3]);
                    SendFrame send = new SendFrame();
                    send.process(msg, connectionId, connections);
                    break;
                case "DISCONNECT":
                    DisconnectFrame disconnectFrame = new DisconnectFrame();
                    disconnectFrame.process(msg, connectionId, connections, this);
                    break;
                default:
                    System.out.println("Incorrect Message received");
                    break;
            }
        }
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
    public void terminate(){
        shouldTerminate = true;
    }
}
