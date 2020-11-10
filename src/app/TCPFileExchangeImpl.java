package app;

import TCP.Client;
import TCP.Connection;
import TCP.Server;
import TCP.TCPConnector;
import fileExchange.FileExchanger;
import fileExchange.FileReceiver;
import fileExchange.FileSender;

import java.io.IOException;

public class TCPFileExchangeImpl implements TCPFileExchange {


    @Override
    public void sendFile2Host(String filename, String hostname, int port) throws IOException {
        //need connection
        Client client = new TCPConnector();
        Connection connection = client.connect(hostname, port);

        //send file
        FileSender fileSender = new FileExchanger(); //der filesender ist vom typ fileexchanger
        fileSender.sendFile(filename, connection.getOutputStream()); //schreibt file in outputstream
    }

    @Override
    public void receiveFile(String filename, int port) throws IOException {
        Server server = new TCPConnector();
        Connection connection = server.acceptConnection(port);


       /** Marina Input **/
        // server.acceptConnection blockiert denn Code bis sich etwas mit dem Port verbindet.
        // Die Zeile unten wird erst ausgefuehrt wenn es also ne Verbindung gab.
        System.out.println("Just received a connection!");
        /****/


        FileReceiver fileReceiver = new FileExchanger();
        fileReceiver.receiveFile(filename, connection.getInputStream()); //siehe oben
    }

}
