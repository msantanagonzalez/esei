import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @class Client_Server
 * @brief Is the interface of the system
 * @access public
 */
public interface Client_Server  extends Remote{
	
	public int[] getJob() throws RemoteException;

	public void union(int[][] partialResult, int start, int end) throws RemoteException;
	
}
