import java.util.*;

public class Question5b {
    int[] discoveryTimes, lowValues;
    int time = 1;
    List<List<Integer>> answer = new ArrayList<>();
    Map<Integer, List<Integer>> edgeMapping = new HashMap<>();

    public List<Integer> findImpactedDevices(int n, List<List<Integer>> connections, int targetDevice) {
        discoveryTimes = new int[n];
        lowValues = new int[n];
        for (int i = 0; i < n; i++)
            edgeMapping.put(i, new ArrayList<Integer>());
        for (List<Integer> conn : connections) {
            edgeMapping.get(conn.get(0)).add(conn.get(1));
            edgeMapping.get(conn.get(1)).add(conn.get(0));
        }
        dfs(targetDevice, -1);

        // Check if the target device is a source node in any connection
        boolean isSourceNode = false;
        for (List<Integer> conn : connections) {
            if (conn.get(0) == targetDevice) {
                isSourceNode = true;
                break;
            }
        }

        if (!isSourceNode) {
            return new ArrayList<>();
        }

        Set<Integer> impactedDevicesSet = new HashSet<>();
        for (List<Integer> connection : answer) {
            int u = connection.get(0);
            int v = connection.get(1);

            if (u == targetDevice) {
                impactedDevicesSet.add(v);
            } else if (v == targetDevice) {
                impactedDevicesSet.add(u);
            }
        }

        Set<Integer> additionalAffectedDevices = new HashSet<>();
        for (int affectedDevice : impactedDevicesSet) {
            for (int neighbor : edgeMapping.get(affectedDevice)) {
                if (!impactedDevicesSet.contains(neighbor)) {
                    additionalAffectedDevices.add(neighbor);
                }
            }
        }

        impactedDevicesSet.addAll(additionalAffectedDevices);
        impactedDevicesSet.remove(targetDevice);

        return new ArrayList<>(impactedDevicesSet);
    }

    public void dfs(int current, int previous) {
        discoveryTimes[current] = lowValues[current] = time++;
        for (int next : edgeMapping.get(current)) {
            if (next == previous) continue;
            if (discoveryTimes[next] == 0) {
                dfs(next, current);
                lowValues[current] = Math.min(lowValues[current], lowValues[next]);
                if (lowValues[next] > discoveryTimes[current])
                    answer.add(Arrays.asList(current, next));
            } else {
                lowValues[current] = Math.min(lowValues[current], discoveryTimes[next]);
            }
        }
    }

    public static void main(String[] args) {
        Question5b q5b = new Question5b();

        int n = 8;
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0, 1));
        connections.add(Arrays.asList(0, 2));
        connections.add(Arrays.asList(1, 3));
        connections.add(Arrays.asList(1, 6));
        connections.add(Arrays.asList(2, 4));
        connections.add(Arrays.asList(4, 6));
        connections.add(Arrays.asList(4, 5));
        connections.add(Arrays.asList(5, 7));

        int targetDevice = 4;

        List<Integer> impactedDevices = q5b.findImpactedDevices(n, connections, targetDevice);

        System.out.println(impactedDevices);
    }
}
