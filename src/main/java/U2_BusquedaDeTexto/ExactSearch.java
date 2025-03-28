package U2_BusquedaDeTexto;

import java.util.Arrays;

public class ExactSearch {

    public static void main(String[] args) {
        System.out.println(indexOf("mano".toCharArray(), "holamanmano".toCharArray()));
    }


    public static int indexOf(char[] query, char[] target) {
        if (query.length > target.length) {
            return -1;
        }
        for (int i = 0; i < target.length - query.length + 1; i++) {
            boolean isEqual = true;
            for (int j = 0; j < query.length && isEqual; j++) {
                if (query[j] != target[j+i]) {
                    isEqual = false;
                }
            }
            if(isEqual) {
                return i;
            }
        }

        return -1;
    }

}
