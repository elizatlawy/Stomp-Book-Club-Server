package bgu.spl.net.api.outFrame;

import bgu.spl.net.srv.Connections;

public class ReceiptFrame {
    private String receipt;

    public ReceiptFrame(String receipt) {
        this.receipt = receipt;
    }
    public void process(int connectionId,  Connections<String> connections) {
        connections.send(connectionId,toString());
    }

    @Override
    public String toString() {
        String output = "RECEIPT" + '\n' + "receipt-id:" + receipt + '\n';
        return output;
    }
}
