package code.dp;

import java.util.Arrays;

public class ShippingCostCalculator {

    public static void main(String[] args) {
        int[] itemVolumes = {10, 20};  // 货物的体积数组
        int[] itemWeights = {6, 10};   // 货物的重量数组
        int[] itemCounts = {10, 5};    // 货物的数量数组

        int[] truckCapacities = {40, 60, 90};  // 车型的装载体积数组
        int[] truckWeights = {30, 50, 80};     // 车型的载重数组
        int[] truckVolumePrices = {6, 4};      // 车型的体积单价数组

        int[][][] dp = calculateMinimumCost(itemVolumes, itemWeights, itemCounts, truckCapacities, truckWeights, truckVolumePrices);
        int[] result = findOptimalSolution(dp, itemVolumes.length, itemCounts, truckCapacities);

        printResult(result, truckCapacities, truckWeights);
    }

    public static int[][][] calculateMinimumCost(int[] itemVolumes, int[] itemWeights, int[] itemCounts, int[] truckCapacities, int[] truckWeights, int[] truckVolumePrices) {
        int itemCount = itemVolumes.length;
        int truckCount = truckCapacities.length;
        int[][][] dp = new int[itemCount + 1][Arrays.stream(itemCounts).max().getAsInt() + 1][truckCount];
        for (int i = 0; i <= itemCount; i++) {
            for (int j = 0; j <= Arrays.stream(itemCounts).max().getAsInt(); j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        dp[0][0][0] = 0;

        for (int i = 0; i < itemCount; i++) {
            for (int j = 0; j <= itemCounts[i]; j++) {
                for (int k = 0; k < truckCount; k++) {
                    for (int l = 0; l <= j; l++) {
                        int remainingVolume = truckCapacities[k] - l * itemVolumes[i];
                        int remainingWeight = truckWeights[k] - l * itemWeights[i];

                        if (remainingVolume >= 0 && remainingWeight >= 0 && dp[i][j][k] != Integer.MAX_VALUE) {
                            dp[i + 1][0][k] = Math.min(dp[i + 1][0][k], dp[i][j][k] + truckVolumePrices[l]);
                            if (k + 1 < truckCount) {
                                dp[i + 1][j - l][k + 1] = Math.min(dp[i + 1][j - l][k + 1], dp[i][j][k] + truckVolumePrices[l]);
                            }
                        }
                    }
                }
            }
        }

        return dp;
    }

    public static int[] findOptimalSolution(int[][][] dp, int itemCount, int[] itemCounts, int[] truckCapacities) {
        int[] result = new int[itemCount];

        int remainingItems = itemCounts[itemCount - 1];
        int remainingCapacity = 0;
        for (int i = 0; i < dp[itemCount][0].length; i++) {
            if (dp[itemCount][0][i] != Integer.MAX_VALUE) {
                remainingCapacity = truckCapacities[i];
                break;
            }
        }

        for (int i = itemCount; i > 0; i--) {
            for (int j = 0; j <= itemCounts[i - 1]; j++) {
                if (dp[i][j][remainingCapacity] != Integer.MAX_VALUE && dp[i][j][remainingCapacity] < dp[i][remainingItems][remainingCapacity]) {
                    result[i - 1] = j;
                    remainingItems -= j;
                    break;
                }
            }
            remainingCapacity--;
        }

        return result;
    }

    public static void printResult(int[] result, int[] truckCapacities, int[] truckWeights) {
        int truckCount = truckCapacities.length;
        int totalCost = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] > 0) {
                int truckIndex = result[i] < truckCount ? result[i] : truckCount - 1;
                int volumePrice = truckCapacities[truckIndex] <= 10 ? 6 : 4;
                int cost = volumePrice * result[i];
                totalCost += cost;
                System.out.println("货物A" + (i + 1) + " 使用车型" + (char) ('A' + truckIndex) + " 运输 " + result[i] + " 件，花费：" + cost + "元");
            }
        }
        System.out.println("最小花费为：" + totalCost + "元");
    }
}
