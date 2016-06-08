/**
 * @class Contador
 * @brief Implements a simple concurrent counter.
 */
import java.util.Random;

public class Contador implements Runnable{
	private int value;
	
	/**
     * @brief Executes a loop n iterations that increases an internal variable at a time, and at the end returns current value of that variable.
     * @param int n 
     * @return int
     * @access public synchronized 
     */
	public synchronized int incrementar(int n){
		System.out.println("----------");
		System.out.println("Before:"+this.value);
		System.out.println("Adding:"+n);
		for(int i=0;i<n;i++){
			this.value++;
		}
		return this.value;
	}
	/**
     * @brief Run function. Running to start the Contador
     * @return void
     * @access public
     */
	public void run(){ 
		try {
			Random r = new Random();
			int v = r.nextInt(50);
			for(int i=0;i<2;i++){
				System.out.println("After: "+ this.incrementar(v));
				Thread.sleep(1000);	
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

