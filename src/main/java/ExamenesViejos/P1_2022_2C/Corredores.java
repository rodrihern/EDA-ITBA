package ExamenesViejos.P1_2022_2C;


public class Corredores {

    public int[] tiemposEntre(int[] tiempos, Pedido[] pedidos) {
        int[] res = new int[pedidos.length];

        for (int i = 0; i < pedidos.length; i++) { // O(M)
            int j = getClosestPosition(pedidos[i].desde, tiempos); // O(logN)
            int k = getClosestPosition(pedidos[i].hasta, tiempos); // O(logN)
            if (j >= tiempos.length) { // hay 0
                res[i] = 0;
                continue;
            }
            // desplazo j hacia la izquierda por si hay repetidos
            while (j > 0 && tiempos[j-1] >= pedidos[i].desde) {
                j--;
            }
            // desplazo k hacia la derecha si hay repetidos
            // mientras este dentro del intervalo avanza
            while (k < tiempos.length && tiempos[k] <= pedidos[i].hasta) {
                k++;
            }
            // j apunta al primero dentro del intervalo
            // k apunta afuera del intervalo
            res[i] = k - j;
        }


        return res;
    }

    private int getClosestPosition(int key, int[] array) {
        int left = 0, right = array.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] == key) {
                return mid;
            }
            if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }






    public static void main(String[] args) {
        Corredores c = new Corredores();

        Pedido[] pedidos = new Pedido[] {
                new Pedido(200, 240),
                new Pedido(180, 210),
                new Pedido(220, 280),
                new Pedido(0, 200),
                new Pedido(290, 10000)
        };

        int[] tiempos = new int[] {
                192,
                200,
                210,
                221,
                229,
                232,
                240,
                240,
                243,
                247,
                280,
                285
        };

        int [] respuestas = c.tiemposEntre(tiempos, pedidos);
        for(int i=0; i< respuestas.length; i++) {
            System.out.println(respuestas[i]);
        }

    }
}

class Pedido {
    public int desde;
    public int hasta;

    public Pedido(int desde, int hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }
}