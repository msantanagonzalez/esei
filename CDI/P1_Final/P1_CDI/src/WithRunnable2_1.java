     /**
     * @class WithRunnable2_1
     * @brief From the command line parameters receive few threads to create, the program will create and execute the indicated number of threads and show a message on the screen.
     * @access public
     */

public class WithRunnable2_1 implements Runnable{
	private int amount;
	
	/**
     * @brief WithRunnable2_1 Constructor 
     * @access public
     */
	
	public WithRunnable2_1(){
		this.amount = 0;
	}

	/**
     * Synchronized run function. Running to synchronized start the thread, and displays a message on screen, after one second displays other message
     * @return void
     * @access public
     */
	
	public synchronized void run(){
		this.amount++;	
		System.out.println("Hello, I’m thread number "+this.amount);
		try {
			Thread.sleep(1000);
			System.out.println("Bye, this was thread number "+this.amount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
