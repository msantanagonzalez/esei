import java.util.ArrayList;
import java.util.Random;
/**
 * @class Matrix
 * @implements Runnable
 * @brief Matrix class to create and control a integer matrix.
 * @access public 
 */
public class Matrix implements Runnable {
	private int type;
	private int threadAmount;
	private int maxElem;
	private int elemAmount;
	private int[][] matrix;
	private Matrix cpOne;
	private Matrix cpTwo;
	
	/**
	 * @brief Matrix constructor, sets the matrix dimension and creates the integer matrix.
	 * @param int n
	 * @access public 
	 */
	public Matrix(int n){
		this.maxElem = n;
		this.elemAmount=0;
		this.matrix = new int[n][n];
		this.fillMatrix();
	}
	/**
	 * @brief Matrix constructor, sets the type and the Threads amount.
	 * @param int t, int a 
	 * @access public 
	 */
	public Matrix(int t, int a){
		this.type = t;
		this.threadAmount = a ;
		this.elemAmount=0;
	}
	/**
	 * @brief Get max element
	 * @return integer
	 * @access public
	 */
	public int getMaxElem(){
		return this.maxElem;
	}
	/**
     * @brief Set max element
     * @param int e
     * @return void
     * @access public 
     */
	public void setMaxElem(int e){
		this.maxElem = e;
		this.matrix = new int[e][e];
	
	}
	/**
     * @brief Get element in a position
     * @param int p
     * @return integer
     * @throws Exception
     * @access public 
     */
	public int getElem(int p) throws Exception{
		if( p >= this.maxElem*this.maxElem){
			throw new Exception("|Error|- There´s no element at that position");
		}else{
			int row = 0;
			int col = p;
			while(col >= this.maxElem){
				col = col - this.maxElem;
				row++;
			}
			return this.matrix[row][col];
		}
	}
	/**
     * @brief Set element
     * @param int e
     * @return void
     * @access public 
     */
	public void setElem(int e){
		if(this.elemAmount == this.maxElem*this.maxElem){
			System.out.println("|ERROR|- Matrix already full");
		}else{
			int max = this.maxElem;
			int col=0;
			int row=0;
			while(matrix[col][row] != 0){
				if(row < max-1){
					row++;
				}else{
					row=0;
					col++;
				}
			}
			System.out.println("Setting pos:"+ col + row);
			matrix[col][row] = e;
			elemAmount++;
		}
	}
	/**
     * @brief Get amount
     * @return integer
     * @access public 
     */
	public int getAmount(){
		return this.elemAmount;
	}
	/**
	 * @brief Check if the matrix is full
	 * @return boolean
     * @access public 
	 */
	public synchronized boolean isFull(){
		if(this.getAmount() < this.getMaxElem()*this.getMaxElem()){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * @brief Sets random element to the integer matrix
	 * @return void
     * @access public 
	 */
	public void fillMatrix(){
		Random ra = new Random();
		for(int i=0;i<maxElem*maxElem;i++){
			int n = ra.nextInt(20);
			if(n == 0){
				n = ra.nextInt(20);
			}
			this.setElem(n);
		}
	}
	/**
     * @brief Show the matrix in the screen.
     * @return void
     * @access public 
     */
	public void showMatrix(){
		int max = this.maxElem;
		for(int i=0;i<max;i++){
			for(int j=0;j<max;j++){
				System.out.print(this.matrix[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	
	/**
     * @brief Set a matrix.
     * @param Matrix a, Matrix b
     * @return void
     * @access public 
     */
	private void setMatrices(Matrix a, Matrix b){
		this.cpOne = a;
		this.cpTwo = b;
	}
	/**
	 * @brief Sets the integer matrix, adding the value of two elements from another matrices.
	 * @param Matrix a, Matrix b,boolean threads
     * @return void
     * @throws Exception
     * @access public  
	 */
	/*  2.2 */
	public void addMatrix(Matrix a, Matrix b, boolean threads) throws Exception{
		if(a.getMaxElem() != b.getMaxElem()){
			throw new Exception("|Error|- Matrix must have the same dimension");
		}else{
			this.setMatrices(a, b);
			int max = a.getMaxElem();
			this.setMaxElem(max);
			/* No Threads (mode=false)*/
			if(!threads){
				switch (this.type) {
				case 1:
					for(int i=0;i<max*max;i++){
						typeOne();	
					}
					break;
				case 2:
					for(int i=0;i<max;i++){
						typeTwo();		
					}
					break;	
				}
			}else{
				/* Using Threads (mode=true)*/
				if(this.threadAmount != 0){ //If the user sets one or more threads.
					ArrayList<Thread> myThreads = new ArrayList<Thread>();
					for(int i=0;i<this.threadAmount;i++){
						Thread t = new Thread(this);
						t.start();
						myThreads.add(t);
					}
					waitUntilFinished(myThreads);
				}else{
					/* If the user doesn´t set one or more threads. */
					ArrayList<Thread> myThreads = new ArrayList<Thread>();
					switch (this.type) {
					case 1:
						for(int i=0;i<this.getMaxElem()*2;i++){
							Thread t = new Thread(this);
							t.start();
							myThreads.add(t);
						}
						
						break;
					case 2:
						for(int i=0;i<this.getMaxElem();i++){
							Thread t = new Thread(this);
							t.start();
							myThreads.add(t);
						}
						break;
					}
					waitUntilFinished(myThreads);
				}
			}
		}
	}
	/* Type 1 Element by Element*/
	private synchronized void typeOne() throws Exception{
		if(this.getAmount() < this.getMaxElem()*this.getMaxElem()){
		this.setElem(this.cpOne.getElem(this.getAmount())+this.cpTwo.getElem(this.getAmount()));
		}
	}
	/* Type 2 Rows by Row*/
	private synchronized void typeTwo() throws Exception{
		int max = this.getMaxElem();
		if(this.getAmount() < max*max){
			for(int i=0;i<max;i++){
				this.setElem(this.cpOne.getElem(this.getAmount())+this.cpTwo.getElem(this.getAmount()));	
			}
		}
	}
	/**
     * Run function. Running to start the thread.
     * @return void
     * @access public
     */
	public void run(){
		try{
			switch (this.type) {
			case 1:
				while(!this.isFull()){
					this.typeOne();
				}
			break;
			case 2:
				while(!this.isFull()){
					this.typeTwo();
				}	
			break;
			}	
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 *  Wait until there´s no running threads
	 */
	/**
     * @brief Method waitUntilFinished.Wait until they stop running the threads, and then deletes
     * @param ArrayList<Thread> myThreads
     * @return void
     * @access private 
     */
	private void waitUntilFinished(ArrayList<Thread> myThreads){
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
}
