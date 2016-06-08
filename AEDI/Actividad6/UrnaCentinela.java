public class UrnaCentinela<E> implements Urna<E>{
	private Nodo<E> first;
	private int elements;
	
	public UrnaCentinela() {
		this.first = new Nodo<E>(null, null);
		this.elements = 0;
	}

	@Override
	public void addElement(E element) {
		//Create the new node
		Nodo<E> newNode = new Nodo<E>(element, this.first.getSiguiente());
		//Add to front
        this.first.setSiguiente(newNode);
        this.elements++;
	}
	
	@Override
	public void copiarUrna(Urna<E> urna) throws NullPointerException{
		
	}

	@Override
	public void delElement(E element) {
		if (this.first.getSiguiente() == null)
			System.out.println("|Error|- Urna vacia");
		else{
			Nodo <E> actual = this.first;
			while (actual.getSiguiente() != null && !actual.getSiguiente().getElemento().equals(element))
                actual = actual.getSiguiente();
			if (actual.getSiguiente() != null){
				actual.setSiguiente(actual.getSiguiente().getSiguiente());
				this.elements--;
			}else{
				System.out.println("|Error|- Elemento no existe");
			}
		}	
	}

	@Override
	public E getElement() throws UrnaVaciaExcepcion{
		if(this.elements==0)
			throw new UrnaVaciaExcepcion("|Error|- Urna vacia");
		Nodo<E> tempo = this.first.getSiguiente();
		this.first.setSiguiente(tempo.getSiguiente());
		this.elements--;
		return tempo.getElemento();
	}

	@Override
	public int amountElements() {
		return this.elements;
	}

}
