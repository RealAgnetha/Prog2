package util;

import app.TCPFileExchange;
import app.TCPFileExchangeImpl;

import java.io.IOException;

/**
 * Nutzeroberfläche FileTransfer
 * expected params:
 * 1) send: filename, hostname (recipient), port
 * 2) receive: filename, port
 */

public class TCPFileExchangerUI {


    /**Marinas Input**/

    private String filename = "";
    private String hostname;
    private int port;
    TCPFileExchange tcpFileExchanger = new TCPFileExchangeImpl();


    public void run(String[] args) throws IOException {
        String portString = null;
        hostname = args[1];
        if(args.length == 2) { // > case: serve
            filename = args[0];
            // Hier gab es port Probleme -- port war 0 nach dem Parsen, weil es eigtl leer war
            port = Integer.parseInt(args[1]);
            //System.out.println("port" + portString);
            listeningAsServer();
        } else if(args.length == 3) { // > case: connect
            filename = args[0];
            port = Integer.parseInt(args[2]);
            connectingAsClient();
        }
    }
    private void connectingAsClient() throws IOException {
        System.out.println("Sending...");
        System.out.println("filename:\t" + filename);
        System.out.println("port:\t" + port);
        System.out.println("hostname:\t" + hostname);
        tcpFileExchanger.sendFile2Host(filename, hostname, port);
    }
    private void listeningAsServer() throws IOException {
        System.out.println("Waiting to receive...");
        System.out.println("filename:\t" + filename);
        System.out.println("port:\t" + port);
        tcpFileExchanger.receiveFile(filename, port);
    }

    public static void main(String[] args) throws IOException {
        TCPFileExchangerUI ui = new TCPFileExchangerUI();
        ui.run(args);
    }


}
    /****/

    //OLD:
 /*  public static void main(String[] args) throws IOException {

        if(args.length < 2) {
            System.err.println("at least 2 arguments required");
            return;
        }

        String filename = args[0];
        String hostname = null;
        int port = -1;


        String portString = null;
        if(args.length == 3) { // > case: send
            hostname = args[1];
            portString = args[2];
        } else if(args.length == 2) { // > case: send
            portString = args[1];
        }

        port = Integer.parseInt(portString);

        TCPFileExchange tcpFileExchanger = new TCPFileExchangeImpl();

        if(hostname == null) {
            //receive
            tcpFileExchanger.receiveFile(filename, port);
        } else {
            //send
            tcpFileExchanger.sendFile2Host(filename, hostname, port);
        }

    } */
