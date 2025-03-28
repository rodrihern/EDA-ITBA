package U2_BusquedaDeTexto;

public class SoundexGenerator {
    private static final char[] weights = {'0', '1', '2', '3', '0', '1', '2', '0', '0', '2', '2', '4', '5', '5', '0', '1', '2', '6', '2', '3', '0', '1', '0', '2', '0', '2'};

    public char[] soundex(String s) {
        char[] word = s.toUpperCase().toCharArray();
        char[] res = {word[0], '0', '0', '0'};
        char prev = getMapping(word[0]);
        for (int i = 1, j = 1; i < word.length && j < 4; i++) {
            char current = getMapping(word[i]);
            if (current != 0 && current != prev) {
                res[j++] = current;
            }
            prev = current;
        }

        return res;
    }

    private char getMapping(char ch) {
        return weights[ch - 'A'];
    }
}
