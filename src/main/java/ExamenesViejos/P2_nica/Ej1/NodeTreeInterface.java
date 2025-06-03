package ExamenesViejos.P2_nica.Ej1;


public interface NodeTreeInterface<T extends Comparable<? super T>> {

	T getData();

	NodeTreeInterface<T> getLeft();

	NodeTreeInterface<T> getRight();

}