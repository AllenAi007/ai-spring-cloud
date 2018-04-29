import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class TestTree {


    /**
     *              1
     *             / \
     *            2   4
     *           /   / \
     *          3   5   6
     *             / \
     *            7   8
     */
    BiTree biTree;

    @Before
    public void before() {
        biTree = new BiTree(1);
        biTree.root.left = new Node(2);
        biTree.root.right = new Node(4);
        biTree.root.right.left = new Node(5);
        biTree.root.right.right = new Node(6);
        biTree.root.left.left = new Node(3);
        biTree.root.right.left.left = new Node(7);
        biTree.root.right.left.right = new Node(8);

    }

    @Test
    public void travelDFS() {
        biTree.dfs();
    }

    @Test
    public void travelBFS(){
        biTree.bfs();
    }

    class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
    }

    class BiTree {
        Node root;

        BiTree(int key) {
            this.root = new Node(key);
        }

        void dfs() {
            rdfs(root);
        }

        void rdfs(Node node) {
            if (node == null) {
                return;
            }
            //in order
            System.out.print(node.key + " ");
            rdfs(node.left);
            rdfs(node.right);
        }

        void bfs() {
            if(root == null) return;
            Queue<Node> queue = new LinkedList<>();
            Node current = null;
            queue.offer(root);
            while(!queue.isEmpty()) {
                current = queue.poll();
                System.out.print(current.key + " ");
                if(current.left != null) {
                    queue.offer(current.left);
                }
                if(current.right != null) {
                    queue.offer(current.right);
                }
            }
        }

    }

}
