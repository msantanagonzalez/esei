public class DoblementeEnlazadaCircularConCentinelas {
	private DobleNodo ultimo;
	private int numElem;
	
	public DoblementeEnlazadaCircularConCentinelas(){
		this.ultimo = new DobleNodo(0, null, null);
		this.ultimo.setSig(ultimo);
		this.ultimo.setAnt(ultimo);
		this.numElem = 0;
	}
	
	public int cantidad(int e){
		int times = 0;
		if(this.ultimo.getSig() == this.ultimo)
			System.out.println("Lista vacia");
		else{
			DobleNodo actual = this.ultimo.getSig();
			while(actual.getSig()!=this.ultimo){
				if(actual.getElemento() == e)
					times++;
				actual = actual.getSig();
			}
		}
		return times;
	}
	
	public void insertarPrincipio(int e){
		DobleNodo nuevo = new DobleNodo(e, this.ultimo.getSig(), this.ultimo );
		this.ultimo.getSig().setAnt(nuevo);
		this.ultimo.setSig(nuevo);
		this.numElem++;
	}
	
	public void insertarFinal(int e){
		DobleNodo nuevo = new DobleNodo(e, this.ultimo, this.ultimo.getAnt());
		this.ultimo.getAnt().setSig(nuevo);
		this.ultimo.setAnt(nuevo);
		this.numElem++;
	}
	
	public void borrar (int e)
	{
		if(this.ultimo.getSig() == this.ultimo)
			System.out.println("Lista vacia");
		else {
			DobleNodo actual = this.ultimo.getSig();
			while (actual != this.ultimo && actual.getElemento()!= e)
				actual = actual.getSig();
			
			if (actual== this.ultimo)
				System.out.println("elemento no esta en la lista");
			else {
				actual.getAnt().setSig(actual.getSig());
				actual.getSig().setAnt(actual.getAnt());
				this.numElem--;
			}
		} 
	}
	
	public String toString(){
		String data = "| ";
		if (this.ultimo.getSig() == this.ultimo)
			data += "VACIO |";
		else{
			DobleNodo actual = this.ultimo.getSig();
			while(actual!=this.ultimo){
					data += actual.getElemento() + ",";
				actual = actual.getSig();
			}
			data += " |";
		}
		return data;
	}
}
