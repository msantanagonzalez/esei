public class DoblementeEnlazadaCentinelas {
	private DobleNodo primero, ultimo;
	private int numElem;
	
	public DoblementeEnlazadaCentinelas(){
		this.primero = new DobleNodo(0,null,null);
		this.ultimo = new DobleNodo(0,null,primero);
		this.primero.setSig(ultimo);
		this.numElem = 0;
	}
	
	public int elementos(){
		/*
		int times = 0;
		if (this.primero.getSig() == this.ultimo && this.ultimo.getAnt() == this.primero)
				System.out.println("estructura vacía");
		else{
			DobleNodo actual = this.primero.getSig();
			while(actual!=this.ultimo){
					times++;
				actual = actual.getSig();
			}
		}
		
		return times;
		*/
		return this.numElem;
	}
	
	public int cantidad(int e){
		int times = 0;
		if (this.primero.getSig() == this.ultimo && this.ultimo.getAnt() == this.primero)
				System.out.println("estructura vacía");
		else{
			DobleNodo actual = this.primero.getSig();
			while(actual!=this.ultimo){
					if(actual.getElemento() == e)
						times++;
				actual = actual.getSig();
			}
		}
		return times;
	}
	
	public boolean esta(int e){
		DobleNodo actual = this.primero.getSig();
		if (actual == this.ultimo && this.ultimo.getAnt() == this.primero)
				System.out.println("estructura vacía");
		else{
			while(actual!=this.ultimo && actual.getElemento() != e){
				actual = actual.getSig();
			}
		}
		return actual!=this.ultimo;
	}
	
	public void insertarPrincipio(int e){
		DobleNodo nuevo = new DobleNodo(e,this.primero.getSig(),this.primero);
		this.primero.getSig().setAnt(nuevo);
		this.primero.setSig(nuevo);
		this.numElem++;
	}
	
	public void insertarFinal(int e){
		//e,sig,ant
		DobleNodo nuevo = new DobleNodo(e,this.ultimo,this.ultimo.getAnt());
		this.ultimo.getAnt().setSig(nuevo);
		this.ultimo.setAnt(nuevo);
		this.numElem++;
	}
	
	public void borrar (int elemento){
		if (this.primero.getSig() == this.ultimo && this.ultimo.getAnt() == this.primero)
		System.out.println("estructura vacia");
		else {
			DobleNodo actual = this.primero.getSig();
			while (actual != this.ultimo && actual.getElemento() != elemento)
				actual = actual.getSig();
			if (actual == this.ultimo)
				System.out.println("elemento no esta");
			else {
				actual.getAnt().setSig(actual.getSig());
				actual.getSig().setAnt(actual.getAnt());
				this.numElem--;
			}
		}
	}
	
	public String toString(){
		String data = "| ";
		if (this.primero.getSig() == this.ultimo && this.ultimo.getAnt() == this.primero)
			data += "VACIO |";
		else{
			DobleNodo actual = this.primero.getSig();
			while(actual!=this.ultimo){
					data += actual.getElemento() + ",";
				actual = actual.getSig();
			}
			data += " |";
		}
		return data;
	}
}
