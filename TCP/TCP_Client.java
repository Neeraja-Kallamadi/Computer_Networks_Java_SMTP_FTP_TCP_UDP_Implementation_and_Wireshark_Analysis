// Import statements for necessary Java libraries
import java.io.*;
import java.net.Socket;

// Define the main class for the TCP client
public class TCP_Client {
    public static void main(String[] args) throws Exception {
        String ipaddr = "localhost"; // Define the server's IP address as "localhost"
        int port_no = 8000; // Define the port number to connect to on the server
        Socket skt = new Socket(ipaddr, port_no); // Create a socket connection to the server

        String sting = "RAM"; // Create a string to send to the server

        // Create an OutputStreamWriter to write data to the socket's output stream
        OutputStreamWriter outpstrwrite = new OutputStreamWriter(skt.getOutputStream());

        // Create a PrintWriter to send data to the server
        PrintWriter pw = new PrintWriter(outpstrwrite);

        pw.println(sting); // Send the string to the server
        pw.flush(); // Flush the PrintWriter to ensure data is sent immediately

        // Create a BufferedReader to read data from the socket's input stream
        BufferedReader buff_readr = new BufferedReader(new InputStreamReader(skt.getInputStream()));

        // Read the data sent by the server
        String shortname = buff_readr.readLine();

        // Display the received data
        System.out.println("Client : Data received from the server is : " + shortname);
        System.out.println("Client : !!Hurray!! successfully Data received.");

        skt.close(); // Close the socket connection
    }
}


/*
Overview of above code:
This Java program acts as a simple TCP client. It connects to a server with the IP address "localhost" on port number 8000. The client sends the string "RAM" to the server and waits for a response. Once the server processes the request, it sends back a response, which the client reads and displays. After receiving the response, the client prints it, indicating successful data reception, and then closes the connection to the server. This code demonstrates a basic client-server interaction over a TCP connection, where the client sends a message and receives a response from the server.
*/