package actividad6_AEDII;
import arbolGeneral.ArbolGeneral;
import arbolGeneral.ArbolVacioExcepcion;
import arbolGeneral.NodoGeneral;

public class EnlazadoArbolGeneral<E> implements ArbolGeneral<E> {
	private NodoGeneral<E> nodo;
	
	public EnlazadoArbolGeneral(){
		nodo = null;
	}
	
	public EnlazadoArbolGeneral(E elemento,ArbolGeneral<E> ... hijos) throws NullPointerException{
		if(hijos == null){
			throw new NullPointerException();
		}else{
			if(hijos.length == 0){
				nodo = new NodoGeneral<E> (elemento,null,null);
			}else{
				for(int i=0;i<hijos.length-1;i++){
				((EnlazadoArbolGeneral<E>)hijos[i]).nodo.setHer(((EnlazadoArbolGeneral<E>)hijos[i+1]).nodo);	
				}
				nodo = new NodoGeneral<E>(elemento, ((EnlazadoArbolGeneral<E>)hijos[0]).nodo, null );
			}
				
		}
	}
	
	public EnlazadoArbolGeneral(NodoGeneral<E> nodoA){
		nodo = nodoA;
	}
	
	public boolean esVacio() {
		if(nodo.getElemento() == null){
			return true;
		}else{
			return false;
		}
	}	
		
	@Override
	public boolean esta(E arg0) {
		return esta(arg0,nodo);
	}
	
	private boolean esta(E elemento, NodoGeneral<E> nodoHijo){
		if(nodoHijo == null){
			return false;
		}else{
			if(nodoHijo.getElemento().equals(elemento)){
				return true;
			}else{
				NodoGeneral<E> aux = nodoHijo.getHijo();
				while(aux != null){
					if(esta(elemento,aux)){
						return true;
					}else{
						aux = aux.getHer();
					}
				}
			}
		}
		return false;
	}

	@Override
	public ArbolGeneral<E> hermanoDer() throws ArbolVacioExcepcion {
		if(esVacio()){
			throw new ArbolVacioExcepcion("Arbol vacio");
		}else{
			return new EnlazadoArbolGeneral<E>(nodo.getHer());
		}
	}

	@Override
	public ArbolGeneral<E> hijoMasIzq() throws ArbolVacioExcepcion {
		if(esVacio()){
			throw new ArbolVacioExcepcion("Arbol Vacio");
		}else{
		return new EnlazadoArbolGeneral<E>(nodo.getHijo());	
		}
	}

	@Override
	public E raiz() throws ArbolVacioExcepcion {
		if(esVacio()){
			throw new ArbolVacioExcepcion("Arbol vacio");
		}else{
		 return nodo.getElemento();	
		}		
	}

	@Override
	public void setHijo(ArbolGeneral<E> arg0) throws ArbolVacioExcepcion,NullPointerException {
		if(arg0 == null){
			throw new NullPointerException();
		}else{
			if(esVacio()){
				throw new ArbolVacioExcepcion("Arbol Vacio");
			}else{
				if(hijoMasIzq().esVacio()){
					nodo.setHijo(((EnlazadoArbolGeneral<E>)arg0).nodo);
				}else{
					NodoGeneral<E> aux = nodo.getHijo();
					while(aux.getElemento() != null){
						aux = aux.getHer();
						aux.setHer(((EnlazadoArbolGeneral<E>)arg0).nodo);
					}
				}
			}
		}
		
	}

	public void setRaiz(E arg0) throws ArbolVacioExcepcion {
		if(esVacio()){
			throw new ArbolVacioExcepcion("Arbol Vacio");
		}else{
		 nodo.setElemento(arg0);	
		}
	}

	public void suprimir() {
		nodo = null;
	}

}
