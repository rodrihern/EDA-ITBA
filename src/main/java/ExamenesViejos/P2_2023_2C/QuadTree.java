package ExamenesViejos.P2_2023_2C;

public class QuadTree {

    private QTNode root;

    public QuadTree( Integer[][] matrix ){
        int dim = checkDimIsSquareAndEven(matrix);
        if (dim == -1)
            throw new RuntimeException("Invalid Dim");

        root = new QTNode();
        root.build(matrix, 0, 0, dim);
    }


    public Integer[][] toMatrix( ) {
        return root.toMatrix();
    }

    private int checkDimIsSquareAndEven(Integer[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return -1;
        }
        int cols = matrix[0].length;

        return (rows == cols && rows % 2 == 0) ? rows : -1;
    }

    private class QTNode{
        private Integer data;
        private int dim;

        private QTNode upperLeft;
        private QTNode upperRight;
        private QTNode lowerLeft;
        private QTNode lowerRight;

        public Integer build(Integer[][] matrix, int i, int j, int dim) {
            this.dim = dim;
            // caso base
            if (dim <= 1) {
                data = matrix[i][j]; // Corregido: usar i y j
                return data;
            }
            // ahora tendria que dividir la matriz en 4 y llamar a build
            upperLeft = new QTNode();
            // Corregido: pasar las coordenadas correctas para los subcuadrantes
            Integer ul = upperLeft.build(matrix, i, j, dim/2);
            upperRight = new QTNode();
            Integer ur = upperRight.build(matrix, i, j + dim/2, dim/2);
            lowerLeft = new QTNode();
            Integer ll = lowerLeft.build(matrix, i + dim/2, j, dim/2);
            lowerRight = new QTNode();
            Integer lr = lowerRight.build(matrix, i + dim/2, j + dim/2, dim/2);
            // si son todos iguales y distintos de null, los quiero resumir en mi
            if (ul != null && ul.equals(ur) && ul.equals(ll) && ul.equals(lr)) {
                this.data = ul;
                // los hago todos null y dejo que el garbage collector haga de las suyas
                upperLeft = upperRight = lowerRight = lowerLeft = null;
            } else { // si no lo son quiero ser null y seguir yendo para arriba
                this.data = null;
            }

            return data;
        }

        public Integer[][] toMatrix() {
            // soy hoja, creo mi matriz y la retorno
            if (data != null) {
                Integer[][] matrix = new Integer[dim][dim];
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        matrix[i][j] = data;
                    }
                }
                return matrix;
            }

            // no soy hoja, tengo que retornar armada la que retornen mis hijos
            Integer[][] ul = upperLeft.toMatrix();
            Integer[][] ur = upperRight.toMatrix();
            Integer[][] ll = lowerLeft.toMatrix();
            Integer[][] lr = lowerRight.toMatrix();

            // ahora armo una matriz donde pongo esas matrices
            int len = ul.length;
            Integer[][] res = new Integer[len*2][len*2];
            // Copiar upperLeft
            for (int x = 0; x < len; x++) {
                for (int y = 0; y < len; y++) {
                    res[x][y] = ul[x][y];
                }
            }
            // Copiar upperRight
            for (int x = 0; x < len; x++) {
                for (int y = 0; y < len; y++) {
                    res[x][y + len] = ur[x][y];
                }
            }
            // Copiar lowerLeft
            for (int x = 0; x < len; x++) {
                for (int y = 0; y < len; y++) {
                    res[x + len][y] = ll[x][y];
                }
            }
            // Copiar lowerRight
            for (int x = 0; x < len; x++) {
                for (int y = 0; y < len; y++) {
                    res[x + len][y + len] = lr[x][y];
                }
            }
            return res;
        }






        public int getDim(){
            return dim;
        }


    }

    public static void main(String[] args) {
        // Matriz de prueba 4x4 (puedes cambiar los valores para probar otros casos)
        Integer[][] matriz = {
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1}
        };

        QuadTree qt = new QuadTree(matriz);

        // Acceso a la raíz para verificar si es hoja o nodo interno
        if (qt.root.data != null) {
            System.out.println("La raíz es hoja. Valor: " + qt.root.data);
        } else {
            System.out.println("La raíz es un nodo interno (no hoja).");
        }
    }

}
