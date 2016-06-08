/**
 * @class WithRunnable3_3
 * @brief The program create and execute the thread and and waits for a possible signal to be interrupted by the user.
 * @access public
 */

public class WithRunnable3_3 implements Runnable{
	private boolean signal=false;
	
	/**
     * @brief Method sendSignal. Send a signal for interrupt execution of thread (signal=true)
     * @return void
     * @access public
     */
	
	public void sendSignal(){
		this.signal=true;
	}
	
	/**
     * @brief Method getSignal. Observe the state of signal
     * @return boolean
     * @access public
     */
	
	public boolean getSignal(){
		return this.signal;
	}
	
	/**
     * Run function. Running to start the thread, and waits for a possible signal to be interrupted by the user.
     * @return void
     * @access public
     */
	
	public void run(){
			try {
				System.out.println("Hello, I´m a Thread");
				boolean flag=false;
				for(int i=0;i<10;i++){
					if(getSignal() && i<4 && !flag){
						System.out.println("Signal received");
						flag=true;
					}
					if(getSignal() && i>4 && !flag){
						throw new InterruptedException();
					}
					System.out.println("I´m alive");
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				System.out.println("Bye, I was interrupted by your signal");
				return;
			}
			System.out.println("Good bye");
	}
}
