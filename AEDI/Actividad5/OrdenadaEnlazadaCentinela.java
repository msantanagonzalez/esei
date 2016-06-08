public class OrdenadaEnlazadaCentinela {
	private Nodo primero;
	private int numElem;
	
	public OrdenadaEnlazadaCentinela(){
		this.primero = new Nodo(0, null);
		this.numElem = 0;
	}
	
	public boolean esta(int e){
		Nodo actual = this.primero.getSig();
		if(actual == null)
			System.out.println("Lista vacia");
		else{
			while(actual != null && actual.getElemento() != e)
				actual = actual.getSig();
		}
		return actual != null;
	}
	
	public void insertar(int e){
		Nodo actual = this.primero;
		while(actual.getSig() != null && actual.getSig().getElemento() <= e)
			actual = actual.getSig();	
		Nodo nuevo = new Nodo(e, actual.getSig());
		actual.setSig(nuevo);	
		this.numElem++;
	}
	
	public void eliminar(int e){
		Nodo actual = this.primero;
		if(actual.getSig() == null){
			System.out.println("Lista vacia");
		}else{
			while(actual.getSig() != null && actual.getSig().getElemento() != e)
				actual = actual.getSig();
			if(actual.getSig() == null)
				System.out.println("Elemento no esta");
			else{
				actual.setSig(actual.getSig().getSig());
			}
		}
	}
	
	public String toString(){
		String data = "| ";
		Nodo actual = this.primero;
		if (actual.getSig() == null)
			data += "VACIO |";
		else{
			while(actual.getSig()!=null){
					data += actual.getSig().getElemento() + ",";
				actual = actual.getSig();
			}
			data += " |";
		}
		return data;
	}
}
