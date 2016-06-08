
public class main {

	public static void main(String[] args) {
		Papeleta p1 = new Papeleta(1, 2);
		Papeleta p2 = new Papeleta(3, 1);
		Papeleta p3 = new Papeleta(7, 3);
		Papeleta p4 = new Papeleta(1, 8);
		
		UrnaCentinela<Papeleta> urna1 = new UrnaCentinela<Papeleta>();
		
		urna1.addElement(p1);
		urna1.addElement(p2);
		urna1.addElement(p3);
		urna1.addElement(p4);
		
		/* Use of interface */
		int[] resultados = new int[11];
		
		/*
		 * Old one, with mine interface
		while(urna1.amountElements()!=0){
			Papeleta tempo = urna1.getElement();
			resultados[tempo.getCandidato1()]++;
			resultados[tempo.getCandidato2()]++;
			urna1.delElement(tempo);
		}
		*/
		
		while(urna1.amountElements()!=0){
			Papeleta tempo = urna1.getElement();
			resultados[tempo.getCandidato1()]++;
			resultados[tempo.getCandidato2()]++;
		}
		
		for(int i=1;i<resultados.length;i++){
			System.out.println(i+") - Votos: " + resultados[i]);
		}
	}

}
