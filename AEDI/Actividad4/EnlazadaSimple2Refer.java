//Linked list
public class EnlazadaSimple2Refer {
	//Pointer to first and last
	private Nodo primero, ultimo;
	//Elements on the list
    private int numElem;           

    public EnlazadaSimple2Refer(){ 
        this.primero = null;
        this.ultimo = null;
        this.numElem = 0;
    }
    
    //Check how many times do we have a element inside 1-x nodes
    public int times (int e){
        int cont = 0;
        for (Nodo actual = this.primero; actual!=null; actual = actual.getSig()){
        	if (actual.getElemento()==e){
        		cont++;
        	}
        }
        return cont;
    }
    //Find a element, returns false if there's no element
    public boolean find(int e){
        Nodo actual = this.primero;
        while (actual!=null && actual.getElemento()!=e){
        	actual = actual.getSig();
        }
        return actual!=null;
    }
    //Add to first position on the linked list
    public void pushFirst(int e){ 
    	//Creates node linked to actual this.first
        Nodo n = new Nodo (e, this.primero);
        //Check if it's empty
        if (this.isEmpty()){
        	this.ultimo = n;
        }
        this.primero = n;
        this.numElem++;
    }
    //Add to last position on the linked list
    public void pushLast(int e){
    	//Creates node linked with null
        Nodo n = new Nodo (e, null);
        //Check if it's empty
        if (isEmpty()){
            this.primero = n;
        }else {
            this.ultimo.setSig(n);
        }
        this.ultimo = n;
       this.numElem ++;       
    }
    
    public void delete (int elem){
        if (isEmpty()){
        	System.out.println("|ERROR|- Empty list");
        }else{
        	//Check if element exist on the list
	            if(this.primero.getElemento() == elem){
	            	this.primero = this.primero.getSig();
	                this.numElem--;
	                if (this.primero == null){
	                	this.ultimo = null;
	                }
	            }else{
	            	Nodo actual = this.primero;
	            	//Check if element it's on the node
	            	while(actual.getSig()!=null && actual.getSig().getElemento()!=elem){
		            	actual = actual.getSig();
		            }
	            	
	            	if(actual.getSig() == null){
	            		System.out.println("|ERROR|- Element not in list");
	            	}else{
	            		actual.setSig(actual.getSig().getSig());
	            		this.numElem--;
	            		if(actual.getSig() == null){
	            			ultimo = actual;
	            		}
	            	}
	            }
        }
    } 
    
    //Check if linked list it's empty
    public boolean isEmpty(){
        return numElem==0;
    }
    
    //Print the linkedList
    public String toString(){
    	Nodo actual = this.primero;
    	String data = "| ";
    	
    	while(actual!=null){
    		data += actual.getElemento() + ",";
    		actual = actual.getSig();
    	}
    	data += " |";
    	return data;
    }
}
