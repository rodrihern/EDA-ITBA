package U2_BusquedaDeTexto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Qgram {



    private int q;

    public Qgram(int q) {
        this.q = q;
    }

    private HashMap<String, Integer> getTokens(String s) {
        String padding = new StringBuilder().repeat('#', q-1).toString();
        s = padding + s + padding;
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length()-q+1; i++) {
            map.merge(s.substring(i, i+q), 1, Integer::sum);
        }

        return map;
    }

    public void printTokens(String s) {
        Map<String, Integer> tokens = getTokens(s);
        for (String str : tokens.keySet()) {
            System.out.println(str);
        }
    }

    public double similarity(String s1, String s2) {



        return 0;
    }


}
