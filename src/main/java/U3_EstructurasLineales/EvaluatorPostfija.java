package U3_EstructurasLineales;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class EvaluatorPostfija {


    private final Scanner scannerLine;


    public EvaluatorPostfija()
    {
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresiOn en notacion postfija: ");
        inputScanner.hasNextLine();

        String line = inputScanner.nextLine();

        scannerLine = new Scanner(line).useDelimiter("\\s+");

    }


    public Double evaluate() {
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





    public static void main(String[] args) {
        Double rta = new EvaluatorPostfija().evaluate();
        System.out.println(rta);
    }


}
