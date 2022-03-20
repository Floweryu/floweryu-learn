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
    
    @Test
    public void codeTest() {
        int[] nums = new int[]{5,19,8,1};
        int res1 = halveArray(nums);
        System.out.println(res1);
    }
}
