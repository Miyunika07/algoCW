public class Edge {
    int from, to;
    int capacity;
    int flow;
    Edge residual;

    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    // Remaining capacity on edge
    public int remainingCapacity() {
        return capacity - flow;
    }

    // Augment the flow
    public void augment(int bottleNeck) {
        flow += bottleNeck;
        residual.flow -= bottleNeck;
    }

    @Override
    public String toString() {
        return String.format("%d -> %d | flow = %d / %d", from, to, flow, capacity);
    }
}
