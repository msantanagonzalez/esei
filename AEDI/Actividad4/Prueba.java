
public class Prueba {

	public static void main(String[] args) {
		EnlazadaSimple2Refer linkedList = new EnlazadaSimple2Refer();
		System.out.println("Before creating| It's empty?: "+linkedList.isEmpty());
		for(int i=1;i<6;i++){
			linkedList.pushLast(i);
		}
		System.out.println("Before creating| It's empty?: "+linkedList.isEmpty());
		System.out.println(linkedList.toString());
		System.out.println("Times 5: " + linkedList.times(5));
		linkedList.pushFirst(5);
		System.out.println(linkedList.toString());
		linkedList.pushFirst(1);
		System.out.println("Times 5: " + linkedList.times(5));
		System.out.println(linkedList.toString());
		System.out.println("----- Deleting one 5 -----");
		linkedList.delete(5);
		System.out.println(linkedList.toString());
	}

}
