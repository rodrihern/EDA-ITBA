package ExamenesViejos.P2_nica.Ej1;


public interface BSTreeInterface<T extends Comparable<? super T>> {

	NodeTreeInterface<T> getRoot();
	
	int getHeight();

}