package croc.models;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * @author CrocTeam
 * client-side interface
 * All the client can do on Players.
 */
public interface RemotePlayer extends Remote {
	public void setlastPlayedCard(int i) throws RemoteException;
	
//	public PirateColor getColor() throws RemoteException;
	public String getColor() throws RemoteException;
	
	public String getName() throws RemoteException;
	
	public Pirate[] getPirate() throws RemoteException;
	
	public boolean isBot() throws RemoteException;
	
	//declare here every method needed by client for display or control
}
