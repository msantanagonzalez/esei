import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

/**
 * @class Client
 * @brief Client implementation of the system
 * @access public
 */

public class Client {
	
    
	private static Client_Server stub;
	
	/**
     * @brief Client constructor, serves to create new clients
     * @access public 
     */
	
	private Client() {}
	
	
	/**
     * @brief Method plot, where the client calculate the corresponding part of the image 
     * @param integer infRow, integer supRow, integer height, integer width
     * @return void
     * @throws RemoteException
     * @access public static
     */
	
	public static void plot(int infRow, int supRow, int height, int width) throws RemoteException{
		int[][] toSend = new int[ supRow - infRow + 1 ][width];
		for (int row = infRow; row <= supRow; row++) {
			for (int col = 0; col < width; col++) {
				double c_re = (col - width/2.0)*4.0/width;
				double c_im = (row - height/2.0)*4.0/width;
				double x = 0, y = 0;
				int iteration = 0;
				while (x*x+y*y <= 4 && iteration < 255) {
					double x_new = x*x - y*y + c_re;
					y = 2*x*y + c_im;
					x = x_new;
					iteration++;
				}
				if (iteration < 255) {
					toSend[row - infRow][col] = iteration;
				}else{
					toSend[row - infRow][col] = 0;
				}  
			}
		}
		stub.union(toSend, infRow, supRow);
	}
	
	/**
     * @brief Method Main of the project, where other programs are initialized
     * @param args
     * @return void
     * @access public static
     */
	
	public static void main(String[] args) {
		
		String host = "127.0.0.1"; 
		int port = 8080;
		try {
			Registry registry = LocateRegistry.getRegistry(host,port);
			stub = (Client_Server) registry.lookup("Practica 5");
	int [] jobs = stub.getJob();
	plot(jobs[0], jobs[1], jobs[2], jobs[3]);

	} catch (Exception e) {
	System.err.println("Client exception: " + e.toString());
	e.printStackTrace();
	}
	}

}
