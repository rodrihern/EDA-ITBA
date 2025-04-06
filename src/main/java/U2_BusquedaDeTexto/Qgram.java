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

        Map<String, Integer> tok1 = getTokens(s1);
        Map<String, Integer> tok2 = getTokens(s2);

        int count = 0;
        int sum = 0;

        for (String tok : tok1.keySet()) {
            sum += tok1.get(tok);
            if (tok2.containsKey(tok)) {
                count += Math.min(tok1.get(tok), tok2.get(tok));
            }
        }
        for (String tok : tok2.keySet()) {
            sum += tok2.get(tok);
        }

        return count / (double) sum;
    }


}
