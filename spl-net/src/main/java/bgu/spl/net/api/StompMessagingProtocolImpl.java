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
        System.out.println("Message received from client");
        if(message.charAt(0) == '\n')
            message = message.substring(1);
        String[] msg = message.split("\\r?\\n"); // split the message by end line
        System.out.println("Server Received: " + msg[0]);
        if(msg[0].equals("CONNECT")){
            ConnectFrame connect = new ConnectFrame();
            connect.process(msg,connectionId, connections, this);
            System.out.println("Server finished process: " +   msg[0]);
        }
        else if(msg[0].equals("SUBSCRIBE")){
            SubscribeFrame subscribe = new SubscribeFrame();
            subscribe.process(msg,connectionId, connections);
            System.out.println("Server finished process: " +   msg[0]);
        }
        else if(msg[0].equals("UNSUBSCRIBE")){
            UnsubscribeFrame unsubscribeFrame = new UnsubscribeFrame();
            unsubscribeFrame.process(msg,connectionId, connections);
            System.out.println("Server finished process: " +   msg[0]);
        }
        else if(msg[0].equals("SEND")){
            System.out.println("Message body: " +  msg[3]);
            SendFrame send = new SendFrame();
            send.process(msg,connectionId, connections);
            System.out.println("Server finished process: " +   msg[0]);
        }
        else if(msg[0].equals("DISCONNECT")){
            DisconnectFrame disconnectFrame = new DisconnectFrame();
            disconnectFrame.process(msg,connectionId,connections, this);
            System.out.println("Server finished process: " +   msg[0]);
        }
        else
            System.out.println("Incorrect Message received");

    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
    public void terminate(){
        shouldTerminate = true;
    }
}
