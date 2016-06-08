import java.util.ArrayList;
import java.util.Iterator;

/**
 * @class WithRunnable2_4
 * @brief From the command line parameters receive few threads to create, the program will create and execute the indicated number of threads and displays the total time it takes to run all the threads on the screen.
 * @access public
 */

public class WithRunnable2_4 implements Runnable{
	private int amount;
	private ArrayList<Long> times = new ArrayList<Long>();
	
	/**
     * @brief WithRunnable2_4 Constructor 
     * @access public
     */
	
	public WithRunnable2_4(){
		this.amount = 0;
	}
	
	/**
     * @brief Void setTime. Add one time in the ArrayList
     * @param time
     * @return void
     * @access public
     */
	
	public void setTime(long time){
		times.add(time);	
	}
	
	/**
     * @brief Method getTimes. Add one time in the ArrayList
     * @return Iterator<Long>
     * @access public
     */
	
	public Iterator<Long> getTimes(){
		return times.iterator();
	}
	
	   /**
     * Synchronized run function. Running to synchronized start the threads, displays a messages on screen and save the times
     * @return void
     * @access public
     */
	
	public synchronized void run(){
		long start = System.nanoTime();
		this.amount++;	
		System.out.println("Hello, I’m thread number "+this.amount);
		System.out.println("Bye, this was thread number "+this.amount);
		long time = System.nanoTime() - start;
		setTime(time);
	}
}
