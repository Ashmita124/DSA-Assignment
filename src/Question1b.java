
import java.util.PriorityQueue;
    public class Question1b {

        public int timeToBuildEngine(int[] engines, int splitCost) {

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int engine : engines) {
                minHeap.add(engine);
            }

            // System.out.println(minHeap);
            int totalTime = 0; // we need to allocate as 0 as base cuz at the start no engine is built


            while (minHeap.size() > 1) {
                int firstEngine = minHeap.poll();
                int secondEngine = minHeap.poll();

                int time = (minHeap.size() > 0) ? splitCost : 0;


                int stepTime = Math.max(firstEngine, secondEngine) + time;
                totalTime += time;
                minHeap.add(stepTime);
            }

            // totalTime += minHeap.poll();

            return totalTime;
        }

        public static void main(String[] args) {
            Question1b q = new Question1b();
            int[] engines = { 3, 4, 5, 2 };
            int splitCost = 2;
            int result = q.timeToBuildEngine(engines, splitCost);
            System.out.println(result);
        }
    }
