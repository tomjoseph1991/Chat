package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import secureChat.Message;
import secureChat.User;

public class ChatClient {
	
	private static ChatClientThread clientThread = null;
	
public static void main(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try {
        	Socket socket = new Socket(hostName, portNumber);
        	clientThread = new ChatClientThread(socket);
        	clientThread.start();
        	
        	User user = new User ();
            
            Scanner keyboard = new Scanner(System.in);
            String option = "1";
            
            while ( option != "0" ) {
            	System.out.println("Please select an option:");
            	System.out.println("\t(0) Quit");
            	System.out.println("\t(1) Submit User");
            	System.out.println("\t(2) Submit Message");
            	
            	option = keyboard.nextLine();
            	System.out.println("Input: " + option);
            	
            	switch(option){
            	case "1":
            		System.out.println("Please provide a screenname");
            		user.setScreenname(keyboard.nextLine());                
                    clientThread.sendUser(user);
            		break;
            	case "2":
            		System.out.println("Please provide a message");
            		String message = keyboard.nextLine();
            		clientThread.sendMessage(new Message(user, user, message));
            		break;
            	case "0":
            	default:
            		option = "0";
            		System.out.println("Good bye!");	
            	}
            }
            
            
            keyboard.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }

public static void closeThread(ChatClientThread chatClientThread) {
	if (clientThread == chatClientThread) {
		clientThread = null;
	}
	
}

}
