//Generic node
public class Nodo {
	//Element
	private int elemento;
	//Next Element
	private Nodo siguiente;
	
	//Constructor 
	public Nodo(int elem,Nodo sig){
		this.elemento=elem;
		this.siguiente=sig;
	}
	
	public int getElemento()
	{
		return this.elemento;
	}
	
	public Nodo getSig()
	{
		return this.siguiente;
	}
	
	public void setElemento(int elem)
	{
		this.elemento=elem;
	}
	
	public void setSig(Nodo sig)
	{
		this.siguiente=sig;
	}
}