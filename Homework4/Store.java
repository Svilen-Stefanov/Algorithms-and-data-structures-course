package com.svilenstefanov.blatt4;
/**
 * Homework 4
 * @author Svilen Stefanov
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;

/**
 * Homework 4
 * @author Svilen Stefanov
 */

public class Store {
  public static void main (String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
    String masterHost = "127.0.0.1";
    int masterStorePort = 5000;
    
    Hashtable<String, Integer> hashTable = new Hashtable<>();;
    
    Socket server = new Socket(masterHost, masterStorePort);
    IResponse response = null;
    while(true){
	    ObjectInputStream in = new ObjectInputStream (server.getInputStream());
		IRequest request = (IRequest)in.readObject();
		
		if(request instanceof StoreRequest){
			hashTable.put(request.getKey(), ((StoreRequest) request).getValue()); 		// put in table
			response = new StoreResponse();
		} else {	
			if(hashTable.containsKey(request.getKey()))		//get from table
				response = new ReadResponse(hashTable.get(request.getKey()));
			else response = new ReadResponse();
		}
		
	    ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out. writeObject(response);
		out.flush();
    }
  }
}
