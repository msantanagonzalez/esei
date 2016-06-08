package actividad8_AEDII;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import grafo.*;

public class main {
	
	//------------- Predecesores
	public static <E,T> Iterator<Vertice<E>> predecesores(Grafo <E,T> g, Vertice<E> v){
		Vector<Vertice<E>> pred = new Vector<Vertice<E>>();
		
		Iterator<Vertice<E>> it = g.vertices();
		
		while(it.hasNext()){
			Vertice<E> vActual = it.next();
			
			Iterator<Vertice<E>> it2 = g.adyacentes(vActual);
			
			while(it2.hasNext()){
				if(it2.next().equals(v)){
					pred.add(vActual);
				}
			}
		}
		return pred.iterator();
	}
	
	//------------- Pozo_Sumidero
	public static <E,T> String pozo_sumidero(Grafo<E,T> g, Vertice<E> v){
		Iterator<Vertice<E>> tot = g.vertices();
		int totalVertices = 0;
		while(tot.hasNext()){
			tot.next();
			totalVertices++;
		}
		
		Iterator<Vertice<E>> prede = predecesores(g, v);
		int entrada = 0;
		while(prede.hasNext()){
			prede.next();
			entrada++;
		}
		
		Iterator<Vertice<E>> adya = g.adyacentes(v);
		int salida = 0;
		while(adya.hasNext()){
			adya.next();
			salida++;
		}
		
		if(entrada == (totalVertices-1) && salida == 0){
			return "Sumidero";	
		}else{
			return "Pozo";
		}
		
	}
	
	//------------- Regular
	public static <E,T> boolean regular(Grafo<E,T> g){
		int amount=0;
		boolean regular=true;
		
		Iterator<Vertice<E>> vertices = g.vertices();
		
		Vertice<E> primerVertice = vertices.next();
		
		Iterator<Vertice<E>> adyacentesPrimero = g.adyacentes(primerVertice);
		while(adyacentesPrimero.hasNext()){
			adyacentesPrimero.next();
			amount++;
		}
		
		while(vertices.hasNext()){
			int amount2 = 0;
			Iterator<Vertice<E>> adyacentes = g.adyacentes(vertices.next());
			while(adyacentes.hasNext()){
				adyacentes.next();
				amount2++;
			}
			if(amount != amount2){
				regular = false;
			}
		}
		
		return regular;
	}
	
	//------------- Impar
	public static <E,T> boolean impar(Grafo<E,T> g){
		boolean impar = true;
		Iterator<Vertice<E>> vertices = g.vertices();
		
		while(vertices.hasNext() && impar){
			Vertice<E> primerVertice = vertices.next();
			
			Iterator<Vertice<E>> prede = predecesores(g, primerVertice);
			int entrada = 0;
			while(prede.hasNext()){
				prede.next();
				entrada++;
			}
			
			Iterator<Vertice<E>> adya = g.adyacentes(primerVertice);
			int salida = 0;
			while(adya.hasNext()){
				adya.next();
				salida++;
			}
			
			if((entrada+salida)%2 == 0){
				impar = false;
			}
		}
		
		return impar;
	}
	
	//------------- Camino simple
	
	public static <E,T> boolean caminoSimple(Grafo<E,T> g, Vertice<E> v1, Vertice<E> v2){
		Vector<Vertice<E>> visitados = new Vector<Vertice<E>>();
		return checkCamino(g,v1,v2,visitados);
	}
	
	public static <E,T> boolean checkCamino(Grafo<E,T> g, Vertice<E> v1, Vertice<E> v2, Vector<Vertice<E>> visitados){
		boolean road=false;
		
		visitados.add(v1);
		
		if(v1.equals(v2)){
			return true;
		}else{
			Iterator<Vertice<E>> vertices = g.adyacentes(v1);
			while(vertices.hasNext()){
				Vertice<E> vActual = vertices.next();
				if(!visitados.contains(vActual)){
					road = checkCamino(g, vActual, v2, visitados);
				}
			}
			return road;	
		}
		
	}
	
