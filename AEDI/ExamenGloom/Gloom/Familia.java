public class Familia {
	private Personaje[] cartasPersonaje = new Personaje[5];
	
	public Familia(Personaje[] p){
		for(int i=0;i<p.length;i++){
			this.cartasPersonaje[i] = p[i];
		}
	}
	
	public int getAutoestima(){
		//Implements calc autoestima
		int autoestima=0;
		
		for(int i=0;i<5;i++){
			if(!cartasPersonaje[i].estaVivo())
			autoestima+=cartasPersonaje[i].getAutoestima();
		}
		
		return autoestima;
	}
	
	public Personaje getPersonaje(int p){
		return this.cartasPersonaje[p];
	}
	
	public boolean muertos(){
		return !cartasPersonaje[0].estaVivo() && !cartasPersonaje[1].estaVivo() &&
				!cartasPersonaje[2].estaVivo() && !cartasPersonaje[3].estaVivo() &&
				!cartasPersonaje[4].estaVivo();
	}
	
	//Examen 1
	public int modCardUsed(){
		int amount=0;
		for(int i=0;i<this.cartasPersonaje.length;i++){
			amount += cartasPersonaje[i].modCardUsed();
		}
		return amount;
	}
	
	//Print the family members
    public String toString(){
    	String data = "";
    	
    	for(int i=0;i<this.cartasPersonaje.length;i++){
    		data += i + ")" + this.cartasPersonaje[i].toString() + "\n";
    	}
    	return data;
    }
}
