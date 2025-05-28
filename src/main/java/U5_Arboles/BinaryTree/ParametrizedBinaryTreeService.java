package U5_Arboles.BinaryTree;

import java.io.IOException;

public interface ParametrizedBinaryTreeService<T>{
    void preorder();

    void postorder();

    void printHierarchy();

    void toFile(String name) throws IOException;

    int getHeight();
}
