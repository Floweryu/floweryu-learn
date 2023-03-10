package code.offer;

/**
 * 剑指 Offer 05. 替换空格
 * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/?favorite=xb9nqhhg
 * @author zhangjunfeng
 * @date 2023/3/8 19:19
 */
public class Common5 {
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }
    
    public String replaceSpace2(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == ' ') {
                sb.append("%20");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
