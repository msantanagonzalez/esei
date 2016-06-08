    /**
     * @class WithThread1_2
     * @brief A thread is created using the Thread class
     * @access public
     */
    
public class WithThread1_2 extends Thread{
    
    /**
     * Run function. Running to start the thread, and displays a message on screen
     * @return void
     * @access public
     */
    
	public void run(){
		System.out.println("|WithTread|- Hello world i'm a java thread");
	}
}