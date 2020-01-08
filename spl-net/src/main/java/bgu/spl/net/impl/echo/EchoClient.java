package bgu.spl.net.impl.echo;

import java.io.*;
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
            String connectMessage1 = "CONNECT" + '\n' +
                    "accept-version:1.2" + '\n' +
                    "host:stomp.cs.bgu.ac.il" + '\n' +
                    "login:bob" + '\n' +
                    "passcode:alice" + '\n' + '\u0000';
            String disconnectMessage = "DISCONNECT" + '\n' +
                    "receipt:77" + '\u0000';

            String connectMessage2 = "CONNECT" + '\n' +
                    "accept-version:1.2" + '\n' +
                    "host:stomp.cs.bgu.ac.il" + '\n' +
                    "login:bob" + '\n' +
                    "passcode:alice" + '\n' + '\u0000';


            // first login
            out.write(connectMessage1);
            // disconnect
            out.write(disconnectMessage);
            // login again
            //out.write(connectMessage3);
            out.newLine();
            out.flush();
            // second login
//            out.write(connectMessage1);
//            out.newLine();
//            out.flush();


            System.out.println("awaiting response");
            String line = in.readLine();
            while (line != null) {
                System.out.println("message from server: " + line);
                line = in.readLine();
            }
        } // try 1
        try (Socket sock = new Socket("localhost", 7777);
             BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()))) {

            String connectMessage3 = "CONNECT" + '\n' +
                    "accept-version:1.2" + '\n' +
                    "host:stomp.cs.bgu.ac.il" + '\n' +
                    "login:bob" + '\n' +
                    "passcode:alice2" + '\n' + '\u0000';

            out.write(connectMessage3);
            out.newLine();
            out.flush();

            System.out.println("awaiting response");
            String line = in.readLine();
            while (line != null) {
                System.out.println("message from server: " + line);
                line = in.readLine();

            }

        }

    }
}
