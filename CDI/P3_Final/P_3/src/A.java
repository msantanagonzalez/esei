/**
 * @class A
 * @access public
 */

public class A {
    
	private int amount=0;
	
	/**
     * @brief void EnterAndWait().Prints a message indicating which is the thread that is beginning to run, then it stops for a few seconds , and reprints another message indicating the thread that is running to execute the method all. 
     * @return void 
     * @access public synchronized 
     */
	
	public synchronized void EnterAndWait() throws InterruptedException{
		amount++;
		System.out.println("Started Thread: "+amount);
		Thread.sleep(2000);
		System.out.println("Finished Thread: "+amount);
	}
}
