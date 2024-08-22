import org.w3c.dom.Node;

public class AVLTree {
    private Noo root;

    private int height(Noo noo) {
        if (noo == null)
            return 0;
        return noo.height;
    }

    private int getBalance(Noo noo) {
        if (noo == null)
            return 0;
        return height(noo.left) - height(noo.right);
    }

    private Noo rightRotate(Noo y) {
        Noo x = y.left;
        Noo T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Noo leftRotate(Noo x) {
        Noo y = x.right;
        Noo T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(int value) {
        root = insertNoo(root, value);
    }

    private Noo insertNoo(Noo noo, int value) {
        if (noo == null)
            return new Noo(value);

        if (value < noo.value)
            noo.left = insertNoo(noo.left, value);
        else if (value > noo.value)
            noo.right = insertNoo(noo.right, value);
        else
            return noo;

        noo.height = 1 + Math.max(height(noo.left), height(noo.right));

        int balance = getBalance(noo);

        if (balance > 1 && value < noo.left.value)
            return rightRotate(noo);

        if (balance < -1 && value > noo.right.value)
            return leftRotate(noo);

        if (balance > 1 && value > noo.left.value) {
            noo.left = leftRotate(noo.left);
            return rightRotate(noo);
        }

        if (balance < -1 && value < noo.right.value) {
            noo.right = rightRotate(noo.right);
            return leftRotate(noo);
        }

        return noo;
    }

    public void delete(int value) {
        root = deleteNoo(root, value);
    }

    private Noo deleteNoo(Noo root, int value) {
        if (root == null)
            return root;

        if (value < root.value)
            root.left = deleteNoo(root.left, value);
        else if (value > root.value)
            root.right = deleteNoo(root.right, value);
        else {
            if ((root.left == null) || (root.right == null)) {
                Noo temp = root.left != null ? root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Noo temp = minValueNoo(root.right);
                root.value = temp.value;
                root.right = deleteNoo(root.right, temp.value);
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private Noo minValueNoo(Noo noo) {
        Noo current = noo;

        while (current.left != null)
            current = current.left;

        return current;
    }

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Noo noo, String indent, boolean last) {
        if (noo != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("D----");
                indent += "   ";
            } else {
                System.out.print("E----");
                indent += "|  ";
            }
            System.out.println(noo.value + " (Balanceamento: " + getBalance(noo) + ")");
            printTree(noo.left, indent, false);
            printTree(noo.right, indent, true);
        }
    }

    public boolean isAVL() {
        return isAVL(root);
    }

    private boolean isAVL(Noo noo) {
        if (noo == null) {
            return true;
        }

        int balance = getBalance(noo);

        if (balance > 1 || balance < -1) {
            return false;
        }

        return isAVL(noo.left) && isAVL(noo.right);
    }
}
