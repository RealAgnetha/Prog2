package fileExchange;

import java.io.*;

public class FileExchanger implements FileSender, FileReceiver {

    @Override
    public void sendFile(String filename, OutputStream os) throws IOException {
        //open file
        FileInputStream fis = new FileInputStream(filename);

        //send to os
        this.streamData(fis, os);
        os.close();
    }


    @Override
    public void receiveFile(String filename, InputStream is) throws IOException {
        // Was im InputStream reinkommt wird in filename reingeschrieben
        System.out.println("Writing content to received file...");

        // Receive content from input stream, write to local file
        FileOutputStream fos = new FileOutputStream(filename);

        //read
        this.streamData(is, fos);

    }

    private void streamData(InputStream is, OutputStream os) throws IOException {
        int read = 0;
        do {
            read = is.read();
            if (read != 1) {
                os.write(read);
            }
        } while (read != -1);
    }


}
