import java.util.*;

public class Question5b {

    public static List<Integer> findImpactedDevices(int[][] edges, int targetDevice) {
        Map<Integer, List<Integer>> graph = buildGraph(edges);
        Set<Integer> visited = new HashSet<>();
        List<Integer> impactedDevices = new ArrayList<>();
        dfs(graph, targetDevice, visited, impactedDevices);
        return impactedDevices;
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int currentDevice,
                            Set<Integer> visited, List<Integer> impactedDevices) {
        visited.add(currentDevice);
        if (graph.containsKey(currentDevice)) {
            for (int neighbor : graph.get(currentDevice)) {
                if (!visited.contains(neighbor)) {
                    if (neighbor != currentDevice) { // Avoid self-loop
                        impactedDevices.add(neighbor);
                    }
                    dfs(graph, neighbor, visited, impactedDevices);
                }
            }
        }
    }

    private static Map<Integer, List<Integer>> buildGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            graph.computeIfAbsent(src, k -> new ArrayList<>()).add(dest);
            graph.computeIfAbsent(dest, k -> new ArrayList<>()).add(src);
        }
        return graph;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{1,3},{1,6},{2,4},{4,6},{4,5},{5,7}};
        int targetDevice = 4;
        List<Integer> impactedDevices = findImpactedDevices(edges, targetDevice);
        System.out.println("Impacted Device List: " + impactedDevices);
    }
}