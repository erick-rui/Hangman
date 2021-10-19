package trees;

public class Node {
    Node left, right;
    int height;
    String data;

    public Node(String data) {
        this.data = data;
        left = null;
        right = null;
        height = 1;
    }
}