	//------------- Caminos
	private static <E,T> void escribirCamino(Vector<E> c){
        for (int i = 0; i < c.size(); i++)
            System.out.print((c.elementAt(i)) + " ");
        System.out.println();    
    }    
    
    private static <E,T> void caminos(Grafo<E,T> g, Vertice<E> v, Vertice<E> w, Vector<Vertice<E>> visitados){
        visitados.add(v);
        if (v.equals(w))
            escribirCamino(visitados);
        else{
            Iterator<Vertice<E>> adys = g.adyacentes(v);
            while (adys.hasNext()){
                Vertice<E> u = adys.next();
                if (!visitados.contains(u))
                    caminos(g, u, w, visitados);
            }
        }
        visitados.removeElementAt(visitados.size() - 1);
    }    
    
    public static <E,T> void caminos (Grafo<E,T> g, Vertice<E> v, Vertice<E> w){
        Vector<Vertice<E>> visitados=new Vector<Vertice<E>>();
        System.out.println("Los caminos entre " + v.getEtiqueta() + " y " + w.getEtiqueta() + " son:");
        caminos(g, v, w, visitados);
        System.out.println();
    }
    
  //------------- Viajante
    public static<E> Grafo<E,Integer> viajante(Grafo<E,Integer> g, Vertice<E> v)
    {
     Vertice<E> nodoActual = v;
     Set<Vertice<E>> porVisitar = new HashSet<Vertice<E>>(); 
     Grafo<E,Integer> solucion = new ListaAdyacencia<E,Integer>();
     
     Iterator<Vertice<E>> it_v = g.vertices();
     
     while (it_v.hasNext()) {
         Vertice<E> vert = it_v.next();
         porVisitar.add(vert);
     }
     
     porVisitar.remove(nodoActual); 
     
     boolean continuar = true;
        
     while (!porVisitar.isEmpty() && continuar)
     {
           Arco<E,Integer> u = minimo(g.arcos(),nodoActual,porVisitar);
           if (u != null)
           {
                porVisitar.remove(u.getDestino());
                solucion.insertarArco(u);
                nodoActual = u.getDestino();
            }
           else continuar = false;
     }
     return solucion;   
    } 
    
   private static <E> Arco<E,Integer> minimo (Iterator<Arco<E,Integer>> arc, Vertice<E> v, Set<Vertice<E>> iPorVisitar)
    {
         int dist_v_min= 0;
         int min = Integer.MAX_VALUE;
         Arco<E,Integer> arco_min = null;
         while (arc.hasNext()) {
                  Arco<E,Integer> a1 = arc.next();
                  Vertice<E> w = a1.getDestino(); 
                  if (a1.getOrigen().equals(v) && iPorVisitar.contains(w))
                  {
                            dist_v_min = a1.getEtiqueta();   
                            if ( dist_v_min < min)
                            {
                               arco_min = a1;
                               min = dist_v_min;
                            }
                                   
                   } 
         } 
        return arco_min;
    }
	
 //------------- ALGORITMOS ------------------------------------
   
   
 //------------ Vuelta Atras   
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
 //------------ Mochila Volumen 
   public static Map<String,Integer> llenarMochila(int volumenMaximo, Map<String,Integer> cantidades, Map<String,Integer> volumenes) {
       
       Map<String,Integer> S = new HashMap<String,Integer>();
       int volumenOcupado = 0;
       boolean llenarMas = true;
       
       int k =0;
       // Mientras no se haya cumplido el objetivo con los objetos disponibles
       while ( volumenOcupado < volumenMaximo && llenarMas)
       {
	    // buscar un objeto que “quepa”
	    String obj = mayor(volumenMaximo-volumenOcupado,cantidades,volumenes);
	    if ( obj != null)
	    {
	       int vol = volumenes.get(obj);
	       k = (volumenMaximo- volumenOcupado) / vol; // ¿Cuántos objetos?
	       int tot = cantidades.get(obj);
	       if (k > tot) k = tot;
		   S.put(obj,k);
		   cantidades.put(obj, tot-k);
		   volumenOcupado += vol*k;
	    }
        else llenarMas=false; //No se puede llenar más, devolvemos la mochila
	    }
     
       return S;
    }
    
