public class Question2a {
    public int minMovesToEqualizeDresses(int[] sewingMachines) {
        int totalDresses = 0;
        int numMachines = sewingMachines.length;

        // Calculate total number of dresses
        for (int dresses : sewingMachines) {
            totalDresses += dresses;
        }

        int targetDresses = totalDresses / numMachines;

        // Check if equal distribution is possible
        if (totalDresses % numMachines != 0) {
            return -1; // Cannot equalize dresses
        }

        int moves = 0;
        for (int i = 1; i < numMachines; i++) {
            int dressesNeeded = (targetDresses * i) - sumArray(sewingMachines, i);
            if (dressesNeeded > 0) {
                sewingMachines[i] += dressesNeeded;
                sewingMachines[0] -= dressesNeeded;
                moves += dressesNeeded;
            }
        }

        return moves;
    }

    // Helper method to calculate sum of array elements till index i
    private int sumArray(int[] array, int endIndex) {
        int sum = 0;
        for (int i = 0; i < endIndex; i++) {
            sum += array[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Question2a question = new Question2a();
        int[] sewingMachines = {1, 0, 5};
        System.out.println(question.minMovesToEqualizeDresses(sewingMachines)); // Output: 2
    }
}
