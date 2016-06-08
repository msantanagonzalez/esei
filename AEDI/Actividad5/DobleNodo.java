public class DobleNodo{
	private int elemento;
	private DobleNodo siguiente, anterior;
	public DobleNodo (int e, DobleNodo sig, DobleNodo ant)
	{
		elemento = e;
		siguiente = sig;
		anterior = ant;
	}
	public int getElemento()
	{
		return elemento;
	}
	public DobleNodo getSig()
	{
		return siguiente;
	}
	public DobleNodo getAnt()
	{
		return anterior;
	}
	public void setElemento( int e)
	{
		elemento = e;
	}
	public void setSig (DobleNodo sig)
	{
		siguiente = sig;
	}
	public void setAnt (DobleNodo ant)
	{
		anterior = ant;
	}
}
