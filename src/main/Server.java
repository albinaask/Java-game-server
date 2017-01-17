package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import threading.ListenerThread;

public class Server {

	private int port = 45723;
	
	private ServerSocket serverSocket;
	private List<Socket> allSockets = new ArrayList<Socket>();
	
	public static boolean closeRequested = false;
	
	private ListenerThread listener;
	private ServerWindow window;
	
	public Server(ServerWindow window){
		this.window = window;
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start(){
		window.writeToLog("server starting process initialized");
		
		listener = new ListenerThread(serverSocket, allSockets);
		
		window.writeToLog("server starting process fulfilled");
	}
	
	public void stop(){
		window.writeToLog("server shutting down");
		closeRequested = true;
		listener.stopThread();
		System.out.println("test");
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		window.writeToLog("server sucessfully shut down");
	}
	
	public void inputRecived(String command){
		System.out.println("recived command: " + command);
	}
	
}
