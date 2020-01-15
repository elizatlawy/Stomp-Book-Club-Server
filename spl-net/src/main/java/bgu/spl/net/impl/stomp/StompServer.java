package bgu.spl.net.impl.stomp;

import bgu.spl.net.api.MessageEncoderDecoderImpl;
import bgu.spl.net.api.StompMessagingProtocolImpl;
import bgu.spl.net.impl.rci.ObjectEncoderDecoder;
import bgu.spl.net.impl.rci.RemoteCommandInvocationProtocol;
import bgu.spl.net.srv.Server;

public class StompServer {

    public static void main(String[] args) {

        short port = (short)Integer.parseInt(args[0]);
        if(args[1].equals("tpc"))
            runThreadPerClientServer(port);
        else if(args[1].equals("reactor"))
            runReactorServer(port);
    }

    private static void runThreadPerClientServer(short port){
        Server.threadPerClient(
                port, //port
                StompMessagingProtocolImpl::new, //protocol factory
                MessageEncoderDecoderImpl::new //message encoder decoder factory
        ).serve();
    }
    private static void runReactorServer(short port){
        Server.reactor(
                Runtime.getRuntime().availableProcessors(),
                port, //port
                StompMessagingProtocolImpl::new, //protocol factory
                MessageEncoderDecoderImpl::new //message encoder decoder factory
        ).serve();

    }

}

