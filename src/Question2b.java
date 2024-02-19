
import java.util.*;

public class Question2b{

    public static Set<Integer> eventualSecret(int n, int[][] intervals) {
        List<Set<Integer>> secretHolders = new ArrayList<>();
        secretHolders.add(new HashSet<>());
        secretHolders.get(0).add(0);

        for (int[] interval : intervals) {
            Set<Integer> newHolders = new HashSet<>();
            for (int holder : secretHolders.get(secretHolders.size() - 1)) {
                for (int i = interval[0]; i <= interval[1]; i++) {
                    if (i != holder) {
                        newHolders.add(i);
                    }
                }
            }
            secretHolders.add(newHolders);
        }

        Set<Integer> eventualSecretSet = new HashSet<>();
        for (Set<Integer> holders : secretHolders) {
            eventualSecretSet.addAll(holders);
        }

        return eventualSecretSet;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] intervals = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        Set<Integer> result = eventualSecret(n, intervals);
        System.out.println(result); // Output: [0, 1, 2, 3, 4]
    }
}

