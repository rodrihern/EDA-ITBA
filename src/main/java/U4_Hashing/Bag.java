package U4_Hashing;

public interface Bag<T>{

    void add(T value);

    void remove(T value);

    int getCount(T value);

    void dump();

}

