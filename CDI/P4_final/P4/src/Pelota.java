public class Pelota {
	public void golpear(Ping p){
		System.out.println(p.getNombre()+" hits the ball");
		System.out.println("Pasing ball");
		p.takeBall();
	}
}
