/**
 * @class B
 * @access public
 */

public class B implements Runnable{
	private A object;
	
	/**
     * @brief B constructor
     * @param A object
     * @access public 
     */
	public B(A object){
		this.object = object;
	}
	
	/**
     * @brief Run function. Running to start the B object
     * @return void
     * @access public
     */
	
	public void run(){
		try {
			this.object.EnterAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
