// Import necessary libraries for Input/Output operations
import java.io.*;
// Import necessary classes for networking (Sockets, ServerSocket, etc.)
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try (
            // Step 1: Create a Socket object to connect to the server at localhost (127.0.0.1) on port 12345
            // This establishes a TCP connection to the server
            Socket socket = new Socket("localhost", 12345)
        ) {
            System.out.println("Connected to the server."); // Confirmation message after successful connection

            // Step 2: Setup a reader to receive data from the server
            // socket.getInputStream() fetches the input stream from the server
            // InputStreamReader converts byte stream to character stream
            // BufferedReader reads text from the input stream efficiently (line by line)
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Step 3: Setup a writer to send data to the server
            // socket.getOutputStream() fetches the output stream to write data to server
            // PrintWriter is used to send text-based data; 'true' enables auto-flush (sends data immediately)
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Step 4: Setup a reader to capture user input from the console
            // This allows the client to type messages to send to the server
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            // Step 5: Create a separate thread to listen for messages from the server
            // This ensures the client can simultaneously read and send messages (full-duplex communication)
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;

                    // Continuously listen for incoming messages from the server
                    while ((message = reader.readLine()) != null) {
                        // Display message with visual formatting for clarity
                        System.out.println("------------------------");
                        System.out.println("[Server]: " + message); // Server message prefixed clearly
                        System.out.println("------------------------");
                    }
                } catch (IOException e) {
                    // If an error occurs during reading (e.g., server disconnects), notify user
                    System.out.println("Connection closed.");
                }
            });

            // Step 6: Start the receiving thread so that server messages are handled in the background
            receiveThread.start();

            // Step 7: In the main thread, keep reading input from the user and send it to the server
            String text;
            while ((text = console.readLine()) != null) {
                writer.println(text); // Send user's input message to the server
            }

        } catch (IOException e) {
            // Handle exceptions such as failed connection, disconnection, or I/O errors
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
}
