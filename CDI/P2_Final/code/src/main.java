/**
 * @class Main
 * @brief Main class of the project.
 */
public class main {
	/* Types:
	 * 1 = Element + Element 
	 * 2 = Row + Row
	 * Mode:
	 * FALSE = No thread
	 * TRUE = With Threads
	 * */
    
    /**
     * @brief Method Main of the project, where other programs are initialized
     * @param args
     * @throws Exception
     * @return void
     * @access public static
     */
	public static void main(String[] args) throws Exception {
		if(args.length != 4){
			System.out.println("|ERROR|- Must indicate 4 params:");
			System.out.println("Dimension Type Thread_Amount mode");
		}else{
			int dimension = Integer.parseInt(args[0]);
			int type=Integer.parseInt(args[1]);
			int threadAmount = Integer.parseInt(args[2]);
			int modeI = Integer.parseInt(args[3]); 
			boolean mode = false;
			if(modeI == 1){
			    
				mode = true;
			}
		
			System.out.println("----- |Matrix A| -----");
			Matrix m = new Matrix(dimension);
			System.out.println("-|Elements|-");
			m.showMatrix();
			
			System.out.println("----- |Matrix B| -----");
			Matrix m2 = new Matrix(dimension);
			System.out.println("-|Elements|-");
			m2.showMatrix();
			
			System.out.println("----- |Matrix C| -----");
			
			Matrix m3 = new Matrix(type,threadAmount);
			
			long init = System.nanoTime();
			m3.addMatrix(m, m2, mode);
			
			long time = System.nanoTime() - init;
			
			System.out.println("-|Details:|-");
			System.out.println("|Type:"+type+"|");
			if(threadAmount == 0){
			System.out.println("|Threads: DEFAULT|");}
			else{
			System.out.println("|Threads:"+threadAmount+"|");   
			}
			System.out.println("|Using Threads:"+mode+"|");
			System.out.println("|Time:"+time+"ns|");
			System.out.println("-|Elements|-");
			m3.showMatrix();
		}	
	}

}
