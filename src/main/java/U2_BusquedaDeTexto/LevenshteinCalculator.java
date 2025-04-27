package U2_BusquedaDeTexto;

import java.util.Arrays;
import java.util.Locale;

public class LevenshteinCalculator {

    public static void main(String[] args) {
        System.out.println(getDistance("hola", "ola")); // 1
        System.out.println(getDistance("hola", "")); // 4
        System.out.println(getDistance("hola", "hola")); // 0
        System.out.println(getDistance("", "")); // 0
        System.out.println(getDistance("big data", "bigdaa")); // 2

        System.out.println("- - - - - - -");

        System.out.println(getDistanceV2("hola", "ola")); // 1
        System.out.println(getDistanceV2("hola", "")); // 4
        System.out.println(getDistanceV2("hola", "hola")); // 0
        System.out.println(getDistanceV2("", "")); // 0
        System.out.println(getDistanceV2("bigdata", "big--data")); // 2

        System.out.println("- - - - - - -");

        System.out.println(normalizeSimilarity("big data", "bigdaa")); // 0.75

    }

    public static int getDistance(String str1, String str2) {
        char[] s1 = str1.toUpperCase().toCharArray();
        char[] s2 = str2.toUpperCase().toCharArray();
        int n = s1.length, m = s2.length;
        if (n == 0 || m == 0) {
            return Math.max(n, m);
        }

        int[][] values = new int[n][m];
        // fill the first col
        for (int i = 0; i < n; i++) {
            values[i][0] = i;
        }

        // fill the first row
        for (int j = 0; j < m; j++) {
            values[0][j] = j;
        }

        // fill the rest with the values
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                values[i][j] = values[i-1][j-1] + (s1[i] != s2[j] ? 1 : 0);
                values[i][j] = Math.min(values[i][j], Math.min(values[i-1][j] + 1, values[i][j-1] + 1));
            }
        }

        return values[n-1][m-1];
    }

    public static int getDistanceV2(String str1, String str2) {
        // Now without a matrix but with 2 arrays
        char[] s1 = str1.toUpperCase().toCharArray();
        char[] s2 = str2.toUpperCase().toCharArray();

        if (s1.length == 0 || s2.length == 0) {
            return Math.max(s1.length, s2.length);
        }

        // make sure s2 is shorter
        if (s1.length < s2.length) {
            char[] temp = s1;
            s1 = s2;
            s2 = temp;
        }
        // m < n
        int n = s1.length, m = s2.length;

        int[] prev = new int[m];
        int[] curr = new int[m];

        for (int j = 0; j < m; j++) {
            prev[j] = j;
        }

        for (int i = 1; i < n; i++) {
            curr[0] = i;
            for (int j = 1; j < m; j++) {
                int temp = prev[j-1] + (s1[i] == s2[j] ? 0 : 1);

                curr[j] = Math.min(temp, Math.min(curr[j-1] + 1, prev[j] + 1));
            }
            prev = curr;
            curr = new int[m];
        }

        return curr[m-1];
    }

    private static double normalizeSimilarity(String s1, String s2) {
        return 1 - getDistanceV2(s1, s2) / (double) Math.max(s1.length(), s2.length());
    }
}
