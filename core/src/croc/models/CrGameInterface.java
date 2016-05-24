package croc.models;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author CrocTeam
 * All the server can tell you about the game.
 */
public interface CrGameInterface extends Remote {
	public Player[] getPlayers() throws RemoteException;
	public ArrayList<Pirate> getPirateOrder() throws RemoteException;
	public Player getWinner() throws RemoteException;
}
