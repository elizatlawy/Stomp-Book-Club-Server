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
        System.out.println("Mesage recived from client");
        String[] msg = message.split("\\r?\\n"); // split the message by end line
        if(msg[0].equals("CONNECT")){
            ConnectFrame connect = new ConnectFrame();
            connect.process(msg,connectionId, connections, this);
        }
        else if(msg[0].equals("SUBSCRIBE")){
            SubscribeFrame subscribe = new SubscribeFrame();
            subscribe.process(msg,connectionId, connections);
        }
        else if(msg[0].equals("UNSUBSCRIBE")){
            UnsubscribeFrame unsubscribeFrame = new UnsubscribeFrame();
            unsubscribeFrame.process(msg,connectionId, connections);
        }
        else if(msg[0].equals("SEND")){
            SendFrame send = new SendFrame();
            send.process(msg,connectionId, connections);
        }
        else if(msg[0].equals("DISCONNECT")){
            DisconnectFrame disconnectFrame = new DisconnectFrame();
            disconnectFrame.process(msg,connectionId,connections, this);
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
