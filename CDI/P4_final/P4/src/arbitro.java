import java.util.ArrayList;

public class arbitro {
	private static boolean gameOn = false;
	//Wait until all threads are finished
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
	
	public static void startGame(Pelota p){
		System.out.println("--- |Starting the game| ---");
		gameOn = true;
	}
	
	public static void stopGame(Pelota p){
		System.out.println("--- |Game finished| ---");
		synchronized (p){
			gameOn = false;
			p.notifyAll();	
		}
	}
	
	public static boolean gameOn(){
		return gameOn;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Pelota p = new Pelota();
		
		int amount = 3;
		Ping players[] = new Ping[amount];
		
		ArrayList<Thread> myThreads = new ArrayList<Thread>();
		
		/* Start the game */
		startGame(p);
		for(int i=0;i<amount;i++){
			/* Create players */
			Ping p1 = new Ping("Player "+(i+1),p);
			players[i] = p1;
			Thread t = new Thread(p1);
			myThreads.add(t);
			t.start();
		}
		Thread.sleep(2000);
		for(int i=0;i<amount;i++){
			players[i].setPelota();	
		}
		/* Notify all about the game ending */
		stopGame(p);
		waitUntilFinished(myThreads);
	}

}
