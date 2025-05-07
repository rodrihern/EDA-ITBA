package rohernandez;

public class Laberinto {


     // Este es el que entregue

    // Idea: recursivo para que cada celda llame a las que puede llegar y le diga si de ahi se puede llegar al final
//    public static boolean existeCamino(int[][] laberinto, int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
//        // caso base
//        if (filaInicio == filaFin && columnaInicio == columnaFin) {
//            return true;
//        }
//        // marco como ya visitada
//        laberinto[filaInicio][columnaInicio] = -1;
//
//
//        // antes de moverme a una celda tengo que chequear limites y que la pueda visitar
//        // la puedo visitar si vale 0
//
//        // arriba
//        if (filaInicio-1 >= 0 && laberinto[filaInicio-1][columnaInicio] == 0) {
//            if (existeCamino(laberinto, filaInicio-1, columnaInicio, filaFin, columnaFin)) {
//                return true;
//            }
//        }
//        // abajo
//        if (filaInicio+1 < laberinto.length && laberinto[filaInicio+1][columnaInicio] == 0) {
//            if (existeCamino(laberinto, filaInicio+1, columnaInicio, filaFin, columnaFin)) {
//                return true;
//            }
//        }
//        // izquierda
//        if (columnaInicio-1 >= 0 && laberinto[filaInicio][columnaInicio-1] == 0) {
//            if (existeCamino(laberinto, filaInicio, columnaInicio-1, filaFin, columnaFin)) {
//                return true;
//            }
//        }
//        // derecha
//        if (columnaInicio+1 < laberinto[0].length && laberinto[filaInicio][columnaInicio+1] == 0) {
//            if (existeCamino(laberinto, filaInicio, columnaInicio+1, filaFin, columnaFin)) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    public static boolean existsPath(int[][] maze, int fromRow, int fromCol, int toRow, int toCol) {
        // base case
        if (fromRow < 0 || fromRow >= maze.length || fromCol < 0 || fromCol >= maze[0].length) {
            return false;
        }
        if (maze[fromRow][fromCol] != 0) {
            return false;
        }
        if (fromRow == toRow && fromCol == toCol) {
            return true;
        }

        // mark as visited
        maze[fromRow][fromCol] = -1;


        if (existsPath(maze, fromRow-1, fromCol, toRow, toCol)) { // up
            return true;
        }
        if (existsPath(maze, fromRow+1, fromCol, toRow, toCol)) { // down
            return true;
        }
        if (existsPath(maze, fromRow, fromCol-1, toRow, toCol)) { // left
            return true;
        }
        if (existsPath(maze, fromRow, fromCol+1, toRow, toCol)) { //right
            return true;
        }
;
        return false;
    }

    public static void main(String[] args) {
        int[][] laberinto = {
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1}
        };

        boolean existe = existsPath(laberinto, 0, 0, 3, 0);
        if (existe) {
            System.out.println("Existe un camino en el laberinto.");
        } else {
            System.out.println("No existe un camino en el laberinto.");
        }
        System.out.println("Caminos recorridos:");
        imprimirLaberinto(laberinto);

        int[][] laberintoSinSalida = {
                {0, 0, 1, 0},
                {1, 0, 1, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 1}
        };
        boolean existeSinSalida = existsPath(laberintoSinSalida, 0, 0, 0, 3);
        if (existeSinSalida) {
            System.out.println("Existe un camino en el laberinto sin salida (¡error!).");
        } else {
            System.out.println("No existe un camino en el laberinto sin salida.");
        }
        System.out.println("Caminos recorridos:");
        imprimirLaberinto(laberintoSinSalida);



    }

    public static void imprimirLaberinto(int[][] laberinto) {
        for (int[] fila : laberinto) {
            for (int celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
}
