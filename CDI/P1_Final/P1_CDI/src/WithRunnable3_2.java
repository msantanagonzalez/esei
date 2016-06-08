
/**
 * @class WithRunnable3_2
 * @brief Case 3.2 .The program create and execute the threads and show the difference between the interrupted () and interrupted () methods
 * @access public
 */

public class WithRunnable3_2 implements Runnable{
	private int amount;
	
	/**
     * @brief WithRunnable3_2 Constructor 
     * @access public
     */
	public WithRunnable3_2(){
		this.amount=0;
	}
	
	 /**
     * Synchronized run function. Running to synchronized start the threads, and displays a message on screen, after five seconds display other message.
     * @return void
     * @access public
     */
	
	public synchronized void run() {
		try {
			amount++;
			System.out.println("Hello, I’m thread number "+this.amount);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("Bye,thread number "+this.amount + " interrupted");
			return;
		}
		System.out.println("Bye, this was thread number "+this.amount);
	}

}
