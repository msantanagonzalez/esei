package actividad7_AEDII;

public class Par<K, V> {
	private K k;
	private V v;
	
	public Par(K clave, V valor){
		k = clave;
		v = valor;
	}
	
	public K getK(){
		return k;
	}
	public V getV(){
		return v;
	}
	
	public void setK(K nk){
		k = nk;
	}
	public void setV(V nv){
		v = nv;
	}
}
