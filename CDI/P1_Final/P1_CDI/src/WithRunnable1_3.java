 /**
     * @class WithRunnable1_3
     * @brief A thread is created using the Runnable interface
     * @access public
     */

public class WithRunnable1_3 implements Runnable {
    
    /**
     * Run function. Running to start the thread, and displays a message on screen after one second
     * @return void
     * @access public
     */
    
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("|WithRunnable|- Hello world i'm a java thread");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}