// Import statements for necessary Java libraries
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Define the main class for the TCP server
public class TCP_Server {
    public static void main(String[] args) throws Exception {
        System.out.println("Server started running the program ..."); // Display a program start message
        ServerSocket serv_skt = new ServerSocket(8000); // Create a ServerSocket listening on port 8000
        System.out.println("\nServer : Waiting for client's request :\n"); // Display a waiting message

        Socket skt = serv_skt.accept(); // Accept a client connection
        System.out.println("!!! Server-client Connection established SUCCESSFULLY !!!"); // Display a successful connection message
        System.out.println("Server : Client Connected "); // Display a client connection message

        // Create a BufferedReader to read data from the client's input stream
        BufferedReader bfr = new BufferedReader(new InputStreamReader(skt.getInputStream()));
        String string = bfr.readLine(); // Read data sent by the client
        System.out.println("Server : Data received from Client is: " + string); // Display received data

        // Extract the first three characters from the received string
        String shortname = string.substring(0, 3);

        // Create an OutputStreamWriter to write data to the socket's output stream
        OutputStreamWriter outpstrwrite = new OutputStreamWriter(skt.getOutputStream());
        PrintWriter out = new PrintWriter(outpstrwrite);

        out.println(shortname); // Send the extracted data back to the client
        out.flush(); // Flush the PrintWriter to ensure data is sent immediately
        System.out.println("Server : Data is successfully sent from server to client"); // Display a success message

        serv_skt.close(); // Close the ServerSocket
    }
}


/*
Overview of the above code:
This Java program functions as a basic TCP server. It starts by setting up a ServerSocket to listen on port 8000 and waits for incoming client requests. When a client connects, it acknowledges the successful connection and reads data sent by the client. The server processes this data by extracting the first three characters and sends them back to the client. Once the data is successfully sent, the server closes its listening socket. This code illustrates a simple client-server interaction over a TCP connection, where the server receives data, processes it, and responds to the client.
*/