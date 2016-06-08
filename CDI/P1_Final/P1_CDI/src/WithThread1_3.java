/**
     * @class WithThread1_3
     * @brief A thread is created using the Thread class
     * @access public
     */

public class WithThread1_3 extends Thread{
    
    /**
     * Run function. Running to start the thread, and displays a message on screen after one second
     * @return void
     * @access public
     */
    
	public void run(){
		try {
			Thread.sleep(1000);
			System.out.println("|WithTread|- Hello world i'm a java thread");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
