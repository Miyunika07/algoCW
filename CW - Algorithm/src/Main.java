import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("benchmarks/ladder_9.txt")); // Input file
        int n = sc.nextInt(); // Number of nodes
        Graph graph = new Graph(n);

        // Read all edges
        while (sc.hasNext()) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cap = sc.nextInt();
            graph.addEdge(from, to, cap);
        }
        sc.close();

        // Find maximum flow
        int maxFlow = MaxFlowSolver.edmondsKarp(graph, 0, n - 1); // 0 = source, n-1 = sink
        System.out.println("Maximum flow: " + maxFlow);
    }
}
