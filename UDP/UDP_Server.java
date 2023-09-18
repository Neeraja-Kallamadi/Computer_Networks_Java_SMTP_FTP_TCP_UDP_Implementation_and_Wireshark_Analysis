// Import statements for necessary Java libraries
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// Define the main class for the UDP server
public class UDP_Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket dgs = new DatagramSocket(8080); // Create a DatagramSocket to listen on port 8080

        byte[] bytes_1 = new byte[1024]; // Create a byte array to receive data
        DatagramPacket dgp1 = new DatagramPacket(bytes_1, bytes_1.length); // Create a DatagramPacket to receive data
        System.out.println("Waiting for a client to connect...."); // Display a waiting message

        dgs.receive(dgp1); // Receive data from a client into the DatagramPacket
        String string3 = new String(dgp1.getData(), 0, dgp1.getLength()); // Convert the received data to a string
        System.out.println(string3); // Display the received message

        String server_reply = "Message from the Server: Yes partner, I am connected!"; // Define the server's reply message
        byte bytes_2[] = String.valueOf(server_reply).getBytes(); // Convert the reply message to bytes

        InetAddress inaddr = InetAddress.getLocalHost(); // Get the local host's IP address
        DatagramPacket dgp2 = new DatagramPacket(bytes_2, bytes_2.length, inaddr, dgp1.getPort()); // Create a DatagramPacket to send the reply
        dgs.send(dgp2); // Send the reply to the client

        dgs.close(); // Close the DatagramSocket
    }
}


/*
Overview of above code:
This Java program serves as a straightforward UDP server. It begins by creating a DatagramSocket, a communication point for receiving UDP packets on port 8080. The server prepares to receive data by setting up a DatagramPacket with a buffer to store the incoming data and waits for a client to connect. When the client sends a message, the server extracts and displays it as a string. Next, the server crafts a reply message, converts it to bytes, and sends it back to the client, specifying the client's address and port. Finally, the DatagramSocket is closed. This code showcases a basic UDP server that listens for client messages, responds, and closes the socket after communication.
*/