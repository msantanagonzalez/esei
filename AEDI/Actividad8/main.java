import java.util.Iterator;

import cola.*;
import lista.*;
public class main {
	
	public static Lista<Integer> greaterThan(Lista<Integer> list, int num){
		Lista<Integer> tempo = new ListaEnlazada<>();
		/*
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()){
			Integer actual = it.next();
			if(actual > num )
				tempo.insertarPrincipio(actual);
		}
		*/
		for(Integer actual: list){
			if(actual>num)
				tempo.insertarPrincipio(actual);
		}
		return tempo;
	}
	
	public static <E> Lista<E> invert(Lista<E> list){
		Lista<E> tempo = new ListaEnlazada<>();
		for(E element:list){
			tempo.insertarFinal(element);
		}
		/*
		Iterator<E> it = list.iterator();
		while(it.hasNext()){
			E element = it.next();
			tempo.insertarFinal(element);
		}
		*/
		return tempo;
	}
	
	private static <E> int count(Lista<E> list,E element){
		int times=0;
		for(E actual:list){
			if(actual.equals(element))
				times++;
		}
		return times;
	}
	
	public static <E> boolean times(Lista<E> list){
		Iterator<E> it = list.iterator();
		int times=0;
		//Check if list it's not empty
		if(it.hasNext()){
			//Get first element amount
			times = count(list, it.next());
		}	
		//Check the list
		while(it.hasNext()){
			if(times!=count(list, it.next()))
				return false;
		}
		return true;
	}
	
	public static double duracionTotal(Cola<CD> cargador){
		double sec = 0;
		Cola<CD> tempo = new EnlazadaCola<>();
		
		//Load "cargador"
		while(!cargador.esVacio()){
			//Load CD
			CD actual = cargador.suprimir();
			tempo.insertar(actual);
			Lista<Cancion> songs = actual.getSongs();
			for(Cancion c:songs){
				sec += c.getDuration();
			}
		}
		cargador = tempo;
		return sec;
	}
	
	public static void main(String[] args) {
		Lista<Integer> l = new ListaEnlazada<>();
		l.insertarPrincipio(1);
		l.insertarPrincipio(1);
		l.insertarPrincipio(2);
		l.insertarPrincipio(2);
		
		//Lista<Integer> t = greaterThan(l, 2);
		Lista<Integer> t = invert(l);
		Iterator<Integer> it = t.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		System.out.println(times(l));
		
		System.out.println("-----| CD |-----");
		
		Lista<Cancion> l1 = new ListaEnlazada<>();
		l1.insertarPrincipio(new Cancion("Speak to me",150));
		l1.insertarPrincipio(new Cancion("Breathe",280));
		l1.insertarPrincipio(new Cancion("Time",310));
		
		CD cd1 = new CD("Pink Floyd", "The Dark side of the moon CD 1", l1);
		
		Lista<Cancion> l2 = new ListaEnlazada<>();
		l2.insertarPrincipio(new Cancion("Money",360));
		l2.insertarPrincipio(new Cancion("Us and them",220));
		l2.insertarPrincipio(new Cancion("Eclipse",120));
		
		CD cd2 = new CD("Pink Floyd", "The Dark side of the moon CD 2", l2);
		
		Cola<CD> cargador = new EnlazadaCola<>();
		cargador.insertar(cd1);
		cargador.insertar(cd2);
		
		System.out.println("Duracion de cargador: "+duracionTotal(cargador));
	}

}
