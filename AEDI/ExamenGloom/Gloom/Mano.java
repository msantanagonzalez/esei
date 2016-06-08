public class Mano {
	private Carta[] mano = new Carta[5];
	private int numCartas;
	
	public Mano(){
		this.numCartas=0;
	}
	
	public int getCantidadCartas(){
		return this.numCartas;
	}
	
	public void usarCarta(Carta c){
		int pos=0;
		while(this.mano[pos]!=c)
			pos++;
		
		for(int i=pos;i<this.mano.length-1;i++){
			this.mano[i] = this.mano[i+1];
		}
		this.numCartas--;
	}
	
	public Carta getCarta(int p){
		return this.mano[p];
	}
	
	public void setCarta(Carta c){
		mano[numCartas] = c;
		this.numCartas++;
	}
	
	/* Print the deck info */
    public String toString(){
    	String data = "";
    	for(int i=0;i<this.numCartas;i++){
    		data += i + ")" + mano[i].toString() + "\n";
    	}
    	return data;
    }
}
