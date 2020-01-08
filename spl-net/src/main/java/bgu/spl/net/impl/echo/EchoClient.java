package bgu.spl.net.impl.echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class EchoClient {

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            args = new String[]{"localhost", "hello"};
        }

        if (args.length < 2) {
            System.out.println("you must supply two arguments: host, message");
            System.exit(1);
        }

        //BufferedReader and BufferedWriter automatically using UTF-8 encoding
        try (Socket sock = new Socket("localhost", 7777);
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()))) {

            System.out.println("sending message to server");
            String connectMessage = "CONNECT" + '\n' +
                    "accept-version:1.2" + '\n' +
                    "host:stomp.cs.bgu.ac.il" + '\n' +
                    "login:bob" + '\n' +
                    "passcode:alice" + '\n' + '\u0000';
            out.write(connectMessage);
            out.newLine();
            out.flush();

            System.out.println("awaiting response");
            int first = in.read();
            while(first != -1){
                String line = first + in.readLine();
                System.out.println("message from server: " + line);
            }

        }
    }
}
