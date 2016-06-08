public class FamiliasJuego {
	static Personaje [][] familias = {
			  {new Personaje("Pulgarcita"), new Personaje("Señor Risas"), new Personaje("Elissandre"),new Personaje("Darius"),new Personaje("Samson")}, 
			  {new Personaje("Los gemelos"), new Personaje("Goody Zarr"), new Personaje("Butterfield"), new Personaje("Lola"), new Personaje("Lord")},
			  {new Personaje("Angel"), new Personaje("Balthazar"), new Personaje("Willen"), new Personaje("Primo"), new Personaje("Dam")}, 
			  {new Personaje("Grogar"), new Personaje("Melisa"), new Personaje("Helena"), new Personaje("Slogar"), new Personaje("Elias")}
			  };
	
	private static int[] familiasSeleccionadas = new int[4];
	private static int cantSeleccionadas=0;
	
	public static Familia getFamilia(int i)
	{
		return new Familia(familias[i]);
	}
	
	public static void setSeleccionada(int opt){
		familiasSeleccionadas[cantSeleccionadas] = opt;
		cantSeleccionadas++;
	}
	
	public static boolean seleccionada(int opt){
		boolean picked = false;
		int pos = 0;
		while(pos < familiasSeleccionadas.length && !picked){
			if(familiasSeleccionadas[pos] == opt)
				picked = true;
			pos++;
		}
		return picked;
	}
	
	public static String listarFamilia(int i){
		String data="";
    	
    	for(int j=0;j<familias[i].length;j++){
    		data += familias[i][j].getNombre() + ","; 
    	}
    	return data;
    }
}
