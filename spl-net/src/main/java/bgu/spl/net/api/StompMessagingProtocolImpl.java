package bgu.spl.net.api;

import bgu.spl.net.api.inFrames.ConnectFrame;
import bgu.spl.net.api.inFrames.SendFrame;
import bgu.spl.net.api.inFrames.SubscribeFrame;
import bgu.spl.net.srv.Connections;
import bgu.spl.net.srv.ConnectionsImpl;

public class StompMessagingProtocolImpl<T> implements StompMessagingProtocol<T> {
    private int connectionId;
    private Connections<T> connections;
    private boolean shouldTerminate;


    @Override
    public void start(int connectionId, Connections<T> connections) {
        this.connectionId = connectionId;
        this.connections = connections;
    }

    @Override
    public void process(String message) {
        String[] msg = message.split("\\r?\\n"); // split the message by end line
        if(msg[0] == "CONNECT"){
            ConnectFrame connect = new ConnectFrame(msg,connectionId, connections );
            connect.process();
        }
        else if(msg[0] == "SUBSCRIBE"){
            SubscribeFrame subscribe = new SubscribeFrame();
            subscribe.process(msg);
        }
        else if(msg[0] == "SEND"){
            SendFrame send = new SendFrame();
            send.process(msg);
        }
        else if(msg[0] == "DISCONNECT"){


        }

    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
