package bgu.spl.net.api.outFrame;

import bgu.spl.net.srv.ConnectionsImpl;

public class ConnectedFrame {
    private String version;

    public ConnectedFrame(String version) {
        this.version = version;
    }

    public void process(int connectionId, ConnectionsImpl connections) {
        connections.send(connectionId,toString());
    }


    @Override
    public String toString() {
        String output = "CONNECTED" + '\n' + "version:"+version + '\n' + '\u0000';
        return output;
    }
}
