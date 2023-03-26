package code.binary;

/**
 * 165. 比较版本号
 * @author zhangjunfeng
 * @date 2023/3/21 20:34
 */
public class Search165 {
    public int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < split1.length || j < split2.length) {
            // 这里赋默认值为0：当某一version分割后数组长度短时，需要继续对另一个version进行判断，就和0比较
            int v1 = 0, v2 = 0;
            if (i < split1.length) {
                v1 = Integer.parseInt(split1[i]);
            }
            if (j < split2.length) {
                v2 = Integer.parseInt(split2[j]);
            }
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            } else {
                i++;
                j++;
            }
        }
        return 0;
    }
    
    
    public static void main(String[] args) {
        int i = Integer.parseInt("0001");
        System.out.println(i);
    }
}
