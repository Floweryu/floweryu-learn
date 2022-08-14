package code;

import org.junit.Test;

import java.util.*;

/**
 * @author Floweryu
 * @date 2022/3/13 22:05
 */
public class CodeTest {

    /**
     * 剑指Offer.48
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        Set<Character> set = new HashSet<>();
        for (int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            while (set.contains(c)) {
                set.remove(s.charAt(l++));
            }
            set.add(c);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int i = 0, j = 0, k = 0;
        
        for (int index = 1; index < n; index++) {
            // 在生成的三个数组中选最小的丑数
            int mini = Math.min(res[i] * 2, Math.min(res[j] * 3, res[k] * 5));
            
            // 三个数组都有可能存在最小的元素, 只要是最小的, 就需要移动指针
            if (res[i] * 2 == mini) i++;
            if (res[j] * 3 == mini) j++;
            if (res[k] * 5 == mini) k++;
            
            res[index] = mini;
        }
        
        return res[n - 1];
    }


    public boolean divideArray(int[] nums) {

        Arrays.sort(nums);

        for (int i = 0; i <= nums.length - 2; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public int halveArray(int[] nums) {
        PriorityQueue<Double> queue = new PriorityQueue<>(Comparator.reverseOrder());
        double sum = 0;
        for (int num : nums) {
            sum += num;
            queue.add((double)num);
        }

        double desc = 0;
        int count = 0;
        while (desc < sum / 2) {
            double poll = queue.poll();
            desc += poll / 2;
            queue.add(poll / 2);
            count++;
        }
        return count;
    }

      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }

    public String tree2str(TreeNode root) {
        StringBuilder res = new StringBuilder();
        preOrder(root, res);
        return res.toString();
    }

    private void preOrder(TreeNode root, StringBuilder res) {
        if (root == null) {
            return;
        }

        int cur = root.val;
        if (root.left != null) {
            res.append(cur).append("(");
        }
        preOrder(root.left, res);
        preOrder(root.right, res);

        if (root.right != null) {
            res.append(")");
        }

    }

    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        // mini记录prices的最小值
        int maxn = 0, mini = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 遇到比mini小的, 替换mini值
            if (prices[i] <= mini) {
                mini = prices[i];
            } else { // 遇到比mini大的, 计算差值
                maxn = Math.max(maxn, prices[i] - mini);
            }
        }
        return maxn;
    }

    /**
     * No.2044
     * @url https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets/
     */
    public int countMaxOrSubsets(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max |= num;
        }
        return dfs(0, nums, 0, max);
    }

    private int dfs(int curIndex, int[] nums, int curValue, int max) {
        if (curIndex == nums.length - 1) {
            return curValue == max ? 1 : 0;
        }
        //
        return dfs(curIndex + 1, nums, curValue | nums[curIndex], max) + dfs(curIndex + 1, nums, curValue, max);
    }



    /**
     * LeetCode.661
     * @param img
     * @return
     */
    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = calc(i, j, m, n, img);
            }
        }
        return res;
    }
    
    private int calc(int i, int j, int m, int n, int[][] img) {
        int sum = 0, count = 0;
        for (int a = i - 1; a <= i + 1; a++) {
            for (int b = j - 1; b <= j + 1; b++) {
                if (a >= 0 && a < m && b >= 0 && b < n) {
                    sum += img[a][b];
                    count++;
                }
            }
        }
        return sum / count;
    }

    /**
     * 118.杨辉三角
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int a = 0; a < i + 1; a++) {
                if (a > 0 && a < i) {
                    tmp.add(res.get(i - 1).get(a - 1) + res.get(i - 1).get(a));
                } else {
                    tmp.add(1);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    /**
     * Leetcode.693
     * @param n
     * @return
     */
    public boolean hasAlternatingBits(int n) {
        String str = Integer.toBinaryString(n);
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * T T F T T F T T
     * @param answerKey
     * @param k
     * @return
     */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxConsecutiveChar(answerKey, k, 'T'), maxConsecutiveChar(answerKey, k, 'F'));
    }

    public int maxConsecutiveChar(String answerKey, int k, char ch) {
        int n = answerKey.length();
        int ans = 0;
        for (int left = 0, right = 0, sum = 0; right < n; right++) {
            sum += answerKey.charAt(right) != ch ? 1 : 0;
            while (sum > k) {
                sum -= answerKey.charAt(left++) != ch ? 1 : 0;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
    
    public int edgeScore(int[] edges) {
        Map<Integer, Long> map = new TreeMap<>();
        for (int i = 0; i < edges.length; i++) {
            if (map.containsKey(edges[i])) {
                map.put(edges[i], map.get(edges[i]) + i);
            } else {
                map.put(edges[i], (long) i);
            }
        }
        System.out.println(map);
        Integer maxKey = -1;
        for (Integer key : map.keySet()) {
            if (map.get(key)  > map.getOrDefault(maxKey, 0L)) {
                maxKey = key;
            }
        }
        return maxKey;
    }
    
    @Test
    public void codeTest() {
        int[] edges = new int[]{2,1,0,0,1,2};
        int i = edgeScore(edges);
        System.out.println(i);
    }
    
}
