package actividad7_AEDII;

import java.util.Iterator;


public class main {

	public static void main(String[] args) {
		Persona p1 = new Persona("Marco","marco@algo.com");
		Persona p2 = new Persona("Polo","polo@algo.com");
		Persona p3 = new Persona("Santana","santana@algo.com");
		Persona p4 = new Persona("Gonzalez","gonzalez@algo.com");
		Persona p5 = new Persona("Pepe","pepe@algo.com");
		
		Persona p6 = new Persona("jose","jose@algo.com");
		Persona p7 = new Persona("aurora","aurora@algo.com");
		
		map<String, Persona> mapa1 = new mapaHash<String,Persona>(3);
		
		mapa1.insertar("02595545H", p1);
		mapa1.insertar("11111111A", p2);
		mapa1.insertar("22222222B", p3);
		mapa1.insertar("33333333C", p4);
		mapa1.insertar("44444444D", p5);
		
		System.out.println("---------| valor Persona 1 Before|----------");
		System.out.println(mapa1.get("02595545H").getName());
		
		Iterator<String> iteClaves = mapa1.getClaves();
		Iterator<Persona> iteValores = mapa1.getValores();
		
		System.out.println("---------| Claves iterador |----------");
		while(iteClaves.hasNext()){
			System.out.println(iteClaves.next());	
		}
		
		System.out.println("---------| Valores iterador |----------");
		while(iteValores.hasNext()){
			System.out.println(iteValores.next().getName());	
		}
		
		System.out.println("Elementos:"+mapa1.tamaño());
		
		mapa1.insertar("02595545H", p6);
		System.out.println("---------| valor Persona 1 After|----------");
		System.out.println(mapa1.get("02595545H").getName());
		
		mapa1.insertar("55555555E", p7);
		
		Iterator<String> ite2Claves = mapa1.getClaves();
		Iterator<Persona> ite2Valores = mapa1.getValores();
		
		System.out.println("---------| Claves iterador |----------");
		while(ite2Claves.hasNext()){
			System.out.println(ite2Claves.next());	
		}
		
		System.out.println("---------| Valores iterador |----------");
		while(ite2Valores.hasNext()){
			System.out.println(ite2Valores.next().getName());	
		}
		System.out.println("Elementos:"+mapa1.tamaño());
	}

}
