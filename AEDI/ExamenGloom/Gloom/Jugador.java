public class Jugador {
	private String nombre;
	private Mano mano;
	private Familia familia;
	
	public Jugador(String nombre){
		this.nombre = nombre;
	}
	
	public void setFamilia(Familia familia){
		this.familia = familia;
	}
	
	public void setMano(Mano mano){
		this.mano = mano;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	private Familia getFamilia(){
		return this.familia;
	}
	
	private Mano getMano(){
		return this.mano;
	}
	
	public String familiaToString(){
		return this.getFamilia().toString();
	}
	
	public boolean familiaMuertos(){
		return this.getFamilia().muertos();
	}
	
	public int getAutoestima(){
		return this.getFamilia().getAutoestima();
	}
	
	public void robarCartas(){
		if(Mazo.getNumCartas()<(5-this.getMano().getCantidadCartas())){
			while(Mazo.getNumCartas()!=0){
				this.getMano().setCarta(Mazo.darCarta());
			}
		}else{
			while(this.getMano().getCantidadCartas() < 5)
				this.getMano().setCarta(Mazo.darCarta());
		}
	}
	
	public Carta seleccionCarta(int option){
		return this.getMano().getCarta(option);
	}
	
	public Personaje getPersonaje(int personaje){
		return this.getFamilia().getPersonaje(personaje);
	}
	
	public void usarCarta(Carta carta){
		this.getMano().usarCarta(carta);
	}
	
	//Examen 1
	public int modCardUsed(){
		return this.getFamilia().modCardUsed();
	}
	
	public String toString(){
		String data = "Familia:\n"+this.getFamilia().toString();
		data += "Mano:\n"+this.getMano().toString();
		return data;
	}
}
