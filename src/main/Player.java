package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Player {
	
	private String userName;
	private Calendar timeOfConnect;
	
	private static List<String> names = new ArrayList<String>();
	private static List<Player> players = new ArrayList<Player>();
	
	static{
		//debug
		players.add(new Player("blbinaask"));
		players.add(new Player("dlbinaask"));
		players.add(new Player("elbinaask"));
		players.add(new Player("flbinaask"));
		players.add(new Player("glbinaask"));
		players.add(new Player("hlbinaask"));
		players.add(new Player("ilbinaask"));
		players.add(new Player("clbinaask"));
		players.add(new Player("albinaask"));
		//end of debug
	}
	
	public Player(String userName) {
		this.userName = userName;
		this.timeOfConnect = Calendar.getInstance();
		players.add(this);
		names.add(this.userName);
		updateNames();
	}

	public void disconnectPlayer(){
		players.remove(this);
		names.remove(this);
	}
	
	public static List<String> getConnectedPlayerNames(){
		return names;
	}
	
	public static List<Player> getPlayers(){
		return players;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public Calendar getTimeOfConnect(){
		return this.timeOfConnect;
	}
	
	private void updateNames(){
		Object[] array = names.toArray();
		Arrays.sort(array);
		for(int i=0;i<array.length;i++){
			names.set(i, (String) array[i]);
		}
	}
	
	public String toString(){
		return userName;
	}
	
	public void performCommandAction(ActionsAvailableToPerformOnPlayers action){
		System.out.println("action " + action.name() + " on player " + userName);
	}
}
