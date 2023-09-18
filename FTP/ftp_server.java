// Import statements for necessary Java libraries
import java.io.*;
import java.net.*;

// Define the main class for the FTP server
public class ftp_server {
    public static void main(String[] args) throws IOException {
        ServerSocket serv_skt = null; // Initialize a ServerSocket for listening to client connections
        InetAddress in_addr = InetAddress.getLocalHost(); // Get the local host's IP address
        try {
            serv_skt = new ServerSocket(3333); // Create a server socket on port 3333
        } catch (IOException exep) {
            System.out.println("Sorry!! Could not set up the server");
        }
        Socket skt = null; // Initialize a socket for communication with a client
        InputStream inp_strm = null; // Initialize an input stream
        FileOutputStream foutp_strm = null; // Initialize a file output stream for saving received data
        try {
            System.out.print("\nWaiting for connection...");
            System.out.println("\nEnter the address displayed in the client side: " + in_addr.getHostAddress()); // Display the server's IP address
            skt = serv_skt.accept(); // Accept a client connection
            System.out.println("\n\nHurray!! Successfully Established Connection\n");
        } catch (IOException exep) {
            System.out.println("Sorry!! Client connection could not be accepted.");
        }
        BufferedReader buff_rdr = new BufferedReader(new InputStreamReader(skt.getInputStream())); // Create a BufferedReader to read from the client
        try {
            inp_strm = skt.getInputStream(); // Get the socket's input stream
        } catch (IOException exep) {
            System.out.println("Cannot get the socket input stream!");
        }
        try {
            foutp_strm = new FileOutputStream("ftp_example.mp3"); // Create a FileOutputStream to write received data to a file
        } catch (FileNotFoundException exep) {
            System.out.println("File not found!");
        }
        int file_size = Integer.parseInt(buff_rdr.readLine()); // Read the file size from the client
        System.out.println("Size of the file is " + file_size + " bytes");
        int counter;
        byte[][] bytedata1 = new byte[100][]; // Initialize an array to store file data in 100 parts
        byte[] bytedata2 = new byte[file_size - (file_size / 100) * 100]; // Initialize an array to store the remaining file data
        for (int j = 0; j < 100; j++) {
            bytedata1[j] = new byte[file_size / 100];
            while ((counter = inp_strm.read(bytedata1[j])) != -1) {
                foutp_strm.write(bytedata1[j], 0, counter); // Write file data to the FileOutputStream
            }
            bytedata1[j] = null;
            System.gc(); // Request garbage collection to free memory
            System.out.print("\nTransfer Progress happening: " + (j + 1) + "% done ||| " +
                    ((file_size / 100) * (j + 1) / 1048576) + "MB out of " + (file_size / 1048576) + "MB");
        }
        while ((counter = inp_strm.read(bytedata2)) != -1) {
            foutp_strm.write(bytedata2, 0, counter); // Write the remaining file data to the FileOutputStream
        }
        System.out.println("\n\nFile Received Successfully!\n\n\n");
        foutp_strm.close(); // Close the FileOutputStream
        inp_strm.close(); // Close the input stream
        skt.close(); // Close the socket
        serv_skt.close(); // Close the ServerSocket
    }
}


/*
Overview of above code:
This Java program acts as a basic FTP server. It listens for incoming client connections on port 3333 and establishes a connection when a client connects. Upon connection, the server receives the file size from the client and saves it as "ftp_example.mp3". The file is divided into 100 parts for efficient transfer, with progress displayed as a percentage and in megabytes. The remaining data is also received and saved. Once the entire file is received, the server confirms success and closes its connections. This code demonstrates a simple file transfer process between a server and a client using sockets and provides progress tracking during the transfer.
*/