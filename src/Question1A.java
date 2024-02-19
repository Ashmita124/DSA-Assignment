public class Question1A {


        public int minCost(int[][] costs) {
            if (costs == null || costs.length == 0) {
                return 0;
            }

            int n = costs.length;
            int k = costs[0].length;

            // Create a dp array to store the minimum cost at each venue with each theme
            int[][] dp = new int[n][k];

            // Initialize the dp array with the costs of the first venue
            System.arraycopy(costs[0], 0, dp[0], 0, k);

            // Iterate through each venue starting from the second one
            for (int i = 1; i < n; i++) {
                // Iterate through each theme for the current venue
                for (int j = 0; j < k; j++) {
                    // Initialize the minimum cost for the current venue and theme
                    dp[i][j] = Integer.MAX_VALUE;

                    // Iterate through each theme for the previous venue
                    for (int prevTheme = 0; prevTheme < k; prevTheme++) {
                        // Skip if the current theme is the same as the previous one
                        if (prevTheme != j) {
                            // Calculate the cost of decorating the current venue with the current theme
                            int cost = costs[i][j] + dp[i - 1][prevTheme];
                            // Update the minimum cost for the current venue and theme
                            dp[i][j] = Math.min(dp[i][j], cost);
                        }
                    }
                }
            }

            // Find the minimum cost from the last venue
            int minCost = Integer.MAX_VALUE;
            for (int themeCost : dp[n - 1]) {
                minCost = Math.min(minCost, themeCost);
            }

            return minCost;
        }

        public static void main(String[] args) {
            Question1A qn1 = new Question1A();
            int[][] costs = {
                    { 1, 3, 2 },
                    { 4, 6, 8 },
                    { 3, 1, 5 }
            };
            System.out.println(qn1.minCost(costs)); // Output: 7
        }
    }


