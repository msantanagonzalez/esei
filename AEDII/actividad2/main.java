package actividad2_AEDII;
import java.awt.List;
import java.util.ArrayList;

import arbolBinario.*;

public class main {
	
	static public <E> void showArbol(ArbolBinario<E> arbol){
		if(!arbol.esVacio()){
			System.out.println(arbol.raiz());
			showArbol(arbol.hijoIzq());
			showArbol(arbol.hijoDer());
		}
	}
	
	static public <E> int numeroNodos(ArbolBinario<E> nodo){
		if(nodo.esVacio()) return 0;
		else return 1 + numeroNodos(nodo.hijoIzq()) + numeroNodos(nodo.hijoDer());
	}
	
	static public <E> int numeroHojas(ArbolBinario<E> nodo){
		if(nodo.esVacio()){
			return 0;
		}else if(nodo.hijoIzq().esVacio() && nodo.hijoDer().esVacio()){
			return 1;
		}
		return numeroHojas(nodo.hijoIzq()) + numeroHojas(nodo.hijoDer());
	}
	
	static public <E> boolean identicos(ArbolBinario<E> a1, ArbolBinario<E> a2){
		try{
			if(a1.esVacio() && a2.esVacio()){
				return true;
			}else{
				return (a1.raiz().equals(a2.raiz())) && identicos(a1.hijoIzq(),a2.hijoIzq()) && identicos(a1.hijoDer(),a2.hijoDer());
			}	
		}catch(ArbolVacioExcepcion excepcion){
			return false;
		}
	}
	
	static public <E> ArbolBinario<E> copia(ArbolBinario<E> arbol){
		if(arbol.esVacio()){
			return new EnlazadoArbolBinario<>();
		}else{
			return new EnlazadoArbolBinario<E>(arbol.raiz(), copia(arbol.hijoIzq()), copia(arbol.hijoDer()));
		}
	}
	
	static public <E> int nodosNivel(ArbolBinario<E> arbol, int nivel){
			if(!arbol.esVacio()){
				if(nivel == 0){
					return 1;
				}else{
					return nodosNivel(arbol.hijoIzq(), nivel-1) + nodosNivel(arbol.hijoDer(), nivel-1);
				}
			}else{
				return 0;
			}
	}
	
	static public <E> ArbolBinario<E> copiaNivel(ArbolBinario<E> arbol, int nivel){
		if(!arbol.esVacio()){
			if(nivel == 0){
				return new EnlazadoArbolBinario<E>();
			}else{
				return new EnlazadoArbolBinario<E>(arbol.raiz(),copiaNivel(arbol.hijoIzq(), nivel-1),copiaNivel(arbol.hijoDer(), nivel-1));
			}
		}else{
			return new EnlazadoArbolBinario<E>();
		}
	}
	
	static public <E> int alturaArbol(ArbolBinario<E> arbol){
		if(arbol.esVacio()){
			return 0;
		}else{
			return Math.max(1+alturaArbol(arbol.hijoIzq()),1+alturaArbol(arbol.hijoDer()));
		}
	}
	
	static public <E> ArbolBinario<E> copiaSinHojas(ArbolBinario<E> arbol){
		if(!arbol.esVacio()){
			if(arbol.hijoIzq().esVacio() && arbol.hijoDer().esVacio()){
				return new EnlazadoArbolBinario<>();
			}else{
				return new EnlazadoArbolBinario<>(arbol.raiz(),copiaSinHojas(arbol.hijoIzq()),copiaSinHojas(arbol.hijoDer()));
			}
		}else{
			return new EnlazadoArbolBinario<>();
		}
	}
	
/*	static public <E> List frontera(ArbolBinario<E> arbol,List listaFrontera){
		if(!arbol.esVacio()){
			if(arbol.hijoIzq().esVacio() && arbol.hijoDer().esVacio()){
				System.out.println(arbol.raiz());
				listaFrontera.add(String.valueOf(arbol.raiz()));
				return listaFrontera;
			}else{
				return frontera(arbol.hijoIzq(),listaFrontera);
			}	
		}else{
			return listaFrontera;
		}
	}*/
	
	static public <E> void padreElemento(ArbolBinario<E> arbol,E elemento){
		try{
			if((arbol.hijoIzq().raiz().equals(elemento)) || (arbol.hijoDer().raiz().equals(elemento))){
				System.out.println(arbol.raiz());
			}else{
				padreElemento(arbol.hijoIzq(), elemento);
				padreElemento(arbol.hijoDer(), elemento);
			}
		}catch(ArbolVacioExcepcion excepcion){
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		//Arbol 1
		EnlazadoArbolBinario<Integer> n1 = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n2 = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n3 = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n4 = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n5 = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n6 = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n7 = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		//Arbol 2
		EnlazadoArbolBinario<Integer> n1b = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n2b = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n3b = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n4b = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n5b = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n6b = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<Integer> n7b = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		
		//Arbol 1
		n1.setRaiz(1);
		n2.setRaiz(2);
		n3.setRaiz(3);
		
		n4.setRaiz(4);
		n5.setRaiz(5);
		n6.setRaiz(6);
		n7.setRaiz(7);
		//Arbol 2
		n1b.setRaiz(1);
		n2b.setRaiz(2);
		n3b.setRaiz(3);
		
		n4b.setRaiz(4);
		n5b.setRaiz(5);
		n6b.setRaiz(6);
		n7b.setRaiz(7);
		
		//Arbol 1
		n1.setHijoIzq(n2);
		n1.setHijoDer(n3);
		n2.setHijoIzq(n4);
		n2.setHijoDer(n5);
		n3.setHijoIzq(n6);
		n6.setHijoIzq(n7);
		//Arbol 2
		n1b.setHijoIzq(n2b);
		n1b.setHijoDer(n3b);
		n2b.setHijoIzq(n4b);
		n2b.setHijoDer(n5b);
		n3b.setHijoIzq(n6b);
		n3b.setHijoDer(n7b);
		
		System.out.println("Numero nodos: "+numeroNodos(n1));
		System.out.println("Numero hojas: "+numeroHojas(n1));
		System.out.println("Identicos: "+identicos(n1, n1b));
		System.out.println("Copia: "+copia(n1));
		System.out.println("Nivel 3: "+nodosNivel(n1,2));
		System.out.println("Altura: "+alturaArbol(n1));
		System.out.println("------------ Original ---------------");
		showArbol(n1);
		System.out.println("------------ After ---------------");
		//showArbol(copiaNivel(n1,2));
		//showArbol(copiaSinHojas(n1));
		//List lista=null;
		//frontera(n1,lista);
		padreElemento(n1,7);
	}

}
