package actividad7_AEDII;
import java.util.Iterator;

public interface map<K,V> {
	public int tama�o();
	public V get (K clave);
	public void insertar(K clave, V valor);
	public V eliminar(K clave);
	public Iterator<K> getClaves();
	public Iterator<V> getValores();
}
