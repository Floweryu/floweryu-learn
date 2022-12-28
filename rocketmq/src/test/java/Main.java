/**
 * @author Floweryu
 * @date 2022/9/25 16:48
 */
public class Main {
    
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
        
            }
        }));
    }
}