    private static String mayor (int parcial, Map<String,Integer> c, Map<String, Integer> v)
    {
         String max=null;
         int maxV = 0;
         
         Iterator<String> obj = v.keySet().iterator();
         
         while (obj.hasNext()) 
         {
                  String m = obj.next();
                  int vol = v.get(m);
                  int cant = c.get(m);
                  
                  if (cant > 0 && vol > maxV && vol <= parcial)
                     {
                            maxV = vol;
                            max = m;
                     } 
         }
                
        return max;
        
    }
    
  //------------ Mochila Volumen + peso
    public static Map<String,Integer> llenarMochila(int volumenMaximo, int pesoMaximo, Map<String,Integer> cantidades, Map<String,Integer> volumenes, Map<String,Integer> pesos) {
	       
	       Map<String,Integer> S = new HashMap<String,Integer>();
	       
	       int volumenOcupado = 0;
	       int pesoOcupado = 0;
	       boolean llenarMas = true;
	       
	       // Mientras no se haya cumplido el objetivo con los objetos disponibles
	       while ( volumenOcupado < volumenMaximo && llenarMas)
	       {
		    // buscar un objeto que “quepa”
		    String obj = mayor(volumenMaximo-volumenOcupado, pesoMaximo-pesoOcupado, volumenes,cantidades,pesos);
		    if ( obj != null)
		    {
		       int vol = volumenes.get(obj);
		       int pes = pesos.get(obj);
		       
		       int k = (volumenMaximo- volumenOcupado) / vol; // ¿Cuántos objetos según volumen?
		       int s = (pesoMaximo - pesoOcupado)/pes; //¿Cuántos objetos según peso?
		       
		       if (k > s) k = s; //Me quedo con la cantidad más pequeña
		       
		       int tot = cantidades.get(obj); //¿cuántos objetos tengo realmente?
		       if (k > tot) k = tot;
			   
		       S.put(obj,k);
		       cantidades.put(obj, tot-k);
			   volumenOcupado += vol*k;
			   pesoOcupado += pes*k;
		    }
	        else llenarMas=false; //No se puede llenar más, devolvemos la mochila
		    }
	     
	       return S;
	    }
	    
