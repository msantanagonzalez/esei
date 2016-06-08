package actividad3_AEDII;
import arbolBinario.*;
import arbolBusqueda.ArbolBinarioBusqueda;
import arbolGeneral.ArbolVacioExcepcion;

public class EnlazadoArbolBinario<E> implements ArbolBinario<E>{
	private NodoBinario<E> nodo;
	
	public EnlazadoArbolBinario(){
		nodo = null;
	}
	
	public EnlazadoArbolBinario(E elemento,NodoBinario<E> hi,NodoBinario<E> hd){
		nodo.setElemento(elemento);
		nodo.setIzq(hi);
		nodo.setDer(hd);
	}
	public boolean esVacio() {
		if(nodo.getElemento() == null){
			return true;
		}else{
			return false;
		}
	}

	public boolean esta(E arg0) {
		return esta(nodo,arg0);
	}
	
	private boolean esta(NodoBinario<E> nodo,E arg){
		if(esVacio()){
			return false;
		}else{
			if(nodo.getElemento() == arg){
				return true;
			}else{
				return esta(nodo.getIzq(),arg) || esta(nodo.getDer(),arg);
			}
		}
	}

	public ArbolBinario<E> hijoDer() throws ArbolVacioExcepcion{
		if(esVacio()){
			throw new ArbolVacioExcepcion("Sin hijo derecho");
		}else{
			NodoBinario<E> aux = nodo.getDer();
			return new EnlazadoArbolBinario<E>(aux.getElemento(), aux.getDer(), aux.getIzq());
		}
	}

	public ArbolBinario<E> hijoIzq() throws ArbolVacioExcepcion{
		if(esVacio()){
			throw new ArbolVacioExcepcion("Sin hijo Izquierdo");
		}else{
			NodoBinario<E> aux = nodo.getIzq();
			return new EnlazadoArbolBinario<E>(aux.getElemento(), aux.getDer(), aux.getIzq());
		}
	}

	public E raiz() throws ArbolVacioExcepcion {
		if(esVacio()){
			throw new ArbolVacioExcepcion("Arbol vacio");
		}else{
		return nodo.getElemento();	
		}
	}

	public void setHijoDer(ArbolBinario<E> arg0) throws ArbolVacioExcepcion,NullPointerException {
		if(arg0 == null){
			throw new NullPointerException();
		}else{
			if(esVacio()){
				throw new ArbolVacioExcepcion("Arbol vacio");	
			}else{
				NodoBinario<E> aux = nodo.getDer();
				aux.setDer(((EnlazadoArbolBinario<E>)arg0).nodo);
			}
		}
		
	}

	public void setHijoIzq(ArbolBinario<E> arg0) throws ArbolVacioExcepcion,NullPointerException {
		if(arg0 == null){
			throw new NullPointerException();
		}else{
			if(esVacio()){
				throw new ArbolVacioExcepcion("Arbol vacio");
			}else{
				NodoBinario<E> aux = nodo.getIzq();
				aux.setIzq(((EnlazadoArbolBinario<E>)arg0).nodo);
			}
		}
	}

	public void setRaiz(E arg0) throws ArbolVacioExcepcion {
		nodo.setElemento(arg0);
	}

	public void suprimir() {
		nodo = null;
	}
	
}