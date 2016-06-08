public class Actividad2 {
	
	public static void checkArray(int[] array){
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}
	
	public static void ordenarConteo(int[] aux)
	{
		int[] cont = new int[aux.length];
		
			for(int i=0;i < aux.length;i++)
			{
				for(int j=0;j < aux.length;j++)
				{
					if((aux[i] > aux[j]))
					{
						cont[i]++;
					}
				}
			} 
		System.out.println("\nArray tempo:");
		
		checkArray(cont);
		
		int[] aux2 = new int[aux.length];
		
			for(int i=0;i<aux.length;i++)
			{
				aux2[cont[i]]=aux[i];
			}
			
			for(int i=0;i<aux.length;i++)
			{
				aux[i]=aux2[i];
			}
	}
	
	public static int BusquedaInsercion(int[] aux,int elem,int max)
	{
		int mid = 0;
		int start = 0;
		int end = aux.length-1;
		
		/* Binary search */
		while(start <= end){
			mid = (start+end)/2;
			if(aux[mid] < elem){
				start = mid + 1;
			}else{
				if(aux[mid] > elem){
					end = mid - 1;
				}else{
					return mid;
				}
			}
		}
		/* End of binary search */
		
		/* Check if the array it´s full */
		if(aux.length == max + 1){
			System.out.println("Cannot save, array it´s full");
			return -1;
		}else{
			/* Check if the last element it´s smaller than the new element */
			if(aux[max] < elem){
				aux[max+1] = elem;
				return max+1;
			}else{
				/* Add the element into the right position  */
				for(int i=max+1;i>start;i--){
					aux[i] = aux[i-1];
				}
				aux[start] = elem;
				return start;
			}			
		}
	}
	
	private static int indice(int elem, int pasada){
		return (elem/((int)Math.pow(10,pasada)))%10; 
	}
	
	public static void radixSort (int [] aux){
		
		int [][] urnas = new int [aux.length][10];
		int cont = 0;
		
		for(int i=0;i<3;i++){
			/* Set the matrix */
			for(int j=0;j<aux.length;j++){
				int place = indice(aux[j],i);
				
				if(urnas[cont][place] == 0){
					urnas[cont][place] = aux[j];
				}else{
					while(urnas[cont][place] != 0){
						cont++;
					}
					urnas[cont][place] = aux[j];
				}
				cont=0;
			}
			/* Save matrix to array and clear the matrix */
			int pos=0;
			for(int j=1;j<=9;j++){
				for(int k=0;k<aux.length;k++){
					if(urnas[k][j] != 0){
						aux[pos] = urnas[k][j];
						pos++;
						urnas[k][j] = 0;
					}
				}	
			}
			
		}	
	}

	public static void main(String[] args) {
		System.out.println("---|Ordenacion por conteo|---:");
		int[] A ={7,3,8,1,4,6,6};
		System.out.println("Sin Ordenar:");
		checkArray(A);
		ordenarConteo(A);
		System.out.println("\nOrdenado:");
		checkArray(A);
	
		System.out.println("\n---|Ordenacion Insercion|---:");
		int[] B ={2,5,8,10,20,35,45,0,0};
		int toFind = 3;
		System.out.println("Array:");
		checkArray(B);
		System.out.println("\nElemento a buscar:" + toFind);
		System.out.println("Pos:"+BusquedaInsercion(B,toFind,6));
		System.out.println("Array:");
		checkArray(B);
		
		System.out.println("\n---|RadixSort|---:");
		int[] C ={345,721,425,572,836,467,672,194,365,236,891,746,431,834,247,529,216,389};
		System.out.println("Array:");
		checkArray(C);
		radixSort(C);
		System.out.println("\nOrdenado:");
		checkArray(C);
	}
}


