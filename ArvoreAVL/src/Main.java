import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            int value = rand.nextInt(1001) - 500;
            tree.insert(value);
        }

        if (tree.isAVL()) {
            System.out.println("\nA árvore resultante é uma AVL.");
        } else {
            System.out.println("\nA árvore resultante NÃO é uma AVL.");
        }

        tree.printTree();

        for (int i = 0; i < 20; i++) {
            int value = rand.nextInt(1001) - 500;
            tree.delete(value);
        }

        if (tree.isAVL()) {
            System.out.println("\nA árvore resultante é uma AVL após as remoções.");
        } else {
            System.out.println("\nA árvore resultante NÃO é uma AVL após as remoções.");
        }

        tree.printTree();
    }
}

