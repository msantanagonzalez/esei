package actividad7_AEDII;

public class Persona {
	private String name;
	private String email;
	
	public Persona(String n, String e){
		name = n;
		email = e;
	}
	
	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return email;
	}
}
