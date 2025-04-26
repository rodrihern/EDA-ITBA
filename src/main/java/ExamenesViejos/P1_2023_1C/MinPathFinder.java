package ExamenesViejos.P1_2023_1C;

public class MinPathFinder {

    // con dinamic programing
    public int findMinPath(int[][] weightMatrix) {
        // asumiendo que los pesos son positivos
        int[][] memo = new int[weightMatrix.length][weightMatrix[0].length];
        return findMinPathFrom(weightMatrix, 0, 0, memo);
    }

    private int findMinPathFrom(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) { // asumimos que los pesos son positivos
            return memo[i][j];
        }
        if (i == matrix.length-1) {
            int res = 0;
            for (int k = j; k < matrix[i].length; k++) {
                res += matrix[i][k];
            }
            return res;
        }
        if (j == matrix[0].length-1) {
            int res = 0;
            for (int k = i; k < matrix.length; k++) {
                res += matrix[k][j];
            }
            return res;
        }

        int down = findMinPathFrom(matrix, i+1, j, memo);
        int right = findMinPathFrom(matrix, i, j+1, memo);

        memo[i][j] = matrix[i][j] + Math.min(down, right);
        return memo[i][j];

    }

    // codigo de nash iterativo sobre matriz auxiliar
    // en la matrix auxiliar vamos poniendo el peso de llegar a cada celda
    public int getMinPath(int[][] weightMatrix){
        int rows = weightMatrix.length;
        int columns = weightMatrix[0].length;

        //Matriz auxiliar que suma los caminos
        int[][] matrix = new int[rows][columns];

        matrix[0][0] = weightMatrix[0][0];

        //Llenamos la primer fila
        for(int i = 1; i < rows; i++){
            matrix[i][0] = matrix[i-1][0] + weightMatrix[i][0];
        }
        //Llenamos la primer columna
        for(int i = 1; i < columns; i++){
            matrix[0][i] = matrix[0][i-1] + weightMatrix[0][i];
        }

        for(int i = 1; i < rows; i++){
            for(int j = 1; j < columns; j++) {
                int fromUp = weightMatrix[i][j] + matrix[i-1][j];
                int fromLeft = weightMatrix[i][j] + matrix[i][j-1];
                matrix[i][j] = Math.min(fromUp, fromLeft);
            }
        }

        return matrix[rows-1][columns-1];
    }

    public static void main(String[] args) {
        int[][] v = new int [][]
                {{2, 8, 32, 30},
                        {12, 6, 18, 19},
                        {1, 2, 4, 8},
                        {1, 31, 1, 16}};
        MinPathFinder minPathFinder = new MinPathFinder();
        int ans = minPathFinder.findMinPath(v);
        System.out.println(ans); // 38

        v = new int [][]
                {{2, 8, 32, 30},
                        {12, 6, 18, 19},
                        {1, 2, 4, 8}};
        ans = minPathFinder.findMinPath(v);
        System.out.println(ans); // 29

        v = new int [][]
                {{1, 3, 1},
                        {1, 5, 1},
                        {4, 2, 1}};
        ans = minPathFinder.findMinPath(v);
        System.out.println(ans);
    }
}
