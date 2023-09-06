package code.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * LCR 111. 除法求值
 * https://leetcode.cn/problems/vlzXQL/description/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @createTime: 2023/09/05
 */
public class LCS111 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, Integer> map = new HashMap<>();
        // 标识节点
        int nodeIndex = 0;
        // 将字母转化为数字标记
        for (List<String> equation : equations) {
            if (!map.containsKey(equation.get(0))) {
                map.put(equation.get(0), nodeIndex++);
            }
            if (!map.containsKey(equation.get(1))) {
                map.put(equation.get(1), nodeIndex++);
            }
        }

        // 用邻接矩阵存储有向图, 设置不可达两个节点距离为-1
        double[][] graph = new double[nodeIndex][nodeIndex];
        for (int i = 0; i < nodeIndex; i++) {
            Arrays.fill(graph[i], -1);
        }

        for (int i = 0; i < equations.size(); i++) {
            int na = map.get(equations.get(i).get(0));
            int nb = map.get(equations.get(i).get(1));
            graph[na][nb] = values[i];
            graph[nb][na] = 1.0 / values[i];
        }

        // 弗洛伊德算法先求出两节点最近距离
        for (int k = 0; k < nodeIndex; k++) {
            for (int i = 0; i < nodeIndex; i++) {
                for (int j = 0; j < nodeIndex; j++) {
                    if (graph[i][k] > 0 && graph[k][j] > 0) {
                        // [a, b]=2, [b, c]=3, 则[a, c]=6
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }

        double[] ans = new double[queries.size()];
        // 默认填充为-1， 因为可能遇到无法确定的除法参数
        Arrays.fill(ans, -1.0);
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            // 判断变量是否有效，有效才进行计算
            if (map.containsKey(query.get(0)) && map.containsKey(query.get(1))) {
                int a = map.get(query.get(0));
                int b = map.get(query.get(1));
                // 因为在初始化graph时，已经设置了默认为-1
                ans[i] = graph[a][b];
            }
        }

        return ans;
    }
}
