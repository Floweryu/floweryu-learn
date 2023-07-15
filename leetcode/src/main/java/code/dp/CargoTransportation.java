package code.dp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Cargo {
    String name;
    int weight;
    int volume;
    int quantity;

    public Cargo(String name, int weight, int volume, int quantity) {
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.quantity = quantity;
    }
}

class Vehicle {
    String name;
    int maxVolume;
    int maxWeight;
    int price;

    public Vehicle(String name, int maxVolume, int maxWeight, int price) {
        this.name = name;
        this.maxVolume = maxVolume;
        this.maxWeight = maxWeight;
        this.price = price;
    }
}

public class CargoTransportation {
    public static void main(String[] args) {
        // 定义货物和车型的参数
        int[] weights = {6, 10}; // 货物重量
        int[] volumes = {10, 20}; // 货物体积
        int[] quantities = {10, 5}; // 货物数量
        int[] vehicleVolumes = {40, 60, 90}; // 车型装载体积
        int[] vehicleCapacities = {30, 50, 80}; // 车型载重能力
        int[] vehiclePrices = {50, 80, 100}; // 车型价格

        // 初始化车型数量和总花费
        int[] vehicleCounts = new int[vehicleVolumes.length];
        int totalCost = 0;

        // 对货物按照体积从大到小进行排序
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < quantities.length; i++) {
            indices.add(i);
        }
        indices.sort(Comparator.comparingInt(i -> -volumes[i]));

        // 遍历货物
        for (int i : indices) {
            int remainingQuantity = quantities[i];
            // 尝试将货物装入最便宜的可用车型
            for (int j = 0; j < vehicleVolumes.length; j++) {
                int availableVolume = vehicleVolumes[j];
                int availableCapacity = vehicleCapacities[j];
                int vehicleCount = 0;
                while (remainingQuantity > 0 && availableVolume >= volumes[i] && availableCapacity >= weights[i]) {
                    remainingQuantity--;
                    availableVolume -= volumes[i];
                    availableCapacity -= weights[i];
                    vehicleCount++;
                }
                // 更新车型数量和总花费
                vehicleCounts[j] += vehicleCount;
                totalCost += vehicleCount * vehiclePrices[j];
            }
        }

        // 输出结果
        System.out.println("使用的车型及其数量：");
        for (int i = 0; i < vehicleCounts.length; i++) {
            if (vehicleCounts[i] > 0) {
                System.out.println("车型 " + (char)('A' + i) + ": " + vehicleCounts[i] + "辆");
            }
        }
        System.out.println("最小花费: " + totalCost + "元");
    }

    public static List<Vehicle> findCheapestTransportation(List<Cargo> cargoList, List<Vehicle> vehicleList) {
        List<Vehicle> result = new ArrayList<>();

        for (Cargo cargo : cargoList) {
            Vehicle selectedVehicle = null;
            double minCostPerUnit = Double.MAX_VALUE;

            for (Vehicle vehicle : vehicleList) {
                if (vehicle.maxVolume >= cargo.volume && vehicle.maxWeight >= cargo.weight) {
                    double costPerUnit = (double) vehicle.price / vehicleCount(vehicle, cargoList);
                    if (costPerUnit < minCostPerUnit) {
                        minCostPerUnit = costPerUnit;
                        selectedVehicle = vehicle;
                    }
                }
            }

            if (selectedVehicle != null) {
                result.add(selectedVehicle);
            }
        }

        return result;
    }

    public static int vehicleCount(Vehicle vehicle, List<Cargo> cargoList) {
        int count = 0;

        for (Cargo cargo : cargoList) {
            if (cargo.volume <= vehicle.maxVolume && cargo.weight <= vehicle.maxWeight) {
                int availableSpace = vehicle.maxVolume / cargo.volume;
                int availableWeight = vehicle.maxWeight / cargo.weight;
                int maxCount = Math.min(availableSpace, availableWeight);
                count += Math.min(maxCount, cargo.quantity);
            }
        }

        return count;
    }

    public static int calculateTotalCost(List<Vehicle> vehicleList) {
        int totalCost = 0;

        for (Vehicle vehicle : vehicleList) {
            totalCost += vehicle.price;
        }

        return totalCost;
    }
}
