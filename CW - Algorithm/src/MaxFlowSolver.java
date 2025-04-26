import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;

public class MaxFlowSolver {
    public static int edmondsKarp(Graph graph, int s, int t) {
        int maxFlow = 0;
        Edge[] parentMap = new Edge[graph.n];

        while (true) {
            int flow = bfs(graph, s, t, parentMap);
            if (flow == 0) break; // No more augmenting paths

            int bottleNeck = Integer.MAX_VALUE;
            for (Edge e = parentMap[t]; e != null; e = parentMap[e.from]) {
                bottleNeck = Math.min(bottleNeck, e.remainingCapacity());
            }

            for (Edge e = parentMap[t]; e != null; e = parentMap[e.from]) {
                e.augment(bottleNeck);
            }

            maxFlow += bottleNeck;
            System.out.println("Augmented path flow = " + bottleNeck);
        }

        // Print final flow values
        System.out.println("\nFinal flows in the network:");
        for (List<Edge> edges : graph.getAdjacencyList()) {
            for (Edge e : edges) {
                if (e.capacity > 0) { // Only show original (forward) edges
                    System.out.println(e);
                }
            }
        }

        return maxFlow;
    }

    private static int bfs(Graph graph, int s, int t, Edge[] parentMap) {
        Arrays.fill(parentMap, null);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (Edge edge : graph.getAdjacencyList()[current]) {
                if (edge.remainingCapacity() > 0 && parentMap[edge.to] == null && edge.to != s) {
                    parentMap[edge.to] = edge;
                    if (edge.to == t) {
                        return 1; // Path found
                    }
                    queue.add(edge.to);
                }
            }
        }
        return 0; // No path found
    }
}
