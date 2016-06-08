public class  Nodo <E>
{
    private E elemento;
    private Nodo<E> siguiente;
    
    public Nodo (E elem, Nodo<E> sig){
        elemento = elem;
        siguiente = sig;
    }
    
    public E getElemento (){
        return elemento;
    }
    
    public void setElemento ( E elem ){
        elemento = elem;
    }
    
    public Nodo<E> getSiguiente (){
        return siguiente;
    }

    public void setSiguiente ( Nodo<E> sig ){
        siguiente = sig; 
    }
}