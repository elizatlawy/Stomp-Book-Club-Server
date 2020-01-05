package bgu.spl.net.api.inFrames;

import bgu.spl.net.srv.Connections;

public class DisconnectFrame {
    private String receipt;

    private void initialize (String[] message){
        char delimiter = ':';
        receipt = message[1].substring(message[1].indexOf(delimiter) + 1);
    }


    public void process(String[] message, int connectionId,  Connections<String> connections) {
        initialize(message);

    }


}

