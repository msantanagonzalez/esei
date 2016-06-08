package actividad7_AEDII;

import java.util.Iterator;
import java.util.Vector;

import lista.*;


public class mapaHash<K, V> implements map<K, V>{
	private Lista<Par<K,V>> [] lista;
	private int amount=5;
	
	public mapaHash(int size) {
		lista = new Lista[size];
		amount = size;
		for(int i=0;i<amount;i++){
			lista[i] = new ListaEnlazada<Par<K,V>>();
		}
	}
	
	public mapaHash(){
		lista = new Lista[amount];
		for(int i=0;i<amount;i++){
			lista[i] = new ListaEnlazada<Par<K,V>>();	
		}
	}
	@Override
	public int tamaño() {
		int cont = 0;
		for(int i=0;i<lista.length;i++){
			cont++;
		}
		return cont;
	}

	public int hash(K clave){
		int value = clave.hashCode();
		int size = lista.length;
		
		return Math.abs(value%size);
	}
	
	@Override
	public V get(K clave) {
		for(Par<K,V> it : lista[hash(clave)]){
			if(it.getK().equals(clave)){
				return it.getV();
			}
		}
		return null;
	}

	@Override
	public void insertar(K clave, V valor) {
		boolean exist = false;
		int position = hash(clave);
		
		for(Par<K,V> it : lista[position]){
			if(it.getK().equals(clave)){
				exist = true;
				it.setV(valor);
			}
		}
		if(!exist){
			Par<K,V> newPair = new Par<K,V>(clave,valor);
			lista[position].insertarFinal(newPair);
		}
		
	}

	@Override
	public V eliminar(K clave) {
		
		int position = hash(clave);
		for(Par<K,V> it : lista[position]){
			if(it.getK().equals(clave)){
				Par<K,V> tempo = new Par<K,V>(it.getK(),it.getV());
				V tempoV = tempo.getV();
				lista[position].suprimir(tempo);
				return tempoV;
			}
		}
		return null;
	}

	@Override
	public Iterator<K> getClaves() {
		Vector<K> vector = new Vector<K>();
		for(int i=0;i<lista.length;i++){
			Lista<Par<K,V>> itTempo = lista[i];
			for(Par<K,V> it : itTempo){
				vector.add(it.getK());
			}
		}
		return vector.iterator();
	}

	@Override
	public Iterator<V> getValores() {
		Vector<V> vector = new Vector<V>();
		for(int i=0;i<lista.length;i++){
			Lista<Par<K,V>> itTempo = lista[i];
			for(Par<K,V> it : itTempo){
				vector.add(it.getV());
			}
		}
		return vector.iterator();
	} 

	
	
}