// Import statements for necessary Java libraries
import java.io.*;
import java.net.*;

// Define the main class for the FTP client
public class ftp_client {
    public static void main(String[] args) throws IOException {
        // Create a BufferedReader to read user input
        BufferedReader buff_rdr = new BufferedReader(new InputStreamReader(System.in));
        Socket client_skt = null; // Initialize a socket for the client

        // Prompt the user to enter the server's IP address
        System.out.print("\nEnter the IP Address displayed in the server side: ");
        String host_addr = buff_rdr.readLine(); // Read the IP address from the user

        // Create a socket connection to the server using the provided IP address and port 3333
        client_skt = new Socket(host_addr, 3333);

        // Create a PrintWriter to send data to the server
        PrintWriter print_wr = new PrintWriter(client_skt.getOutputStream(), true);

        // Prompt the user to enter the name of the file to be sent
        System.out.print("\nEnter the name of the file to be sent: ");
        String my_fName = buff_rdr.readLine(); // Read the filename from the user

        // Create a File object with the specified filename
        File my_file = new File(my_fName);

        // Create a FileInputStream to read the file's contents
        FileInputStream finp_strm = new FileInputStream(my_file);

        // Create an OutputStream to send data to the server
        OutputStream outp_strm = client_skt.getOutputStream();

        // Get the total size (in bytes) of the file
        int i = finp_strm.available();

        // Send the file size to the server as a string
        print_wr.println(String.valueOf(i));
        print_wr.flush();

        // Display a message indicating that the file transfer is in progress
        System.out.print("\nFile is getting processed...\nPlease Wait...\n");

        // Initialize arrays to store file data
        byte[][] bytedata_1 = new byte[100][];
        byte[] bytedata_2 = new byte[i - (i / 100) * 100];

        System.out.println();

        // Read and send the file data in 100 parts
        for (int j = 0; j < 100; j++) {
            bytedata_1[j] = new byte[i / 100];
            finp_strm.read(bytedata_1[j], 0, i / 100);
            outp_strm.write(bytedata_1[j]);
            bytedata_1[j] = null;
            System.gc(); // Request garbage collection to free memory
            System.out.print("\rTransfer Progress status: " + (j + 1) + "% done ||| " +
                    ((i / 100) * (j + 1) / 1048576) + "MB out of " + (i / 1048576) + "MB");
	    /*
	    j + 1 is used to calculate the percentage of completion for each part of the file being sent. j represents the current part being processed (from 0 to 99), and by adding 1, we 	    get the percentage done (1% to 100%).
	    1048576 is used to convert bytes to megabytes. It is equal to 1024^2, as there are 1024 kilobytes in a megabyte and 1024 bytes in a kilobyte. So, i / 1048576 calculates the 	    total file size in megabytes, and ((i / 100) * (j + 1) / 1048576) calculates the transferred data size in megabytes for progress tracking.
	    */
        }

        // Read and send the remaining file data
        finp_strm.read(bytedata_2);
        outp_strm.write(bytedata_2);

        // Display the size of the data transferred
        System.out.println("\n\n\n\nSize of the Data transferred is " + i + " bytes");

        // Close the output stream, file input stream, and socket
        outp_strm.close();
        finp_strm.close();
        client_skt.close();
    }
}


/*
Overview of above code:
This Java program represents a basic FTP client that allows a user to send a file to a server. It first prompts the user to enter the server's IP address and the filename they want to send. The program establishes a socket connection with the server, sends the file's size to the server, and then proceeds to send the file in 100 parts to monitor the transfer progress. The j + 1 is used to calculate the percentage of completion for each part, and 1048576 is used for converting bytes to megabytes for progress tracking. Once the file is completely sent, it displays the size of the data transferred, and the client closes its connections. This code serves as a simple illustration of how a file transfer process might work using sockets and basic progress monitoring.
*/
