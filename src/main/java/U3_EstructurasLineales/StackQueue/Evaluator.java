package U3_EstructurasLineales.StackQueue;


import java.util.*;

public class Evaluator {

    private final Map<String, Operation> operationMap = new HashMap<>();
    private Map<String, Double> variables;

    private static final Map<String, Integer> precedenceMap = new HashMap<>() {
        { put("+", 0); put("-", 1); put("*", 2); put("/", 3); put("^", 4); put("(", 5); put(")", 6);}
    };

    private static final boolean[][] precedenceMatriz= {
            // +      -      *      /      ^      (      )
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


    public Evaluator(Map<String, Double> variables)  {
        this.variables = variables;
        for (Operation op : Operation.values()) {
            operationMap.put(op.toString(), op);
        }

        Scanner input = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresion en notacion infija: ");
        String line= input.nextLine();
        input.close();


        scannerLine = new Scanner(line).useDelimiter("\\s+");
    }

    public Evaluator() {
        this(new HashMap<>());
    }
    
    // Constructor for testing purposes
    public Evaluator(String expression, Map<String, Double> variables) {
        this.variables = variables;
        for (Operation op : Operation.values()) {
            operationMap.put(op.toString(), op);
        }
        scannerLine = new Scanner(expression).useDelimiter("\\s+");
    }
    
    // Constructor for testing with no variables
    public Evaluator(String expression) {
        this(expression, new HashMap<>());
    }


    public Double evaluate() {
        // hay que pasarlo a infija primero
        Deque<Double> operands = new ArrayDeque<>();
        scannerLine = new Scanner(infijaToPostfija()).useDelimiter("\\s+");

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

    public String infijaToPostfija() {
        StringBuilder postfija= new StringBuilder();
        Deque<String> operators = new ArrayDeque<>();


        while( scannerLine.hasNext() ) {
            String current = scannerLine.next();
            System.out.println("token: " + current);
            System.out.println(operators);
            System.out.println("postfija: " + postfija);
            System.out.println("-------------------");

            try { // vemos si es un numero
                double num = Double.parseDouble(current);
                postfija.append(num).append(" ");
            } catch (Exception _) { // no es un numero
                if (variables.containsKey(current)) { // vemos si es una variable
                    postfija.append(variables.get(current)).append(" ");
                } else { // no es una variable
                    while (!operators.isEmpty() && getPrecedence(operators.peek(), current)) {
                        // opera con todos los que tengan mayor precedencia
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
        }

        while (!operators.isEmpty()) {
            postfija.append(operators.pop()).append(" ");
        }

        return postfija.toString();
    }


    public static void main(String[] args) {
        String input = "5 ^ ( 1 - 1 ) ^ ( 3 / 1 ) * 5";
        System.out.println("infija: " + input);
        System.out.println("-------------------");
        Evaluator eval = new Evaluator(input, new HashMap<>());
        System.out.println("postfija: " + eval.infijaToPostfija());
    }
}

