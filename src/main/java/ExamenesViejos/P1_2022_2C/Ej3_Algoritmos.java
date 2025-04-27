package ExamenesViejos.P1_2022_2C;

/*
*Determinar:
● ¿Qué hace el algoritmo unalgoritmo en palabras y en una oración)? - por
ejemplo: encuentra el elemento más pequeño en un arreglo.
● ¿Qué orden (O grande) de complejidad temporal tiene? ¿Qué orden (O
grande) de complejidad espacial tiene?
● Implementar el algoritmo
public static int mialgoritmo(int[] array)
que haga lo mismo que unalgoritmo pero que tenga un orden de
complejidad temporal mejor. Calcular dicha complejidad temporal
¿Cómo es su complejidad espacial?
*
* */

public class Ej3_Algoritmos {
    public static int unalgoritmo(int[] array) {
        // cuenta el maximo numero de repeticiones de un elemento de array
        int i = 0;
        int j = 0;
        int m = 0;
        int c = 0;
        while (i < array.length) {
            if (array[i] == array[j]) {
                c++;
            }
            j++;
            if (j >= array.length) {
                if (c > m) {
                    m = c;
                }
                c = 0;
                i++;
                j = i;
            }
        }
        // por cada i recorr el arreglo hasta el final -> O(N^2)
        return m;
    }

    public static int mialgoritmo(int[] array) {
        // recorro para buscar el maximo
        // asumiendo que son todos no negativos por simplicidad
        int max = 0;
        for (int num : array) {
            if (num < 0) {
                throw new IllegalArgumentException("array[i] must be non negative");
            }
            max = Math.max(max, num);
        }

        int[] apps = new int[max+1]; // array auxiliar para contar apariciones

        for (int num : array) {
            apps[num]++;
        }


        max = 0;
        for (int count : apps) {
            max = Math.max(max, count);
        }

        return max;
    }

    // Recorro 3 veces luego T(N) = 3N -> es O(N)
    // como me reservo un espacio de el maximo elemento de array, la complejidad espacial es O(max)
    // donde max es el maximo elemento del array


}




