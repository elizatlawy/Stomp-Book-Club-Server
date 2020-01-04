package bgu.spl.net.api.outFrame;

import bgu.spl.net.srv.ServerData;

public class ErrorFrame {
    private String receiptId;
    private String message;

    public ErrorFrame(String message) {
        this.message = message;

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
