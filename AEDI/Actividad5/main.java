public class main {

	public static void main(String[] args) {
		System.out.println("-----| Doblememente Enlazada Centinelas |-----");
		DoblementeEnlazadaCentinelas lista = new DoblementeEnlazadaCentinelas();
		lista.insertarPrincipio(2);
		lista.insertarPrincipio(4);
		lista.insertarPrincipio(3);
		lista.insertarPrincipio(5);
		lista.insertarPrincipio(1);
		System.out.println(lista.toString());
		lista.insertarFinal(5);
		System.out.println(lista.toString());
		System.out.println("Cantidad de elementos:");
		System.out.println(lista.elementos());
		System.out.println("Esta (5):");
		System.out.println(lista.esta(5));
		System.out.println("Cantidad de veces (5):");
		System.out.println(lista.cantidad(5));
		System.out.println("Borrando (5)");
		lista.borrar(5);
		System.out.println(lista.toString());
		System.out.println("Cantidad de veces (5):");
		System.out.println(lista.cantidad(5));
		System.out.println("Cantidad de elementos:");
		System.out.println(lista.elementos());
		System.out.println("-----| Doblememente Enlazada Circular Centinelas |-----");
		DoblementeEnlazadaCircularConCentinelas lista2 = new DoblementeEnlazadaCircularConCentinelas();
		lista2.insertarPrincipio(2);
		lista2.insertarPrincipio(4);
		lista2.insertarPrincipio(3);
		lista2.insertarPrincipio(5);
		lista2.insertarPrincipio(1);
		System.out.println(lista2.toString());
		lista2.insertarFinal(5);
		System.out.println(lista2.toString());
		System.out.println("Borrando (5)");
		lista2.borrar(5);
		System.out.println(lista2.toString());
		System.out.println("-----| Ordenada Enlaza Centinela |-----");
		OrdenadaEnlazadaCentinela lista3 = new OrdenadaEnlazadaCentinela();
		lista3.insertar(5);
		lista3.insertar(1);
		lista3.insertar(3);
		lista3.insertar(2);
		lista3.insertar(5);
		lista3.insertar(4);
		lista3.insertar(9);
		System.out.println(lista3.toString());
		System.out.println("Esta (5):");
		System.out.println(lista3.esta(5));
		System.out.println("Borrando (3)");
		lista3.eliminar(3);
		System.out.println(lista3.toString());
	}

}
