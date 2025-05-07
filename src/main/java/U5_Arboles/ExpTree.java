package U5_Arboles;


import java.util.Scanner;

import static U5_Arboles.Utils.*;


public class ExpTree implements ExpressionService {

	private Node root;

	public ExpTree() {
	    System.out.print("Introduzca la expresi�n en notaci�n infija con todos los par�ntesis y blancos: ");

		// token analyzer
	    Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
	    String line= inputScanner.nextLine();
	    inputScanner.close();

	    buildTree(line);
	}
	
	private void buildTree(String line) {	
		  // space separator among tokens
		  Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
		  root= new Node(lineScanner);
		  lineScanner.close();
	}
	


	
	static final class Node {
		private String data;
		private Node left, right;
		
		private Scanner lineScanner;

		public Node(Scanner theLineScanner) {
			lineScanner= theLineScanner;
			
			Node auxi = buildExpression();
			data= auxi.data;
			left= auxi.left;
			right= auxi.right;
			
			if (lineScanner.hasNext() ) 
				throw new RuntimeException("Bad expression");
		}

		private Node() 	{
		}


		private Node buildExpression() {
			if (!lineScanner.hasNext()) {
				throw new RuntimeException("Expresión mal formada");
			}
			Node node = new Node();
			String token = lineScanner.next();
			if (Utils.isOpenParenthesis(token)) {
				// Construir subárbol recursivamente
				node.left = buildExpression();
				if (!lineScanner.hasNext()) {
					throw new RuntimeException("Falta operador en la expresión");
				}
				String operator = lineScanner.next();
				if (!Utils.isOperator(operator)) {
					throw new RuntimeException("Operador inválido: " + operator);
				}
				node.right = buildExpression();
				if (!lineScanner.hasNext() || !Utils.isCloseParenthesis(lineScanner.next())) {
					throw new RuntimeException("Falta paréntesis de cierre");
				}
				node.data = operator;
				return node;
			}
			if (Utils.isConstant(token)) {
				// Crear nodo hoja
				node.data = token;
				node.left = null;
				node.right = null;
				return node;
			} else {
				throw new RuntimeException("Token inválido: " + token);
			}
		}



	}  // end Node class

	
	
	// hasta que armen los testeos
	public static void main(String[] args) {
		ExpressionService myExp = new ExpTree();
	
	}

}  // end ExpTree class
