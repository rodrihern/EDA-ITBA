package ExamenesViejos.P1_2022_2C;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class StringEval {

    public String evaluate(String expression) {
        Deque<String> operands = new ArrayDeque<>();
        String[] tokens = expression.split("\\s+");
        // TODO: completar

        for (String tok : tokens) {
            if (tok.matches("^[a-zA-Z]+$")) { // operand
                operands.push(tok);
            } else { // operator
                try {
                    String s2 = operands.pop();
                    String s1 = operands.pop();
                    operands.push(apply(s1, s2, tok));
                } catch (Exception _) {
                    throw new RuntimeException("Missing operands");
                }
            }
        }

        if (operands.size() != 1) {
            throw new RuntimeException("invalid expression");
        }

        return operands.pop();


    }

    private String apply(String s1, String s2, String op) {
        String res;
        switch (op) {
            case "+" -> res = concatenate(s1, s2);
            case "-" -> res = delete(s1, s2);
            case "*" -> res = merge(s1, s2);
            case "/" -> res = div(s1, s2);
            case "^" -> res = pow(s1, s2);
            default -> throw new RuntimeException("invalid operator");
        }

        return res;
    }




    // funciones

    private String concatenate(String s1, String s2) {
        return s1 + s2;
    }

    private String delete(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int i = indexOf(c2, c1);
        if (i < 0) {
            return s1;
        }

        return s1.substring(0, i) + s1.substring(i+c2.length);
    }

    private String merge(String s1, String s2) {
        StringBuilder res = new StringBuilder();
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int i = 0, j = 0;
        while (i < c1.length && j < c2.length) {
            res.append(c1[i++]).append(c2[j++]);
        }
        while (i < c1.length) {
            res.append(c1[i++]);
        }
        while (j < c2.length) {
            res.append(c2[j++]);
        }

        return res.toString();
    }

    private String div(String s1, String s2){
        for(int i = 0; i < s2.length(); i++){
            String toDel = "" + s2.charAt(i);
            s1 = s1.replaceAll(toDel, "");
        }
        return s1;
    }

    private String pow(String s1, String s2){
        StringBuilder s = new StringBuilder();
        for(int i = 1; i <= s2.length(); i++) {
            s.append(s1);
            s.append(s2, 0, i);
        }
        return s.toString();
    }





    // KMP
    private static int[] nextComputation(char[] query) {
        int[] next = new int[query.length];

        int border=0;  // Length of the current border

        int rec=1;
        while(rec < query.length){
            if(query[rec]!=query[border]){
                if(border!=0)
                    border=next[border-1];
                else
                    next[rec++]=0;
            }
            else{
                border++;
                next[rec]=border;
                rec++;
            }
        }
        return next;
    }

    private int indexOf( char[] query, char[] target) {
        int[] lps = nextComputation(query);
        int i = 0, j = 0;
        while (i < target.length) {
            if (query[j] == target[i]) {
                j++;
                i++;
            } else {
                if (j != 0) {
                    j = lps[j-1];
                } else {
                    i++;
                }
            }

            if (j == query.length) {
                return i - j;
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        StringEval stringEval = new StringEval();
        System.out.println(stringEval.evaluate("AA BB CC DEF ^ * AE / + BC -"));
        System.out.println("AABCDCCDCCDF");
        System.out.println(stringEval.evaluate("HOLA QUE + TAL COMO ^ ESTAS / BIEN * + BIEN -"));
        System.out.println("HOLAQUELBCILECNOLCOMLCOMO");
    }
}

