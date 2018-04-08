package com.svilenstefanov.blatt4;
/**
 * Homework 4
 * @author Svilen Stefanov
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Homework 4
 * @author Svilen Stefanov
 */
public class Master {

  public static void main (String[] args) throws IOException, ClassNotFoundException {
    int storeCount = 16;		//can be changed and define the hashtable size
    int storeServerPort = 5000;
    int clientServerPort = 5555;
    
    //Connection with Client
    ServerSocket clientServer = new ServerSocket(clientServerPort);
    ServerSocket storeServer = new ServerSocket(storeServerPort);
    
    Socket [] stores = new Socket[storeCount];
    for (int i = 0; i < storeCount; i++) 			// wait till all stores are connected (the for should)
		stores[i] = storeServer.accept();
    
    Runnable runnable = ()->{		//every thread runs it! (without threads the Runnable is not needed -> for testing)
    	while(true){
        	Socket client=null;
			try {
				client = clientServer.accept();		//wait to till connectivity with a client
			} catch (Exception e) {
				System.out.println("Exception occured!");
				e.printStackTrace();
			}
        	ObjectInputStream in = null;
			try {
				in = new ObjectInputStream (client.getInputStream());		//receive the request from the client
			} catch (Exception e) {
				System.out.println("Exception occured!");
				e.printStackTrace();
			}
        	IRequest request = null;
			try {
				request = (IRequest)in.readObject();		//read the request
			} catch (Exception e) {
				System.out.println("Exception occured!");
				e.printStackTrace();
			}
        	
        	HashString hash = new HashString(storeCount);		//hash the value
        	int value = hash.hash(request.getKey());
        	
        	ObjectOutputStream out = null;
			try {
				out = new ObjectOutputStream(stores[value].getOutputStream());		//send the request to the store
			} catch (Exception e) {
				System.out.println("Exception occured!");
				e.printStackTrace();
			}
    		try {
				out.writeObject(request);		
			} catch (Exception e) {
				System.out.println("Exception occured!");
				e.printStackTrace();
			}
    		try {
				out.flush();
			} catch (Exception e) {
				System.out.println("Exception occured!");
				e.printStackTrace();
			}
    		
    		ObjectInputStream in2 = null;
			try {
				in2 = new ObjectInputStream (stores[value].getInputStream());		//receive the response from the store
			} catch (Exception e) {
				System.out.println("Exception occured!");
				e.printStackTrace();
			}
        	IResponse response = null;
			try {
				response = (IResponse)in2.readObject();			//save the response from the store
			} catch (Exception e) {
				System.out.println("Exception occured!");
				e.printStackTrace();
			}

        	try {
				ObjectOutputStream out2 = new ObjectOutputStream(client.getOutputStream());			//send the response to the client
			} catch (Exception e) {
				System.out.println("Exception occured!");
				e.printStackTrace();
			}
    		try {
				out.writeObject(response);
			} catch (Exception e) {
				System.out.println("Exception occured!");
				e.printStackTrace();
			}
    		try {
				out.flush();
			} catch (Exception e) {
				System.out.println("Exception occured!");
				e.printStackTrace();
			}
        }
    };

    Thread run1 = new Thread(runnable);		//create a thread for the user and start the connection to the store
    run1.start(); 
  }
}
