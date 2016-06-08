package actividad3_AEDII;

import arbolBinario.EnlazadoArbolBinario;

public class main {

	public static void main(String[] args) {
		//EnlazadoArbolBinario<Integer> n1 = new EnlazadoArbolBinario<Integer>(null,new EnlazadoArbolBinario<Integer>(),new EnlazadoArbolBinario<Integer>());
		EnlazadoArbolBinario<String> n1 = new EnlazadoArbolBinario<String>(null,new EnlazadoArbolBinario<String>(),new EnlazadoArbolBinario<String>());
		EnlazadoArbolBinario<String> n2 = new EnlazadoArbolBinario<String>(null,new EnlazadoArbolBinario<String>(),new EnlazadoArbolBinario<String>());
		EnlazadoArbolBinario<String> n3 = new EnlazadoArbolBinario<String>(null,new EnlazadoArbolBinario<String>(),new EnlazadoArbolBinario<String>());
		EnlazadoArbolBinario<String> n4 = new EnlazadoArbolBinario<String>(null,new EnlazadoArbolBinario<String>(),new EnlazadoArbolBinario<String>());
		EnlazadoArbolBinario<String> n5 = new EnlazadoArbolBinario<String>(null,new EnlazadoArbolBinario<String>(),new EnlazadoArbolBinario<String>());
		EnlazadoArbolBinario<String> n6 = new EnlazadoArbolBinario<String>(null,new EnlazadoArbolBinario<String>(),new EnlazadoArbolBinario<String>());
		EnlazadoArbolBinario<String> n7 = new EnlazadoArbolBinario<String>(null,new EnlazadoArbolBinario<String>(),new EnlazadoArbolBinario<String>());
		
		n1.setRaiz("a");
		n2.setRaiz("b");
		n3.setRaiz("c");
		n4.setRaiz("d");
		n5.setRaiz("e");
		n6.setRaiz("f");
		n7.setRaiz("g");
		
		n1.setHijoIzq(n2);
		n1.setHijoDer(n3);
		
		n2.setHijoIzq(n4);
		
		n3.setHijoIzq(n5);
		n3.setHijoDer(n6);
		
		n4.setHijoIzq(n7);

		System.out.println(n1.esVacio());
		System.out.println(n1.esta("g"));
		
	}

}
