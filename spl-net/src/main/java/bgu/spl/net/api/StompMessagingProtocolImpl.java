package bgu.spl.net.api;

import bgu.spl.net.api.inFrames.ConnectFrame;
import bgu.spl.net.api.inFrames.SendFrame;
import bgu.spl.net.api.inFrames.SubscribeFrame;
import bgu.spl.net.api.inFrames.UnsubscribeFrame;
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
        String[] msg = message.split("\\r?\\n"); // split the message by end line
        if(msg[0] == "CONNECT"){
            ConnectFrame connect = new ConnectFrame();
            connect.process(msg,connectionId, connections);
        }
        else if(msg[0] == "SUBSCRIBE"){
            SubscribeFrame subscribe = new SubscribeFrame();
            subscribe.process(msg,connectionId, connections);
        }
        else if(msg[0] == "UNSUBSCRIBE"){
            UnsubscribeFrame unsubscribeFrame = new UnsubscribeFrame();
            unsubscribeFrame.process(msg,connectionId, connections);
        }
        else if(msg[0] == "SEND"){
            SendFrame send = new SendFrame();
            send.process(msg,connectionId, connections);
        }
        else if(msg[0] == "DISCONNECT"){


        }

    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
