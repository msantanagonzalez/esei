/**
 * @class Main
 * @brief Main class of the project.
 * @access public
 */
import java.util.ArrayList;

public class main {
    /**
     * @brief Method waitUntilFinished.Wait until they stop running the threads, and then deletes
     * @param Array of threads
     * @return void
     * @access public static 
     */
	public static void waitUntilFinished(ArrayList<Thread> myThreads){
		int amount = myThreads.size();
		while(!myThreads.isEmpty()){
			for(int i=0;i<amount;i++){
				if(!myThreads.get(i).isAlive()){
					myThreads.remove(i);
					amount--;
				}
			}
		}
	}
	
	 /**
     * @brief Method Main of the project, where other programs are initialized
     * @param args
     * @return void
     * @access public static
     */
	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("|Error|- You must indicate one param");
		}else{
			int threads = Integer.parseInt(args[0]);
			System.out.println("----------| 2-Contador |----------");
				Contador c = new Contador();
				ArrayList<Thread> myThreads = new ArrayList<Thread>();
				for(int i=0;i<threads;i++){
					Thread t = new Thread(c);
					myThreads.add(t);
					t.start();
				}
				waitUntilFinished(myThreads);
			System.out.println("----------| 3-Sincronizacion |----------");
				A mainObject = new A();
				for(int i=0;i<threads;i++){
					B o1 = new B(mainObject);
					Thread t = new Thread(o1);
					myThreads.add(t);
					t.start();
				}
				waitUntilFinished(myThreads);
		}
	}
}
