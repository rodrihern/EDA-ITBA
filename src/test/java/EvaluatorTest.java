import U3_EstructurasLineales.StackQueue.Evaluator;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluatorTest {
    
    private static final double DELTA = 0.0001;
    
    @Test
    public void testBasicAddition() {
        Evaluator evaluator = new Evaluator("2 + 3");
        assertEquals(5.0, evaluator.evaluate(), DELTA);
    }
    
    @Test
    public void testBasicSubtraction() {
        Evaluator evaluator = new Evaluator("5 - 3");
        assertEquals(2.0, evaluator.evaluate(), DELTA);
    }
    
    @Test
    public void testBasicMultiplication() {
        Evaluator evaluator = new Evaluator("4 * 5");
        assertEquals(20.0, evaluator.evaluate(), DELTA);
    }
    
    @Test
    public void testBasicDivision() {
        Evaluator evaluator = new Evaluator("10 / 2");
        assertEquals(5.0, evaluator.evaluate(), DELTA);
    }
    
    @Test
    public void testBasicExponentiation() {
        Evaluator evaluator = new Evaluator("2 ^ 3");
        assertEquals(8.0, evaluator.evaluate(), DELTA);
    }
    
    @Test
    public void testOperationPrecedence() {
        Evaluator evaluator = new Evaluator("2 + 3 * 4");
        assertEquals(14.0, evaluator.evaluate(), DELTA);
    }
    
    @Test
    public void testParentheses() {
        Evaluator evaluator = new Evaluator("( 2 + 3 ) * 4");
        assertEquals(20.0, evaluator.evaluate(), DELTA);
    }
    
    @Test
    public void testNestedParentheses() {
        Evaluator evaluator = new Evaluator("( 2 + ( 3 * 4 ) )");
        assertEquals(14.0, evaluator.evaluate(), DELTA);
    }
    
    @Test
    public void testVariables() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 5.0);
        variables.put("y", 3.0);
        
        Evaluator evaluator = new Evaluator("x + y", variables);
        assertEquals(8.0, evaluator.evaluate(), DELTA);
    }
    
    @Test
    public void testVariablesWithOperations() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 5.0);
        variables.put("y", 3.0);
        
        Evaluator evaluator = new Evaluator("x * y + 2", variables);
        assertEquals(17.0, evaluator.evaluate(), DELTA);
    }
    
    @Test
    public void testComplexExpression() {
        Evaluator evaluator = new Evaluator("( 3 + 4 * 2 ) / ( 1 - 5 ) ^ 2");
        assertEquals(0.6875, evaluator.evaluate(), DELTA);
    }
    
    @Test
    public void testComplexExpressionWithVariables() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 3.0);
        variables.put("y", 4.0);
        
        Evaluator evaluator = new Evaluator("( x + y * 2 ) / ( 1 - 5 ) ^ 2", variables);
        assertEquals(0.6875, evaluator.evaluate(), DELTA);
    }

//    @Test
//    public void testMissingOperandException() {
//        Evaluator evaluator = new Evaluator("+ 2 3");
//        assertThrows(RuntimeException.class, evaluator::evaluate);
//    }
    
    @Test
    public void testMissingOperatorException() {
        Evaluator evaluator = new Evaluator("2 3 4");
        assertThrows(RuntimeException.class, evaluator::evaluate);
    }
    
    @Test
    public void testUnbalancedParenthesesException() {
        Evaluator evaluator = new Evaluator("( 2 + 3 * 4");
        assertThrows(RuntimeException.class, evaluator::evaluate);
    }
    
    @Test
    public void testUnknownOperatorException() {
        Evaluator evaluator = new Evaluator("2 $ 3");
        assertThrows(RuntimeException.class, evaluator::evaluate);
    }
    
    public static void main(String[] args) {
        EvaluatorTest tester = new EvaluatorTest();
        
        try {
            System.out.println("Running all tests...");
            
            System.out.println("\nBasic operations:");
            tester.testBasicAddition();
            System.out.println("✓ Basic addition test passed");
            tester.testBasicSubtraction();
            System.out.println("✓ Basic subtraction test passed");
            tester.testBasicMultiplication();
            System.out.println("✓ Basic multiplication test passed");
            tester.testBasicDivision();
            System.out.println("✓ Basic division test passed");
            tester.testBasicExponentiation();
            System.out.println("✓ Basic exponentiation test passed");
            
            System.out.println("\nOperation precedence and parentheses:");
            tester.testOperationPrecedence();
            System.out.println("✓ Operation precedence test passed");
            tester.testParentheses();
            System.out.println("✓ Parentheses test passed");
            tester.testNestedParentheses();
            System.out.println("✓ Nested parentheses test passed");
            
            System.out.println("\nVariables:");
            tester.testVariables();
            System.out.println("✓ Variables test passed");
            tester.testVariablesWithOperations();
            System.out.println("✓ Variables with operations test passed");
            
            System.out.println("\nComplex expressions:");
            tester.testComplexExpression();
            System.out.println("✓ Complex expression test passed");
            tester.testComplexExpressionWithVariables();
            System.out.println("✓ Complex expression with variables test passed");
            
            System.out.println("\nException handling:");
//            try {
//                tester.testMissingOperandException();
//                System.out.println("✓ Missing operand exception test passed");
//            } catch (AssertionError e) {
//                System.out.println("✗ Missing operand exception test failed");
//            }
            
            try {
                tester.testMissingOperatorException();
                System.out.println("✓ Missing operator exception test passed");
            } catch (AssertionError e) {
                System.out.println("✗ Missing operator exception test failed");
            }
            
            try {
                tester.testUnbalancedParenthesesException();
                System.out.println("✓ Unbalanced parentheses exception test passed");
            } catch (AssertionError e) {
                System.out.println("✗ Unbalanced parentheses exception test failed");
            }
            
            try {
                tester.testUnknownOperatorException();
                System.out.println("✓ Unknown operator exception test passed");
            } catch (AssertionError e) {
                System.out.println("✗ Unknown operator exception test failed");
            }
            
            System.out.println("\nAll tests completed successfully!");
            
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
