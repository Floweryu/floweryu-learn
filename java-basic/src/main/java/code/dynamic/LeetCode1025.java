package code.dynamic;

/**
 * @author Floweryu
 * @date 2022/12/5 22:05
 */
public class LeetCode1025 {
    /**
     * 如果n是奇数：a拿n, 则奇数的因数只能是奇数, n-x一定是偶数, 所以b的一定是偶数, 然后b再只取1, 后面n-x一定是奇数, 这样a获得的一直都是奇数, b一直偶数, 直到b获取到2后选1, a必输即返回false
     * 如果n是偶数: a拿n, 取1的话, 到b一定是奇数, 后面a一定会获取到偶数, a一定赢
     * @param n
     * @return
     */
    public static boolean divisorGame(int n) {
        return n % 2 == 0;
        
    }
    
    public static void main(String[] args) {
        
    }
}
