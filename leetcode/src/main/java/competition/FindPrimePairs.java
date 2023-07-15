package competition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Floweryu
 * @date 2023/7/2 10:36
 */
public class FindPrimePairs {
    public List<List<Integer>> findPrimePairs(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 2) {
            return res;
        }
        if (n % 2 != 0) {
            if (isPrime(n - 2)) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(2);
                tmp.add(n - 2);
                res.add(tmp);
            }
        } else {
            for (int i = 2; i <= n / 2; i++) {
                if (isPrime(i) && isPrime(n - i)) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.add(n - i);
                    res.add(tmp);
                }
            }
        }


        return res;
    }

    private boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        for (int i = 2; i * i <= num; ++i) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
