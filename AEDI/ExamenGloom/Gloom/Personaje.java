public class Personaje{
	private String nombre;
	private boolean estaVivo;
	private int puntoAlto;
	private int puntoMedio;
	private int puntoBajo;
	private Carta[] cartasAplicadas = new Carta[98];
	private int cantidadCartas;
	
	public Personaje(String nombre){
		this.nombre = nombre;
		this.estaVivo = true;
		this.cantidadCartas = 0;
		this.puntoAlto = 0;
		this.puntoMedio = 0;
		this.puntoBajo = 0;
	}
	public String getNombre(){
		return this.nombre;
	}
	public boolean estaVivo(){
		return this.estaVivo;
	}
	public int getPuntoAlto(){
		return this.puntoAlto;
	}
	public int getPuntoMedio(){
		return this.puntoMedio;
	}
	public int getPuntoBajo(){
		return this.puntoBajo;
	}
	
	public int getAutoestima(){
		return this.puntoAlto + this.puntoMedio + this.puntoBajo;
	}
	
	public void matarPersonaje(){
		this.estaVivo = false;
	}
	//Examen 2
	public void revivirPersonaje(){
		this.estaVivo = true;
	}
	private void setPuntoAlto(int p){
		this.puntoAlto = p;
	}
	private void setPuntoMedio(int p){
		this.puntoMedio = p;
	}
	private void setPuntoBajo(int p){
		this.puntoBajo = p;
	}
	public void setCarta(Carta c){
		this.cartasAplicadas[this.cantidadCartas] = c;
		//Check if dead card
		if(c.esMuerte())
			this.matarPersonaje();
		//Examen 2
		if(c.esRevive()){
			this.revivirPersonaje();
			this.setPuntoAlto(0);
			this.setPuntoMedio(0);
			this.setPuntoBajo(0);
		}
					
		if(c.getPuntoAlto() == -1)
			this.setPuntoAlto(0);
		else
			this.setPuntoAlto(this.getPuntoAlto()+c.getPuntoAlto());
		
		if(c.getPuntoMedio() == -1)
			this.setPuntoMedio(0);
		else
			this.setPuntoMedio(this.getPuntoMedio()+c.getPuntoMedio());
		
		if(c.getPuntoBajo() == -1)
			this.setPuntoBajo(0);
		else
			this.setPuntoBajo(this.getPuntoBajo()+c.getPuntoBajo());
		
		this.cantidadCartas++;
	}
	
	//Examen 1
	public int modCardUsed(){
		return this.cantidadCartas;
	}
	
	//Print the character info
    public String toString(){
    	String data = "";
    		data += this.getNombre() + ", ";
    		if(this.estaVivo()){
    			data += " Estado: vivo, ";	
    		}else{
    			data += " Estado: MUERTO, ";
    		}
    		
    		data += "PA:" + this.getPuntoAlto() + ",";
        	data += "PM:" + this.getPuntoMedio() + ",";
        	data += "PB:" + this.getPuntoBajo() + ",";	
    	
    	return data;
    }
}

