import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Arrays;

public class GraphAlgorithmsTest {

    private Graph<Integer> graph;
    private Vertex<Integer> v1;
    private Vertex<Integer> v2;
    private Vertex<Integer> v3;
    private Vertex<Integer> v4;
    private Vertex<Integer> v5;

    @BeforeEach
    public void setUp() {
        v1 = new Vertex<>(1);
        v2 = new Vertex<>(2);
        v3 = new Vertex<>(3);
        v4 = new Vertex<>(4);
        v5 = new Vertex<>(5);

        Set<Vertex<Integer>> vertices = new HashSet<>(Arrays.asList(v1, v2, v3, v4, v5));
        Set<Edge<Integer>> edges = new HashSet<>(Arrays.asList(
                new Edge<>(v1, v2, 1),
                new Edge<>(v1, v3, 1),
                new Edge<>(v2, v4, 1),
                new Edge<>(v3, v4, 1),
                new Edge<>(v4, v5, 1)
        ));

        graph = new Graph<>(vertices, edges);
    }

    @Test
    public void testBFS() {
        List<Vertex<Integer>> bfsResult = GraphAlgorithms.bfs(v1, graph);
        List<Vertex<Integer>> expectedBFSOrder = Arrays.asList(v1, v2, v3, v4, v5);
        assertEquals(expectedBFSOrder, bfsResult);
    }

    @Test
    public void testDFS() {
        List<Vertex<Integer>> dfsResult = GraphAlgorithms.dfs(v1, graph);
        List<Vertex<Integer>> expectedDFSOrder = Arrays.asList(v1, v2, v4, v5, v3);
        assertEquals(expectedDFSOrder, dfsResult);
    }

    @Test
    public void testBFSWithSelfLoop() {
        graph.getAdjList().get(v1).add(new VertexDistance<>(v1, 1)); // Adding self-loop
        List<Vertex<Integer>> bfsResult = GraphAlgorithms.bfs(v1, graph);
        List<Vertex<Integer>> expectedBFSOrder = Arrays.asList(v1, v2, v3, v4, v5);
        assertEquals(expectedBFSOrder, bfsResult); // The self-loop should not affect the BFS order
    }

    @Test
    public void testDFSWithSelfLoop() {
        graph.getAdjList().get(v1).add(new VertexDistance<>(v1, 1)); // Adding self-loop
        List<Vertex<Integer>> dfsResult = GraphAlgorithms.dfs(v1, graph);
        List<Vertex<Integer>> expectedDFSOrder = Arrays.asList(v1, v2, v4, v5, v3);
        assertEquals(expectedDFSOrder, dfsResult); // The self-loop should not affect the DFS order
    }

    @Test
    public void testBFSWithParallelEdges() {
        graph.getAdjList().get(v1).add(new VertexDistance<>(v2, 1)); // Adding parallel edge
        List<Vertex<Integer>> bfsResult = GraphAlgorithms.bfs(v1, graph);
        List<Vertex<Integer>> expectedBFSOrder = Arrays.asList(v1, v2, v3, v4, v5);
        assertEquals(expectedBFSOrder, bfsResult); // The parallel edge should not affect the BFS order
    }

    @Test
    public void testDFSWithParallelEdges() {
        graph.getAdjList().get(v1).add(new VertexDistance<>(v2, 1)); // Adding parallel edge
        List<Vertex<Integer>> dfsResult = GraphAlgorithms.dfs(v1, graph);
        List<Vertex<Integer>> expectedDFSOrder = Arrays.asList(v1, v2, v4, v5, v3);
        assertEquals(expectedDFSOrder, dfsResult); // The parallel edge should not affect the DFS order
    }
}
