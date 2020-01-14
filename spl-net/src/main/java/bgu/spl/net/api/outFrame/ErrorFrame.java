package bgu.spl.net.api.outFrame;

import bgu.spl.net.api.StompMessagingProtocolImpl;
import bgu.spl.net.srv.Connections;
import bgu.spl.net.srv.ConnectionsImpl;
import bgu.spl.net.srv.ServerData;

public class ErrorFrame {
    private String receiptId;
    private String message;

    public ErrorFrame(String message) {
        this.message = message;

    }

    public void process(int connectionId,  Connections<String> connections, StompMessagingProtocolImpl protocol) {
        int messageNumber = ServerData.getInstance().incrementAndGetMsgCounter();
        receiptId = "message-" + messageNumber;
        connections.send(connectionId,toString());
        protocol.terminate();
    }

    @Override
    public String toString() {
        String output = "ERROR" + '\n'
                + "receipt-id:" +  receiptId +  '\n'
                + "message: " + message + '\n' + '\n' +
                "The message: " + message + '\n'; // the last line represent the message body if some client reads it
        return output;
    }
}
