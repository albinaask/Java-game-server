package threading;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import main.Server;

public class ListenerThread implements Runnable{
	
	private Thread thread;
	private ServerSocket socketToListenTo;
	
	private List<Socket> accociateSocketList;
	
	public ListenerThread(ServerSocket socketToListenTo, List<Socket> accociateSocketList){
		this.socketToListenTo = socketToListenTo;
		this.accociateSocketList = accociateSocketList;
		thread = new Thread(this, "Asp-listener, this thread listens to new calls from the server");
		thread.setPriority(Thread.NORM_PRIORITY);
		thread.start();
	}
	
	public void stopThread(){
		thread.interrupt();
		System.out.println(thread.isAlive());
		System.out.println(thread.isInterrupted());
	}
	public void startThread(){
		thread.start();
	}
	
	public void run(){
		while(!Server.closeRequested){
			Socket socket = null;
			try {
				socket = socketToListenTo.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(socket != null){
				accociateSocketList.add(socket);
			}
		}
		for(Socket socket:accociateSocketList){
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
