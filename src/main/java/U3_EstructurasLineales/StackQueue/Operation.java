package U3_EstructurasLineales.StackQueue;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;

public enum Operation {

    SUM("+", Double::sum),
    SUB("-", (x, y) -> x-y),
    MUL("*", (x, y) -> x*y),
    DIV("/", (x, y) -> x/y),
    POW("^", Math::pow);

    private final String symbol;
    private BinaryOperator<Double> fun;

    Operation(String symbol, BinaryOperator<Double> fun) {
        this.symbol = symbol;
        this.fun = fun;
    }

    @Override
    public String toString() {
        return "%s".formatted(symbol);
    }

    public double apply(double n1, double n2) {
        return fun.apply(n1, n2);
    }
}
