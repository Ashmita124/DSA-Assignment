import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class Question4b {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> closest = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Double.compare(Math.abs(target - b), Math.abs(target - a)));

        inorder(root, pq, k, target);

        while (!pq.isEmpty()) {
            closest.add(pq.poll());
        }

        return closest;
    }

    private void inorder(TreeNode node, PriorityQueue<Integer> pq, int k, double target) {
        if (node == null) return;

        inorder(node.left, pq, k, target);
        pq.offer(node.val);
        if (pq.size() > k) {
            pq.poll();
        }
        inorder(node.right, pq, k, target);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        Question4b solution = new Question4b();
        List<Integer> closestValues = solution.closestKValues(root, 3.8, 2);
        System.out.println("Closest values to 3.8: " + closestValues);
    }
}
