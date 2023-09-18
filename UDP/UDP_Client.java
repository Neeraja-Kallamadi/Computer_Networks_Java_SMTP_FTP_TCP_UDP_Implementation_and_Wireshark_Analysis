// Import statements for necessary Java libraries
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// Define the main class for the UDP client
public class UDP_Client {
    public static void main(String[] args) throws Exception {
        DatagramSocket dgsk = new DatagramSocket(); // Create a DatagramSocket for sending and receiving UDP packets

        String client_req = "Message of Client: Are you connected?"; // Define the client's request message
        System.out.println(client_req); // Display the client's request message

        byte[] bytes_1 = String.valueOf(client_req).getBytes(); // Convert the request message to bytes

        InetAddress in_addr = InetAddress.getLocalHost(); // Get the local host's IP address
        DatagramPacket dgpt_1 = new DatagramPacket(bytes_1, bytes_1.length, in_addr, 8080); // Create a DatagramPacket to send the message to the server
        dgsk.send(dgpt_1); // Send the DatagramPacket to the server

        byte[] bytes_2 = new byte[1024]; // Create a byte array to receive data
        DatagramPacket dgpt_2 = new DatagramPacket(bytes_2, bytes_2.length); // Create a DatagramPacket to receive data
        dgsk.receive(dgpt_2); // Receive data from the server

        String string = new String(dgpt_2.getData()); // Convert the received data to a string
        System.out.println(string); // Display the received data

        dgsk.close(); // Close the DatagramSocket
    }
}


/*
why specially message is converted to bytes here?
In this UDP client code, the message is converted to bytes because UDP (User Datagram Protocol) is a connectionless protocol that transmits data in the form of datagrams, which are essentially packets of bytes. When you send data over a UDP socket, you need to convert your data (in this case, the message) into a byte array so that it can be transmitted as bytes in the UDP packet.
Here's why the message is converted to bytes:
Data Serialization: Messages or data in Java are typically represented as strings or objects. When sending data over a network using UDP, you need to serialize this data into a byte format that can be transmitted. The String.valueOf(client_req).getBytes() line accomplishes this serialization by converting the string message into a byte array.
Network Transmission: UDP operates at the transport layer of the OSI model and deals with raw bytes. It doesn't handle higher-level data types like strings or objects directly. Therefore, you must convert your message (which is a string in this case) into bytes to send it over the UDP socket.
Compatibility: Converting the message to bytes ensures that it can be sent and received as a common data type that both the client and server can understand. This byte representation is a universal format for transmitting data over the network.
In summary, converting the message to bytes is essential for sending data over a UDP socket, as UDP deals with raw bytes rather than higher-level data types like strings or objects.
*/


/*
Overview of above code:
This Java program functions as a simple UDP client. It starts by creating a DatagramSocket, a communication endpoint for sending and receiving UDP packets. The client prepares a message, "Message of Client: Are you connected?", and converts it into bytes since UDP transmits data in byte format. It then specifies the destination server's IP address (localhost in this case) and port number (8080) in a DatagramPacket and sends the message to the server. The client then prepares to receive a response by creating another DatagramPacket with a buffer to store the data. Once a response is received from the server, it is converted back into a string and displayed. Finally, the DatagramSocket is closed. This code showcases a basic UDP client that sends a message to a server, receives a response, and displays it.
*/