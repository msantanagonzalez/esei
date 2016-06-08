import java.util.ArrayList;
import java.util.Iterator;

/**
 * @class WithRunnable2_6
 * @brief The program creates and executes a growing number of threads, displaying messages on screen and performing mathematical operations and shows a graph in which the number of threads in relation compared with the runtime
 * @access public
 */

public class WithRunnable2_6 implements Runnable{
	private int amount;
	/** To save Threads time*/
	private ArrayList<ArrayList<Long>> times = new ArrayList<ArrayList<Long>>(); 
	private int option;
	/** Time when the first threat start to run */
	private long initTime;
	/** Time when the last threat finish the execution */
	private long finishTime;
	/** Flag to activate the setInitTime method */
	private boolean flagInit;
	/** Flag to activate the setFinishTime method */
	private boolean flagFinish;
	
	/**
     * @brief WithRunnable2_6 Constructor 
     * @access public
     */
	
	public WithRunnable2_6(int o){
		this.amount = 0;
		this.option = o;
		this.flagInit = false;
		this.flagFinish = false;
	}
	
	/**
     * @brief void setInitTime. Saves when the first thread starts the execution
     * @param t
     * @return void
     * @access public
     */
	
	public void setInitTime(long t){
		this.initTime = t;
	}
	
	/**
     * @brief void setFinishTime. Saves when the last thread finish the execution 
     * @param t
     * @return void
     * @access public
     */
	
	public void setFinishTime(long t){
		this.finishTime = t;
	}
	
	/**
     * @brief Method getInitTime. Get when the last thread starts the execution 
     * @return long
     * @access public
     */
	
	public long getInitTime(){
		return this.initTime;
	}
	
	/**
     * @brief Method getFinishTime. Get when the last thread finish the execution 
     * @return long
     * @access public
     */
	
	public long getFinishTime(){
		return this.finishTime;
	}
	
	/**
     * @brief Method getTimes. Get ArrayList of times 
     * @return ArrayList<ArrayList<Long>>
     * @access public
     */
	
	public ArrayList<ArrayList<Long>> getTimes(){
		return times;
	}
	
	/**
     * @brief void addElement. Adds a ArrayList object into the main ArrayList 
     * @param e
     * @return void
     * @access private
     */
	
	private void addElement(ArrayList<Long> e){
		times.add(e);
	}
	
	/**
     * @brief void addElement. Returns the running time of the Threads
     * @return long
     * @access public
     */
	
	public long getMaxTime(){
		long time=0;
		Iterator<ArrayList<Long>> it = times.iterator();
		while(it.hasNext()){
			ArrayList<Long> actual = it.next();
			time = time + actual.get(2);
		}
		return time;
	}
	
	/**
     * @brief Method setValues. Adds the thread time information into a ArrayList object 
     * @param s,f,t
     * @return ArrayList<Long>
     * @access private
     */
	
	private ArrayList<Long> setValues(long s, long f, long t){
		ArrayList<Long> tempo = new ArrayList<Long>();
		tempo.add(s);
		tempo.add(f);
		tempo.add(t);
		return tempo;
	}
	
	/**
     * @brief Method void withText. Show messages on the screen 
     * @return void
     * @access private
     */
	
	private void withText(){
		System.out.println("Hello, I’m thread number "+this.amount);
		System.out.println("Bye, this was thread number "+this.amount);
	}
	
	/**
     * @brief Method void withText. Performs mathematical operations 
     * @return void
     * @access private
     */
	
	private void withMath(){
		int valor=0;
		for(int i=1;i<=100;i+=2){
			for(int j=2;j<=200;j+=2){
				valor = valor + (i+j);
			}
		}
	}
	 /**
     * Synchronized run function. Running to synchronized start the threads, displays a messages on screen and save the times
     * @return void
     * @access public
     */
	
	public synchronized void run(){
		this.amount++;
		long start = System.nanoTime();
		if(!this.flagInit){
			setInitTime(start);
			this.flagInit = true;
		}
		if(this.option==1){
			withText();
		}else{
			withMath();
		}
		long end = System.nanoTime();
		if(!this.flagFinish){
			setFinishTime(end);
			this.flagFinish = true;
		}
		long totalTime = end - start;
		addElement(setValues(start, end, totalTime));
	}
}
