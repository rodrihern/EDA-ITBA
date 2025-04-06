package U2_BusquedaDeTexto;

public class Tester {
    public static void main(String[] args) {
        Qgram q = new Qgram(3);

        System.out.println(q.similarity("holanda", "olavarria"));
        System.out.println(q.similarity("hola", "holanda"));
    }
}
