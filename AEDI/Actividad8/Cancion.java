public class Cancion {
	private String title;
	private double duration;
	
	public Cancion(String title,double duration){
		this.title = title;
		this.duration = duration;
	}
	
	public String getTitulo(){
		return this.title;
	}
	
	public double getDuration(){
		return this.duration;
	}
}
