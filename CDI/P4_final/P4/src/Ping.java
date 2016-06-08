public class Ping implements Runnable{
	private String nombre;
	private Pelota pelota;
	private boolean hasIt;
	
	public Ping(String n, Pelota p){
		this.nombre = n;
		this.pelota = p;
		this.hasIt = false;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setPelota(){
		this.hasIt = true;
		synchronized (this.pelota) {
			this.pelota.notifyAll();	
		}
	}
	
	public void takeBall(){
		this.hasIt = false;
		synchronized (this.pelota) {
			this.pelota.notifyAll();	
		}
	}
	
	public boolean hasIt(){
		return this.hasIt;
	}
	
	public void run(){
		synchronized(this.pelota){
			try {
				while(arbitro.gameOn()){
					if(hasIt()){
						this.pelota.golpear(this);
					}else{
						System.out.println(getNombre()+" waiting");
						this.pelota.wait();
					}		
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}

