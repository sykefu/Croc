import java.rmi.Remote;
import java.rmi.RemoteException;

//client-side interface
public interface RemotePlayer extends Remote {
	public void setlastPlayedCard(int i) throws RemoteException;
	
	//declare here every method needed by client for display or control
}
