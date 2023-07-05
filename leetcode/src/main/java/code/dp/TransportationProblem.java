package code.dp;

/**
 * @author Floweryu
 * @date 2023/7/5 22:47
 * 入参：
 *    N种货物，每种货物有其体积和重量；
 *    M中车型，每种车型有其载重和装载体积，以及体积单价；
 *
 * 计算：
 *    求最经济的运输方式。
 *
 * 比如
 *   货物A1，30KG，10m³
 *   货物A2，50KG，20m³
 *
 *   车型A 装载体积40m³，载重30千克，体积单价5m³ 内8元，10m³内6元，30m³内 4元， 30m³内 5元。
 *   车型B 装载体积60m³，载重50千克，体积单价5m³ 内8元，10m³内6元，30m³内 4元， 30m³内 5元。
 *   车型C 装载体积90m³，载重80千克，体积单价5m³ 内8元，10m³内6元，30m³内 4元， 30m³内 5元。
 */
public class TransportationProblem {

    public static void main(String[] args) {
        // 货物数据
        int[] weights = {30, 50}; // 货物重量
        int[] volumes = {10, 20}; // 货物体积

        // 车型数据
        int[] maxWeights = {30, 50, 80}; // 车型最大载重
        int[] maxVolumes = {40, 60, 90}; // 车型最大装载体积
        int[][] volumePrices = {
                {8, 6, 4, 5}, // 车型A体积单价
                {8, 6, 4, 5}, // 车型B体积单价
                {8, 6, 4, 5}  // 车型C体积单价
        };
        int[][] weightPrices = {
                {10, 8, 6, 7}, // 车型A重量单价
                {10, 8, 6, 7}, // 车型B重量单价
                {10, 8, 6, 7}  // 车型C重量单价
        };

        int n = weights.length; // 货物种类数
        int m = maxWeights.length; // 车型种类数

        // 动态规划求解最小费用
        int[][][] dp = new int[n + 1][m + 1][2];
        int INF = Integer.MAX_VALUE / 2; // 初始费用设置为无穷大的一半

        // 初始化dp数组
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j][0] = INF; // 体积费用
                dp[i][j][1] = INF; // 重量费用
            }
        }

        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 计算货物i的体积费用和重量费用
                int volumeCost = 0;
                int weightCost = 0;

                // 找到适合的体积单价区间
                int volumeIndex = getVolumeIndex(volumes[i - 1], volumePrices[j - 1]);

                // 计算体积费用
                if (volumeIndex != -1) {
                    volumeCost = volumes[i - 1] * volumePrices[j - 1][volumeIndex];
                }

                // 找到适合的重量单价区间
                int weightIndex = getWeightIndex(weights[i - 1], weightPrices[j - 1]);

                // 计算重量费用
                if (weightIndex != -1) {
                    weightCost = weights[i - 1] * weightPrices[j - 1][weightIndex];
                }

                // 更新dp数组
                for (int k = 1; k <= m; k++) {
                    if (volumes[i - 1] <= maxVolumes[k - 1] && weights[i - 1] <= maxWeights[k - 1]) {
                        // 考虑按体积计费的情况
                        if (dp[i - 1][k - 1][0] != INF) {
                            dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][k - 1][0] + volumeCost);
                        }

                        // 考虑按重量计费的情况
                        if (dp[i - 1][k - 1][1] != INF) {
                            dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][k - 1][1] + weightCost);
                        }
                    }
                }
            }
        }

        // 找到最小费用
        int minCost = INF;
        for (int j = 1; j <= m; j++) {
            minCost = Math.min(minCost, Math.min(dp[n][j][0], dp[n][j][1]));
        }

        // 输出结果
        System.out.println("最小费用为：" + minCost);
    }

    // 辅助函数：根据货物体积和车型体积单价，找到合适的体积单价区间
    private static int getVolumeIndex(int volume, int[] volumePrices) {
        int n = volumePrices.length;

        for (int i = 0; i < n - 1; i++) {
            if (volumePrices[i] >= volume) {
                return i;
            }
        }

        return -1;
    }

    // 辅助函数：根据货物重量和车型重量单价，找到合适的重量单价区间
    private static int getWeightIndex(int weight, int[] weightPrices) {
        int n = weightPrices.length;

        for (int i = 0; i < n - 1; i++) {
            if (weightPrices[i] >= weight) {
                return i;
            }
        }

        return -1;
    }
}
