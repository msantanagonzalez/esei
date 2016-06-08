public class UrnaVaciaExcepcion extends RuntimeException{
    
	public UrnaVaciaExcepcion(){
        super ();
    }   
    
    public UrnaVaciaExcepcion ( String message ) {
        super (message);
    }
}