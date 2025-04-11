package U3_EstructurasLineales.StackQueue;


import java.util.*;

public class EvaluatorInFijaBasicOperator {

    // opcion 1, esto porai hacerlo de otra manera para el ultimo

    private static final Map<String, Integer> precedenceMap = new HashMap<String, Integer>() {
        { put("+", 0); put("-", 1); put("*", 2); put("/", 3); put("^", 4); put("(", 5); put(")", 6);}
    };
    private final Map<String, Operation> operationMap = new HashMap<>();

    private static final boolean[][] precedenceMatriz= {
            { true,  true,  false, false, false, false, true  }, // +
            { true,  true,  false, false, false, false, true  }, // -
            { true,  true,  true,  true,  false, false, true  }, // *
            { true,  true,  true,  true,  false, false, true  }, // /
            { true,  true,  true,  true,  false, false, true  }, // ^
            { false, false, false, false, false, false, false }  // (
    };

    private boolean getPrecedence(String tope, String current) {
        Integer topeIndex;
        Integer currentIndex;

        if ((topeIndex= precedenceMap.get(tope))== null)
            throw new RuntimeException(String.format("tope operator %s not found", tope));

        if ((currentIndex= precedenceMap.get(current)) == null)
            throw new RuntimeException(String.format("current operator %s not found", current));

        return precedenceMatriz[topeIndex][currentIndex];
    }



    private Scanner scannerLine;

    public EvaluatorInFijaBasicOperator()  {
        Scanner input = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresion en notacion infija: ");
        String line= input.nextLine();
        input.close();

        for (Operation op : Operation.values()) {
            operationMap.put(op.toString(), op);
        }

        scannerLine = new Scanner(line).useDelimiter("\\s+");
    }


    // TODO: cambiar el try-catch del parseDouble cuando hagamos el metodo isOperand
    public Double evaluate() {
        // hay que pasarlo a infija primero
        Deque<Double> operands = new ArrayDeque<>();
        scannerLine = new Scanner(infijaToPostfija()).useDelimiter("\\s+");;

        while( scannerLine.hasNext() ) {
            String tok = scannerLine.next();
            try {
                double num = Double.parseDouble(tok);
                operands.push(num);
            } catch (Exception _) {
                try {
                    double op2 = operands.pop();
                    double op1 = operands.pop();

                    try {
                        operands.push(operationMap.get(tok).apply(op1, op2));
                    } catch (Exception _) {
                        throw new RuntimeException("Invalid operator");
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


    private String infijaToPostfija() {
        StringBuilder postfija= new StringBuilder();
        Deque<String> operators = new ArrayDeque<>();


        while( scannerLine.hasNext() ) {
            String current = scannerLine.next();

            // TODO: cambiar por un if-else con un metodo isOperand
            try {
                double num = Double.parseDouble(current);
                postfija.append(num).append(" ");
            } catch (Exception _) {
                while (!operators.isEmpty() && getPrecedence(operators.peek(), current)) {
                    postfija.append(operators.pop()).append(" ");
                }
                if (current.equals(")")) {
                    if ("(".equals(operators.peek())) {
                        operators.pop();
                    } else {
                        throw new RuntimeException("closing parenthesis without previous opening");
                    }

                } else {
                    operators.push(current);
                }
            }
        }

        while (!operators.isEmpty()) {
            postfija.append(operators.pop()).append(" ");
        }

        return postfija.toString();
    }




    public static void main(String[] args) {
        EvaluatorInFijaBasicOperator e = new EvaluatorInFijaBasicOperator();
        System.out.println(e.evaluate());
    }
}
