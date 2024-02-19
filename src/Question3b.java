import java.util.*;

// Class to represent an Edge in the graph
class Edge implements Comparable<Edge> {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    // Compare edges based on their weights
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

// Class to represent a disjoint set
class DisjointSet {
    int[] parent, rank;

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find the parent of a node with path compression
    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    // Union of two sets by rank
    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY)
            return;

        if (rank[rootX] < rank[rootY])
            parent[rootX] = rootY;
        else if (rank[rootX] > rank[rootY])
            parent[rootY] = rootX;
        else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}

public class Question3b {
    private List<Edge> result;
    private int V; // Number of vertices

    public Question3b(int V) {
        this.V = V;
        result = new ArrayList<>();
    }

    // Function to add an edge to the graph
    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        result.add(edge);
    }

    // Kruskal's algorithm to find Minimum Spanning Tree
    public List<Edge> kruskalMST() {
        List<Edge> minimumSpanningTree = new ArrayList<>();

        // Sort all the edges in non-decreasing order of their weight
        Collections.sort(result);

        DisjointSet disjointSet = new DisjointSet(V);

        for (Edge edge : result) {
            int sourceParent = disjointSet.find(edge.source);
            int destParent = disjointSet.find(edge.destination);

            // If including this edge doesn't cause a cycle, include it in the result
            if (sourceParent != destParent) {
                minimumSpanningTree.add(edge);
                disjointSet.union(sourceParent, destParent);
            }
        }

        return minimumSpanningTree;
    }

    public static void main(String[] args) {
        int V = 4;
        Question3b graph = new Question3b(V);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        List<Edge> minimumSpanningTree = graph.kruskalMST();

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : minimumSpanningTree) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
}
