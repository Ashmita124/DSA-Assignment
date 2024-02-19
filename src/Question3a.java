import java.util.ArrayList;
import java.util.List;

public class Question3a {
    private List<Double> scores;

    public Question3a() {
        scores = new ArrayList<>();
    }

    public void addScore(double score) {
        if (scores.isEmpty()) {
            scores.add(score);
            return;
        }

        int index = findInsertionIndex(score);
        scores.add(index, score);
    }

    private int findInsertionIndex(double score) {
        int low = 0;
        int high = scores.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (scores.get(mid) == score) {
                return mid;
            } else if (scores.get(mid) < score) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public double getMedianScore() {
        int size = scores.size();
        if (size == 0) {
            throw new IllegalStateException("No scores added yet.");
        }
        if (size % 2 == 0) {
            int mid = size / 2;
            return (scores.get(mid - 1) + scores.get(mid)) / 2.0;
        } else {
            return scores.get(size / 2);
        }
    }

    public static void main(String[] args) {
        Question3a scoreTracker = new Question3a();
        scoreTracker.addScore(85.5);
        scoreTracker.addScore(92.3);
        scoreTracker.addScore(77.8);
        scoreTracker.addScore(90.1);
        double median1 = scoreTracker.getMedianScore();
        System.out.println("Median 1: " + median1); // Output: 87.8
        scoreTracker.addScore(81.2);
        scoreTracker.addScore(88.7);
        double median2 = scoreTracker.getMedianScore();
        System.out.println("Median 2: " + median2); // Output: 87.1
    }
}
