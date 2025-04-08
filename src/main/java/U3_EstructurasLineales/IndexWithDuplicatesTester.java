package U3_EstructurasLineales;

import java.util.Arrays;

public class IndexWithDuplicatesTester {
    public static void main(String[] args) {
        // Pruebas básicas
        IndexGeneric<Integer> myIndex = new IndexGeneric<>();
        System.out.println("-- Pruebas básicas --");
        System.out.println("Ocurrencias de 10: " + myIndex.occurrences(10)); // se obtiene 0
        myIndex.delete(10); // ignora
        System.out.println("Buscar 10: " + myIndex.search(10)); // se obtiene false
        myIndex.insert(80); // almacena [80]
        myIndex.insert(20); // almacena [20, 80]
        myIndex.insert(80); // almacena [20, 80, 80]

        System.out.println("Ocurrencias de 80: " + myIndex.occurrences(80)); // se obtiene 2

        try {
            myIndex.initialize(null);
        } catch (Exception e) {
            System.out.println("Excepción al inicializar con null: " + e.getMessage());
        }

        Integer[] initialData = {100, 50, 30, 50, 80, 100, 100, 30};
        myIndex.initialize(initialData);
        // el índice posee [30, 30, 50, 50, 80, 100, 100, 100]
        System.out.println("Buscar 20: " + myIndex.search(20)); // se obtiene false
        System.out.println("Buscar 80: " + myIndex.search(80)); // se obtiene true
        System.out.println("Ocurrencias de 50: " + myIndex.occurrences(50)); // se obtiene 2
        myIndex.delete(50);
        System.out.println("Ocurrencias de 50 después de borrar: " + myIndex.occurrences(50)); // se obtiene 1

        // Pruebas específicas de range
        System.out.println("\n-- Pruebas de la función range --");
        
        // Crear un nuevo índice con datos ordenados para pruebas de range
        IndexGeneric<Integer> rangeIndex = new IndexGeneric<>();
        Integer[] data = {10, 20, 20, 30, 40, 40, 40, 50, 60, 70, 70, 80};
        rangeIndex.initialize(data);
        
        // Caso 1: Rango completo, ambos límites incluidos
        System.out.println("Range(10, 80, true, true): " + 
            Arrays.toString(rangeIndex.range(10, 80, true, true)));
        
        // Caso 2: Rango completo, límites excluidos
        System.out.println("Range(10, 80, false, false): " + 
            Arrays.toString(rangeIndex.range(10, 80, false, false)));
        
        // Caso 3: Elementos duplicados en los límites
        System.out.println("Range(20, 70, true, true): " + 
            Arrays.toString(rangeIndex.range(20, 70, true, true)));
        
        // Caso 4: Límite inferior mayor a todos los elementos
        System.out.println("Range(90, 100, true, true): " + 
            Arrays.toString(rangeIndex.range(90, 100, true, true)));
        
        // Caso 5: Límite superior menor a todos los elementos
        System.out.println("Range(1, 5, true, true): " + 
            Arrays.toString(rangeIndex.range(1, 5, true, true)));
        
        // Caso 6: Límites iguales
        System.out.println("Range(40, 40, true, true): " + 
            Arrays.toString(rangeIndex.range(40, 40, true, true)));
        
        // Caso 7: Límites iguales pero excluidos
        System.out.println("Range(40, 40, false, false): " + 
            Arrays.toString(rangeIndex.range(40, 40, false, false)));
        
        // Caso 8: Incluir solo límite inferior
        System.out.println("Range(30, 50, true, false): " + 
            Arrays.toString(rangeIndex.range(30, 50, true, false)));
        
        // Caso 9: Incluir solo límite superior
        System.out.println("Range(30, 50, false, true): " + 
            Arrays.toString(rangeIndex.range(30, 50, false, true)));
        
        // Caso 10: Elementos consecutivos
        System.out.println("Range(10, 20, true, true): " + 
            Arrays.toString(rangeIndex.range(10, 20, true, true)));
        
        // Caso 11: Valores que no existen en el array
        System.out.println("Range(15, 45, true, true): " + 
            Arrays.toString(rangeIndex.range(15, 45, true, true)));
        
        // Caso 12: Índice vacío
        IndexGeneric<Integer> emptyIndex = new IndexGeneric<>();
        try {
            System.out.println("Range en índice vacío: " + 
                Arrays.toString(emptyIndex.range(10, 20, true, true)));
        } catch (Exception e) {
            System.out.println("Error en range con índice vacío: " + e.getMessage());
        }
    }
}
