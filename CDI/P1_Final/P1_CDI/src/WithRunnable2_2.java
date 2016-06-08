     /**
     * @class WithRunnable2_2
     * @brief From the command line parameters receive few threads to create, the program will create and execute the indicated number of threads and show a runtime each thread on the screen.
     * @access public
     */

public class WithRunnable2_2 implements Runnable{
	private int amount;
	
	/**
     * @brief WithRunnable2_2 Constructor 
     * @access public
     */
	
	public WithRunnable2_2(){
		this.amount = 0;
	}
	
	/**
     * Synchronized run function. Running to synchronized start the thread, and displays a message on screen, after show a runtime each thread
     * @return void
     * @access public
     */
	
	public synchronized void run(){
		long start = System.nanoTime();
		this.amount++;	
		System.out.println("Hello, I’m thread number "+this.amount);
		long time = System.nanoTime() - start;
		System.out.println("Bye, this was thread number "+this.amount+" and i took " + time + "ns aprox.");
	}
}
