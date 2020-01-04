package bgu.spl.net.api.outFrame;

import bgu.spl.net.srv.ConnectionsImpl;
import bgu.spl.net.srv.ServerData;

public class ErrorFrame {
    private String receiptId;
    private String message;

    public ErrorFrame(String message) {
        this.message = message;

    }

    public void process(int connectionId, ConnectionsImpl connections) {
        connections.send(connectionId,toString());
        connections.disconnect(connectionId);
    }

    @Override
    public String toString() {
        int messageNumber = ServerData.getInstance().getMessageCounter();
        String output = "EROOR" + '\n'
                + "receipt-id:" + "message-" + messageNumber + '\n'
                + "message: " + message + '\n' + '\u0000';
        return output;
    }
}
