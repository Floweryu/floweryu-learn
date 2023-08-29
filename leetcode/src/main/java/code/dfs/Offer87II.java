package code.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * LCR 087. 复原 IP 地址
 * https://leetcode.cn/problems/0on3uN/
 * @author: zhangjunfeng
 * @createTime: 2023/08/28
 */
public class Offer87II {

    private List<String> ans = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backTrack(s, 0, new ArrayList<>());
        return ans;
    }


    public void backTrack(String s, int index, List<String> res) {
        // 结束条件
        if (res.size() == 4) {
            if (index == s.length()) {
                // 如果找到4段并且已经遍历完字符串，说明找到一个Ip
                ans.add(String.join(".", res));
            }
            return;
        }

        for (int i = index; i < s.length(); i++) {
            String addr = s.substring(index, i + 1);
            // 下面条件不满足
            // 1. 不能以0开头
            // 2. 不能大于255
            // 3. 长度不能大于3
            if ((addr.length() > 1 && addr.startsWith("0")) || Integer.parseInt(addr) > 255 || addr.length() >= 4) {
                return;
            }
            res.add(addr);
            // 当前位置选择后，继续进行下面选择
            backTrack(s,  i + 1, res);
            // 回溯
            res.remove(res.size() - 1);
        }
    }

    public static void main(String[] args) {
        Offer87II offer87II = new Offer87II();
        offer87II.restoreIpAddresses("25525511135");
    }
}
