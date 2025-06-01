package ExamenesViejos.P2_2023_2C;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuadTreeTest {

    @Test
    public void testQuadTreeRootIsLeaf() {
        Integer[][] matriz = {
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1}
        };
        QuadTree qt = new QuadTree(matriz);
        // Acceso a la raíz para verificar si es hoja (data != null)
        assertNotNull(qt);
        // Usamos reflexión para acceder a root y su data
        try {
            java.lang.reflect.Field rootField = QuadTree.class.getDeclaredField("root");
            rootField.setAccessible(true);
            Object root = rootField.get(qt);
            java.lang.reflect.Field dataField = root.getClass().getDeclaredField("data");
            dataField.setAccessible(true);
            Integer data = (Integer) dataField.get(root);
            assertEquals(1, data);
        } catch (Exception e) {
            fail("No se pudo acceder a root o data por reflexión: " + e.getMessage());
        }
    }

    @Test
    public void testQuadTreeAllDifferent() {
        Integer[][] matriz = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        QuadTree qt = new QuadTree(matriz);
        // La raíz debe ser nodo interno (data == null)
        try {
            java.lang.reflect.Field rootField = QuadTree.class.getDeclaredField("root");
            rootField.setAccessible(true);
            Object root = rootField.get(qt);
            java.lang.reflect.Field dataField = root.getClass().getDeclaredField("data");
            dataField.setAccessible(true);
            Integer data = (Integer) dataField.get(root);
            assertNull(data);
        } catch (Exception e) {
            fail("No se pudo acceder a root o data por reflexión: " + e.getMessage());
        }
    }

    @Test
    public void testQuadTreePartialCompact() {
        Integer[][] matriz = {
                {1, 1, 3, 3},
                {1, 2, 3, 3},
                {3, 1, 4, 4},
                {2, 1, 4, 4}
        };
        QuadTree qt = new QuadTree(matriz);
        // Verificamos que la raíz sea nodo interno
        try {
            java.lang.reflect.Field rootField = QuadTree.class.getDeclaredField("root");
            rootField.setAccessible(true);
            Object root = rootField.get(qt);
            java.lang.reflect.Field dataField = root.getClass().getDeclaredField("data");
            dataField.setAccessible(true);
            Integer data = (Integer) dataField.get(root);
            assertNull(data);

            // Accedemos a los hijos (NO, NE, SO, SE)
            java.lang.reflect.Field ulField = root.getClass().getDeclaredField("upperLeft");
            ulField.setAccessible(true);
            Object ul = ulField.get(root);
            java.lang.reflect.Field urField = root.getClass().getDeclaredField("upperRight");
            urField.setAccessible(true);
            Object ur = urField.get(root);
            java.lang.reflect.Field llField = root.getClass().getDeclaredField("lowerLeft");
            llField.setAccessible(true);
            Object ll = llField.get(root);
            java.lang.reflect.Field lrField = root.getClass().getDeclaredField("lowerRight");
            lrField.setAccessible(true);
            Object lr = lrField.get(root);

            // NE debe ser hoja con valor 3
            java.lang.reflect.Field neDataField = ur.getClass().getDeclaredField("data");
            neDataField.setAccessible(true);
            Integer neData = (Integer) neDataField.get(ur);
            assertEquals(3, neData);

            // SE debe ser hoja con valor 4
            java.lang.reflect.Field seDataField = lr.getClass().getDeclaredField("data");
            seDataField.setAccessible(true);
            Integer seData = (Integer) seDataField.get(lr);
            assertEquals(4, seData);

        } catch (Exception e) {
            fail("No se pudo acceder a los nodos hijos por reflexión: " + e.getMessage());
        }
    }

    @Test
    public void testQuadTreeInvalidMatrixThrows() {
        Integer[][] matriz = {
                {1, 2, 3},
                {4, 5, 6}
        };
        assertThrows(RuntimeException.class, () -> new QuadTree(matriz));
    }

    @Test
    public void testToMatrixAllEqual() {
        Integer[][] matriz = {
            {7, 7, 7, 7},
            {7, 7, 7, 7},
            {7, 7, 7, 7},
            {7, 7, 7, 7}
        };
        QuadTree qt = new QuadTree(matriz);
        Integer[][] result = qt.toMatrix();
        assertArrayEquals(matriz, result);
    }

    @Test
    public void testToMatrixAllDifferent() {
        Integer[][] matriz = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        QuadTree qt = new QuadTree(matriz);
        Integer[][] result = qt.toMatrix();
        assertArrayEquals(matriz, result);
    }

    @Test
    public void testToMatrixPartialCompact() {
        Integer[][] matriz = {
            {1, 1, 3, 3},
            {1, 2, 3, 3},
            {3, 1, 4, 4},
            {2, 1, 4, 4}
        };
        QuadTree qt = new QuadTree(matriz);
        Integer[][] result = qt.toMatrix();
        assertArrayEquals(matriz, result);
    }

}
