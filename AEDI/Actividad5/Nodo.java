public class Nodo{
	private int elemento;
	private Nodo siguiente;
	
	public Nodo (int elem, Nodo sig){
		elemento = elem;
		siguiente = sig;
	}
	public int getElemento(){
		return elemento;
	}
	public Nodo getSig(){
		return siguiente;
	}
	public void setElemento(int elem){
		elemento = elem;
	}
	public void setSig(Nodo sig){
		siguiente = sig;
	}
}