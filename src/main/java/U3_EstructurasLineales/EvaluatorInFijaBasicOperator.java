package U3_EstructurasLineales;


import java.util.*;

public class EvaluatorInFijaBasicOperator {

    // opcion 1, esto porai hacerlo de otra manera para el ultimo

    private static Map<String, Integer> mapping = new HashMap<String, Integer>()
    {   { put("+", 0); put("-", 1); put("*", 2); put("/", 3); }
    };

    private static final boolean[][] precedenceMatriz= {
            { true,  true,  false, false },
            { true,  true,  false, false },
            { true,  true,  true,  true  },
            { true,  true,  true,  true },
    };

    private boolean getPrecedence(String tope, String current)
    {
        Integer topeIndex;
        Integer currentIndex;

        if ((topeIndex= mapping.get(tope))== null)
            throw new RuntimeException(String.format("tope operator %s not found", tope));

        if ((currentIndex= mapping.get(current)) == null)
            throw new RuntimeException(String.format("current operator %s not found", current));

        return precedenceMatriz[topeIndex][currentIndex];
    }



    private final Scanner scannerLine;

    public EvaluatorInFijaBasicOperator()  {
        Scanner input = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresion en notacion infija: ");
        String line= input.nextLine();
        input.close();

        scannerLine = new Scanner(line).useDelimiter("\\s+");
    }



    public Double evaluate() {
        // hay que pasarlo a infija primero
        Deque<Double> operands = new ArrayDeque<>();

        while( scannerLine.hasNext() ) {
            String tok = scannerLine.next();
            try {
                double num = Double.parseDouble(tok);
                operands.push(num);
            } catch (Exception _) {
                try {
                    double op2 = operands.pop();
                    double op1 = operands.pop();

                    switch (tok) {
                        case "+" -> operands.push(op1 + op2);
                        case "-" -> operands.push(op1 - op2);
                        case "*" -> operands.push(op1 * op2);
                        case "/" -> operands.push(op1 / op2);
                        default -> throw new RuntimeException("Invalid input");
                    }

                } catch (Exception _) {
                    throw new RuntimeException("Missing operands bro");
                }

            }
        }

        if (operands.size() != 1) {
            throw new RuntimeException("Missing operators");
        }


        return operands.pop();
    }

    // TODO: ESTO

    private String infijaToPostfija()
    {
        StringBuilder postfija= new StringBuilder();
        Deque<String> operators = new ArrayDeque<>();


        while( scannerLine.hasNext() ) {
            String current = scannerLine.next();

            try {
                double num = Double.parseDouble(current);
                postfija.append(num);
            } catch (Exception _) {

                if (operators.isEmpty()) {
                    operators.push(current);
                } else {
                    while (!operators.isEmpty() && getPrecedence(operators.peek(), current)) {
                        postfija.append(operators.pop());
                    }
                    operators.push(current);
                }

            }
        }

        while (!operators.isEmpty()) {
            postfija.append(operators.pop());
        }


        System.out.println("Postfija= " + postfija);
        return postfija.toString();
    }




    public static void main(String[] args) {

        EvaluatorInFijaBasicOperator e = new EvaluatorInFijaBasicOperator();
        // System.out.println(e.evaluate());
        System.out.println(e.infijaToPostfija());

    }
}
