import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
/**
 * @class Main
 * @brief Main class of the project, where other programs are initialized
 */
public class main {
    
	/**
	 * @brief Method waitUntilFinished.Wait until they stop running the threads, and then deletes
	 * @param Array of threads
	 * @return void
	 * @access public static 
	 */
    
	public static void waitUntilFinished(ArrayList<Thread> myThreads){
		int amount = myThreads.size();
		while(!myThreads.isEmpty()){
			for(int i=0;i<amount;i++){
				if(!myThreads.get(i).isAlive()){
					myThreads.remove(i);
					amount--;
				}
			}
		}
	}
	
	/**
	 * @brief Method Main of the project, where other programs are initialized
	 * @param args
	 * @throws InterruptedException,ArrayIndexOutOfBoundsException
	 * @return void
	 * @access public static
	 */
	
	public static void main(String[] args) throws InterruptedException,ArrayIndexOutOfBoundsException {
		try{
			if(args.length < 1){
				System.out.println("|ERROR| - You must indicate at least one argument");
			}else{
				switch (args[0]) {
				    
				    /**
				     * @brief Case 1.2 .A thread is created using the Thread class and Runnable interface
				     */
				    
				case "1.2":
					/**
					 *  Create the threads
					 */
				    
					WithThread1_2 t1 = new WithThread1_2();
					WithRunnable1_2 r1 = new WithRunnable1_2();
					Thread r1_1 = new Thread(r1);
					
					/**
					 *  Start the threads
				     */ 
					
					t1.start();
					r1_1.start();
					
					/**
					 *  Wait until there큦 no running threads
					 */
					
					ArrayList<Thread> myThreads = new ArrayList<Thread>();
					myThreads.add(t1);
					myThreads.add(r1_1);
					waitUntilFinished(myThreads);
					
					break;
					
					/**
                     * @brief Case 1.3 .A thread is created using the Thread class and Runnable interface, and when running displays a message on screen after one second
                     */
					
				case "1.3":
				    
				    /**
                     *  Create the threads
                     */
					WithThread1_3 t2 = new WithThread1_3();
					WithRunnable1_3 r2 = new WithRunnable1_3();
					Thread r2_1 = new Thread(r2);
					
					/**
					 *  Start the threads 
					 */  
					
					t2.start();
					r2_1.start();
					
					/**
					 *  Wait until there큦 no running threads
					 */
					
					ArrayList<Thread> myThreads2 = new ArrayList<Thread>();
					myThreads2.add(t2);
					myThreads2.add(r2_1);
					waitUntilFinished(myThreads2);
					
					break;
					
					/**
                     * @brief Case 1.4 .A thread is created using the Runnable interface, and list the threads what are running on screen 
                     */
					
				case "1.4":
				    
					/**
					 *  Create the thread
					 */
				    
					WithRunnable1_3 r3 = new WithRunnable1_3();
					Thread r3_1 = new Thread(r3);
					
					/**
					 *  Start the thread
					 */
					
					r3_1.start();
					
					/**
                     *List the threads what are running on screen 
                     */
					
					System.out.println("-----|Active threads|-----: ");
					Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
					Iterator<Thread> it = threadSet.iterator();
					while(it.hasNext()){
						System.out.println(it.next().getName());
					}
					
					/**
					 *  Wait until there큦 no running thread 
					 */
					
					ArrayList<Thread> myThreads3 = new ArrayList<Thread>();
					myThreads3.add(r3_1);
					waitUntilFinished(myThreads3);
					
					break;
					
					/**
					* @brief Case 2.1 .From the command line parameters receive few threads to create, the program will create and execute the indicated number of threads and show a messages on the screen.	
				    */
					
				case "2.1":
					if(args.length != 2){
						System.out.println("|ERROR| - You must indicate two arguments");
					}else{
						try {
						    
						    /**
		                     *  It creates the number of threads specified and start the threads
		                     */
						    int amount = Integer.parseInt(args[1]);
						    
						    ArrayList<Thread> myThreads4 = new ArrayList<Thread>();
						    WithRunnable2_1 r4 = new WithRunnable2_1();
						    
						    for(int i=0;i<amount;i++){
						    	Thread r4_rx = new Thread(r4);
						    	r4_rx.start();
						    	myThreads4.add(r4_rx);
						    }
						    
						    /**
		                     *  Wait until there큦 no running threads
		                     */
						    
							waitUntilFinished(myThreads4);
						} catch (NumberFormatException e) {
						    System.out.println("|ERROR| - Second argument must be a integer");
						}
					}
					
					break;
				
					/**
	                 * @brief Case 2.2 .From the command line parameters receive few threads to create, the program will create and execute the indicated number of threads and show a runtime each thread on the screen.   
	                 */
					
				case "2.2":
					if(args.length != 2){
						System.out.println("|ERROR| - You must indicate two arguments");
					}else{
						try {
						    
						    /**
                             *  It creates the number of threads specified and start the threads
                             */
						    
						    int amount = Integer.parseInt(args[1]);
						    
						    ArrayList<Thread> myThreads4 = new ArrayList<Thread>();
						    WithRunnable2_2 r4 = new WithRunnable2_2();
						    
						    for(int i=0;i<amount;i++){
						    	Thread r4_rx = new Thread(r4);
						    	r4_rx.start();
						    	myThreads4.add(r4_rx);
						    }
						    
						    /**
                             *  Wait until there큦 no running threads
                             */
						    
							waitUntilFinished(myThreads4);
						} catch (NumberFormatException e) {
						    System.out.println("|ERROR| - Second argument must be a integer");
						}
					}
					break;
				
				/**
				* @brief Case 2.4 .From the command line parameters receive few threads to create, the program will create and execute the indicated number of threads and displays the total time it takes to run all the threads on the screen.
				*/
					
				case "2.4":
					if(args.length != 2){
						System.out.println("|ERROR| - You must indicate two arguments");
					}else{
						try {
						    
						    /**
                             *  It creates the number of threads specified and start the threads
                             */
						    
						    int amount = Integer.parseInt(args[1]);
						    
						    ArrayList<Thread> myThreads4 = new ArrayList<Thread>();
						    WithRunnable2_4 r4 = new WithRunnable2_4();
						    for(int i=0;i<amount;i++){
						    	Thread r4_rx = new Thread(r4);
						    	r4_rx.start();
						    	myThreads4.add(r4_rx);
						    }
						    
						    /**
                             *  Wait until there큦 no running threads
                             */
						    
							waitUntilFinished(myThreads4);
							Iterator<Long> it2_4 = r4.getTimes();
							
							/**
                             *  Calculates and displays the total run time of the threads 
                             */
							
							long time=0;
							while(it2_4.hasNext()){
								time = time + it2_4.next();
							}
							
							System.out.println("Total time: "+time + " ns");
							
						} catch (NumberFormatException e) {
						    System.out.println("|ERROR| - Second argument must be a integer");
						}
					}
					break;
					
				
					/**
		            * @brief Case 2.5 .In this case displays the creation time and execution of the threads on the screen.
		            */
					
				case "2.5":
					if(args.length != 2){
						System.out.println("|ERROR| - You must indicate two arguments");
					}else{
						try {
						    
						    /**
                             *  It creates the number of threads specified and start the threads
                             */
						    
						    int amount = Integer.parseInt(args[1]);
						    
						    ArrayList<Thread> myThreads4 = new ArrayList<Thread>();
						    WithRunnable2_4 r4 = new WithRunnable2_4();
						    
						    /**
                             *  Calculates and displays the creation time and execution time of the threads 
                             */
						    
						    long cTimeStart = System.nanoTime();
						    for(int i=0;i<amount;i++){
						    	Thread r4_rx = new Thread(r4);
						    	myThreads4.add(r4_rx);
						    }
						    long cTime = System.nanoTime() - cTimeStart;
						    System.out.println("Threads creation time: "+cTime+" ns.");
						    
						    for(int i=0;i<amount;i++){
						    	myThreads4.get(i).start();
						    }
							waitUntilFinished(myThreads4);
							Iterator<Long> it2_4 = r4.getTimes();
							
							long time=0;
							while(it2_4.hasNext()){
								time = time + it2_4.next();
							}
							
							System.out.println("Threads execution time: "+time + " ns.");
							
						} catch (NumberFormatException e) {
						    System.out.println("|ERROR| - Second argument must be a integer");
						}
					}
					break;
					
					/**
	                 * @brief Case 2.6 .The program creates and executes a growing number of threads, displaying messages on screen and performing mathematical operations and shows a graph in which the number of threads in relation compared with the runtime
	                 */
					
				case "2.6":
				    if(args.length != 3){
                        System.out.println("|ERROR| - You must indicate three arguments");
                    }else{
                        try {
                            int amount = Integer.parseInt(args[1]);
                            int type = Integer.parseInt(args[2]);
                            
                            /**
                             *  It creates the number of threads specified and start the threads
                             */
                            
                            ArrayList<Thread> myThreads4 = new ArrayList<Thread>();
                            WithRunnable2_6 r4 = new WithRunnable2_6(type);
                            for(int i=0;i<amount;i++){
                                Thread r4_rx = new Thread(r4);
                                r4_rx.start();
                                myThreads4.add(r4_rx);
                            }
                            
                            /**
                             *  Wait until there큦 no running threads
                             */
                            
                            waitUntilFinished(myThreads4);
                            
                            
                            long totalTime=r4.getMaxTime();
                            
                            /**
                             *  Creates the main frame(Window)
                             */
                            
                            BasicFrame frame = new BasicFrame(type,r4.getInitTime(),r4.getFinishTime(),totalTime,r4.getTimes());
                            
                            } catch (NumberFormatException e) {
                            System.out.println("|ERROR| - Second and third argument must be a integer");
                        }
                    }
					break;
					
					/**
					 * @brief Case 3.2 .The program create and execute the threads and show the difference between the interrupted () and interrupted () methods
					 */
					
				case "3.2":
				    
						    ArrayList<Thread> myThreads4 = new ArrayList<Thread>();
						    
						    WithRunnable3_2 r4 = new WithRunnable3_2();
						    
						    /**
		                     *  Create and start the thread
		                     */
						    
						    Thread r4_1 = new Thread(r4);
						    myThreads4.add(r4_1);
						    r4_1.start();
						    
						    /**
		                     *  Use of method isInterrupted()
		                     */
						    
						    System.out.println("---|Using isInterrupted on a thread|---");
						    System.out.println("Thread 1 isInterrupted? "+ r4_1.isInterrupted());
						    System.out.println("Thread 1 isInterrupted? "+ r4_1.isInterrupted());
						    r4_1.interrupt();
						    System.out.println("Thread 1 isInterrupted? "+ r4_1.isInterrupted());
						    waitUntilFinished(myThreads4);
						    
						    /**
                             *  Create and start the thread
                             */
						    
						    Thread r4_2 = new Thread(r4);
						    r4_2.start();
						    myThreads4.add(r4_2);
						    
						    /**
                             *  Use of method interrupted()
                             */
						    
						    System.out.println("---|Using interrupted on a thread|---");
						    System.out.println("Thread 2 interrupted? "+ Thread.interrupted());
						    System.out.println("Thread 2 interrupted? "+ Thread.interrupted());
						    r4_2.interrupt();
						    System.out.println("Thread 2 interrupted? "+ Thread.interrupted());
						    
						    /**
                             *  Wait until there큦 no running threads
                             */
							waitUntilFinished(myThreads4);
					break;	
				/**
                 * @brief Case 3.3 .The program create and execute the thread and and waits for a possible signal to be interrupted.
                 */	
				case "3.3":
				    
				    /**
                     *  Create and start the thread
                     */
				    
				    ArrayList<Thread> myThreads5 = new ArrayList<Thread>();
				    
				    WithRunnable3_3 r5 = new WithRunnable3_3();
				    
				    Thread r5_1 = new Thread(r5);
				    myThreads5.add(r5_1);
				    r5_1.start();
				    
				    /**
	                 * @brief Case 3.3 .The program create and execute the thread and and waits for a possible signal to be interrupted by the user .
	                 */ 
				    
				    Scanner signal=new Scanner(System.in);
				    String signalValue = signal.nextLine();
				    if(!signalValue.isEmpty()){
				    	r5.sendSignal();
				    	signal.close();
				    }
				    
				    /**
                     *  Wait until there큦 no running threads
                     */
				    
				    waitUntilFinished(myThreads5);
				    if(signalValue.isEmpty()){
				    	System.out.println("No signal sended");
				    	signal.close();
				    }
					break;
					
				default:
					System.out.println("|ERROR| argument must be one of the follow:");
					System.out.println("1.2 1.3 1.4 2.1 2.2 2.4 2.5 2.6 3.2 3.3");
					break;
				}
				
			}
			System.out.println("Program of excercise "+args[0]+" has terminated");
	
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
	}

}
