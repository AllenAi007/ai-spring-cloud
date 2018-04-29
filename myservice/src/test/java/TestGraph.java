import org.junit.Assert;
import org.junit.Test;

public class TestGraph {


    @Test
    public void grapAdd() {
        Graph graph = new Graph(4);
        graph.add(0, new int[]{1, 2, 3});
        graph.add(1);
        graph.add(2);
        graph.add(3);

        Assert.assertArrayEquals(new int[]{0, 1, 2, 3}, graph.vertices);
        Assert.assertEquals(4, graph.size);

        for (int i = 0; i < graph.adjacency.length; i++) {
            for (int j = 0; j < graph.adjacency[i].length; j++) {
                if (i == 0) {
                    switch (j) {
                        case 1:
                        case 2:
                        case 3:
                            Assert.assertEquals(1, graph.adjacency[i][j]);
                            break;
                        default:
                            Assert.assertEquals(0, graph.adjacency[i][j]);
                    }
                } else {
                    Assert.assertEquals(0, graph.adjacency[i][j]);
                }
            }
        }
    }

    class Graph {
        int[] vertices;
        int[][] adjacency; // 0 means has no adjacency, 1 means has adjacency
        int size = 0;

        Graph(int capacity) {
            this.vertices = new int[capacity];
            adjacency = new int[capacity][capacity];
            for (int i = 0; i < adjacency.length; i++) {
                for (int j = 0; j < adjacency[i].length; j++) {
                    adjacency[i][j] = 0;
                }
            }
        }

        /**
         * Add vertex without indicate its adjacency
         *
         * @param vertex
         */
        void add(int vertex) {
            vertices[size] = vertex;
            size++;
        }


        /**
         * Add vertex with its adjacency
         *
         * @param vertex
         */
        void add(int vertex, int[] adjacency) {
            vertices[size] = vertex;
            for (int i = 0; i < adjacency.length; i++) {
                this.adjacency[size][adjacency[i]] = 1;
            }
            size++;
        }

        /**
         * Deep search first
         */
        void dsf() {

        }

        /**
         * Breadth search first
         */
        void bsf() {

        }

    }
}
