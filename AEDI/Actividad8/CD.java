import lista.*;


public class CD {
	private String author;
	private String title;
	private Lista<Cancion> songs;
	
	public CD(String author,String title,Lista<Cancion> songs){
		this.author = author;
		this.title = title;
		this.songs = songs;
	}
	
	public String getAuthor(){
		return this.author;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public Lista<Cancion> getSongs(){
		return this.songs;
	}
}
