package U4_Hashing;

import java.util.function.Function;

public class ClosedHashing<K, V> implements IndexParametricService<K, V> {
    final private int initialLookupSize= 10;
    private final double maxLoadFactor = 0.6;

    // estática. No crece. Espacio suficiente...
    @SuppressWarnings({"unchecked"})
    private Slot<K,V>[] lookup = (Slot<K,V>[]) new Slot[initialLookupSize];
    private int usedKeys = 0;
    private Function<? super K, Integer> prehash;

    public ClosedHashing( Function<? super K, Integer> mappingFn) {
        if (mappingFn == null)
            throw new RuntimeException("fn not provided");

        prehash= mappingFn;
    }

    // ajuste al tamaño de la tabla
    private int hash(K key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");

        return prehash.apply(key) % lookup.length;
    }



    public void insertOrUpdate(K key, V data) {
        if (key == null || data == null) {
            String msg= String.format("inserting or updating (%s,%s). ", key, data);
            if (key==null)
                msg+= "Key cannot be null. ";

            if (data==null)
                msg+= "Data cannot be null.";

            throw new IllegalArgumentException(msg);
        }

        int hashing = hash(key);
        // colision
        if (lookup[hashing] != null && !key.equals(lookup[hashing].key)) {
            throw new IllegalArgumentException();
        }

        if (lookup[hashing] == null) {
            usedKeys++;
            double loadFactor = usedKeys / (double) lookup.length;
            if (loadFactor > maxLoadFactor) {
                resize();
            }
        }

        lookup[ hash(key) ] = new Slot<K, V>(key, data);
    }

    private void resize() {
        Slot<K,V>[] temp = lookup;
        lookup = (Slot<K,V>[]) new Slot[2*temp.length];
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == null) {
                continue;
            }
            lookup[hash(temp[i].key)] = temp[i];
        }
    }

    // find or get
    public V find(K key) {
        if (key == null)
            return null;

        Slot<K, V> entry = lookup[hash(key)];
        if (entry == null)
            return null;

        return entry.value;
    }

    public boolean remove(K key) {
        if (key == null)
            return false;

        // lo encontre?
        if (lookup[ hash( key) ] == null)
            return false;

        lookup[ hash( key) ] = null;
        return true;
    }


    public void dump()  {
        for(int rec = 0; rec < lookup.length; rec++) {
            if (lookup[rec] == null)
                System.out.printf("slot %d is empty%n", rec);
            else
                System.out.printf("slot %d contains %s%n",rec, lookup[rec]);
        }
    }


    public int size() {
        return usedKeys;
    }



    static private final class Slot<K, V>	{
        private final K key;
        private V value;
        private boolean isDeleted = false;

        private Slot(K theKey, V theValue){
            key= theKey;
            value= theValue;
        }


        public String toString() {
            return String.format("(key=%s, value=%s)", key, value );
        }
    }


    public static void main(String[] args) {
        ClosedHashing<Integer, String> myHash= new ClosedHashing<>(f->f);
        myHash.insertOrUpdate(55, "Ana");
        myHash.insertOrUpdate(44, "Juan");
        myHash.insertOrUpdate(18, "Paula");
        myHash.insertOrUpdate(19, "Lucas");
        myHash.insertOrUpdate(21, "Sol");
        myHash.dump();

    }

/*
	public static void main(String[] args) {
		ClosedHashing<Integer, String> myHash= new ClosedHashing<>(f->f);
		myHash.insertOrUpdate(55, "Ana");
		myHash.insertOrUpdate(29, "Victor");
		myHash.insertOrUpdate(25, "Tomas");
		myHash.insertOrUpdate(19, "Lucas");
		myHash.insertOrUpdate(21, "Sol");
		myHash.dump();
	}
*/
}
