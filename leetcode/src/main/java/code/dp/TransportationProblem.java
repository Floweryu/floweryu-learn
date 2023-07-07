package code.dp;

import java.util.ArrayList;
import java.util.List;

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
        Goods g1 = new Goods(30, 10, 10);
        Goods g2 = new Goods(50, 20, 5);
        Goods[] goods = new Goods[]{g1, g2};
        Vehicle v1 = new Vehicle(40, 30, 8);
        Vehicle v2 = new Vehicle(60, 50, 6);
        Vehicle v3 = new Vehicle(90, 80, 4);
        Vehicle[] vehicles = new Vehicle[]{v1, v2, v3};
        int minimumCost = findMinimumCost(goods, vehicles);
        System.out.println(minimumCost);
    }

    public static int findMinimumCost(Goods[] goods, Vehicle[] vehicles) {
        int N = goods.length;
        int M = vehicles.length;

        int[][] dp = new int[N + 1][M + 1];
        int[][] prev = new int[N + 1][M + 1];

        // 初始化第一行和第一列的值为0
        for (int j = 0; j <= M; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= N; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int minCost = dp[i - 1][j]; // 不选择当前货物i
                int prevIndex = j; // 默认前一个状态是选择当前车型
                Goods currentGoods = goods[i - 1];
                Vehicle currentVehicle = vehicles[j - 1];

                if (currentGoods.getCount() > 0 && currentVehicle.getMaxLoad() >= currentGoods.getWeight()
                        && currentVehicle.getMaxVolume() >= currentGoods.getVolume()) {
                    int cost = currentGoods.getVolume() * currentVehicle.getVolumePrice();
                    if (dp[i][j - 1] + cost < minCost) {
                        minCost = dp[i][j - 1] + cost;
                        prevIndex = j - 1; // 选择当前货物i和车型j
                        currentGoods.count -= 1; // 装载当前货物到车型j
                    }
                }

                dp[i][j] = minCost;
                prev[i][j] = prevIndex;
                currentGoods.count += 1; // 恢复货物个数
            }
        }

        // 构建最优运输车型组合
        List<Integer> selectedVehicles = new ArrayList<>();
        int i = N;
        int j = M;
        while (i > 0 && j > 0) {
            if (prev[i][j] == j - 1) {
                selectedVehicles.add(j);
                Goods currentGoods = goods[i - 1];
                currentGoods.count -= 1;
            }
            j = prev[i][j];
            i--;
        }

    // 输出最优运输车型组合
        System.out.println("最优运输车型组合：");
        for (int k = selectedVehicles.size() - 1; k >= 0; k--) {
            System.out.println("货物" + (N - k) + "：" + "车型" + selectedVehicles.get(k));
        }

        return dp[N][M];
    }
}



class Goods {
    int volume;
    int weight;
    int count;
    // 其他属性和构造函数
    Goods(int volume, int weight, int count) {
        this.volume = volume;
        this.weight = weight;
        this.count = count;
    }

    // 获取货物的体积
    public int getVolume() {
        return volume;
    }

    // 获取货物的重量
    public int getWeight() {
        return weight;
    }

    // 获取货物的个数
    public int getCount() {
        return count;
    }
}

class Vehicle {
    int maxLoad;
    int maxVolume;
    int volumePrice;
    // 其他属性和构造函数


    public Vehicle(int maxLoad, int maxVolume, int volumePrice) {
        this.maxLoad = maxLoad;
        this.maxVolume = maxVolume;
        this.volumePrice = volumePrice;
    }

    // 获取车型的载重
    public int getMaxLoad() {
        return maxLoad;
    }

    // 获取车型的装载体积
    public int getMaxVolume() {
        return maxVolume;
    }

    // 获取车型的体积单价
    public int getVolumePrice() {
        return volumePrice;
    }
}
