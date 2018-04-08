package com.svilenstefanov.blatt4;
/**
 * Homework 4
 * @author Svilen Stefanov
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please chose a command. The following commands are available: (please enter the correspondung number)\n"
						+ "1 - \"store key value\"" + "\n"
						+ "2 - \"read key\"\n");
		int req = 0;
		boolean isInt = true;
		while (!(isInt = input.hasNextInt()) || (req = input.nextInt()) != 1 || req != 2) { // check whether the user typed the right input
			try {
				if (req != 1 && req != 2 && isInt)
					System.out.println("Invalid input! Please enter 1 or 2!");
				else if (!isInt)
					throw new InputMismatchException();
				else
					break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please enter 1 or 2!");
				input.next();
			}
		}
		
		String message = new String();
		System.out.println("Enter your key: ");
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); // expect key from the user
		try {
			message = buffer.readLine();
		} catch (IOException e) {
			System.out.println("The key couldn't be read");
			e.printStackTrace();
		}
		
		IRequest request;
		if (req == 2) // user wants to read
			request = new ReadRequest(message);
		else {		//user wants to store
			int value = 0;
			System.out.println("Enter an integer for the value to be saved in the hash table: "); 			//no time for error check!
			value = input.nextInt();
			request = new StoreRequest(message, value);
		}

		String masterHost = "127.0.0.1";
		int masterClientPort = 5555;
		try {
			Socket server = new Socket(masterHost, masterClientPort);		//connectivity to master
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeObject(request);
			out.flush();

			ObjectInputStream in = new ObjectInputStream (server.getInputStream());		//wait for the response of the master
			IResponse response = null;
			try {
				response = (IResponse)in.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException occured!");
				e.printStackTrace();
			}

			ResponseVisitor rv = new ResponseVisitor();
			rv.__((ReadResponse readResponse) -> {
				SerializableOptional<Integer> result = readResponse.getValue();
				if (result.isPresent())
					System.out.println(" Read response with value " + result.get() + ".");
				else
					System.out.println(" Read response : Uknown key!");
			}, (StoreResponse storeResponse) -> {
				System.out.println(" Store successful !");
			});
			response.accept(rv);
		} catch (UnknownHostException e) {
			System.out.println("The known is unknown!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException occured");
			e.printStackTrace();
		}
	}
}