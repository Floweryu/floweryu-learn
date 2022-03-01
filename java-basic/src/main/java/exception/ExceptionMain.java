package exception;

/**
 * @author zhangjunfeng
 * @date 2022/2/28 19:35
 */
public class ExceptionMain {
    
    public static void exceptionTest() {
        try {
            if (8 / 2 != 0) {
                throw new RuntimeException("1 != 0");
            }
        } catch (Exception e) {
            System.out.println("异常： " + e.getMessage());
        }
    }
}
