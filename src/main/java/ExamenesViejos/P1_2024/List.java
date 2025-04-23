package ExamenesViejos.P1_2024;

import java.util.Random;

public class List {

    private Item first;
    private Item last;

    public List (){
        first = null;
        last = null;
    }

    public List(int maxNumero ){
        if (maxNumero < 1)
            throw new RuntimeException("tope debe ser por lo menos 1");
        last = new Item(1);
        first = last;
        for ( int i = 2; i <= maxNumero; ++i ){
            Item c = new Item( i );
            last.next = c;
            last = c;
        }
    }

    public List (int lower, int numberItems, boolean sorpresa ){
        if (numberItems <= 0)
            throw new RuntimeException("cantidad de numeros debe ser mayor que 0");

        // lower bound cableado
        last = new Item(lower);
        first = last;

        while( --numberItems > 0) {
            if (sorpresa)
                lower+= 2;
            else
                lower+= 5;

            sorpresa= !sorpresa;
            Item c = new Item( lower );
            last.next = c;
            last = c;
        }
    }


    private  List[] randomSplitLists( Integer nLists ) {
        // como la lista ya viene ordenada siempre que inserto inserto al final
        // recorro la lista y los voy metiendo en el arreglo
        List[] res = new List[nLists];
        // inicializo todos los constructores
        for (int i = 0; i < nLists; i++) {
            res[i] = new List();
        }

        Item current = first;
        while (current != null) {
            Item next = current.next; // backup del next
            int idx = getRandom(nLists);
            res[idx].insert(current); // pierdo la referencia al siguiente (por eso me guardo el next)
            current = next;
        }

        return res;
    }

    private void insert(Item item) {
        if (first == null) {
            first = last = item;
            return;
        }
        last.next = item;
        last = item;
        item.next = null; // final de la lista
    }

    /*
     * La complejidad temporal es O(n) pues tiene que recorrer toda la lista
     *  insertar es lineal ya que insertamos directamente al final y es solo reacomodar punteros
     * La complejidad espacial es O(n) ya que se usa un array de tamaño nList
     * y no se necesita de espacio extra ya que usamos los mismos item ya creados
     * */



    private int randP = 1;
    private Random r = new Random(randP);

    private Integer getRandom(Integer n){
        Integer retVal = r.nextInt( n );
        System.out.println( " {" + randP + "} [" + retVal.toString() + "]" );
        ++randP;
        return retVal;
    }

    private final class Item {
        private final Integer numero;
        private Item next = null;

        public Item(Integer numero) {
            this.numero = numero;
        }

        public String toString(){
            return numero.toString();
        }
    }

    public void dump() {
        String auxi= "";

        Item rec = first;
        while (rec != null) {
            auxi+= String.format("%s->", rec);
            rec= rec.next;
        }
        if (auxi.length() >0   )
            auxi= auxi.substring(0, auxi.length()-2);

        System.out.print(String.format("List with header: first vble points to %s, last vble points to  %s, items: %s", first, last, auxi));


        System.out.println();
    }

    // caso 1 (main1)
    public static void main(String[] args) {
        List l = new List( 10 ); // l será: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
        // List original al principio
        System.out.print("First, the original list is ");
        l.dump();

        // distribuir entre 4
        List[] caso = l.randomSplitLists( 4 );

        for(int rec= 0; rec<caso.length; rec++) {
            System.out.print(String.format("list %d is ", rec));
            caso[rec].dump();
        }
        // List original al final
        System.out.print("Finally, the original list is ");
        l.dump();
    }


    // caso B (main2)
    public static void main2(String[] args) {
        List l = new List( 5, 7, true ); // l será: 5 -> 7 -> 12 -> 14->19->21->26
        // List original al principio
        System.out.print("First, the original list is ");
        l.dump();

        List[] caso = l.randomSplitLists( 6 );
        for(int rec= 0; rec<caso.length; rec++) {
            System.out.print(String.format("list %d is ", rec));
            caso[rec].dump();
        }
        // List original al final
        System.out.print("Finally, the original list is ");
        l.dump();
    }



    // caso de uso C (main 3)
    public static void main3(String[] args) {
        List l = new List( 5, 7, false ); // l será: 5 -> 10 ->12-> 17 -> 19->24->26
        // List original al principio
        System.out.print("First, the original list is ");
        l.dump();

        List[] caso = l.randomSplitLists( 6 );
        for(int rec= 0; rec<caso.length; rec++) {
            System.out.print(String.format("list %d is ", rec));
            caso[rec].dump();
        }

        // List original al final
        System.out.print("Finally, the original list is ");
        l.dump();
    }



    // caso de uso D (main4)
    public static void main4(String[] args) {
        List l = new List(); // l tiene 0 items
        // List original al principio
        System.out.print("First, the original list is ");
        l.dump();
        List[] caso = l.randomSplitLists( 4 );

        for(int rec= 0; rec<caso.length; rec++) {
            System.out.print(String.format("list %d is ", rec));
            caso[rec].dump();
        }
        // List original al final
        System.out.print("Finally, the original list is ");
        l.dump();
    }


}



