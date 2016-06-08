package actividad9_AEDII;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class main {
	
	public static boolean llenarCD(int capacidadDisco, Map<String,Integer> listaProgramas, Map<String,Integer> resultado){
		
		boolean objetivo = false;
		Iterator<String> programas = listaProgramas.keySet().iterator(); //Acceder a todos los programas		
		
		while(programas.hasNext() && !objetivo){
			String programaActual = programas.next(); //Programa actual
			int espacioPrograma = listaProgramas.get(programaActual); //Espacio del programa
			
			if(espacioPrograma > 0 && capacidadDisco >= espacioPrograma){
				
				listaProgramas.put(programaActual, 0);
				resultado.put(programaActual, espacioPrograma);
				
				if(espacioPrograma == capacidadDisco){
					
					objetivo = true;
					
				}else{
					
					objetivo = llenarCD(capacidadDisco-espacioPrograma,listaProgramas,resultado);
					
					if(objetivo){
						resultado.put(programaActual, espacioPrograma);
					}else{
						listaProgramas.put(programaActual, espacioPrograma);
						resultado.remove(programaActual);
					}
					
				}
			}
		}
		return objetivo;
	}
	
	public static void main(String[] args) {
		
		int capacidadDisco = 1024;
		
		Map<String, Integer> p = new HashMap<String, Integer>();
		Map<String, Integer> r = new HashMap<String, Integer>();
		
		p.put("Programa1", 500);
		p.put("Programa2", 400);
		p.put("Programa3", 100);
		p.put("Programa4", 100);
		p.put("Programa5", 100);
		p.put("Programa6", 524);
		
		boolean saved = llenarCD(capacidadDisco, p, r);
		
		if(saved){
			System.out.println("-Optimizado para: " + capacidadDisco +"KB-");
			System.out.println("---------| Programas: |----------");
			Iterator<String> it = r.keySet().iterator();
			while(it.hasNext()){
				String programa =  it.next();
				System.out.println(programa + ":" + r.get(programa) + "KB");
			}	
		}else{
			System.out.println("No hay combinaciones para aprovechar la capacidad del disco");	
		}
		
		
		
		
		
		
	}

}
