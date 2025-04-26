import java.util.ArrayList;
import java.util.List;

public class Graph {
    int n; // Number of nodes
    List<Edge>[] adj; // Adjacency list

    @SuppressWarnings("unchecked")
    public Graph(int n) {
        this.n = n;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    // Add forward and residual edges
    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(from, to, capacity);
        Edge backward = new Edge(to, from, 0); // Residual edge
        forward.residual = backward;
        backward.residual = forward;

        adj[from].add(forward);
        adj[to].add(backward);
    }

    public List<Edge>[] getAdjacencyList() {
        return adj;
    }
}
