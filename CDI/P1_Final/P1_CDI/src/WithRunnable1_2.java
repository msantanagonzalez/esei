   /**
     * @class WithRunnable1_2
     * @brief A thread is created using the Runnable interface
     * @access public
     */
    
public class WithRunnable1_2 implements Runnable{
 
    /**
     * Run function. Running to start the thread, and displays a message on screen
     * @return void
     * @access public
     */
    
	public void run(){
		System.out.println("|WithRunnable|- Hello world i'm a java thread");
	}
}
