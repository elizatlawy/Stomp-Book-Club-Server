package bgu.spl.net.api.outFrame;

public class ConnectedFrame {
    private String version;

    public ConnectedFrame(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        String output = "CONNECTED" + '\n' + "version:"+version + '\n' + '\u0000';
        return output;
    }
}
