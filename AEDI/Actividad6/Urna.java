// Nombre: Urna<E>
// Declaracion de tipos: E
// Caracteristicas:
// 		Numero ilimitado de objetos
//		Modificable
public interface Urna<E> {

	//public Urna();
		//Produce: Urna vacia.
	public void addElement( E element);
		//Produce: Añade un objeto a la urna.
		//Modifica: 'this'.
	public void copiarUrna(Urna<E> urna) throws NullPointerException;
		//Produce: Insercion de elementos 'this' a 'urna'. Una excepcion si urna es null.
		//Modifica: 'this'.
	public void delElement(E element);
		//Produce: Elimina el elemento de 'this' siempre y cuando el elemento exista. 
		//Modifica: 'this'.
	public E getElement() throws UrnaVaciaExcepcion;
		//Produce: Devolucion de un elemento de 'this'. Una excepcion si la urna esta vacia.
		//Modifica: 'this'.
	public int amountElements();
		//Produce: Devolucion de un entero representado la cantidad de elementos que hay en 'this'.
}
	
