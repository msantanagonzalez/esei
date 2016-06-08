public class Carta{
	private boolean esMuerte;
	private boolean esRevive;
	private int puntoAlto;
	private int puntoMedio;
	private int puntoBajo;
	
	public Carta(boolean esMuerte,int puntoAlto, int puntoMedio,int puntoBajo){
		this.esMuerte = esMuerte;
		this.puntoAlto = puntoAlto;
		this.puntoMedio = puntoMedio;
		this.puntoBajo = puntoBajo;
		if(!esMuerte && puntoAlto == 0 && puntoMedio == 0 && puntoBajo == 0)
			this.esRevive = true;
	}
	public boolean esMuerte(){
		return this.esMuerte;
	}
	public boolean esRevive(){
		return this.esRevive;
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
	//Print the card info
    public String toString(){
    	String data = "| ";
    	if(esMuerte){
    		data += "MUERTE - ";
    	}
    	
    	if(esRevive){
    		data += "REVIVE - PA:0,PM:0,PB:0";
    	}else{
    		if(this.getPuntoAlto() == -1)
        		data += "PA: * ";
        	else{
        		if(this.getPuntoAlto() == 0)
        			data += "PA: ";
        		else{
        			data += "PA: " + this.getPuntoAlto() + " ";
        		}
        	}
        	
        	if(this.getPuntoMedio() == -1)
        		data += "PM: * ";
        	else{
        		if(this.getPuntoMedio() == 0)
        			data += "PM: ";
        		else{
        			data += "PM: " + this.getPuntoMedio() + " ";
        		}
        	}
        	
        	if(this.getPuntoBajo() == -1)
        		data += "PB: * ";
        	else{
        		if(this.getPuntoBajo() == 0)
        			data += "PB: ";
        		else{
        			data += "PB: " + this.getPuntoBajo() + " ";
        		}
        	}
    	}
    	
    	data += " |";
    	return data;
    }
}
