package competition;

import java.util.*;

/**
 * @author Floweryu
 * @date 2023/7/9 10:56
 */
public class Solution {
    public int maximumJumps(int[] nums, int target) {
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(nums[i] - nums[i - 1]) <= target) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[nums.length - 1] == 0 ? -1 : dp[nums.length - 1];
    }

    public int maximumJumps2(int[] nums, int target) {
        int ans = 0, index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (i > index && Math.abs(nums[i] - nums[index]) <= target) {
                ans++;
                index = i;
                System.out.println(index);
            }
        }
        return index == nums.length - 1 ? ans : -1;
    }

    public int sumOfSquares(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums.length % (i + 1) == 0) {
                System.out.println(nums[i]);
                sum += nums[i] * nums[i];
            }
        }
        return sum;
    }

    public int minimumIndex(List<Integer> nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Integer num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            int val = 0, time = 0;
            for (Integer key: map.keySet()) {
                if (map.get(key) * 2 > nums.size()) {
                    val = key;
                    time = map.get(key);
                }
            }
            int left = 0;
            for (int i = 0; i < nums.size(); i++) {
                if (nums.get(i) == val) {
                    left++;
                    time--;
                }
                if (left * 2 > i + 1 && time * 2 > nums.size() - i - 1) {
                    return i;
                }
            }
            return -1;
    }

    public boolean isGood(int[] nums) {
        int maxn = -1;
        for (int num : nums) {
            maxn = Math.max(num, maxn);
        }
        if (nums.length != maxn + 1) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - 1 && nums[i] != i + 1) {
                return false;
            }
            if (nums[nums.length - 1] != nums[nums.length - 2]) {
                return false;
            }
        }
        return true;
    }


    public String sortVowels(String s) {
        Set<Character> charSet = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        char[] chars = s.toCharArray();
        List<Character> list = new ArrayList<>();
        PriorityQueue<Character> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        for (char ch : chars) {
            if (charSet.contains(ch)) {
                queue.add(ch);
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if (charSet.contains(chars[i])) {
                chars[i] = queue.poll();
            }
        }
        return String.copyValueOf(chars);
    }

    public long maxScore(int[] nums, int x) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (! check(nums, i, i - 1)) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }

        }
        return 1;
    }

    private boolean check(int[] nums, int i, int j) {
        if (nums[i] % 2 == 0 && nums[j] % 2 == 0) {
            return true;
        } else return nums[i] % 2 != 0 && nums[j] % 2 != 0;
    }

    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> ans = new ArrayList<>();
        for (String s: words) {
            String[] split;
            if (separator == '.') {
                split = s.split("\\.");
            } else if (separator == '|') {
                split = s.split("\\|");
            } else if (separator == '$') {
                split = s.split("\\$");
            }
            else {
                split = s.split(String.valueOf(separator));
            }

            ans.addAll(Arrays.stream(split).filter(v -> !v.isEmpty()).toList());
        }
        return ans;
    }

    public long maxArrayValue(int[] nums) {
        while (true) {
            boolean merged = false;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] <= nums[i + 1]) {
                    nums[i + 1] = nums[i] + nums[i + 1];
                    // Move elements to the left to fill the gap after merging
                    for (int j = i; j < nums.length - 1; j++) {
                        nums[j] = nums[j + 1];
                    }
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                break;
            }
        }

        int maxValue = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > maxValue) {
                maxValue = num;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> list = List.of(1, 2, 2, 2);
        solution.splitWordsBySeparator(List.of("$easy$","$problem$"), '$');
    }
}
