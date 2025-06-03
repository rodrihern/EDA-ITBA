package ExamenesViejos.P2_nica.Ej1;


public class FiboTree implements BSTreeInterface<Float> {

	private Node root;
	

	public FiboTree(int N) {
		if (N < 0) {
			throw new IllegalArgumentException();
		}

		root = buildFiboTree(N);

	}

	private Node buildFiboTree(int n) {
		if (n == 0) {
			return null;
		}
		if (n == 1) {
			return new Node(Float.NaN);
		}

		Node aux = new Node(Float.NaN);
		aux.left = buildFiboTree(n-1);
		aux.right = buildFiboTree(n-2);

		return aux;
	}



	
	
	public FiboTree(int N,  int lower) {
		
		this(N);   // esto no cambiarlo. es la generaci�n de la forma, o sea 1.1

		fillInOrder(root, new int[]{lower});

	}

	private void fillInOrder(Node node, int[] value) {
		if (node == null) {
			return;
		}
		fillInOrder(node.left, value);
		node.data = (float) value[0];
		value[0]++;
		fillInOrder(node.right, value);
	}

	
	
	


	public NodeTreeInterface<Float> getRoot() {
		return root;
	}

	public int getHeight() {
		return getHeight(root);
	}
	
	private int getHeight(Node aNode) {
		if (aNode== null)
			return -1;
		
		return 1 + Math.max( getHeight(aNode.left), getHeight(aNode.right) );
	}
    
	class Node implements NodeTreeInterface<Float> {

		private Float data;
		private Node left;
		private Node right;
		
		public Node(Float myData) {
			this.data= myData;
		}

		
		public Float getData() {
			return data;
		}
		public NodeTreeInterface<Float> getLeft() {
			return left;
		}
		
		public NodeTreeInterface<Float> getRight() {
			return right;
		}

	}

}