import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @class Server
 * @brief Server implementation of the system
 * @access public
 */

public class Server implements Client_Server{
	
	public static int[][] result;
	public Stack<int[]> jobs;
	public static int Nclients;
	public static int jobsC;
	public static Registry registry;
	
	/**
     * @brief Server constructor, serves to create new clients
     * @param integer clients
     * @access public 
     */
	
	public Server( int clients) {
		result =  new int[999][999];
		jobs = new Stack<int[]>();
		Nclients = clients;
		jobsC = 1;
		
		int job = result.length / Nclients;
		int aux = 0;
		for(int i = 0; i <= Nclients-2; i++){
			jobs.push(new int[] {aux, aux+job - 1, result[0].length, result.length});
			aux += job;
		}
		jobs.push(new int[] {aux, result.length - 1, result[0].length, result.length});
	}
	
	/**
     * @brief Method getJob(), return jobs of the stack
     * @return int[]
     * @access public 
     */
    
	public int[] getJob() throws RemoteException{
		return jobs.pop();
	}
	
	/**
     * @brief Method union, connects the parts of the result image
     * @param int[][] partialResult, integer start, integer end
     * @return void
     * @throws RemoteException
     * @access public 
     */
    
	public void union(int[][] partialResult, int start, int end) throws RemoteException{
		for(int i = start; i <= end; i++){
			result[i] = partialResult[i - start];
		}
		jobsC++;
		System.out.println("Jobs finished: " + (jobsC -1));	
	}
	
	/**
     * @brief Method createFile, writes in a file representation Mandelbrot
     * @param String filename
     * @return void
     * @throws FileNotFoundException
     * @access public static
     */
    
	public static void createFile(String filename) throws FileNotFoundException
	{
		int[][] image = new int[999][999];
		image = result;
		PrintWriter pw = new PrintWriter(filename);
		int width = image[0].length;
		int height = image.length;

		pw.println("P2");
		pw.println(width + " " + height);
		pw.println(255);

		int lineLength = 0;
		for (int i = 0; i < height; ++i)
		{
			for (int j = 0; j < width; ++j)
			{
				int value = image[i][j];

				String stringValue = "" + value;
				int currentLength = stringValue.length() + 1;
				if (currentLength + lineLength > 70)
				{
					pw.println();
					lineLength = 0;
				}
				lineLength += currentLength;
				pw.print(value + " ");
			}
		}
		pw.close();  
	}
	
	/**
     * @brief Method Main of the project, where other programs are initialized
     * @param args
     * @return void
     * @access public static
     */
	
	public static void main(String args[]) {
		
		System.out.println ("Enter the server port: ");
		Scanner sc = new Scanner (System.in);
		int port = sc.nextInt();
		
		System.out.println ("Enter the number of clients to work: ");
		Scanner sc1 = new Scanner (System.in);
		int nclients = sc.nextInt();
		
		System.out.println ("Enter the name of the resulting file: ");
		Scanner sc2 = new Scanner (System.in);
		String resultFile = sc2.next();
		
		sc.close();
		sc1.close();
		sc2.close();
		
		try {
			Server obj = new Server(nclients);
			Client_Server stub = (Client_Server) UnicastRemoteObject.exportObject(obj,0);
			// Bind the remote object's stub in the registry

            //UnicastRemoteObject.unexportObject(registry, true);
			Registry registry = LocateRegistry.createRegistry(port);
			registry = LocateRegistry.getRegistry(port);
			registry.rebind("Practica 5", stub);
			System.err.println("The server is ready");
			while(obj.jobsC -1 < Nclients){
				Thread.sleep(500);
			}
			
			if(resultFile.substring(resultFile.length() - 4, resultFile.length()).equals(".pgm")){
				createFile(resultFile);
			}else{
				createFile(resultFile + ".pgm");
			}

			System.err.println("The execution of the program ends");

			UnicastRemoteObject.unexportObject(obj, true);
			UnicastRemoteObject.unexportObject(registry, true);
			
		} catch (Exception e) {
		}
	}
}
