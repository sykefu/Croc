import java.rmi.RemoteException;
import java.util.ArrayList;

//client-side interface
public interface CrGameInterface extends Remote {
	public Player[] getPlayers() throws RemoteException;
	public ArrayList<Pirate> getPirateOrder() throws RemoteException;
	public Player getWinner() throws RemoteException;
}
