import pila.*;
import cola.*;
public class main {
	
	public static <E> boolean iguales (Pila<E> p1,Pila<E> p2){
		if(p1.tamaño() != p2.tamaño())
			return false;
		while(!p1.esVacio() && !p2.esVacio()){
			if(!p1.pop().equals(p2.pop())){
				return false;
			}
		}
		return true;
	}
	
	public static <E> Pila<E> copia(Pila<E> p){
		Pila<E> resultado = new EnlazadaPila<>();
		Pila<E> tempo = new EnlazadaPila<>();
		//1,2,3,4
		while(!p.esVacio()){
			tempo.push(p.pop());
		}
		//4,3,2,1
		while(!tempo.esVacio()){
			E elemento = tempo.pop();
			resultado.push(elemento);
			p.push(elemento);
		}
		//1,2,3,4
		return resultado;
	}
	
	public static String invert(String texto){
		String result = "";
		Pila<Character> tempo = new EnlazadaPila<>();
		for(int i=0;i<texto.length();i++){
			if(texto.charAt(i) == ' '){
				result += " ";
				while(!tempo.esVacio()){
					result += tempo.pop();
				}
			}else{
				tempo.push(texto.charAt(i));
			}
		}
		result += " ";
		while(!tempo.esVacio()){
			result += tempo.pop();
		}
		return result;
	}
	
	public static void decimalToBin(int number){
		Pila<Integer> tempo = new EnlazadaPila<>();
		while(number > 0){
			tempo.push(number%2);
			number=number/2;
		}
		while(!tempo.esVacio()){
			System.out.println(tempo.pop());
		}
	}
	
	public static <E> void concatenar(Cola<E> q1,Cola<E> q2){
		while(!q2.esVacio()){
			q1.insertar(q2.suprimir());
		}
	}
	
	public static <E> Cola<E> barajar(Cola<E> q1, Cola<E> q2){
		Cola<E> tempo = new EnlazadaCola<E>();
		while(!q1.esVacio() && !q2.esVacio()){
			tempo.insertar(q1.suprimir());	
			tempo.insertar(q2.suprimir());	
		}
		while(!q1.esVacio()){
			tempo.insertar(q1.suprimir());
		}
		while(!q2.esVacio()){
			tempo.insertar(q2.suprimir());
		}
		return tempo;
	}
	
	public static void main(String[] args) {
		System.out.println(invert("Hello my name its Marco"));
		decimalToBin(7);
	}

}