	    private static String mayor (int parcialVolumen, int parcialPeso, Map<String,Integer> v, Map<String,Integer> c, Map<String,Integer> p)
	    {
	         String max=null;
	         int maxV = 0;
	         
	         Iterator<String> obj = v.keySet().iterator();
	         
	         while (obj.hasNext()) 
	         {
	                  String m = obj.next();
	                  int vol = v.get(m);
	                  int pes = p.get(m);
	                  
	                  if (c.get(m) > 0 && vol > maxV && vol <= parcialVolumen && pes <= parcialPeso)
	                     {
	                            maxV = vol;
	                            max = m;
	                     } 
	         }
	                
	        return max;
	        
	    }  
	    
	    
	public static void main(String[] args) {
		//--------------------------- Vertices
		Vertice<String> v1 = new Vertice<String>("Nodo1");
		Vertice<String> v2 = new Vertice<String>("Nodo2");
		Vertice<String> v3 = new Vertice<String>("Nodo3");
		Vertice<String> v4 = new Vertice<String>("Nodo4");
		Vertice<String> v5 = new Vertice<String>("Nodo5");
		Vertice<String> v6 = new Vertice<String>("Nodo6");
		Vertice<String> v7 = new Vertice<String>("Nodo7");
		
		//--------------------------- Grafo1
		Grafo<String, String> g = new ListaAdyacencia<String, String>();
		Arco<String, String> a1 = new Arco<String, String>(v1, v2, "A1");
		Arco<String, String> a2 = new Arco<String, String>(v1, v4, "A2");
		Arco<String, String> a3 = new Arco<String, String>(v2, v4, "A3");
		Arco<String, String> a4 = new Arco<String, String>(v2, v3, "A4");
		Arco<String, String> a5 = new Arco<String, String>(v4, v3, "A5");
		Arco<String, String> a6 = new Arco<String, String>(v3, v1, "A6");
		Arco<String, String> a7 = new Arco<String, String>(v2, v6, "A7");
		Arco<String, String> a8 = new Arco<String, String>(v4, v7, "A8");
		Arco<String, String> a9 = new Arco<String, String>(v7, v6, "A9");
		Arco<String, String> a10 = new Arco<String, String>(v6, v5, "A10");
		Arco<String, String> a11 = new Arco<String, String>(v5, v7, "A11");
		g.insertarVertice(v1);
		g.insertarVertice(v2);
		g.insertarVertice(v3);
		g.insertarVertice(v4);
		g.insertarVertice(v5);
		g.insertarVertice(v6);
		g.insertarVertice(v7);
		g.insertarArco(a1);
		g.insertarArco(a2);
		g.insertarArco(a3);
		g.insertarArco(a4);
		g.insertarArco(a5);
		g.insertarArco(a6);
		g.insertarArco(a7);
		g.insertarArco(a8);
		g.insertarArco(a9);
		g.insertarArco(a10);
		g.insertarArco(a11);
		
		//--------------------------- Grafo2
		Grafo<String, String> g2 = new ListaAdyacencia<String, String>();
		Arco<String, String> a12 = new Arco<String, String>(v1, v2, "A1");
		Arco<String, String> a13 = new Arco<String, String>(v2, v3, "A2");
		Arco<String, String> a14 = new Arco<String, String>(v3, v4, "A3");
		Arco<String, String> a15 = new Arco<String, String>(v4, v1, "A4");
		g2.insertarVertice(v1);
		g2.insertarVertice(v2);
		g2.insertarVertice(v3);
		g2.insertarVertice(v4);
		g2.insertarArco(a12);
		g2.insertarArco(a13);
		g2.insertarArco(a14);
		g2.insertarArco(a15);
		
		//--------------------------- Grafo3
		Grafo<String, String> g3 = new ListaAdyacencia<String, String>();
		Arco<String, String> a16 = new Arco<String, String>(v1, v2, "A1");
		Arco<String, String> a17 = new Arco<String, String>(v2, v3, "A2");
		Arco<String, String> a18 = new Arco<String, String>(v2, v4, "A3");
		//Arco<String, String> a19 = new Arco<String, String>(v2, v5, "A4");
		g3.insertarVertice(v1);
		g3.insertarVertice(v2);
		g3.insertarVertice(v3);
		g3.insertarVertice(v4);
		//g3.insertarVertice(v5);
		g3.insertarArco(a16);
		g3.insertarArco(a17);
		g3.insertarArco(a18);
		//g3.insertarArco(a19);
		
		//----------------------Test Zone----------------------------
		
		System.out.println("Vacio: " + g.esVacio());
		System.out.println("Esta vertice A4: " + g.estaVertice(v7));
		System.out.println("Esta arco A1: " + g.estaArco(a1));
		System.out.println("--------- Arcos -----------");
		Iterator<Arco<String, String>> it = g.arcos();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println("--------- Vertices -----------");
		Iterator<Vertice<String>> it2 = g.vertices();
		while(it2.hasNext()){
			System.out.println(it2.next());
		}
		System.out.println("--------- Adyacentes Vertice6: -----------");
		Iterator<Vertice<String>> it3 = g.adyacentes(v6);
		while(it3.hasNext()){
			System.out.println(it3.next());
		}
		System.out.println("--------- Predecesores Vertice6: -----------");
		Iterator<Vertice<String>> it4 = predecesores(g, v6);
		while(it4.hasNext()){
			System.out.println(it4.next());
		}
		
		System.out.println("--------- pozo_sumidero Grafo1 Vertice6: -----------");
		System.out.println(pozo_sumidero(g, v6));
		
		System.out.println("--------- Regular g2: -----------");
		System.out.println(regular(g2));
		
		System.out.println("--------- Impar g3: -----------");
		System.out.println(impar(g3));
		
		System.out.println("--------- Camino g3: -----------");
		System.out.println(caminoSimple(g3, v1, v4));
		
	}

}
