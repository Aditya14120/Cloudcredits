import java.io.*;//Import classes for Input Outpput Operation
import java.net.*;//Import classes for Networking(Socket,Server)
//Main class for chat server
public class ChatServer {
    //Main method:Entry point of the server program 
    public static void main(String[] args) {
        //Create a server socket on port 12345
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            //Print message that server is ready and waiting for client to connect
            System.out.println("Server started. Waiting for client...");
            //Accept an incoming client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");//Confirm client connection
            //Setup input stream to read messages from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Setup output stream to send messages to the client
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            //Read server user input from the terminal 
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            //Create a new thread to handle incoming messages from the client
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    //Continiously read messages send by the client
                    while ((message = reader.readLine()) != null) {
                        //Using visual seprator and displaying client message
                        System.out.println("---------------------------");
                        System.out.println("[Client]: " + message);
                        System.out.println("---------------------------");
                    }
                } catch (IOException e) {
                    //Print error message if client disconnects or communication fails
                    System.out.println("Connection closed.");
                }
            });
            //start the threadto recieve messages
            receiveThread.start();
            //Main thread:Continiously read user input from the server-side terminaal
            String text;
            while ((text = console.readLine()) != null) {
                //send servers message to the client
                writer.println(text);
            }

        } catch (IOException e) {
            //Catch and print any IOException that occur during server operation
            e.printStackTrace();
        }
    }
}
